package com.app.android;

import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainWindow extends Activity
{
	
	private TextView lblMessage; 
	private ImageView iView;
	
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		 
		String qrTestString = "Hi%20Matthias";
		
		Bundle extras = getIntent().getExtras();
		if (extras == null) 
		{
			return;
		}
		String user = extras.getString("user");
		if (user != null) 
		{
			lblMessage = (TextView)findViewById(R.id.TextView01);
			lblMessage.setText(user);
		}
		
			 
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

