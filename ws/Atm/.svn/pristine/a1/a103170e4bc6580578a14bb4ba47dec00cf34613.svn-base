package com.tom.atm;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class WeatherActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather);
		
		new WeatherTask().execute(
			"http://api.openweathermap.org/data/2.5/weather?mode=xml&q=Taipei");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.weather, menu);
		return true;
	}
	
	class WeatherTask extends AsyncTask<String, Void, String>{
		String data = null;
		@Override
		protected String doInBackground(String... params) {
			URLFetcher.fetch(params[0], new WeatherHandler());
			return data;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			float temp = Float.parseFloat(data);
			temp = temp - 273.15f;
			TextView tv = (TextView)findViewById(R.id.info);
			tv.setText(temp+"");
		}
		class WeatherHandler extends DefaultHandler{
			@Override
			public void startElement(String uri, String localName, String qName,
					Attributes attributes) throws SAXException {
				if (localName.equals("temperature")){
					data = attributes.getValue("value");
				}
			}
		}
	}
	
	
}






