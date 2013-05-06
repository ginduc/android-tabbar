package com.dynobjx;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import butterknife.InjectView;
import butterknife.Views;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.dynobjx.fragments.FavoritesFragment;
import com.dynobjx.fragments.HomeFragment;
import com.dynobjx.fragments.SearchFragment;
import com.dynobjx.fragments.SettingsFragment;
import com.dynobjx.fragments.TopRatedFragment;

public class MainActivity extends SherlockFragmentActivity {
	@InjectView(android.R.id.tabhost) FragmentTabHost tabHost;
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Views.inject(this);
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.app_name);
        actionBar.setDisplayShowHomeEnabled(false);
        
        tabHost.addTab(tabHost.newTabSpec("Top").setIndicator(null, getResources().getDrawable(R.drawable.btn_toprated)),
                TopRatedFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("Search").setIndicator(null, getResources().getDrawable(R.drawable.btn_search)),
                SearchFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("Home").setIndicator(null, getResources().getDrawable(R.drawable.btn_home)),
                HomeFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("Settings").setIndicator(null, getResources().getDrawable(R.drawable.btn_settings)),
                SettingsFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("Faves").setIndicator(null, getResources().getDrawable(R.drawable.btn_favorites)),
                FavoritesFragment.class, null);

        tabHost.setCurrentTab(2);
    }

}
