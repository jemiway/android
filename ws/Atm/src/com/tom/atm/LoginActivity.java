package com.tom.atm;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener,
		OnCheckedChangeListener {
	private EditText uid;
	private EditText pw;
	private CheckBox saveUserid;
	private CheckBox savePasswd;
	boolean needSaveUserid = false;
	boolean needSavePasswd = false;
	private String userid;
	private String passwd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		findViews();
		String userid = getPreferences(MODE_PRIVATE).getString(
				getString(R.string.pref_userid), "");
		uid.setText(userid);
		String passwd = getPreferences(MODE_PRIVATE).getString(
				getString(R.string.pref_passwd), "");
		pw.setText(passwd);
		saveUserid.setOnCheckedChangeListener(this);
		savePasswd.setOnCheckedChangeListener(this);
		saveUserid.setChecked(getPreferences(MODE_PRIVATE).getBoolean(
				getString(R.string.pref_save_userid), false));
		savePasswd.setChecked(getPreferences(MODE_PRIVATE).getBoolean(
				getString(R.string.pref_save_passwd), false));
	}

	public void findViews() {
		uid = (EditText) findViewById(R.id.userid);
		pw = (EditText) findViewById(R.id.passwd);
		saveUserid = (CheckBox) findViewById(R.id.save_userid);
		savePasswd = (CheckBox) findViewById(R.id.save_passwd);
	}

	public void login(View v) {
		Toast.makeText(this, "haha", Toast.LENGTH_LONG).show();
		userid = uid.getText().toString();
		passwd = pw.getText().toString();
		
		new LoginTask().execute();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		finish();
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		String pref = null;
		if (buttonView == saveUserid) {
			pref = getString(R.string.pref_save_userid);
			needSaveUserid = isChecked;
		} else {
			pref = getString(R.string.pref_save_passwd);
			needSavePasswd = isChecked;
		}
		getPreferences(MODE_PRIVATE).edit().putBoolean(pref, isChecked)
				.commit();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onPause() {
		super.onPause();
		Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onResume() {
		super.onResume();
		Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onStart() {
		super.onStart();
		Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onStop() {
		super.onStop();
		Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
	}
	
	class LoginTask extends AsyncTask<Void, Void, Integer>{

		@Override
		protected Integer doInBackground(Void... params) {
			// 網路連線
			int result = -1;
			try {
				URL url = new URL("http://j.snpy.org/atm/login?userid="+userid+"&pw="+passwd);
				HttpURLConnection conn = 
					(HttpURLConnection)url.openConnection();
				InputStream is = conn.getInputStream();
				result = is.read();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}

		@Override
		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
			if (result==49){
				
				setResult(RESULT_OK);
				User.logon= true;
				User.userid = userid;
				User.passwd = passwd;
				Toast.makeText(LoginActivity.this, "登入成功", Toast.LENGTH_LONG).show();
				if (!needSaveUserid)
					userid = "";
				getPreferences(MODE_PRIVATE).edit()
						.putString(getString(R.string.pref_userid), userid)
						.commit();
				if (!needSavePasswd)
					passwd = "";
				getPreferences(MODE_PRIVATE).edit()
						.putString(getString(R.string.pref_passwd), passwd)
						.commit();
				finish();
				
//				Intent intent = new Intent(LoginActivity.this, FuncActivity.class);
//				Bundle b = new Bundle();
//				b.putString("USERID", userid);
//				b.putString("PASSWD", passwd);
//				intent.putExtras(b);
////				intent.putExtra("USERID", userid);
////				intent.putExtra("PASSWD", passwd);
//				startActivity(intent);
			}else{
				Toast.makeText(LoginActivity.this, "登入失敗", Toast.LENGTH_LONG).show();
				new AlertDialog.Builder(LoginActivity.this).setTitle("登入訊息").setMessage("登入失敗")
				.setPositiveButton("OK", null)
				.setNeutralButton("前往官網", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(LoginActivity.this, "按了官網",
								Toast.LENGTH_SHORT).show();
					}
				}).setNegativeButton("離開", LoginActivity.this).show();
		// AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// builder.setTitle("登入訊息");
		// builder.setMessage("登入失敗");
		// builder.show();
			}
		}
	}
	
	public void cancel(View v){
		setResult(RESULT_CANCELED);
		finish();
	}
}







