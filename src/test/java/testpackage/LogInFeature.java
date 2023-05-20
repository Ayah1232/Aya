package testpackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import companypackage.Customer;
import companypackage.Test;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LogInFeature {

	public  String keyName, valuePass ;
    boolean flag =true;
    int i ;
    Customer c;
    public ArrayList<String> name , phone, address , email , id , password ,state;


	
	@Given("these customers with there information contained in the system")
	public void these_customers_with_there_information_contained_in_the_system(io.cucumber.datatable.DataTable dataTable) {
		//name , phone, address , email , id , password ,state;
		name = new ArrayList<String>();
		phone = new ArrayList<String>();
		address = new ArrayList<String>();
		email = new ArrayList<String>();
		id = new ArrayList<String>();
		password = new ArrayList<String>();
		state = new ArrayList<String>();
		
		name.addAll(dataTable.column(0));
		phone.addAll(dataTable.column(1));
		address.addAll(dataTable.column(2));
		email.addAll(dataTable.column(3));
		id.addAll(dataTable.column(4));
		password.addAll(dataTable.column(5));
		state.addAll(dataTable.column(6));

	}
	
	
	
	
	
	@Given("name field is empty")
	public void name_field_is_empty() {
		keyName = "";


	}

	@Given("password field is empty")
	public void password_field_is_empty() {
	   
		valuePass = "";
	}

	@When("he fill {string} with {string}")
	public void he_fill_with(String string, String string2) {

		if(string.equalsIgnoreCase("email"))
		{
			keyName = string2;
		    i = Test.checkIndex(keyName , email);
			if(i == -1)
				{
				flag = false; 

				}
			else 
			{
				flag  = true;
			}
			
			
		}
		
	    if(string.equalsIgnoreCase("password"))
		{
			if(flag == false)
			{
				assertEquals(false, flag);
				System.out.println("Wrong! Try again");

			}
			else
			{	
			valuePass =  string2;
			flag = Test.checkLog(valuePass , password, i);
			
			
			}
		}
	
		if (flag)
		{
			c.setName(keyName);
			c.setPassword(valuePass);
			state.set(i, "true");
			c.setCustomerLogedIn();
		}
		else
		{
			
		 assertEquals(false, flag);
			
		}
		
		

	

		
		
	}
	


	

@Then("his profile will open")
public void his_profile_will_open() {
	

	
	assertTrue(c.getCustomerloged());
	System.out.print("Welcome!");




	
}


@Then("a {string} should appear said that there is something error with your input")
public void a_should_appear_said_that_there_is_something_error_with_your_input(String string) {

	System.out.println("Wrong!Try Again");


}

	@Given("Customer click on login and flag is {string}")
	public void customer_click_on_login_and_flag_is(String string) {
		flag  = Boolean.parseBoolean(string);
	   
	}

	@Then("field {string} should be error")
	public void field_should_be_error(String string) {
		
		System.out.println("Please enter information first!");
		assertEquals(false, flag);

		
	}

	@Then("an error message will show {string}")
	public void an_error_message_will_show(String string) {
		
		System.out.println("Please enter information first!");

		 
	   
	}
	
	
	
}
