package com.MyAndroidCollection.App;

import java.util.ArrayList;
import java.util.HashMap;

import Owen.Data.sington;
import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.MyAndroidCollection.R;

public class MyListView  extends Activity {
	
	private ListView myListView;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.mylistview);
		sington.getSingtonInstance();//测试单例
		myListView = (ListView) findViewById(R.id.listview);

		ArrayList<HashMap<String, Object>> listItemArrayList = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 10; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemTitle", "paper" + i);
			map.put("ItemText", "android project" + i);
			listItemArrayList.add(map);

		}

		SimpleAdapter listItemSimpleAdapter = new SimpleAdapter(this,
				listItemArrayList, // 數據
				R.layout.mylistviewitem,// listItem的xml實現
				//android.R.layout.simple_list_item_2,
				new String[] { "ItemTitle", "ItemText" }, // 從map的批定Key
				new int[] { R.id.txtviewtop, R.id.txtviewbuttom } // 到要實現的控件上
				//new int[] { android.R.id.title, android.R.id.text1 }
		);

		myListView.setAdapter(listItemSimpleAdapter);

		myListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				RelativeLayout lr = (RelativeLayout) arg1;
				TextView mText = (TextView) lr.getChildAt(1);
				Toast.makeText(MyListView.this,
						"你點擊了第" + arg2 + "項的" + mText.getText().toString(),
						1000).show();

			}
		});

		myListView
				.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

					public void onCreateContextMenu(ContextMenu menu, View v,
							ContextMenuInfo menuInfo) {
						ListView lr = (ListView) v;
						RelativeLayout myte = (RelativeLayout) lr.getChildAt(0);
						TextView dd = (TextView) myte.getChildAt(1); // title
						menu.setHeaderIcon(R.drawable.icon);
						menu.setHeaderTitle(dd.getText().toString());
						menu.add(1, 0, 0, "高亮");
						menu.add(0, 1, 0, "置頂");
					}
				});

	}

	public boolean onContextItemSelected(MenuItem item) {
		String Temp = "";
		switch (item.getItemId()) {
		case 0:
			Temp = "高亮";
			break;
		case 1:
			Temp = "置頂";
			break;
		default:
			break;
		}
		Toast.makeText(this, Temp + "處理", 1000).show();
		return super.onContextItemSelected(item);
	}
}


