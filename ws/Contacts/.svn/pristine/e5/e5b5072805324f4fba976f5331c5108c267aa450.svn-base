package com.tom.contacts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContentResolver cr = getContentResolver();
        Cursor c = cr.query(Contacts.CONTENT_URI, null, null, null, null);
        test(c);
//        String[] from = { Contacts.DISPLAY_NAME };
//        int[] to = {android.R.id.text1 };
//        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, 
//        	android.R.layout.simple_list_item_1, c, from, to);
//        ListView list = (ListView)findViewById(R.id.mylist);
//        list.setAdapter(adapter);
        
    }


	private void test(Cursor c) {
		List<HashMap<String, String>> data = 
			new ArrayList<HashMap<String,String>>();
		while(c.moveToNext()){
        	String name = c.getString(c.getColumnIndex(Contacts.DISPLAY_NAME));
        	int id = c.getInt(c.getColumnIndex(Contacts._ID));
        	int hasPhone = c.getInt(c.getColumnIndex(Contacts.HAS_PHONE_NUMBER));
        	Log.i("TAG", name+"/"+id+"/"+hasPhone);
        	if (hasPhone == 1){
        		Cursor c2 = getContentResolver().query(Phone.CONTENT_URI, null, 
        			Phone.CONTACT_ID+"=?", new String[]{id+""}, null);
        		while(c2.moveToNext()){
        			HashMap<String, String> row = new HashMap<String, String>();
        			String number = c2.getString(c2.getColumnIndex(Phone.NUMBER));
        			Log.i("PH", "  "+number);
        			row.put("name", name);
        			row.put("phone", number);
        			data.add(row);
        		}
        	}
        }
		ListView list = (ListView)findViewById(R.id.mylist);
		String[] from =  {"name", "phone"};
		int[] to = {android.R.id.text1, android.R.id.text2};
		SimpleAdapter adapter = 
				new SimpleAdapter(this, data, android.R.layout.simple_list_item_2, from, to);
		list.setAdapter(adapter);
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
