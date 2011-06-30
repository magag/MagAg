package com.app.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class Info extends Activity
{
	private ImageButton archivBt;
	private ImageButton barBt;
	private ImageButton propBt;
	private ImageButton logout;
	private TextView login;

	private ListView lv1;
	private String lv_arr[]={
			"Montag      11.00 – 16.00   17.30 – 19.00",
			"Dienstag    11.00 – 16.00",
			"Mittwoch    11.00 – 16.00",
			"Donnerstag  11.00 – 16.00",
			"Freitag     11.00 – 16.00",
			"Kontakt     kontakt@mitschriftenag.de",
			"(Telefonisch sind wir nicht erreichbar. Wir bitten um Euer Verständnis.)",
			
			};
	
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);

		login = (TextView)findViewById(R.id.login);
		archivBt = (ImageButton) findViewById(R.id.Button01);
		logout = (ImageButton)findViewById(R.id.logout);
		barBt = (ImageButton) findViewById(R.id.Button02);
		propBt = (ImageButton) findViewById(R.id.Button03);
		
		lv1=(ListView)findViewById(R.id.listView1);
		lv1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , lv_arr));
		 
		
		
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
				Intent i = new Intent(Info.this, LoginWindow.class);
            	startActivity(i);
			}
		});
		
		archivBt.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(Info.this, MainWindow.class);
            	i.putExtra("user", user);
            	startActivity(i);
			}
		});
		
		barBt.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(Info.this, BarC.class);
            	i.putExtra("user", user);
            	startActivity(i);
			}
		});
		
		propBt.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(Info.this, Prop.class);
            	i.putExtra("user", user);
            	startActivity(i);
			}
		});
	}
}
