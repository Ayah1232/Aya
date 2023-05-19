package companypackage;

import java.util.Scanner;
import java.util.logging.Logger;

public class Testall {

	private static String name;
	private static String phone;
	private static String address;
	private static String city;
	private static String email;
	private static String password;
	private static String id;
	static int press;
	private static int customerProductNumber;
	private static String color;
	private static double height;
	private static double width;
	private static String dimention;
	private static String material;
	private static String payType;
	private static int q;
	private static String picture;
	private static String category;
	private static boolean rst;
	private static String adminName = "Ali";
	private static String adminPassword = "123";

	private static final String FILENAME = "Customers.txt";
	private static final Logger LOGGER = Logger.getLogger(Testall.class.getName());
	static final String YESORNO = "press 1 for yes , 2 for No";
	private static final String CATEGORY = "category";
	private static final String MATERIAL = "material";
	private static final String COLOR = "color";
	private static final String DIMENTION = "dimention";
	private static final String PAYMENTYPE = "payment type";
	private static final String QUANTITY = "quantity";
	private static final String PICTURE = "picture";

	public static void main(String[] args) {
		Scanner scanner3 = new Scanner(System.in);
		Scanner scanner2 = new Scanner(System.in);
		Scanner scanner = new Scanner(System.in);

		LOGGER.info("Press 1 Admin form");
		LOGGER.info("Press 2 User form");

		press = scanner2.nextInt();

		if (press == 2)// User
		{

			LOGGER.info("Welcome to our application!");
			LOGGER.info("Press 1 to sign Up");
			LOGGER.info("Press 2 to logIn");
			press = scanner2.nextInt();

			if (press == 1) {

				signUp(scanner, scanner2);

			}

			else if (press == 2) {

				logIn(scanner, scanner2, scanner3);

			}

		}

		else if (press == 1) {

			adminlogIn(scanner);

		}

	}

	private static void adminlogIn(Scanner scanner) {

		LOGGER.info("Enter UserName");
		String user = scanner.nextLine();

		LOGGER.info("Enter Password");
		String password = scanner.nextLine();

		if (isAdmin(user, password)) {
			adminMenu(scanner);
		} else {
			LOGGER.warning("Wrong User name or Password, try again...");
		}

	}

	private static void logIn(Scanner scanner, Scanner scanner2, Scanner scanner3) {

		LOGGER.info("Enter Your email");
		String user = scanner.nextLine();

		LOGGER.info("Enter Password");
		String password = scanner.nextLine();

		boolean isValidCredentials = Customer.checkCustomerCredentials(FILENAME, user, password);

		if (isValidCredentials) {
			String name = Customer.findNameByEmail(user);
			LOGGER.info("Welcome, " + name);

			int press;

			do {
				LOGGER.info("Click 1 to order clean service");
				LOGGER.info("Click 2 to track your orders");
				LOGGER.info("Click 3 if you want to delete any order you have been ordered");
				LOGGER.info("Click 4 if you want to update any order you have been ordered");
				LOGGER.info("Press 0 to exit");

				press = scanner2.nextInt();
				scanner2.nextLine();

				switch (press) {
				case 1:
					orderCleanService(scanner, scanner2, scanner3, user);
					break;
				case 2:
					trackOrders(user);
					break;
				case 3:
					deleteOrder( scanner2, user);
					break;
				case 4:
					updateOrder(scanner, scanner2, scanner3);
					break;
				case 0:
					LOGGER.info("Goodbye!");
					return;
				default:
					LOGGER.warning("Invalid option. Please try again.");
				}
			} while (press != 0);
		} else {
			LOGGER.warning("Wrong username or password, try again!");
		}

	}

