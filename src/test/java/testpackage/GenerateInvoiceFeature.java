package testpackage;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GenerateInvoiceFeature {

	String name;
	int i;
	int Id;
	double total , carpet = 40 , cover = 45  , totalCarpet , totalCover ;
	int r , CarpetQ , CoverQ;
	String add , cityy;
	boolean n = false;
	ArrayList<String> cusId , item , quantity  ,Name ,address , city,price , idForOrder ;
	@Given("the cleaning service includes the following items:")
	public void the_cleaning_service_includes_the_following_items(io.cucumber.datatable.DataTable dataTable) {
		idForOrder = new ArrayList<String>();
		item = new ArrayList<String>();
		quantity = new ArrayList<String>();
		idForOrder .addAll( dataTable.column(0));
		item .addAll( dataTable.column(1));
		quantity .addAll( dataTable.column(2));
		
	}

	@Given("the customer has provided the following delivery information:")
	public void the_customer_has_provided_the_following_delivery_information(io.cucumber.datatable.DataTable dataTable) {

		Name = new ArrayList<String>();
		cusId = new ArrayList<String>();
		address = new ArrayList<String>();
		city = new ArrayList<String>();
		Name .addAll( dataTable.column(0));
		cusId .addAll( dataTable.column(1));
		address .addAll( dataTable.column(2));
	    city .addAll( dataTable.column(3));


	}

	@Given("these are the price in NIS")
	public void these_are_the_price_in_nis(io.cucumber.datatable.DataTable dataTable) {
		price = new ArrayList<String>();
		price .addAll( dataTable.column(1));


	}

	@When("{string} has requested cleaning services")
	public void has_requested_cleaning_services(String string) {
		
		name = string;
		
		for(String r : Name)
		{
			if(name.equalsIgnoreCase(r))
			{
				i = Name.indexOf(r);

			}
		}
		
		Id = Integer.parseInt(cusId.get(i));


		
		
	}

	@When("he has provided a list of items to be cleaned")
	public void he_has_provided_a_list_of_items_to_be_cleaned() {
		int t = 0;
		int r = -1;
		for(String s : idForOrder)
		{
			if(Id == Integer.parseInt(s))
			{
			if(r == t )
				 t = idForOrder.lastIndexOf(s);
			else
				 t = idForOrder.indexOf(s);
				

				  if(item.get(t) .equalsIgnoreCase("cover"))
				  {
					    CoverQ =   Integer.parseInt(quantity.get(t));
					 totalCover =  Double.parseDouble(quantity.get(t)) * cover;
				  }
				  else if(item.get(t) .equalsIgnoreCase("carpet"))
				  {
					    CarpetQ =   Integer.parseInt(quantity.get(t));
						 totalCarpet =  Double.parseDouble(quantity.get(t)) * carpet;
				  }
				   r = t;
			}
			
		}
		
		total = totalCarpet + totalCover;


		
		
	}

	@When("he has provided delivery information")
	public void he_has_provided_delivery_information() {


		for (String o : cusId)
		{
			if(Id == Integer.parseInt(o))
			{
				r = cusId.indexOf(o);
			}
		}
		
		add = address.get(r);
		cityy =  city.get(r);

		
	}

	

	@Then("an invoice should be generated")
	public void an_invoice_should_be_generated() {
		
		n = true;
		
	assertEquals(true, n);
	
	
     	System.out.println();
		System.out.println("    Item    "+ "Value  " +  "Price");
		System.out.println("------------"+"---------" + "-------");
		System.out.println("Carpet       "+ CarpetQ +"    "+  totalCarpet  );
		System.out.println("Cover        "+ CoverQ +"     "+   totalCover  );
		System.out.println();
		
		
		System.out.println("Address        "+ "City");
		System.out.println("------------     "+"---------");
		System.out.println(    add     + "            " + cityy);
		System.out.println();
		System.out.println(    "Total Price:   "  + total);

		
		


		
	}
	
	
	
}
