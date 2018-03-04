package com.tom.expense;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.widget.ListView;

public class ListExpenseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_expense);
		ListView list = (ListView)findViewById(R.id.exp_list);
		DBHelper dbHelper = new DBHelper(this, "exp.db", null, 1);
		Cursor c = 
			dbHelper.getReadableDatabase().query("expense", null, null, null, null, null, null);
		String[] from = {"name", "amount"};
		int[] to = {android.R.id.text1, android.R.id.text2};
		SimpleCursorAdapter adapter = 
			new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, c, from, to);
		list.setAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_expense, menu);
		return true;
	}

}
