package com.app.android;

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
	public User user = null;
	
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
                if ("".equals(username) && "".equals(password))
                {
                	lblResult.setText("Bitte Email/Passwort eingeben!");
                }
                else
                {
                	HttpLoginUser hLogin = new HttpLoginUser();
                	user = hLogin.getLogin(username, password);
                	
                	if (user == null)
                    {
                    	lblResult.setText("Email/Passwort falsch!");
                    }
                    else
                    {
                    	Intent i = new Intent(LoginWindow.this, MainWindow.class);
                    	i.putExtra("user", username);
                    	startActivity(i);
                    }
                }
			}
		});
    }
}