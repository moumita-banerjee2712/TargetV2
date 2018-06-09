package com.example.productDetailV3.productDetailV3.Resource;


import com.oracle.javafx.jmx.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
public class ClientConsumer {

    public  String covertJSON(String id) throws ParseException {


        /*Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://redsky.target.com/v2/pdp/tcin/13860428");
        String output = target.request(MediaType.APPLICATION_JSON).get(String.class);*/
        RestTemplate restTemplate = new RestTemplate();
        String URI= AppConstants.URI+id;
        String output = restTemplate.getForObject(URI, String.class);
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(output);
       // System.out.println(jsonObject);
        // String name = (JSONObject) jsonObject.get("product").g
        String val = recurseKeys(jsonObject,"title");
        //System.out.println("value "+val);
        return val;
    }

    public static String recurseKeys(JSONObject jObj, String findKey) throws JSONException {
        String finalValue = "";
        if (jObj == null) {
            return "";
        }
        Iterator<String> keyItr =  jObj.keySet().iterator();
        Map<String, String> map = new HashMap<>();
        while (keyItr.hasNext()) {
            String key = keyItr.next();
            map.put(key, jObj.get(key).toString());
        }
        for (Map.Entry<String, String> e : (map).entrySet()) {
            String key = e.getKey();
            if (key.equalsIgnoreCase(findKey)) {
                return jObj.get(key).toString();
            }
            // read value
            Object value = jObj.get(key);
            if (value instanceof JSONObject) {
                finalValue = recurseKeys((JSONObject) value, findKey);
                if (finalValue!=null && finalValue!="")
                    return finalValue;

            }
        }
        // key is not found
        return finalValue;
    }



}