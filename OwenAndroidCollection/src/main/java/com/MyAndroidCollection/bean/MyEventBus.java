package com.MyAndroidCollection.bean;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class MyEventBus extends Activity {

    protected void  onCreate(Bundle  savedInstanceState) {
        super.onCreate(savedInstanceState);

        initLayout();



    }




    private void initLayout() {
        Button eventBtnButton=new Button(this);
        eventBtnButton.setText("start event");

        LinearLayout linearLayout =new LinearLayout(this);
        LayoutParams layoutParams =new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        linearLayout.addView(eventBtnButton);
        setContentView(linearLayout);
    }
}
