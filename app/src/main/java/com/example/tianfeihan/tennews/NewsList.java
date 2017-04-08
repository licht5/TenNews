package com.example.tianfeihan.tennews;

import android.app.Activity;
import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * Created by tianfeihan on 17/4/7.
 */

public class NewsList extends TabActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newslist);
        TabHost tabHost = getTabHost();

        TabHost.TabSpec page1 = tabHost.newTabSpec("tab1")
                .setIndicator("推荐新闻")
                .setContent(R.id.introduce_news);
        tabHost.addTab(page1);

        TabHost.TabSpec page2 = tabHost.newTabSpec("tab2")
                .setIndicator("最热新闻")
                .setContent(R.id.new_news);
        tabHost.addTab(page2);

        TabHost.TabSpec page3 = tabHost.newTabSpec("tab3")
                .setIndicator("最新新闻")
                .setContent(R.id.hot_news);
        tabHost.addTab(page3);
    }


}