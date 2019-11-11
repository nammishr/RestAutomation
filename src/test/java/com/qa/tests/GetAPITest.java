package com.qa.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.model.Datum;
import com.qa.model.ListUsers;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;


public class GetAPITest extends TestBase {

    String serviceURL;
    String apiURL;
    String URL;
    String singleUser;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;


    TestBase testBase;

    @BeforeMethod
    public void setUp() throws IOException {
        testBase = new TestBase();
        serviceURL = prop.getProperty("serviceURL");
        apiURL = prop.getProperty("apiURL");
        singleUser = prop.getProperty("singleUser");
        URL = serviceURL + apiURL;
    }

    @Test(priority = 1)
    public void singleUserAPITest() throws URISyntaxException, IOException {
        CloseableHttpClient httpClient = RestClient.getCloseableHttpClientInstance();
        HttpGet httpGet = RestClient.fetchGetConnectionInstance();
        httpGet.setURI(new URI(URL + singleUser));
        closeableHttpResponse = RestClient.executeAndReturnResp(httpGet,httpClient);

        // Getting response code
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("statusCode is "+ statusCode);


        // Asserting response code
        Assert.assertEquals(statusCode, RESPONSE_CODE_200, "Response code is not 200");
    }





    @Test(priority = 2)
    public void listUserAPITest() throws IOException,URISyntaxException {

        CloseableHttpClient httpClient = RestClient.getCloseableHttpClientInstance();
        HttpGet httpGet = RestClient.fetchGetConnectionInstance();
        httpGet.setURI(new URI(URL));
        closeableHttpResponse = RestClient.executeAndReturnResp(httpGet,httpClient);

        // Getting response code
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("statusCode is "+ statusCode);


        // Asserting response code
        Assert.assertEquals(statusCode, RESPONSE_CODE_200, "Response code is not 200");



        // Asserting response body data in case of success HTT 200 response
        ObjectMapper objectMapper = new ObjectMapper();
        ListUsers listUsers = objectMapper.readValue(closeableHttpResponse.getEntity().getContent(),ListUsers.class);

        int perPage = listUsers.getPerPage().intValue();
        System.out.printf("Value of per_page is : " + perPage);
        Assert.assertEquals(6,perPage);
        System.out.println("\n");

        List<Datum> data = listUsers.getData();
        for(Datum dat : data)
        {
            if(dat.getId() == 2)
            {
                System.out.printf("First Name is : " + dat.getFirstName());
                Assert.assertEquals("Janet",dat.getFirstName());
                break;
            }
        }

        System.out.println("\n");
        Header[] headersArray = closeableHttpResponse.getAllHeaders();
        HashMap<String, String> allheaders = new HashMap<String, String>();
        for (Header header : headersArray)
        {
            allheaders.put(header.getName(), header.getValue());
        }
        System.out.println("Headers are -> " + allheaders);

    }





}
