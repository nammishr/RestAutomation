package com.qa.utility;

import com.google.gson.Gson;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Utility {

    public static Map<String, JSONObject> getResponse(JSONObject jsonResponse) {
        Map<String, JSONObject> jsonMap = new HashMap();
        Gson gson = new Gson();
        jsonMap = gson.fromJson(jsonResponse.toString(), HashMap.class);
	System.out.println("jsonMap is "+jsonMap);
        return jsonMap;
    }

}
