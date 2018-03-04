package com.tom.atm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class HistoryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		new HistoryTask().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.history, menu);
		return true;
	}

	class HistoryTask extends AsyncTask<Void, Void, String>{

		@Override
		protected String doInBackground(Void... params) {
			String line = null;
			try {
				URL url = new URL("http://j.snpy.org/atm/q?userid="+User.userid+"&pw="+User.passwd);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				InputStream is = conn.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader in = new BufferedReader(isr);
				line = in.readLine();
				Log.i("HISTORY", line);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return line;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			try {
				SimpleDateFormat sdf
					= new SimpleDateFormat("yyyy/MM/dd");
				JSONArray array = new JSONArray(result);
				ArrayList<HashMap<String, String>> bag
				= new ArrayList<HashMap<String,String>>();
				for (int i=0; i<array.length();i++){
					HashMap<String, String> map = 
							new HashMap<String, String>();
					
					JSONObject obj = array.getJSONObject(i);
					int amount = obj.getInt("amount");
					String userid = obj.getString("userid");
					JSONObject dateObj = obj.getJSONObject("date");
					long time = dateObj.getLong("time");
					Date date = new Date(time);
					Log.d("OBJ", date+"/"+amount+"/"+userid);
					map.put("date", sdf.format(date));
					map.put("amount", amount+"");
					map.put("userid", userid);
					bag.add(map);
				}
				String[] from = {"date", "amount", "userid"};
				int[] to = {R.id.hist_date, R.id.hist_amount, R.id.hist_userid};
				SimpleAdapter adapter = 
					new SimpleAdapter(HistoryActivity.this,
						bag, R.layout.history_row, from, to);
				ListView list = (ListView)findViewById(R.id.history_list);
				list.setAdapter(adapter);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
}
