package com.tom.mymap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity implements OnMyLocationButtonClickListener {
	static LatLng SCE = new LatLng(25.02592295, 121.537993);
	static LatLng SEVEN = new LatLng(25.0257431, 121.5385991);

    private GoogleMap map;
	@TargetApi(Build.VERSION_CODES.HONEYCOMB) 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
        map.setMyLocationEnabled(true);
        map.setOnMyLocationButtonClickListener(this);
//        map.moveCamera( CameraUpdateFactory.newLatLngZoom(
//        	new LatLng(SCE.latitude, SCE.longitude), 17));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(SCE, 17), 3000, null);
//        map.addMarker(new MarkerOptions()
//        	.title("文化推廣")
//        	.position(SCE)
//        	.snippet("台北市大安區建國南路xxx號"));
//        map.addMarker(new MarkerOptions()
//        	.title("7-11")
//        	.position(SEVEN)
//        	.icon(BitmapDescriptorFactory.fromResource(R.drawable.seven)));
        new PlacesTask().execute();
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public boolean onMyLocationButtonClick() {

        LocationManager locationManager = 
        	(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        String provider = locationManager.getBestProvider(criteria, true);
        Location location = locationManager.getLastKnownLocation(provider);
        if (location!=null){
        	map.moveCamera(
        		CameraUpdateFactory.newLatLngZoom(
        			new LatLng(location.getLatitude(), location.getLongitude()), 15)
        	);
        }
		return false;
	}
    
	class PlacesTask extends AsyncTask<Void, Void, String>{

		@Override
		protected String doInBackground(Void... params) {
			StringBuilder sb = 
				new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
			sb.append("location=25.02592295,121.537993");
			sb.append("&radius=500");
			sb.append("&sensor=false");
			sb.append("&language=zh-TW");
			sb.append("&types=atm");
			sb.append("&key=AIzaSyB_CQFIneyWCfI3a2s7QHECeP0rCYz08Q0");
			StringBuffer buffer = new StringBuffer();
			try {
				URL url = new URL(sb.toString());
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				BufferedReader in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
				String line = in.readLine();
				while(line!=null){
					buffer.append(line);
					line = in.readLine();
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return buffer.toString();
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			try {
				JSONObject obj = new JSONObject(result);
				JSONArray results = obj.getJSONArray("results");
				Log.i("RESULT SIZE", results.length()+"");
				for (int i=0; i<results.length(); i++){
					JSONObject place = results.getJSONObject(i);
					JSONObject geometry = place.getJSONObject("geometry");
					JSONObject location = geometry.getJSONObject("location");
					double lat = location.getDouble("lat");
					double lng = location.getDouble("lng");
					String name = place.getString("name");
					String vicinity = place.getString("vicinity");
					map.addMarker(new MarkerOptions().position(new LatLng(lat, lng))
					.title(name)
					.snippet(vicinity)
					.icon(BitmapDescriptorFactory.fromResource(R.drawable.atm))
					);
				}
				
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
}














