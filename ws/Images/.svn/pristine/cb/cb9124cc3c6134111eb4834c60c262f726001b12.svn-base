package com.tom.images;

import java.util.ArrayList;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.app.Activity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.ImageView;

public class DetailActivity extends Activity implements OnGestureListener {
	String id;
	ArrayList<String> imageIds;
	int index = 0;
	private ImageView iv;
	GestureDetector detector;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		// collecting data
		id = getIntent().getStringExtra("IMAGE_ID");
		imageIds = getIntent().getStringArrayListExtra("IMAGES");
		index = imageIds.indexOf(id);
		iv = (ImageView)findViewById(R.id.detail_img);
		iv.setImageURI(Uri.withAppendedPath(Media.EXTERNAL_CONTENT_URI, id));
		// regist gesture detector
		detector = new GestureDetector(this, this);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return detector.onTouchEvent(event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch(keyCode){
		case KeyEvent.KEYCODE_DPAD_LEFT:
			previousImage();
			break;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			nextImage();
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void nextImage() {
		index = (index==imageIds.size()-1)? 0: index+1;
		iv.setImageURI(
				Uri.withAppendedPath(
						Media.EXTERNAL_CONTENT_URI, imageIds.get(index)));
	}

	private void previousImage() {
		index = (index==0)? imageIds.size()-1 : index-1;
		iv.setImageURI(
				Uri.withAppendedPath(
						Media.EXTERNAL_CONTENT_URI, imageIds.get(index)));
	}

	@Override
	public boolean onDown(MotionEvent e) {
		Log.d("GESTURE", "onDown");
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		float distance = e2.getX()-e1.getX();
		Log.d("GESTURE", "onFling/"+distance);
		if (distance > 100){
			nextImage();
		}else if (distance < -100){
			previousImage();
		}
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		Log.d("GESTURE", "onLongPress");
		
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		Log.d("GESTURE", "onScroll");
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		Log.d("GESTURE", "onShowPress");
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		Log.d("GESTURE", "onSingleTapUp");
		return false;
	}

}
