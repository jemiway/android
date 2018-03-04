package com.tom.expense;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
//	String[] names = {"早餐", "中餐", "飲料", "晚餐"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String[] names = getResources()
				.getStringArray(R.array.names);
		ListView list = (ListView)findViewById(R.id.mylist);
		ArrayAdapter<String> adapter = 
				new ArrayAdapter<String>(this, 
						android.R.layout.simple_list_item_1, names);
		list.setAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