	private static void deleteOrder(Scanner scanner2, String user) {
	    boolean exit = false;

	    while (!exit) {
	        LOGGER.info("Which order do you want to delete? Enter the order number or choose an option:");
	        LOGGER.info("Press 1 to go to track");
	        LOGGER.info("Press 2 to enter order number");
	        LOGGER.info("Press 3 to return");

	        int press = scanner2.nextInt();
	        scanner2.nextLine();

	        switch (press) {
	            case 1:
	                String id = Customer.findIdByEmail(user);
	                Product.trackStatus(id);
	                break;
	            case 2:
	                LOGGER.info("Enter the order number you want to delete");
	                int number = scanner2.nextInt();
	                scanner2.nextLine();
	                Product.deleteProduct(number);
	                break;
	            case 3:
	                exit = true; // Set exit to true to break out of the loop
	                break;
	            default:
	                LOGGER.warning(String.format("Invalid input %d" , press));
	                break;
	        }
	    }
	}


	private static void trackOrders(String user) {

		String id = Customer.findIdByEmail(user);
		Product.trackStatus(id);

	}

	private static void orderCleanService(Scanner scanner, Scanner scanner2, Scanner scanner3, String user) {

		LOGGER.info("What do you want to clean?");
		LOGGER.info("Press 1 for carpet, 2 for cover");
		int press = scanner2.nextInt();
		scanner2.nextLine();

		String category;
		if (press == 1) {
			category = "carpet";
		} else {
			category = "cover";
		}

		LOGGER.info("Material: ");
		String material = scanner.nextLine();

		LOGGER.info("Color: ");
		String color = scanner.nextLine();

		LOGGER.info("Height: ");
		double height = scanner3.nextDouble();

		LOGGER.info("Width: ");
		double width = scanner3.nextDouble();

		String dimension = height + "," + width;

		id = Customer.findIdByEmail(user);

		LOGGER.info("What method of payment will you be using?");
		LOGGER.info("press 1 for Credit card");
		LOGGER.info("press 2 for Cash");
		press = scanner2.nextInt();
		if (press == 1) {
			payType = "Credit Card";
		} else
			payType = "Cash";

		LOGGER.info("Picture: ");
		picture = scanner.nextLine();
		LOGGER.info("Quantity: ");
		q = scanner2.nextInt();

		customerProductNumber = Product.getLastOrderNumber();
		customerProductNumber++;

		Product.createProduct(customerProductNumber, id, dimension, material, color, category, payType, q, picture,
				Product.calculatePrice(category, height, width, rst, q));
		System.out.println("Are you sure you want this order");
		System.out.println("press 1 for Yes");
		System.out.println("press 2 for No");

		press = scanner2.nextInt();
		if (press == 1)
			Product.addProduct();
		else if (press == 2)
			return;

	}

	private static void updateOrder(Scanner scanner, Scanner scanner2, Scanner scanner3)

	{

		LOGGER.info("What order number do you want to update?");
		int orderNumber = scanner2.nextInt();
		scanner2.nextLine();

		String row = Product.getRowByProductNumber(orderNumber);
		if (row == null) {
			return;
		}

		String[] rowFile = row.split("\t");

		updateField(scanner2, CATEGORY, rowFile[2], scanner);
		updateField(scanner2, MATERIAL, rowFile[3], scanner);
		updateField(scanner2, COLOR, rowFile[4], scanner);
		updateField(scanner2, DIMENTION, rowFile[5], scanner3);
		updateField(scanner2, PAYMENTYPE, rowFile[6], scanner);
		updateField(scanner2, QUANTITY, rowFile[7], scanner2);
		updateField(scanner2, PICTURE, rowFile[8], scanner);

		/*Product.updateProduct(customerProductNumber, rowFile[1], category, material, color, dimention, payType, q,
				picture, rowFile[9], rowFile[10],
				Double.toString(Product.calculatePrice(category, height, width, rst, q)), 0);*/

	}

