package com.tom.expense;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class ExpenseActivity extends Activity {
	DBHelper dbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expense);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expense, menu);
		return true;
	}
	
	public void add(View v){
		String cdate = 
			((EditText)findViewById(R.id.cdate)).getText().toString();
		String name = 
			((EditText)findViewById(R.id.name)).getText().toString();
		String s = 
			((EditText)findViewById(R.id.amount)).getText().toString();
		int amount = Integer.parseInt(s);
//		String sql = "INSERT INTO expense(cdate,name,amount) VALUES('"+cdate+"','"+name+"',"+amount+")"; 
//		Log.d("TAG", sql);
		DBHelper dbHelper =  new DBHelper(this, "exp.db", null, 1);
//		dbHelper.getWritableDatabase().execSQL(sql);
		ContentValues values = new ContentValues();
		values.put("cdate", cdate);
		values.put("name", name);
		values.put("amount", amount);
		dbHelper.getWritableDatabase().insert("expense", null, values);
		
	}
	
	public void list(View v){
		Intent intent = new Intent(this, ListExpenseActivity.class);
		startActivity(intent);
	}
}







