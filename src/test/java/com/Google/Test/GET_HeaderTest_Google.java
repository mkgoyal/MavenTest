package com.Google.Test;

import java.util.LinkedHashMap;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GET_HeaderTest_Google {

	@Test
	void googleMapTest() {
		
		Logger logger=Logger.getLogger(GET_HeaderTest_Google.class);
		PropertyConfigurator.configure("log4j.properties");
		//Base URI
		logger.info("Base URI");
		RestAssured.baseURI="https://maps.googleapis.com";
		
		//create request object
		logger.info("create request object");
		RequestSpecification httpRequest=RestAssured.given();
		
		//Create response object
		logger.info("Create response object");
		Response response=httpRequest.request(Method.GET,"/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");
		
		//Print response in console
		logger.info("Print response in console");
		String responseBody=response.getBody().asString();
		System.out.println("Response boady is:"+responseBody);
		
		//Verify header content type
		logger.info("Verify header content type");
		String contentHeader=response.header("Content-Type");
		System.out.println("Content Type is:"+contentHeader);
		Assert.assertEquals(contentHeader,"application/xml; charset=UTF-8");
		
		//verify header Content-Encoding
		logger.info("verify header Content-Encoding");
		String contentEncodingHeader=response.header("Content-Encoding");
		System.out.println("Content-Encoding is:"+contentEncodingHeader);
		Assert.assertEquals(contentEncodingHeader,"gzip");
		
		//object for output Header as linked hash map
		LinkedHashMap<String,String> outh=new LinkedHashMap<String, String>();
		
		//Print all header
		logger.info("Print all header");
		Headers allHeaders=response.headers();
		
		for (Header header:allHeaders) {
			System.out.println(header.getName()+"-----"+header.getValue());
			outh.put(header.getName(),header.getValue());
		}
		
		//printing saved header
		logger.info("printing saved header");
		System.out.println(outh);
		
		
	}
	

	
}