	private static void updateField(Scanner scanner2, String fieldName, String defaultValue, Scanner scanner) {
		LOGGER.info("Do you want to update " + fieldName + "?");
		LOGGER.info(YESORNO);
	    int press = scanner2.nextInt();
	    scanner2.nextLine(); // Consume the newline character

	    if (press == 1) {
	        String updatedValue = null;
	        if (fieldName.equals("dimension")) {
	        	LOGGER.info("Enter height:");
	            double height = scanner.nextDouble();
	            LOGGER.info("Enter width:");
	            double width = scanner.nextDouble();
	            scanner.nextLine(); // Consume the newline character
	            updatedValue = height + "," + width;
	        } else {
	        	LOGGER.info("Enter " + fieldName + ":");
	            updatedValue = scanner.nextLine();
	        }
	        
	        if (!updatedValue.isEmpty()) {
	            switch (fieldName) {
	                case CATEGORY:
	                    category = updatedValue;
	                    break;
	                case MATERIAL:
	                    material = updatedValue;
	                    rst = material.equalsIgnoreCase("wool");
	                    break;
	                case COLOR:
	                    color = updatedValue;
	                    break;
	                case DIMENTION:
	                    dimention = updatedValue;
	                    break;
	                case PAYMENTYPE:
	                    payType = updatedValue;
	                    break;
	                case QUANTITY:
	                    q = Integer.parseInt(updatedValue);
	                    break;
	                case PICTURE:
	                    picture = updatedValue;
	                    break;
	                    
	                default:
	                	LOGGER.warning("Invalid field name.");
	                    break;
	            }
	        }
	    } else if (press == 2) {
	        switch (fieldName) {
	            case CATEGORY:
	                category = defaultValue;
	                break;
	            case MATERIAL:
	                material = defaultValue;
	                rst = defaultValue.equalsIgnoreCase("wool");
	                break;
	            case COLOR:
	                color = defaultValue;
	                break;
	            case DIMENTION:
	                dimention = defaultValue;
	                break;
	            case PAYMENTYPE:
	                payType = defaultValue;
	                break;
	            case QUANTITY:
	                q = Integer.parseInt(defaultValue);
	                break;
	            case PICTURE:
	                picture = defaultValue;
	                break;
	            default:
	            	LOGGER.warning("Invalid field name.");
                    break;
	        }
	    }
	}


	private static void signUp(Scanner scanner, Scanner scanner2) {

		LOGGER.info("Id: ");
		id = scanner.nextLine();
		LOGGER.info("name: ");

		name = scanner.nextLine();

		LOGGER.info("Phone: ");

		phone = scanner.nextLine();

		LOGGER.info("address: ");

		address = scanner.nextLine();
		LOGGER.info("City: ");

		city = scanner.nextLine();
		LOGGER.info("Email: ");
		email = scanner.nextLine();
		LOGGER.info("Password: ");
		password = scanner.nextLine();

		LOGGER.info("Are you sure to submit?");
		LOGGER.info("Press 1 for Yes");
		LOGGER.info("Press 2 for No");
		press = scanner2.nextInt();

		if (press == 1) {
			Customer.createCustomer(id, name, phone, address, city, email, password);
			Customer.addCustomer(FILENAME);
		} else {
			return;
		}
	}

	private static void adminMenu(Scanner scanner) {
		LOGGER.info("Welcome to your page");
		LOGGER.info("Press 1 to generate customized reports about business.");
		LOGGER.info("Press 2 to Generate statistics");
		LOGGER.info("Press 3 to Distribute the orders");
		LOGGER.info("Press 4 to send invoice to customer when it's complete");

		int press = scanner.nextInt();
		scanner.nextLine(); // Consume newline character

		switch (press) {
		case 1:
			Admin.generateReport();
			break;
		case 2:
			Customer.getStatistics();
			break;
		case 3:
			Admin.distributeOrdersToWorkers();
			break;
		case 4:
			LOGGER.info("Customer email to send invoice: ");
			String email = scanner.nextLine();
			Customer.generateInvoice(email);
			break;
		default:
			LOGGER.warning(String.format("Invalid input: %s", press));
			break;
		}
	}

	private static boolean isAdmin(String username, String password) {
		return username.equals(adminName) && password.equals(adminPassword);
	}

}