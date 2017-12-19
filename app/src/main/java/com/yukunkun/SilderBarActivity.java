package com.yukunkun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yukunkun.sidebar.R;

public class SilderBarActivity extends AppCompatActivity {

    private SideBar mSideBar;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_silder_bar);
        mSideBar = (SideBar) findViewById(R.id.bar);
        mTextView = (TextView) findViewById(R.id.tv);
        mSideBar.setOnStrSelectCallBack(new ISideBarSelectCallBack() {
            @Override
            public void onSelectStr(int index, String selectStr) {
                mTextView.setText(selectStr);
            }

            @Override
            public void onSelectEnd() {
                //只有SideBar.STYLENORMAL才会调用这个方法
                mTextView.setVisibility(View.GONE);
            }

            @Override
            public void onSelectStart() {
                //只有SideBar.STYLENORMAL才会调用这个方法
                mTextView.setVisibility(View.VISIBLE);
            }
        });
    }

    //设置三种style
    public void wave(View view) {
        mSideBar.setStyle(SideBar.STYLEWAVE);
    }
    public void nowave(View view) {
        mSideBar.setStyle(SideBar.STYLENOWAVE);

    }
    public void normal(View view) {
        mSideBar.setStyle(SideBar.STYLENORMAL);
    }
}
