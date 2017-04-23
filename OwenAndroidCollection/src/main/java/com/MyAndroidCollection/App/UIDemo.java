package com.MyAndroidCollection.App;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.MyAndroidCollection.R;

public class UIDemo extends Activity {
	
	 private ListView mListView;   
	    @Override  
	    public void onCreate(Bundle savedInstanceState) {   
	       super.onCreate(savedInstanceState);   
	       setContentView(R.layout.uidemo);   
          
	       setupViews();   
	    }   
	        
	    private void setupViews(){   
	        mListView = (ListView)findViewById(R.id.content);   
	        mListView.setAdapter(new ListViewAdapter());   
	    }   
	          
	private class ListViewAdapter extends BaseAdapter {
		// 这里返回10行，ListView有多少行取决于getCount()方法
		public int getCount() {
			return 10;
		}

		public Object getItem(int arg0) {
			return null;
		}

		public long getItemId(int arg0) {
			return 0;
		}

		public View getView(int position, View v, ViewGroup parent) {

			final LayoutInflater inflater = LayoutInflater
					.from(getApplicationContext());

			if (v == null) {
				v = inflater.inflate(R.layout.listview_layout, null);
			}
			TextView mBookName = (TextView) v.findViewById(R.id.bookname);
			TextView mBookAuthor = (TextView) v.findViewById(R.id.author);

			mBookName.setText("Android傻瓜教程" + position);
			mBookAuthor.setText("Frankiewei" + position);
			return v;
		}

	}



}
