package com.tom.atm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class GoogleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_google);
		new GoogleTask().execute(new String[]{
			"2498","2474","2330","2881"	
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.google, menu);
		return true;
	}
	
	class GoogleTask extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... params) {
			String data = "";
			try {
				String s = "http://finance.google.com/finance/info?client=ig&q=";
				for (String stock: params){
					s = s+stock+",";
				}
				URL url = new URL(s);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				InputStream is = conn.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader in = new BufferedReader(isr);
				String line = in.readLine();
				while(line!=null){
					data = data +line;
					line = in.readLine();
				}
				data = data.substring(3);
				Log.i("GOOGLE", data);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return data;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			try {
				JSONArray array = new JSONArray(result);
				ArrayList<HashMap<String, String>> list
					= new ArrayList<HashMap<String,String>>();
				for (int i=0; i<array.length(); i++){
					JSONObject stock = array.getJSONObject(i);
					HashMap<String, String> map = new HashMap<String, String>();
					TableRow tr = new TableRow(GoogleActivity.this);
					String symbol = stock.getString("t");
					String current = stock.getString("l_cur");
					String change = stock.getString("c");
					String percent = stock.getString("cp");
					map.put("symbol", symbol);
					map.put("current", current);
					map.put("change", change);
					map.put("percent", percent);
					list.add(map);
				}
				ListView google = (ListView)findViewById(R.id.google_list);
				String[] from = {"symbol", "current", "change", "percent"};
				int[] to = {R.id.google_symbol2,R.id.google_current2, R.id.google_change2, R.id.google_percent2}; 
				SimpleAdapter adapter = 
					new SimpleAdapter(GoogleActivity.this, list, R.layout.google_row2, from, to);
				adapter.setViewBinder(new ViewBinder() {
					@Override
					public boolean setViewValue(View view, Object data,
							String textRepresentation) {
						switch(view.getId()){
						case R.id.google_change2:
							Log.i("BINDER", data+"/"+textRepresentation);
							TextView tv = (TextView)view;
							if (textRepresentation.startsWith("+")){
								tv.setTextColor(getResources().getColor(R.color.stock_up));
							}else{
								tv.setTextColor(getResources().getColor(R.color.stock_down));
							}
							tv.setText(textRepresentation);
							return true;
						}
						
						return false;
					}
				});
				
				
				google.setAdapter(adapter);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}









