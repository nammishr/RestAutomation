package com.qa.client;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class RestClient {

    private static CloseableHttpClient httpClient = null;
    private static HttpGet httpGet = null;

    //GET Method
  /*  public static void main(String[] args) {
        System.out.println();
    }*/

    /*public CloseableHttpResponse get(String URL) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(URL);
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);
        return closeableHttpResponse;

    }*/

    public static CloseableHttpClient getCloseableHttpClientInstance() {
        if(httpClient == null) {
            httpClient = HttpClients.createDefault();
        }
        return httpClient;
    }

    public static HttpGet fetchGetConnectionInstance() {
        if(httpGet == null) {
            httpGet = new HttpGet();
        }
        return httpGet;
    }

    public static CloseableHttpResponse executeAndReturnResp(HttpRequestBase httpReqMethod, CloseableHttpClient httpClient ) throws IOException{
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpReqMethod);
        return closeableHttpResponse;
    }


}
