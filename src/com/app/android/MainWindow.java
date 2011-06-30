package com.app.android;

import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

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
			"5105/SS2011/1/Biemann/Human Resource Management",
			"5145/SS2011/2/Biemann/Human Resource Management",
			"5108/SS2011/1/Bienemann/Channel Management",
			"5198/SS2011/2/Biemann/Channel Management",
			"5104/SS2011/3/Biemann/Channel Management"
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
		
		
		
		
//		String qrTestString = "Hi%20Matthias";
//		
//		Bundle extras = getIntent().getExtras();
//		if (extras == null) 
//		{
//			return;
//		}
//		String user = extras.getString("user");
//		if (user != null) 
//		{
//			lblMessage = (TextView)findViewById(R.id.TextView01);
//			lblMessage.setText(user);
//		}
//		
//		textFlipper1 = (TextView)findViewById(R.id.TextView02);
//		textFlipper1.setText("fdasdfs");
//		
//		textFlipper2 = (TextView)findViewById(R.id.TextView03);
//		textFlipper2.setText("dsfsdf");
//		
			 
//		iView = (ImageView)findViewById(R.id.qrCode);
//		iView.setImageDrawable(qrGenerate(qrTestString));
//		setContentView(iView);
	}
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 private Drawable qrGenerate(String convertToQr) 
	 {
		// Nimmt einen String und baut daraus einen QR Code. 
		// Die Bild URL wird dann als Drawable zurückgegeben. 
		if (!convertToQr.equals("")) {
	 		try {
	 	String qrURL = "http://chart.apis.google.com/chart?cht=qr&chs=350x350&chl=" + convertToQr;		
		URL url = new URL(qrURL); 
		InputStream is = (InputStream) url.getContent();
		 //+ convertToQr
		Drawable d = Drawable.createFromStream(is, "src");
	
		return d;
	 		} catch (Exception e) {
	 			return null;
	 		}
	 	} else {
	 		return null;
	 	}
	}
}

