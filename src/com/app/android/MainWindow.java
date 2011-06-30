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

public class MainWindow extends Activity
{
	private ImageButton barBt;
	private ImageButton propBt;
	private ImageButton infoBt;
	private ImageButton logout;
	private TextView login;
	private ListView lv1;
	private String lv_arr[]={
			"5101/SS2011/1/Voelckner/Marketing",
			"5105/SS2011/1/Biemann/Human_Resource_Management",
			"5145/SS2011/2/Biemann/Human_Resource_Management",
			"5108/SS2011/1/Bienemann/Channel_Management",
			"5198/SS2011/2/Biemann/Channel_Management",
			"5104/SS2011/3/Biemann/Channel_Management"
			};
	 
	
	
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		login = (TextView)findViewById(R.id.login);
		barBt = (ImageButton)findViewById(R.id.Button02);
		logout = (ImageButton)findViewById(R.id.logout);
		propBt = (ImageButton)findViewById(R.id.Button03);
		infoBt = (ImageButton)findViewById(R.id.Button04);
		
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
		
		barBt.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(MainWindow.this, BarC.class);
            	i.putExtra("user", user);
            	startActivity(i);
			}
		});
		
		logout.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(MainWindow.this, LoginWindow.class);
            	startActivity(i);
			}
		});
		
		propBt.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(MainWindow.this, Prop.class);
            	i.putExtra("user", user);
            	startActivity(i);
			}
		});
		
		infoBt.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(MainWindow.this, Info.class);
            	i.putExtra("user", user);
            	startActivity(i);
			}
		});
	}
}

