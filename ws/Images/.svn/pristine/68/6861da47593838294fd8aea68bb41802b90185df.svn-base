package com.tom.images;

import java.util.ArrayList;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Thumbnails;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnItemClickListener {
	ArrayList<String> imageIds = new ArrayList<String>();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ThumbnailAdapter adapter = new ThumbnailAdapter();
        GridView grid = (GridView)findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(this);
        
    }

    class ThumbnailAdapter extends BaseAdapter{
    	ArrayList<String> ids = new ArrayList<String>();
    	
    	public ThumbnailAdapter(){
    		Cursor c = getContentResolver().query(
    			Thumbnails.EXTERNAL_CONTENT_URI, null, null, null, null);
    		while(c.moveToNext()){
    			String id = c.getString(c.getColumnIndex(Thumbnails._ID));
    			String imageId = c.getString(c.getColumnIndex(Thumbnails.IMAGE_ID));
    			ids.add(id);
    			imageIds.add(imageId);
    		}
    	}
		@Override
		public int getCount() {
			return ids.size();
		}

		@Override
		public Object getItem(int position) {
			return ids.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View view, ViewGroup parent) {
			if (view == null){
				view = getLayoutInflater().inflate(R.layout.thumb, null);
				ImageView iv = (ImageView) view.findViewById(R.id.thumb);
				iv.setImageURI(Uri.withAppendedPath(Thumbnails.EXTERNAL_CONTENT_URI, ids.get(position)));
			}
			return view;
		}
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	@Override
	public void onItemClick(AdapterView<?> parent, 
		View view, int position, long itemId) {
		Intent detail = new Intent(this, DetailActivity.class);
		detail.putExtra("IMAGE_ID", imageIds.get(position));
		detail.putStringArrayListExtra("IMAGES", imageIds);
		startActivity(detail);
	}
 
}

















