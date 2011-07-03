package com.app.android;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class HttpLoginUser
{
	private String url = "http://www.wiso-buero.uni-koeln.de/magshop/customer/account/loginPost/";
	
	@SuppressWarnings("null")
	public User getLogin(String username, String password)
	{
		String result = "";
		String cookie = "";
        HttpResponse resp = null;
		DefaultHttpClient client = new DefaultHttpClient();
		
		client.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy() {
		    @Override
		    public long getKeepAliveDuration(
		            HttpResponse response,
		            HttpContext context) {
		        long keepAlive = super.getKeepAliveDuration(response, context);
		        if (keepAlive == -1) {
		            // Keep connections alive 5 seconds if a keep-alive value 
		            // has not be explicitly set by the server 
		            keepAlive = 500000000;
		        }
		        return keepAlive;
		    }
		});


        HttpPost method = null;
        
		try 
		{
			method = new HttpPost(new URI(url));
		} 
		catch (URISyntaxException e4) {
			e4.printStackTrace();
		};
		
		//User Parameter
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);        
    	nameValuePairs.add(new BasicNameValuePair("login[username]", username));        
    	nameValuePairs.add(new BasicNameValuePair("login[password]", password));        
    	try 
    	{
			method.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		} 
    	catch (UnsupportedEncodingException e2) {
			e2.printStackTrace(); return null;
		} 
    	
        //Header setzen
        method.addHeader("Accept","text/html, application/xhtml+xml, */*");
        method.addHeader("Referer","http://www.wiso-buero.uni-koeln.de/magshop/customer/account/login/");
        method.addHeader("Accept-Language","de-DE");
      	method.addHeader("User-Agent","Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
      	method.addHeader("Content-Type","application/x-www-form-urlencoded");
      	method.addHeader("Accept-Encoding","gzip, deflate");
      	method.addHeader("Host","www.wiso-buero.uni-koeln.de");
      	//method.addHeader("Content-Length","70");
      	method.addHeader("Connection","Keep-Alive");
      	method.addHeader("Pragma","no-cache");
      	
      	//Cookies abfragen
        try 
        {
			client.execute(method);
		} 
        catch (ClientProtocolException e3) {
			e3.printStackTrace(); return null;
		} 
        catch (IOException e3) {
			e3.printStackTrace(); return null;
		}
        List<Cookie> cookies = client.getCookieStore().getCookies(); 
        if (cookies.isEmpty()) 
		{             
			return null;      
		} 
		else 
		{   
			cookie = cookies.get(0).getName() + "=" + cookies.get(0).getValue().toString();
		}
      	
      	method.addHeader("Cookie", cookie); //"frontend=r9phlun9jr9hq3cv1dngo84bt7"
      	
    	try 
        {
			resp = client.execute(method);
		} 
    	catch (ClientProtocolException e1) {
			e1.printStackTrace(); return null;
		} 
    	catch (IOException e1) {
			e1.printStackTrace(); return null;
		}
		
		HttpEntity entity = resp.getEntity();
		if (entity != null) 
		{
			try 
			{
				result = EntityUtils.toString(entity);
				System.out.print(result);
			} 
			catch (ParseException e) {
				e.printStackTrace(); return null;
			} 
			catch (IOException e) {
				e.printStackTrace(); return null;
			}
		}
		
		User user = new User();
		
		user.setEmail(username);
		
		return user;
	}
}
