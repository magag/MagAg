package com.app.android;

import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class BarC extends Activity
{
	private ImageButton archivBt;
	private ImageButton propBt;
	private ImageButton infoBt;
	private ImageButton logout;
	private TextView login;

	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.barc);

		login = (TextView)findViewById(R.id.login);
		archivBt = (ImageButton) findViewById(R.id.Button01);
		logout = (ImageButton)findViewById(R.id.logout);
		propBt = (ImageButton) findViewById(R.id.Button03);
		infoBt = (ImageButton) findViewById(R.id.Button04);
		
		Bundle extras = getIntent().getExtras();
		if (extras == null) 
		{
			return;
		}
		final String user = extras.getString("user");
		final String userId = extras.getString("userId");
		if (user != null) 
		{
			login.setText("Angemeldet als: " + user);
		}
		
		ImageView iv =(ImageView) findViewById(R.id.grViewUser);
        iv.setImageDrawable(qrGenerate(userId));
		
		logout.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(BarC.this, LoginWindow.class);
            	startActivity(i);
			}
		});
		
		archivBt.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(BarC.this, MainWindow.class);
				i.putExtra("userId", userId);
            	i.putExtra("user", user);
            	startActivity(i);
			}
		});
		
		propBt.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(BarC.this, Prop.class);
				i.putExtra("userId", userId);
            	i.putExtra("user", user);
            	startActivity(i);
			}
		});
		
		infoBt.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(BarC.this, Info.class);
				i.putExtra("userId", userId);
            	i.putExtra("user", user);
            	startActivity(i);
			}
		});
	}
	
	private Drawable qrGenerate(String convertToQr) 
	{
		  // Nimmt einen String und baut daraus einen QR Code. 
		  // Die Bild URL wird dann als Drawable zurückgegeben. 
		  if (!convertToQr.equals("")) 
		  {
		      try 
		      {
		    	  String qrURL = "http://chart.apis.google.com/chart?cht=qr&chs=350x350&chl=" + convertToQr;  
		    	  URL url = new URL(qrURL); 
				  InputStream is = (InputStream) url.getContent();
				   //+ convertToQr
				  Drawable d = Drawable.createFromStream(is, "src");
		
				  return d;
		      } 
		      catch (Exception e) 
		      {
			       return null;
		      }
		  } 
		  else 
		  {
		      return null;
		  }
 	}
}