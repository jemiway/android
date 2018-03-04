package com.tom.atm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class BalanceActivity extends Activity {

	private String userid;
	private String passwd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_balance);
		userid = getIntent().getStringExtra("USERID");
		passwd = getIntent().getStringExtra("PASSWD");
		new BalanceTask().execute(new String[]{userid, passwd});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.balance, menu);
		return true;
	}
	
	class BalanceTask extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... params) {
			int balance = 0;
			try {
				URL url = new URL("http://j.snpy.org/atm/b?userid="+params[0]+"&pw="+params[1]);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				InputStream is = conn.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader in = new BufferedReader(isr);
				String line = in.readLine();
				Log.i("BALANCE", line);
				// parse JSON Object
				try {
					JSONObject obj = new JSONObject(line);
					balance = obj.getInt("balance");
					String name = obj.getString("userid");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return balance+"";
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			TextView tv = (TextView)findViewById(R.id.balance);
			tv.setText(result);
		}
	}
}








