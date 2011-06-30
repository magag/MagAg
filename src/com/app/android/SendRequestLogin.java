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

import android.util.Log;

public class SendRequestLogin 
{
	public String getData(ArrayList<NameValuePair> nameValuePairs, String http)
	{
		String result = "";
    	
    	InputStream is = null;
    	//http post
    	try
    	{
    	        HttpClient httpclient = new DefaultHttpClient();
    	        HttpPost httppost = new HttpPost(http);
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
    	}
    	
    	return result;
	}
}
