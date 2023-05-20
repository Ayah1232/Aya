package companypackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Admin {

	private static String email;
	private static String password;
	private static final Logger LOGGER = Logger.getLogger(Admin.class.getName());
	private static final String PRODUCTFILE = "Products.txt";
	private static final String WORKERFILE = "Workers.txt";
	
	
	private Admin()
	{
		
	}

	public static void setEmail(String email2) {

		email = email2;

	}

	public static void setPassword(String password2) {

		password = password2;

	}

	public static String getEmail() {

		return email;
	}

	public static String getPassword() {

		return password;
	}

	public static void distributeOrdersToWorkers() {
		try {
			List<String[]> products = readDataFile(PRODUCTFILE);
			List<String[]> workers = readDataFile(WORKERFILE);
			List<String[]> availableWorkers = getAvailableWorkers(workers);
			if (availableWorkers.isEmpty()) {
				LOGGER.log(java.util.logging.Level.WARNING, "No available workers to assign orders.");
				return;
			}
			boolean orderAssigned = assignOrders(products, availableWorkers);
			if (!orderAssigned) {
				LOGGER.log(java.util.logging.Level.WARNING, "No orders available to assign to workers.");
				return;
			}
			writeDataFile(PRODUCTFILE, products);
			writeDataFile(WORKERFILE, workers);
		} catch (IOException e) {
			LOGGER.log(java.util.logging.Level.SEVERE, "An error occurred while");
		}
	}

	private static List<String[]> readDataFile(String fileName) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		List<String[]> data = new ArrayList<>();
		String line;
		while ((line = reader.readLine()) != null) {
			String[] rowData = line.split("\t");
			data.add(rowData);
		}
		reader.close();
		return data;
	}

	private static List<String[]> getAvailableWorkers(List<String[]> workers) {
		List<String[]> availableWorkers = new ArrayList<>();
		for (String[] workerData : workers) {
			if (workerData[5].equals("true")) {
				availableWorkers.add(workerData);
			}
		}
		return availableWorkers;
	}

	private static boolean assignOrders(List<String[]> products, List<String[]> availableWorkers) {
		for (String[] productData : products) {
			if (productData[9].equalsIgnoreCase("waiting")) {
				for (String[] workerData : availableWorkers) {
					if (workerData[5].equals("true")) {
						workerData[4] = productData[0];
						workerData[5] = "false";
						productData[9] = "in treatment";
						LOGGER.log(java.util.logging.Level.INFO, "Order {0} assigned to Worker {1}",
								new Object[] { productData[0], workerData[1] });
						return true;
					}
				}
			}
		}
		return false;
	}

	private static void writeDataFile(String fileName, List<String[]> data) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		String header = String.join("\t", data.get(0));
		writer.write(header + "\n");
		for (int i = 1; i < data.size(); i++) {
			String row = String.join("\t", data.get(i));
			writer.write(row + "\n");
		}
		writer.close();
	}

	public static void generateReport() {
	    try {
	        // Read Products.txt
	        BufferedReader productReader = new BufferedReader(new FileReader(PRODUCTFILE));

	        // Print column headers for the relevant columns
	        String productHeaders = String.format("%-10s%-15s%-15s%-10s%n", "Order No.", "Customer ID", "Category",
	                "Rating");

	        FileWriter writer = new FileWriter("Report.txt");
	        writer.write(productHeaders);

	        String line;
	        while ((line = productReader.readLine()) != null) {
	            String[] data = line.split("\t");

	            if (Double.parseDouble(data[12]) > 0.0) {
	                String reportLine = String.format("%-10s%-15s%-15s%-10s%n", data[0], data[1], data[2], data[12]);
	                writer.write(reportLine);
	            }

	        }
	        productReader.close();

	        BufferedReader workerReader = new BufferedReader(new FileReader(WORKERFILE));

	        String workerHeaders = String.format("ID\tName\tPhone\tEmail\tOrdersNom\tavailable%n");
	        writer.write("\n" + workerHeaders);

	        while ((line = workerReader.readLine()) != null) {
	            String workerLine = line + "\n";
	            writer.write(workerLine);
	        }
	        workerReader.close();

	        writer.close();
	        LOGGER.info("Report generated successfully!");
	    } catch (IOException e) {
	        LOGGER.warning(String.format("An error occurred: %s", e.getMessage()));
	    }
	}

}
