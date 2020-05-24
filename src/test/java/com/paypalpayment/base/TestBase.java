package com.paypalpayment.base;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import com.paypalpayment.utils.PropertyReader;

import io.restassured.RestAssured;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
public class TestBase {
	public	static String accessToken;
	public static PropertyReader prop;
	public Logger logger;
	public HashMap<String, Integer> testDataColumnFields = new HashMap<String, Integer>();
	
	public HashMap<String, Integer> testDataRowFields = new HashMap<String, Integer>();

	
	@BeforeClass
	public void setup(){
		
		logger=Logger.getLogger("PayPalPayment");//added Logger
		PropertyConfigurator.configure("Log4j.properties"); //added logger
		logger.setLevel(Level.DEBUG);
		prop = PropertyReader.getInstance();
		//System.out.println("Test");
		RestAssured.baseURI=prop.getProperty("baseurl");
		RestAssured.basePath=prop.getProperty("basepath");
	
		accessToken=	given()
				.formParam("grant_type", "client_credentials")
				//.parameters("grant_type","client_credentials")
				.auth()
				.preemptive()
				.basic(prop.getProperty("clientid"), prop.getProperty("clientsecret"))
				.when()
				.post("/oauth2/token")
				.then()
				.extract()
				.path("access_token");
		 System.out.println("The token is: "+accessToken);
		
	}
	
	

}
