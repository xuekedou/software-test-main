package com.example.android_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SearchView searchView;
    private ListView listView;
    //定义自动完成的列表
    private final String[] mStrings = {"test1","test2","test4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_view);
        listView = (ListView) findViewById(R.id.lv);
        final ArrayAdapter adapter = new ArrayAdapter<String> (this,android.R.layout.simple_list_item_1,mStrings);
        listView.setAdapter(adapter);
        //为ListView启动过滤
        listView.setTextFilterEnabled(true);
        searchView = (SearchView) findViewById(R.id.sv);
        //设置SearchView自动缩小为图标
        searchView.setIconifiedByDefault(false);//设为true则搜索栏 缩小成俄日一个图标点击展开
        //设置该SearchView显示搜索按钮
        searchView.setSubmitButtonEnabled(true);
        //设置默认提示文字
        searchView.setQueryHint("输入您想查找的内容");
        //配置监听器
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //点击搜索按钮时触发
            @Override
            public boolean onQueryTextSubmit(String query) {
                //此处添加查询开始后的具体时间和方法
                Toast.makeText(MainActivity.this,"you choose:" + query,Toast.LENGTH_SHORT).show();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                //如果newText长度不为0
                if (TextUtils.isEmpty(newText)){
                    listView.clearTextFilter();
                }else{
                    listView.setFilterText(newText);
//          adapter.getFilter().filter(newText.toString());//替换成本句后消失黑框！！！
                }
                return true;
            }
        });
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object string = adapter.getItem(position);
                searchView.setQuery(string.toString(),true);
            }
        });

    }
}