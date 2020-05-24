package com.paypalpayment.testcases;

import com.paypalpayment.base.TestBase;
import com.paypalpayment.utils.TestData;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;


public class PaymentTestcases extends TestBase {
	static String payment_id;
	TestData td=new TestData();
	public String[][]XData;
	@Test
	public void createAPayment() throws Exception{
		XData=td.readXL("Payment");
		System.out.println("Body"+td.fieldValue(XData, "CreatePayment", "Path"));
		
		payment_id=given()
		.contentType(ContentType.JSON)
		.auth()
		.oauth2(accessToken)
		.when()
		.body(td.fieldValue(XData, "CreatePayment", "Body"))
		.post(td.fieldValue(XData, "CreatePayment", "Path"))
		.then()
		.log()
		.all()
		.extract()
		.path("id");
		
		System.out.println(payment_id);
		Assert.assertTrue(payment_id!=null);
		}
	@Test
	public void getPaymentDetails(){
		given()
		.auth()
		.oauth2(accessToken)
		.when()
		.get(td.fieldValue(XData, "GetPaymentDetails", "Path")+payment_id)
		.then()
		.assertThat()
		.statusCode(Integer.parseInt(td.fieldValue(XData, "GetPaymentDetails", "ExpectedResult")));
	
	}

}
