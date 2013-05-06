package com.dynobjx;

import android.os.Bundle;
import android.widget.TabHost;
import butterknife.InjectView;
import butterknife.Views;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.dynobjx.fragments.HomeFragment;
import com.dynobjx.fragments.TopRatedFragment;

public class MainActivity extends SherlockFragmentActivity {
	@InjectView(android.R.id.tabhost) TabHost tHost;
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Views.inject(this);
        tHost.setup();
        
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("OnTheMoney");
        actionBar.setDisplayShowHomeEnabled(false);

        TabHost.OnTabChangeListener tabChangeListener = new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                HomeFragment androidFragment = (HomeFragment) fm.findFragmentByTag("Home");
                TopRatedFragment appleFragment = (TopRatedFragment) fm.findFragmentByTag("Top");
                android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
 
                /** Detaches the androidfragment if exists */
                if(androidFragment!=null)
                    ft.detach(androidFragment);
 
                /** Detaches the applefragment if exists */
                if(appleFragment!=null)
                    ft.detach(appleFragment);
 
                /** If current tab is android */
                if(tabId.equalsIgnoreCase("android")){
 
                    if(androidFragment==null){
                        /** Create AndroidFragment and adding to fragmenttransaction */
                        ft.add(R.id.realtabcontent,new HomeFragment(), "android");
                    }else{
                        /** Bring to the front, if already exists in the fragmenttransaction */
                        ft.attach(androidFragment);
                    }
 
                }else{    /** If current tab is apple */
                    if(appleFragment==null){
                        /** Create AppleFragment and adding to fragmenttransaction */
                        ft.add(R.id.realtabcontent,new TopRatedFragment(), "apple");
                     }else{
                        /** Bring to the front, if already exists in the fragmenttransaction */
                        ft.attach(appleFragment);
                    }
                }
                ft.commit();
            }
        };
        tHost.setOnTabChangedListener(tabChangeListener);
 
        TabHost.TabSpec topTab = tHost.newTabSpec("Top");
        topTab.setIndicator(null,getResources().getDrawable(R.drawable.btn_toprated));
        topTab.setContent(new TabContent(getBaseContext()));
        tHost.addTab(topTab);
        
        TabHost.TabSpec searchTab = tHost.newTabSpec("Search");
        searchTab.setIndicator(null,getResources().getDrawable(R.drawable.btn_search));
        searchTab.setContent(new TabContent(getBaseContext()));
        tHost.addTab(searchTab);
        
        TabHost.TabSpec tSpecAndroid = tHost.newTabSpec("Home");
        tSpecAndroid.setIndicator(null,getResources().getDrawable(R.drawable.btn_home));
        tSpecAndroid.setContent(new TabContent(getBaseContext()));
        tHost.addTab(tSpecAndroid);
        
        TabHost.TabSpec settingsTab = tHost.newTabSpec("Settings");
        settingsTab.setIndicator(null, getResources().getDrawable(R.drawable.btn_settings));
        settingsTab.setContent(new TabContent(getBaseContext()));
        tHost.addTab(settingsTab);
        
        TabHost.TabSpec favoritesTab = tHost.newTabSpec("Faves");
        favoritesTab.setIndicator(null,getResources().getDrawable(R.drawable.btn_favorites));
        favoritesTab.setContent(new TabContent(getBaseContext()));
        tHost.addTab(favoritesTab);
 
    }

}
