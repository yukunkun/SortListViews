# SortListView，一个电话本列表的实现
#### 简书介绍
[一个仿手机联系人自动排序的列表实现`sortlistview`（一）](http://www.jianshu.com/p/3793f8bfefed)
[一个仿手机联系人自动排序的列表实现`sortlistview`（二)](https://www.jianshu.com/p/9181e59bb946)
#### 直接上图
![S71219-14231477.jpg](http://upload-images.jianshu.io/upload_images/3001453-a08726ca92a3b0d1.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![S71219-14234724.jpg](http://upload-images.jianshu.io/upload_images/3001453-8fccd59dc5dde09f.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![S71219-14221862.jpg](http://upload-images.jianshu.io/upload_images/3001453-87f0dae88cea0a1d.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
#### 具体的可以看代码
#### 布局
    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        xmlns:sidebar="http://schemas.android.com/apk/res-auto"
        tools:context="com.yukunkun.SideBarDemoActivity">
        <ListView android:layout_width="match_parent"
                  android:id="@+id/list"
                  android:layout_height="match_parent">
        </ListView>
    <com.yukunkun.SideBar
        android:textColor="@color/colorAccent"
        android:textSize="15sp"
        android:paddingRight="10dp"
        sidebar:scaleTime="1"
        android:layout_width="200dp"
        android:id="@+id/bar"
        android:layout_height="match_parent"
        android:layout_alignParentTop="true"
        android:layout_alignParentRight="true"
        android:layout_alignParentEnd="true"/>
        <TextView android:layout_width="55dp"
                  android:text="A"
                  android:id="@+id/tv"
                  android:gravity="center"
                  android:textSize="25sp"
                  android:visibility="gone"
                  android:textColor="#bb4e79f1"
                  android:background="@color/green"
                  android:layout_centerInParent="true"
                  android:layout_height="55dp"/>
    
    </RelativeLayout>

#### 主要使用
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
    //获取到需要的排序后的集合
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


