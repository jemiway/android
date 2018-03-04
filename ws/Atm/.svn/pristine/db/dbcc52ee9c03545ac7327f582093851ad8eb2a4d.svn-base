package com.tom.atm;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class FuncActivity extends Activity implements OnItemClickListener {
	private static final String TAG = "FuncActivity";
	private String userid;
	private String passwd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_func);
		if (!User.logon) {
			Intent intent = new Intent(this, LoginActivity.class);
			startActivityForResult(intent, 0);
		} else {
			// Log.i("User", User.userid);
			// Log.i("User", User.passwd);

			// userid = getIntent().getStringExtra("USERID");
			// passwd = getIntent().getStringExtra("PASSWD");
			
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
//		Bundle b = getIntent().getExtras();
//		userid = b.getString("USERID");
//		passwd = b.getString("PASSWD");
		// String[] names = {"餘額查詢", "提款","轉帳", "歷史交易","更改密碼"};
		// ArrayAdapter<String> adapter =
		// new ArrayAdapter<String>(
		// this, android.R.layout.simple_list_item_1, names);
		IconAdapter adapter = new IconAdapter();
		GridView grid = (GridView) findViewById(R.id.mygrid);
		grid.setOnItemClickListener(this);
		grid.setAdapter(adapter);
	}

	class IconAdapter extends BaseAdapter {
		String[] names = { "餘額查詢", "提款", "轉帳", "歷史交易", "更改密碼" };
		int[] images = { R.drawable.func_balance, R.drawable.func_withdraw,
				R.drawable.f3, R.drawable.func_history, R.drawable.f5 };

		@Override
		public int getCount() {
			return names.length;
		}

		@Override
		public Object getItem(int position) {
			return names[position];
		}

		@Override
		public long getItemId(int position) {
			return images[position];
		}

		@Override
		public View getView(int position, View view, ViewGroup parent) {
			if (view == null) {
				view = getLayoutInflater().inflate(R.layout.icon, null);
				TextView tv = (TextView) view.findViewById(R.id.icon_text);
				ImageView iv = (ImageView) view.findViewById(R.id.icon_image);
				tv.setText(names[position]);
				iv.setImageResource(images[position]);
			}
			return view;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.func, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long itemId) {
		switch ((int) itemId) {
		case R.drawable.func_balance:
			Log.d(TAG, position + "");
			Intent intent = new Intent(this, BalanceActivity.class);
			intent.putExtra("USERID", userid);
			intent.putExtra("PASSWD", passwd);
			startActivity(intent);
			break;
		case R.drawable.func_withdraw:
			Log.d(TAG, position + "");
			Intent weather = new Intent(this, WeatherActivity.class);
			startActivity(weather);
			break;
		case R.drawable.f3:
			Log.d(TAG, position + "");
			Intent google = new Intent(this, GoogleActivity.class);
			startActivity(google);
			break;
		case R.drawable.func_history:
			Log.d(TAG, position + "");
			Intent hist = new Intent(this, HistoryActivity.class);
			startActivity(hist);
			break;
		case R.drawable.f5:
			Log.d(TAG, position + "");
			break;

		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.i("RESULT", requestCode+"/"+resultCode);
		if (resultCode==RESULT_CANCELED)
			finish();
	}

}





