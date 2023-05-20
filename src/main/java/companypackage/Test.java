package companypackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Test {

	private Test()
	{
		
	}
	private static final Logger LOGGER = Logger.getLogger(Test.class.getName());

	
	public static boolean checkPhone(String phone) {

		if (phone.length() == 5) {
			for (char c : phone.toCharArray()) {
				if (!Character.isDigit(c)) {
					return false;
				}
			}
		} else {
			return false;
		}

		return true;

	}

	public static boolean checkAddress(String address) {

		return address.length() > 5;

	}

	public static boolean checkEmail(String email) {

		return email.length() > 10 && email.endsWith("@gmail.com");
	}

	public static boolean checkID(String id) {
		if (id.length() == 3) {
			for (char c : id.toCharArray()) {
				if (!Character.isDigit(c)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public static boolean checkPassword(String password) {
		if (password.length() >= 30) {
			return false;
		}

		boolean hasDigit = false;
		boolean hasLowerCase = false;
		boolean hasUpperCase = false;

		for (char c : password.toCharArray()) {
			if (Character.isDigit(c)) {
				hasDigit = true;
			} else if (Character.isLowerCase(c)) {
				hasLowerCase = true;
			} else if (Character.isUpperCase(c)) {
				hasUpperCase = true;
			}
		}

		return hasDigit && hasLowerCase && hasUpperCase;
	}

	public static boolean checkLog(String key, List<String> keyArray, int i) {
		return keyArray.get(i).equals(key);
	}

	public static int checkIndex(String keyName, List<String> email) {
		return email.indexOf(keyName);
	}

	public static boolean checkIsRequiredSpecialTreatment(boolean isRequiredSpecialTreatment) {
		if (isRequiredSpecialTreatment) {
			LOGGER.info("It's required special treatment!");
			return false;
		} else {
			return true;
		}
	}
	public static void sendEmail(String recipient, String subject, String body) throws MessagingException {
	    String host = "smtp.gmail.com";
	    String port = "465";
	    String username = null;
	    String password = null;

	    try (BufferedReader reader = new BufferedReader(new FileReader("Admin.txt"))) {
	        String discardLine = reader.readLine();
	        String discardLine2 = reader.readLine();

	        String usernameLine = reader.readLine();
	        username = usernameLine.trim();
	        String passwordLine = reader.readLine();
	        password = passwordLine.trim();

	        
	    } catch (IOException e) {
	        LOGGER.warning(String.format("An error occurred while reading Admin.txt: %s", e.getMessage()));
	    }

	    Properties props = new Properties();
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.port", port);
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.ssl.enable", "true");

	    Session session = createSession(props, username, password);

	    MimeMessage message = createMimeMessage(session, username, recipient, subject, body);

	    sendMessage(message);
	}

	private static Session createSession(Properties props, final String username, final String password) {
	    return Session.getInstance(props, new javax.mail.Authenticator() {
	    	protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(username, password);
	        }
	    });
	}


	private static MimeMessage createMimeMessage(Session session, String from, String to, String subject, String body)
			throws MessagingException {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject(subject);
		message.setText(body);
		return message;
	}

	private static void sendMessage(MimeMessage message) throws MessagingException {
		LOGGER.info("Sending...");
		Transport.send(message);
		LOGGER.info("Sent message successfully.");
	}

	public static boolean checkColor(String color) {
		String[] validColors = { "red", "green", "blue", "yellow", "black", "white", "silver" };
		return containsIgnoreCase(validColors, color);
	}

	private static boolean containsIgnoreCase(String[] array, String value) {
		for (String item : array) {
			if (item.equalsIgnoreCase(value)) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkMaterial(String material, String category) {
		String[] validCarpetMaterials = { "Wool", "Cotton", "Olefin", "Polyester", "Nylon", "Jute", "Silk" };
		String[] validCoverMaterials = { "Cotton", "Polyester", "Leather", "Velvet", "Linen", "Silk", "Acrylic" };

		if (category.equalsIgnoreCase("carpet")) {
			return containsIgnoreCase(validCarpetMaterials, material);
		} else if (category.equalsIgnoreCase("cover")) {
			return containsIgnoreCase(validCoverMaterials, material);
		}

		return false;
	}

	public static boolean checkQuantity(int quantity) {
		return quantity > 0 && quantity <= 100;
	}

	public static boolean isRequiredSpecialTreatment(String category, String material) {
		return (category.equalsIgnoreCase("Carpet") || category.equalsIgnoreCase("Cover"))
				&& (material.equalsIgnoreCase("Silk") || material.equalsIgnoreCase("Wool"));
	}

	public static boolean checkPicture(String pic) {
		if (pic.isEmpty()) {
			return false;
		}

		String extension = pic.substring(pic.lastIndexOf(".") + 1).toLowerCase();
		String[] validExtensions = { "jpg", "jpeg", "png", "gif", "bmp" };

		return Arrays.asList(validExtensions).contains(extension);
	}

	public static boolean checkDimension(String dimension) {
		String[] dimensions = dimension.split(",");

		if (dimensions.length == 2) {
			double length = Double.parseDouble(dimensions[0]);
			double width = Double.parseDouble(dimensions[1]);

			return length > 0 && width > 0;
		}

		return false;
	}

	public static boolean checkCategory(String category) {
		if (category == null || category.isEmpty()) {
			return false;
		}

		for (char c : category.toCharArray()) {
			if (!Character.isLetter(c) && c != ' ') {
				return false;
			}
		}

		return true;
	}

	public static boolean checkPrice(double price) {
		return price > 20.0;
	}

}
