package com.ucon.tabviewintro;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ucon.libraryview.adapter.TabAdapter;
import com.ucon.libraryview.model.TabInfo;
import com.ucon.libraryview.tabselectview.TabView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private TabView mTabView;
    private TabAdapter mAdapter;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabView = (TabView) findViewById(R.id.tabView);
        mButton = (Button) findViewById(R.id.btn_test);
        mAdapter = new TabAdapter(this);
        final List<TabInfo> listData = new ArrayList<>();
        listData.clear();
        mTabView.loadCategoriesFromResource(R.xml.dashboard_categories, listData);
        mAdapter.setData(listData);
        mTabView.setAdapter(mAdapter);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listData != null) {
                    listData.clear();
                }
                mTabView.loadCategoriesFromResource(R.xml.dashboard_categories, listData);
            }
        });
        for (TabInfo info : listData) {
            Log.d("shao", info.titleRes + "--" + info.title + "--" + info.iconRes + "--" + info.intent);
        }
    }
}
