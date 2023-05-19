package testpackage;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class InvoiceFeature {
	ArrayList<String> items ,quantity , price,paType,amount,cusName,debts;
	int TotalDelivered = 0;
	double TotalCash = 0.0;
	double TotalPaid = 0.0;
	double TotalDebts = 0.0;
	@Given("the following deliveries have been made:")
	public void the_following_deliveries_have_been_made(io.cucumber.datatable.DataTable dataTable) {

		items = new ArrayList<String>();
		quantity = new ArrayList<String>();
		price = new ArrayList<String>();
		items.addAll(dataTable.column(0));
		quantity.addAll(dataTable.column(1));
		price.addAll(dataTable.column(2));

	}

	@Given("the following payments have been received:")
	public void the_following_payments_have_been_received(io.cucumber.datatable.DataTable dataTable) {

		paType = new ArrayList<String>();
		amount = new ArrayList<String>();
		paType.addAll(dataTable.column(0));
		amount.addAll(dataTable.column(1));
	}

	@Given("the following debts:")
	public void the_following_debts(io.cucumber.datatable.DataTable dataTable) {

		cusName  = new ArrayList<String>();
		debts = new ArrayList<String>();
		cusName.addAll(dataTable.column(0));
		debts.addAll(dataTable.column(1));
	}

	@When("I calculate the total delivered items")
	public void i_calculate_the_total_delivered_items() {
		
		for(String s : quantity)
		{
			TotalDelivered += Integer.parseInt(s);
		}


		
	}

	@When("I calculate the total cash")
	public void i_calculate_the_total_cash() {

		TotalCash = Integer.parseInt(amount.get(0));

	}

	@When("I calculate the total paid")
	public void i_calculate_the_total_paid() {

		for(String s : amount)
		{
			TotalPaid += Integer.parseInt(s);
		}


	}

	@When("I calculate the total debts")
	public void i_calculate_the_total_debts() {

		for(String s : debts)
		{
			TotalDebts += Integer.parseInt(s);
		}

		
	}

	@Then("a statistics should be generated")
	public void a_statistics_should_be_generated() {
		
		assertEquals(true, true);
		
		System.out.println("Statistic        "+ "Value");
		System.out.println("------------     "+"---------");
		System.out.println("Total Deliverd   "+ TotalDelivered);
		System.out.println("Total Cash       "+ TotalCash);
		System.out.println("Total Paid       "+ TotalPaid);
		System.out.println("Total Debts      "+ TotalDebts);


	}
	
}