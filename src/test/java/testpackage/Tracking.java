package testpackage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Tracking {

	String CustomerName;
	int productNum;
	String Status;
	@Given("{string} have an order with the order number {int}")
	public void have_an_order_with_the_order_number(String string, Integer int1) {

		CustomerName = string;
		productNum = int1;
	}

	@When("he check the status of his order")
	public void he_check_the_status_of_his_order() {


		
	}

	@Then("the status should be {string}")
	public void the_status_should_be(String string) {
		Status = string;
		System.out.println(CustomerName+"," +" Yor Order with number  "+productNum + " is "+ Status+"!");


	}

	@When("the order is being processed")
	public void the_order_is_being_processed() {


		Status = "in treatment";
		
	}

	@When("the order is complete")
	public void the_order_is_complete() {

		Status = "complete";
		
	}
	
	
	
	
}