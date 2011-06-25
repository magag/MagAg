package com.app.android;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class MagAppActivity extends Activity 
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	//Test Commit
    	
    	
    	//pyxsMdeg
    	//user1484171
    	//www8.subdomain.com
    	
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        
        String result = getData();
        
        TextView tv = new TextView(this);       
        tv.setText(result);       
        setContentView(tv);
    }
    
	private String getData()
    {
    	String result = "";
    	
    	//the year data to send
    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
    	
    	nameValuePairs.add(new BasicNameValuePair("year","1980"));
    	
    	InputStream is = null;
    	//http post
    	try
    	{
    	        HttpClient httpclient = new DefaultHttpClient();
    	        HttpPost httppost = new HttpPost("http://www8.mag-ag.co.de/repository/getAllBenutzer.php");
    	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
    	        
    	        HttpResponse response = httpclient.execute(httppost); 
    	        
    	        HttpEntity entity = response.getEntity();
    	        is = entity.getContent();
    	}
    	catch(Exception e)
    	{
    	        Log.e("log_tag", "Error in http connection " + e.toString());
    	}

    	
    	
    	//convert response to string
    	if (is!=null)
    	{
    		try
    		{
    		        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
    		        StringBuilder sb = new StringBuilder();
    		        String line = null;
    		        while ((line = reader.readLine()) != null) 
    		        {
    		                sb.append(line + "\n");
    		        }
    		        is.close();
    		        result=sb.toString();
    		}
    		catch(Exception e11)
    		{
    		        Log.e("log_tag", "Error converting result " + e11.toString());
    		}
    	
    		//parse json data
    		try
    		{
    		        JSONArray jArray = new JSONArray(result);
    		        for(int i=0;i<jArray.length();i++)
    		        {
    		                JSONObject json_data = jArray.getJSONObject(i);
    		                Log.i("log_tag","id: "+json_data.getInt("id")+
    		                        ", name: "+json_data.getString("name")+
    		                        ", sex: "+json_data.getInt("sex")+
    		                        ", birthyear: "+json_data.getInt("birthyear"));
    		        }
    		}
    		
    		catch(JSONException e)
    		{
    		        Log.e("log_tag", "Error parsing data "+e.toString());
    		}
    	}
    	
    	return result;
    }
	
	
	private String qrGenerate(String convertToQr) {
		// Nimmt einen String und baut daraus einen QR Code. 
		// Die Bild URL wird dann als String zurückgegeben. 
		
		String qrUrl = "http://chart.apis.google.com/chart?cht=qr&chs=350x350&chl=" + convertToQr;
		return qrUrl;
		
	}
}