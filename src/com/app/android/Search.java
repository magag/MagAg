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
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class Search extends Activity
{
	private ImageButton barBt;
	private ImageButton propBt;
	private ImageButton infoBt;
	private ImageButton logout;
	private TextView login;
	private ListView lv1;
	
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);

		login = (TextView)findViewById(R.id.login);
		barBt = (ImageButton)findViewById(R.id.Button02);
		logout = (ImageButton)findViewById(R.id.logout);
		propBt = (ImageButton)findViewById(R.id.Button03);
		infoBt = (ImageButton)findViewById(R.id.Button04);
		
		Bundle extras = getIntent().getExtras();
		if (extras == null) 
		{
			return;
		}
		final String user = extras.getString("user");
		final String userId = extras.getString("userId");
		final String suchfeld = extras.getString("suchfeld");
		
		if (user != null) 
		{
			login.setText("Angemeldet als: " + user);
		}
		
		//Hole Daten für Benutzer
		String result="";
    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
    	nameValuePairs.add(new BasicNameValuePair("veranstaltung", suchfeld));
    	SendRequestLogin r = new SendRequestLogin();

    	result = r.getData(nameValuePairs, "http://www8.mag-ag.co.de/repository/getSearch.php");
		
    	if ("0".equals(result) || "null\n".equals(result) || "".equals(result))
        {
    		String lv_arr[] = {"Keine vorhandene Mitschriften"};
    		lv1=(ListView)findViewById(R.id.listView1);
    		lv1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , lv_arr));
        }
        else
        {
        	JSONArray jArray = null;
			try 
			{
				jArray = new JSONArray(result);
				
			} 
			catch (JSONException e) { e.printStackTrace(); }
			String lv_arr[] = new String [jArray.length()];
	        for(int i = 0; i < jArray.length(); i++)
	        {
	        	
                JSONObject json_data = null;
                
                int lenhthArr = 0;
				
                try 
				{
					lenhthArr = jArray.getJSONArray(i).length();
				} catch (JSONException e1) {e1.printStackTrace();}
				
				String info = "";
				
                for(int j = 0; j < lenhthArr; j++)
		        {
					try 
					{
						json_data = jArray.getJSONArray(i).getJSONObject(j);
					} 
					catch (JSONException e){ e.printStackTrace();}
					try 
					{
						info = json_data.getString("Veranstaltung") + " " + json_data.getString("Semester") + json_data.getString("Jahr") + " " + json_data.getString("Dozent");
					} 
					catch (JSONException e) { e.printStackTrace(); }
		        }
                lv_arr[i] = info;
	        }
	        lv1=(ListView)findViewById(R.id.listView1);
    		lv1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , lv_arr));
        }
		
		barBt.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(Search.this, BarC.class);
            	i.putExtra("userId", userId);
            	i.putExtra("user", user);
            	startActivity(i);
			}
		});
		
		logout.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(Search.this, LoginWindow.class);
            	startActivity(i);
			}
		});
		
		propBt.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(Search.this, Prop.class);
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
				Intent i = new Intent(Search.this, Info.class);
				i.putExtra("userId", userId);
            	i.putExtra("user", user);
            	startActivity(i);
			}
		});
	}
}

