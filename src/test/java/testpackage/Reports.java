package testpackage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Reports {

	
	@Given("the following Customer and their Ids are contained in the system:")
	public void the_following_customer_and_their_ids_are_contained_in_the_system(io.cucumber.datatable.DataTable dataTable) {
	
	}

	@Given("the following cleaning jobs have been completed:")
	public void the_following_cleaning_jobs_have_been_completed(io.cucumber.datatable.DataTable dataTable) {
	 
	}

	@Given("Admin logged in to the application")
	public void admin_logged_in_to_the_application() {


	}

	@When("he selects the option to generate a report")
	public void he_selects_the_option_to_generate_a_report() {


	}

	@When("he chooses to generate a report for all cleaning jobs")
	public void he_chooses_to_generate_a_report_for_all_cleaning_jobs() {
	  
	}

	@Then("a report is generated showing all cleaning jobs")
	public void a_report_is_generated_showing_all_cleaning_jobs() {


	}

	@When("he chooses to generate a report for cleaning jobs between {string} and {string}")
	public void he_chooses_to_generate_a_report_for_cleaning_jobs_between_and(String string, String string2) {


	}

	@Then("a report is generated showing cleaning jobs between the specified dates")
	public void a_report_is_generated_showing_cleaning_jobs_between_the_specified_dates() {


	}

	@When("he chooses to generate a report for cleaning jobs with a minimum rating of {int}")
	public void he_chooses_to_generate_a_report_for_cleaning_jobs_with_a_minimum_rating_of(Integer int1) {


	}

	@Then("a report is generated showing cleaning jobs with a rating of {int} or higher")
	public void a_report_is_generated_showing_cleaning_jobs_with_a_rating_of_or_higher(Integer int1) {


		
	}
	
	
	
	
	
	
}