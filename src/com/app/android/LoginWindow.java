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
/*
 * Anmeldeinformationen: insgesamt 2 Benutzer in der DB
 * 
 * Benutzer 1
 * email: ksenial0@smail.uni-koeln.de
 * pass: helloworld :)
 * 
 * Benutzer 2:
 * email: mgerber@smail.uni-koeln.de
 * pass: helloworld
 */
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
                	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                	nameValuePairs.add(new BasicNameValuePair("email",username));
                	nameValuePairs.add(new BasicNameValuePair("pass",password));
                	SendRequestLogin r = new SendRequestLogin();
                	
                	//script ruft Anmeldeinformationen mit vorhandenen Anmeldeinformationen ab
                	//konvertiert in ein JASON-Object
                	result = r.getData(nameValuePairs, "http://www8.mag-ag.co.de/repository/getLoginData.php");
            	
            		//Zweite Variante User zu prüfen -> direkt über die Webseite
            		
                	//HttpLoginUser hLogin = new HttpLoginUser();
                	//user = hLogin.getLogin(username, password);
                	
                	//if (user == null)
            		if ("0".equals(result) || "null\n".equals(result) || "".equals(result))
                    {
                    	lblResult.setText("Email/Passwort falsch!");
                    }
                    else
                    {
                    	JSONArray jArray = null;
						try 
						{
							jArray = new JSONArray(result);
						} 
						catch (JSONException e) { e.printStackTrace(); }
        		        for(int i = 0; i < jArray.length(); i++)
        		        {
        		                JSONObject json_data = null;
        		                
        		                int lenhthArr = 0;
								
        		                try 
								{
									lenhthArr = jArray.getJSONArray(i).length();
								} catch (JSONException e1) {e1.printStackTrace();}
								
        		                for(int j = 0; j < lenhthArr; j++)
                		        {
									try 
									{
										json_data = jArray.getJSONArray(i).getJSONObject(j);
									} 
									catch (JSONException e){ e.printStackTrace();}
									try 
									{
										user.setName(json_data.getString("name"));
										user.setEmail(json_data.getString("email"));
										user.setId(json_data.getString("id"));
									} 
									catch (JSONException e) { e.printStackTrace(); }
                		        }
        		        }
                    	
                    	Intent i = new Intent(LoginWindow.this, MainWindow.class);
                    	i.putExtra("user", user.getName());
                    	i.putExtra("userId", "4001214");
                    	startActivity(i);
                    }
                }
			}
		});
    }
}