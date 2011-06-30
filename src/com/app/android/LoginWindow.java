package com.app.android;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginWindow extends Activity 
{
	public User user = new User();
	
	private EditText etUsername; 
	private EditText etPassword; 
	private Button btnLogin; 
	private TextView lblResult; 

    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etUsername = (EditText)findViewById(R.id.username); 
	    etPassword = (EditText)findViewById(R.id.password); 
	    btnLogin = (Button)findViewById(R.id.login_button); 
	    lblResult = (TextView)findViewById(R.id.result); 
        
        btnLogin.setOnClickListener(new OnClickListener() 
        {
			@Override
			public void onClick(View v) 
			{
			    String username = etUsername.getText().toString(); 
                String password = etPassword.getText().toString(); 
                if ("".equals(username) || "".equals(password))
                {
                	lblResult.setText("Bitte Email/Passwort eingeben!");
                }
                else
                {
                	String result="";
                	//the year data to send
                	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                	nameValuePairs.add(new BasicNameValuePair("email",username));
                	nameValuePairs.add(new BasicNameValuePair("pass",password));
                	SendRequestLogin r = new SendRequestLogin();
                	//result = r.getData(nameValuePairs, "http://www8.mag-ag.co.de/repository/getAllBenutzer.php");
            	
            		//Zweite Variante User zu prüfen -> direkt über die Webseite
            		//HttpLoginUser hLogin = new HttpLoginUser();
                	//user = hLogin.getLogin(username, password);
                	//if (user == null)
            		if ("0".equals(result) || "null".equals(result)) // || "".equals(result))
                    {
                    	lblResult.setText("Email/Passwort falsch!");
                    }
                    else
                    {
//                    	JSONArray jArray = null;
//						try 
//						{
//							jArray = new JSONArray(result);
//						} 
//						catch (JSONException e) { e.printStackTrace(); }
//        		        for(int i=0;i < jArray.length();i++)
//        		        {
//        		                JSONObject json_data = null;
//								try 
//								{
//									json_data = jArray.getJSONObject(i);
//								} 
//								catch (JSONException e){ e.printStackTrace();}
//								try 
//								{
//									user.setName(json_data.getString("name"));
//								} 
//								catch (JSONException e) { e.printStackTrace(); }
//        		        }
                    	user.setEmail(username);
                    	
                    	Intent i = new Intent(LoginWindow.this, MainWindow.class);
                    	i.putExtra("user", user.getEmail());
                    	startActivity(i);
                    }
                }
			}
		});
    }
}