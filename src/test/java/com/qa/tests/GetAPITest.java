package com.qa.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.model.Datum;
import com.qa.model.ListUsers;
import com.qa.utility.Utility;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GetAPITest extends TestBase {

    String serviceURL;
    String apiURL;
    String URL;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;


    TestBase testBase;

    @BeforeMethod
    public void setUp() throws IOException {
        testBase = new TestBase();
        serviceURL = prop.getProperty("serviceURL");
        apiURL = prop.getProperty("apiURL");
        URL = serviceURL + apiURL;
    }


    @Test
    public void getAPITest() throws IOException {
        restClient = new RestClient();
        closeableHttpResponse = restClient.get(URL);

        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPONSE_CODE_200, "Response code is not 200");

        ObjectMapper objectMapper = new ObjectMapper();
        ListUsers listUsers = objectMapper.readValue(closeableHttpResponse.getEntity().getContent(),ListUsers.class);

        int perPage = listUsers.getPerPage().intValue();
        System.out.printf("Value of per_page is : " + perPage);
        Assert.assertEquals(3,perPage);


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

        Header[] headersArray = closeableHttpResponse.getAllHeaders();
        HashMap<String, String> allheaders = new HashMap<String, String>();
        for (Header header : headersArray)
        {
            allheaders.put(header.getName(), header.getValue());
        }
        System.out.println("Headers are -> " + allheaders);

    }


}
