package com.huang.pyy.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.huang.pyy.R;

/**
 * Created by hyk on 2018/11/22.
 */

public abstract class BasicActivity extends AppCompatActivity{

    protected TextView title_tv;
    protected Button btn_bottom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRootView();
//        initTitle();
    }

    /**
     * 让子类去实现，要加载布局
     */
    abstract void setRootView();

    protected void initTitle(){
        title_tv = findViewById(R.id.title_tv);
    }

    protected void setTitle(String centerTxt){
        title_tv.setText(centerTxt);
    }

    protected void initBottomBtn(){
        btn_bottom = findViewById(R.id.btn_bottom);
    }

    protected void setBottomBtnText(String txt){
        btn_bottom.setText(txt);
    }
}
