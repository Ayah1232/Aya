package companypackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

    public class Product {
	private static int number;
	private static int rate;
	private static String cId;
	private static String category;
	private static String picture;
	private static boolean isrequiredSpecialTreatment;
	private static int quantity;
	private static double price;
	private static String status;
	private static String color;
	private static String payementType;
	private static String dimensions;
	private static String material;
	private static final String PRODUCT_FILENAME = "Products.txt";
	private static final String ERROR = "Error: ";
	private static final Logger LOGGER = Logger.getLogger(Product.class.getName());

	private Product()
	{
		
		
	}
	
	
	public static void setCategory(String cat) {
		category = cat;

	}

	public static void setPicture(String pic) {
		picture = pic;

	}

	public static void setIsrequiredSpecialTreatment(boolean t) {

		isrequiredSpecialTreatment = t;
	}

	public static void setQuantity(int q) {

		quantity = q;

	}

	public static void setPrice(int price2) {

		price = price2;

	}

	public static String getCategory() {

		return category;
	}

	public static int getQuantity() {

		return quantity;
	}

	public static int getNumber() {

		return number;
	}

	public static  String getStatus() {

		return status;
	}

	public static  void createProduct(int nom, String cId, String dimention, String material, String color,
			String category, String payType, int quantity, String pic, double price) {
		
	    
		if (!Test.checkColor(color)) 
		{
			LOGGER.warning("Wrong Color");
			return;
		}

		if (!Test.checkQuantity(quantity)) {
			LOGGER.warning("Wrong quantity");
			return;
		}

		setIsrequiredSpecialTreatment(Test.isRequiredSpecialTreatment(category, material));

		if (!Test.checkPicture(pic)) {
			LOGGER.warning("Wrong Picture");
			return;
		}

		if (!Test.checkDimension(dimention)) {
			LOGGER.warning("Wrong dimension");
			return;
		}

		if (!Test.checkPrice(price)) {
			LOGGER.warning("Wrong price");
			return;
		}
		 
		
		//Product product = new Product();

		setCategory(category);
		setNumber(nom);
		setCid(cId);
		setDimention(dimention);
		setMaterial(material);
		setColor(color);
		setPayment(payType);
		setQuantity(quantity);
		setPicture(pic);
		setStatus("waiting");
		setRate(0);

	}

	private static  void setRate(int i) {

		rate = i;

	}

	public static  void addProduct() {

		try {

			// Append new records to the file
			FileWriter writer = new FileWriter(PRODUCT_FILENAME, true);
			if (getCategory() != null && getCID() != null && getDimention() != null
					&& getMaterial() != null && getColor() != null && getPayment() != null
					&& getQuantity() != 0 && getpicture() != null && getStatus() != null
					&& getPrice() != 0) {
				writer.write(getNumber() + "\t" + getCID() + "\t" + getCategory() + "\t"
						+ getMaterial() + "\t" + getColor() + "\t" + getDimention() + "\t"
						+ getPayment() + "\t" + getQuantity() + "\t" + getpicture() + "\t"
						+ getStatus() + "\t" + getIRS() + "\t" +  getPrice() + "\t"
						+ getRate() + "\n");
				writer.close();
				LOGGER.log(java.util.logging.Level.INFO,
						"Your order now added, you can track your order and its status from your account.");
			} else {
				LOGGER.log(java.util.logging.Level.SEVERE,
						"An error occurred: one or more required fields are missing.");
			}
		} catch (IOException e) {
			LOGGER.log(java.util.logging.Level.SEVERE, "An error occurred while writing to file.", e);
		}

	}

	private static int getRate() {

		return rate;
	}

	private static double getPrice() {
		return price;
	}

	private static  boolean getIRS() {

		return isrequiredSpecialTreatment;
	}

	private static  String getpicture() {

		return picture;
	}

	private static  String getPayment() {

		return payementType;
	}

	private static  String getColor() {

		return color;
	}

	private static  String getMaterial() {

		return material;
	}

	private static  String getDimention() {

		return dimensions;
	}

	private static  String getCID() {

		return cId;
	}

	private static  void setStatus(String string) {

		status = string;

	}

	private static  void setPayment(String payType) {

		payementType = payType;

	}

	private static  void setColor(String color2) {

		color = color2;

	}

	private static  void setMaterial(String material2) {

		material = material2;

	}

	private static  void setDimention(String dimention) {

		dimensions = dimention;

	}

	private static  void setCid(String cId2) {

		cId = cId2;

	}

	private static  void setNumber(int nom) {

		number = nom;

	}

	public static  int getLastOrderNumber() {
		int lastOrderNumber = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(PRODUCT_FILENAME));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] fields = line.split("\t");
				int orderNumber = Integer.parseInt(fields[0]);
				if (orderNumber > lastOrderNumber) {
					lastOrderNumber = orderNumber;
				}
			}
			reader.close();
		} catch (IOException e) {
			LOGGER.log(java.util.logging.Level.WARNING, "An error occurred while reading the products file.", e);
		} catch (NumberFormatException e) {
			LOGGER.log(java.util.logging.Level.WARNING, "An error occurred while parsing the order number.", e);
		}
		return lastOrderNumber;
	}

	public static void trackStatus(String customerId) {
		boolean b = false;
		Scanner s = new Scanner(System.in);
		try (BufferedReader reader = new BufferedReader(new FileReader(PRODUCT_FILENAME))) {
			StringBuilder sb = new StringBuilder();
			String header = reader.readLine(); 
			sb.append(header).append("\n");
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split("\t"); 
				if (data[1].equals(customerId)) { 
					LOGGER.info("order with number " + data[0] + "  category " + data[2] + " Material " + data[3]
							+ " Color " + data[4] + " dimention " + data[5] + " quantity " + data[7] + " picture "
							+ data[8] + " is now in " + data[9] + " status" + "\n");
					b = true;
					if (data[9].equalsIgnoreCase("complete")) {
						LOGGER.info("How do you rate our cleaning?");
						LOGGER.info("1 , 2, ,3 ,4 or 5");
						int i = s.nextInt();
						data[12] = Integer.toString(i);
						String updatedLine = String.join("\t", data); 
																		
						sb.append(updatedLine).append("\n"); 
					}
				} else {
					sb.append(line).append("\n"); 
				}
			}
			if (!b) {
				LOGGER.warning("You don't have any orders to track");
			} else {
				
				FileWriter writer = new FileWriter(PRODUCT_FILENAME);
				writer.write(sb.toString());
				writer.close();
				LOGGER.info("Ratings updated successfully.");
			}
		} catch (IOException e) {
			LOGGER.log(java.util.logging.Level.SEVERE, String.format("Error updating ratings: %s", e.getMessage()), e);
		}
	}

	public static void deleteProduct(int idToDelete) {

		try {
			File inputFile = new File(PRODUCT_FILENAME);
			final String PRODUCT_NUMBER_PREFIX = "product with number ";

			File tempFile = new File("temp.txt");

			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

			String headerLine = reader.readLine();

			writer.write(headerLine + System.lineSeparator());

			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split("\t");
				int id = Integer.parseInt(parts[0]);
				String status = parts[9];
				if (id == idToDelete && status.equals("waiting")) {
					LOGGER.log(java.util.logging.Level.SEVERE,
						     String.format("%s%s and status waiting deleted!", PRODUCT_NUMBER_PREFIX, idToDelete));

				} else if (id != idToDelete) {
					writer.write(line + System.lineSeparator());
				} else {
					LOGGER.log(java.util.logging.Level.WARNING,
						   String.format("%s%s cannot be deleted due to invalid status", PRODUCT_NUMBER_PREFIX, idToDelete));

				}

			}

			reader.close();
			writer.close();

			// Delete the input file and rename the temporary file to the input file name
			if (!inputFile.delete()) {
				LOGGER.log(java.util.logging.Level.WARNING,
					    String.format("Could not delete input file: %s", inputFile.getName()));
				return;
			}
			if (!tempFile.renameTo(inputFile)) {
				LOGGER.log(java.util.logging.Level.WARNING,
					    String.format("Could not rename temp file: %s", tempFile.getName()));
			}

		} catch (IOException e) {
			LOGGER.log(java.util.logging.Level.SEVERE,
				     String.format("%s%s", ERROR, e.getMessage()),
				    e);
		}
	}

	public static void updateProduct(int orderNumToUpdate, String cusId, String category, String material, String color,
            String dimention, String payType, int quantity, String picture, String status,
            String isReq, String price, int rate) {

try {
File inputFile = new File(PRODUCT_FILENAME);
File tempFile = new File("temp.txt");

try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

String headerLine = reader.readLine();

writer.write(String.format("%s%s%n", headerLine, System.lineSeparator()));

String line;
while ((line = reader.readLine()) != null) {
String[] fields = line.split("\t");
int id = Integer.parseInt(fields[0]);
if (id != orderNumToUpdate) {
writer.write(String.format("%s%s%n", line, System.lineSeparator()));
} else {
String updatedLine = String.format("%d\t%s\t%s\t%s\t%s\t%s\t%s\t%d\t%s\t%s\t%s\t%s\t%d",
       orderNumToUpdate, cusId, category, material, color, dimention, payType, quantity,
       picture, status, isReq, price, rate);
writer.write(String.format("%s%s%n", updatedLine, System.lineSeparator()));
LOGGER.log(java.util.logging.Level.SEVERE, String.format("product with number %d updated!", orderNumToUpdate));
}
}
}

if (!inputFile.delete()) {
LOGGER.log(java.util.logging.Level.WARNING, String.format("Could not delete input file: %s", inputFile.getName()));
return;
}
if (!tempFile.renameTo(inputFile)) {
LOGGER.log(java.util.logging.Level.WARNING, String.format("Could not rename temp file: %s", tempFile.getName()));
}

} catch (IOException e) {
LOGGER.log(java.util.logging.Level.SEVERE, String.format("%s%s", ERROR, e.getMessage()), e);
}
}


	public static  String getRowByProductNumber(int productNumber) {
		String row = null;
		try {
			File inputFile = new File(PRODUCT_FILENAME);
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));

			reader.readLine();

			String line;
			while ((line = reader.readLine()) != null) {
				int id = Integer.parseInt(line.split("\t")[0]);
				if (id == productNumber) {
					row = line;
					break;
				}
			}

			reader.close();

		} catch (IOException e) {
			LOGGER.warning(String.format( "%s %s", ERROR, e.getMessage()));
		}

		if (row == null) {
			LOGGER.warning(String.format("No order found with number %d", productNumber));
			return null;

		}

		return row;
	}

	public static  double calculatePrice(String category, double height, double width, boolean needsSpecialTreatment,
			int q) {
		double basePrice = 0.0;
		if (category.equalsIgnoreCase("carpet")) {
			basePrice = 40.0;
		} else if (category.equalsIgnoreCase("cover")) {
			basePrice = 45.0;
		} else {

			return -1.0;
		}

		double size = height * width;
		double price = basePrice * size * q;
		if (needsSpecialTreatment) {
			price += 20.0;
		}
		return price;
	}

}