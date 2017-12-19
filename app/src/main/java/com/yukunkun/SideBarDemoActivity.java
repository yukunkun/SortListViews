package com.yukunkun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.yukunkun.sidebar.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SideBarDemoActivity extends AppCompatActivity {
 private SideBar bar;
    private ListView mListView;
    private String[] mStrings;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar= (SideBar) findViewById(R.id.bar);
        bar.setStyle(SideBar.STYLEWAVE);
//        bar.setStyle(SideBar.STYLENOWAVE);
//        bar.setStyle(SideBar.STYLENORMAL);
        mListView = (ListView) findViewById(R.id.list);
        mTextView = (TextView) findViewById(R.id.tv);
        mStrings=getResources().getStringArray(R.array.date);

        //数据转换成我们需要的module
        final List<SortModel> sortModule = getSortModule();
        final LVAdapter lvAdapter=new LVAdapter(sortModule,this);
        mListView.setAdapter(lvAdapter);
        //silderbar的回调
        bar.setOnStrSelectCallBack(new ISideBarSelectCallBack() {
            @Override
            public void onSelectStr(int index, String selectStr) {
                mTextView.setText(selectStr);
                for (int i = 0; i < sortModule.size() ; i++){
                    if(sortModule.get(i).getSortLetters().equals(selectStr)){
                        mListView.setSelection(i);
                        return;
                    }
                }
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

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(SideBarDemoActivity.this,SilderBarActivity.class);
                startActivity(intent);
            }
        });
    }

    private List<SortModel> getSortModule() {
        List<SortModel> filterDateList = new ArrayList<SortModel>();
        for (int i = 0; i < mStrings.length; i++) {
            String pinYinFirstLetter = Cn2Spell.getPinYinFirstLetter(mStrings[i]);
            SortModel sortModel=new SortModel(mStrings[i],pinYinFirstLetter.toUpperCase().charAt(0)+"");
            filterDateList.add(sortModel);
        }
        Collections.sort(filterDateList,new PinyinComparator());
        return filterDateList;
    }
}
