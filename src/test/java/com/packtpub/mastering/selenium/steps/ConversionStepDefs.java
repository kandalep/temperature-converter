package com.packtpub.mastering.selenium.steps;

import com.packtpub.mastering.selenium.pageobjects.Google;
import com.packtpub.mastering.selenium.pageobjects.TemperatureConverterPage;
import com.packtpub.mastering.selenium.pageobjects.Configuration;
import com.packtpub.mastering.selenium.pageobjects.Driver;

import java.io.File;
import java.io.IOException;

import cucumber.annotation.After;
import cucumber.annotation.Before;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.runtime.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import static org.junit.Assert.assertEquals;
import java.util.concurrent.TimeUnit;

public class ConversionStepDefs {
    private WebDriver driver;
    public Google googlePage;
    public TemperatureConverterPage temperatureConverterPage;

    @Before
    public void setUp() throws Exception {
        Configuration.load();
        Configuration.print();
        String baseUrl = Configuration.get("url");
        System.setProperty("webdriver.chrome.driver", new File("drivers/chromedriver").getAbsolutePath());
        System.setProperty("webdriver.gecko.driver", new File("drivers/geckodriver").getAbsolutePath());
        DesiredCapabilities cap = new DesiredCapabilities();
        Driver.add(Configuration.get("browser"),cap);
        driver = Driver.current();

    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Given("^I want to  convert (\\d+).(\\d+) degree Fahrenheit to Celsius$")
    public void I_want_to_convert_degree_Fahrenheit_to_Celsius(int arg1, int arg2) throws Throwable {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        googlePage = new Google(driver);
        temperatureConverterPage = googlePage.goToTemperatureConversionPage();
    }

    @When("^I input the value of Fahrenheit as (\\d+).(\\d+) in text field$")
    public void I_input_the_value_of_Fahrenheit_as_in_text_field(int arg1, int arg2) throws Throwable {
        temperatureConverterPage.inputFahrenheit(98.6);
    }

    @Then("^It should be converted to Celsius as (\\d+) degree$")
    public void It_should_be_converted_to_Celsius_as_degree(int arg1) throws Throwable {
        assertEquals(temperatureConverterPage.actualResult(), temperatureConverterPage.expectedResult());
        driver.quit();
    }

}
