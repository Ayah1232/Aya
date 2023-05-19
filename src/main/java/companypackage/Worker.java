package companypackage;

public class Worker {
	
	private Worker()
	{
		
	}
	

	private static boolean isAvailable;
	private static String name;

	public static void setName(String name1) {

		name = name1;

	}

	public static void setAvailable(boolean b) {

		isAvailable = b;

	}

	public static boolean getAvailability() {
		return isAvailable;
	}

	public static String getName() {

		return name;
	}

}