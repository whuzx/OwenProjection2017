package com.MyAndroidCollection.control;




import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.MyAndroidCollection.R;



public class SpinnerTest extends Activity {

	public void onCreate(Bundle  savedInstanceState){
		super.onCreate(savedInstanceState);
		
		LinearLayout linearLayout =new LinearLayout(this);
		
		Spinner spinner = new Spinner(this);

		final LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		spinner.setLayoutParams(params);
		
		
		LinearLayout itemslayoutLayout=new LinearLayout(this);
		
		TextView itemsView =new TextView(this);
		final LayoutParams itemsparams=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		itemsView.setLayoutParams(itemsparams);
		itemslayoutLayout.addView(itemsView);
		
		View itemsView2=View.inflate(this,R.layout.simple_spinner_dropdown_item,null);

		

		final CheckedTextView textView= (CheckedTextView) itemsView2.findViewById(R.id.item_text);
		
		textView.setLayoutParams(itemsparams);
		
		//linearLayout.addView(textView);
		
		
		
		Log.d("owen", "id1=" +itemsView2.getId());
		Log.d("owen", "id2=" +R.layout.simple_spinner_dropdown_item);
		
		
		
		
		
		 ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	                this, com.MyAndroidCollection.R.array.colors, R.layout.simple_spinner_dropdown_item);
	        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
	        
	        spinner.setAdapter(adapter);
	        spinner.setOnItemSelectedListener(
	                new OnItemSelectedListener() {
	                    public void onItemSelected(
	                            AdapterView<?> parent, View view, int position, long id) {
	                        showToast("Spinner1: position=" + position + " id=" + id);
	                        Log.d("owen", "params width" +params.width);
	                        params.height=200;
	                    	//textView.setLayoutParams(itemsparams);
	                    	 Log.d("owen", "textView width" +textView.getWidth());
	                    	 Log.d("owen", "textView height" +textView.getHeight());
	                    }

	                    public void onNothingSelected(AdapterView<?> parent) {
	                        showToast("Spinner1: unselected");
	                        Log.d("owen", "params width" +params.width);
	                        
	                       
	                    }

	                });
	        
	        
	        linearLayout.addView(spinner);
	        setContentView(linearLayout);

	}
	
	

	private void showToast(String string) {
		Log.d("owen", string);
		Toast.makeText(getApplicationContext(), string, Toast.LENGTH_LONG);
		
	}
	
	
	
}
