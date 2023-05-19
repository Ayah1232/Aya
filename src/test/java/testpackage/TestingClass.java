package testpackage;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "use_cases", glue = {"testpackage"}, plugin = {"html:target/cucumber/wikipedia.html"})//,tags = "" )





public class TestingClass {
	
	
	
	
	
	
	

}