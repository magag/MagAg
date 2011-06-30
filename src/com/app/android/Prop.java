package com.app.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Prop extends Activity
{
	private ImageButton archivBt;
	private ImageButton barBt;
	private ImageButton logout;
	private ImageButton infoBt;
	private TextView login;
	private Button bt;

	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.prop);

		login = (TextView)findViewById(R.id.login);
		archivBt = (ImageButton) findViewById(R.id.Button01);
		logout = (ImageButton)findViewById(R.id.logout);
		barBt = (ImageButton) findViewById(R.id.Button02);
		infoBt = (ImageButton) findViewById(R.id.Button04);
		bt = (Button)findViewById(R.id.button1);
		
		Bundle extras = getIntent().getExtras();
		if (extras == null) 
		{
			return;
		}
		final String user = extras.getString("user");
		if (user != null) 
		{
			login.setText("Angemeldet als: " + user);
		}
		
		logout.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(Prop.this, LoginWindow.class);
            	startActivity(i);
			}
		});
		
		archivBt.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(Prop.this, MainWindow.class);
            	i.putExtra("user", user);
            	startActivity(i);
			}
		});
		
		bt.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(Prop.this, MainWindow.class);
            	i.putExtra("user", user);
            	startActivity(i);
			}
		});
		
		barBt.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(Prop.this, BarC.class);
            	i.putExtra("user", user);
            	startActivity(i);
			}
		});
		
		infoBt.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(Prop.this, Info.class);
            	i.putExtra("user", user);
            	startActivity(i);
			}
		});
	}
}