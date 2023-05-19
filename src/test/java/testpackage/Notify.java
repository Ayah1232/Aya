package testpackage;

import java.util.ArrayList;

import javax.mail.MessagingException;
import companypackage.Test;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Notify {

	boolean flag;
	String Emails;
	int i ,orderID;
	String ID ;
	ArrayList<String> Name,Email,Id , CPId , PName,PId , status;
	@Given("these users names, their emails and their IDs are contained in the system")
	public void these_users_names_their_emails_and_their_i_ds_are_contained_in_the_system(io.cucumber.datatable.DataTable dataTable) {

		Name = new ArrayList<String>();
		Email = new ArrayList<String>();
		Id = new ArrayList<String>();
		Name.addAll(dataTable.column(0));
		Email.addAll(dataTable.column(1));
		Id.addAll(dataTable.column(2));


		
		
	}

	@Given("these orders names, their IDs are contained in the system")
	public void these_orders_names_their_i_ds_are_contained_in_the_system(io.cucumber.datatable.DataTable dataTable) {

		PName = new ArrayList<String>();
		CPId = new ArrayList<String>();
		PId = new ArrayList<String>();
		status = new ArrayList<String>();
		PName.addAll(dataTable.column(0));
		CPId.addAll(dataTable.column(1));
		PId.addAll(dataTable.column(2));
		status.addAll(dataTable.column(3));

		
	}

	@Given("a customer with this {string}")
	public void a_customer_with_this(String string) {

		Emails = string;
		
		for(String e : Email)
		{
			if(e.equalsIgnoreCase(Emails))
			{ 
				i = Email.indexOf(e);
				flag = true;
			}
		}
		ID = Id.get(i);
		

	}

	@Given("an order with this {int} is marked as complete")
	public void an_order_with_this_is_marked_as_complete(Integer int1) {

		orderID = int1;
		for(String p : PId)
		{
			if(Integer.parseInt(p) == orderID)
			{ 
				i = PId.indexOf(p);
			}
		}
		
		if(status.get(i).equalsIgnoreCase("complete") & flag == true)
		{
			flag = true;
		}
		
		else flag = false;
		

		
	}





@Then("the user should receive an email from system with subject {string} and  the email body should contain {string}")
public void the_user_should_receive_an_email_from_system_with_subject_and_the_email_body_should_contain(String string, String string2) {
	try {
		Test.sendEmail("abtammam2020@gmail.com", "Your order is complete", "Dear customer, your order is complete!");
		
		
	} catch (MessagingException e) {
		e.printStackTrace();
	}
}




}