package com.dynobjx;

import android.os.Bundle;
import android.widget.TabHost;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.dynobjx.fragments.TabContent;
import com.dynobjx.fragments.TabOneFragment;
import com.dynobjx.fragments.TabTwoFragment;

public class MainActivity extends SherlockFragmentActivity {
	TabHost tHost;
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("OnTheMoney");
        actionBar.setDisplayShowHomeEnabled(false);
 
        tHost = (TabHost) findViewById(android.R.id.tabhost);
        tHost.setup();
 
        /** Defining Tab Change Listener event. This is invoked when tab is changed */
        TabHost.OnTabChangeListener tabChangeListener = new TabHost.OnTabChangeListener() {
 
            @Override
            public void onTabChanged(String tabId) {
                android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                TabOneFragment androidFragment = (TabOneFragment) fm.findFragmentByTag("android");
                TabTwoFragment appleFragment = (TabTwoFragment) fm.findFragmentByTag("apple");
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
                        ft.add(R.id.realtabcontent,new TabOneFragment(), "android");
                    }else{
                        /** Bring to the front, if already exists in the fragmenttransaction */
                        ft.attach(androidFragment);
                    }
 
                }else{    /** If current tab is apple */
                    if(appleFragment==null){
                        /** Create AppleFragment and adding to fragmenttransaction */
                        ft.add(R.id.realtabcontent,new TabTwoFragment(), "apple");
                     }else{
                        /** Bring to the front, if already exists in the fragmenttransaction */
                        ft.attach(appleFragment);
                    }
                }
                ft.commit();
            }
        };
 
        /** Setting tabchangelistener for the tab */
        tHost.setOnTabChangedListener(tabChangeListener);
 
        /** Defining tab builder for Andriod tab */
        TabHost.TabSpec tSpecAndroid = tHost.newTabSpec("android");
        tSpecAndroid.setIndicator("",getResources().getDrawable(R.drawable.tabone));
        tSpecAndroid.setContent(new TabContent(getBaseContext()));
        tHost.addTab(tSpecAndroid);
 
        /** Defining tab builder for Apple tab */
        TabHost.TabSpec tSpecApple = tHost.newTabSpec("apple");
        tSpecApple.setIndicator("",getResources().getDrawable(R.drawable.tabtwo));
        tSpecApple.setContent(new TabContent(getBaseContext()));
        tHost.addTab(tSpecApple);
        
        /** Defining tab builder for Apple tab */
        TabHost.TabSpec settingsTab = tHost.newTabSpec("Settings");
        settingsTab.setIndicator("", getResources().getDrawable(R.drawable.btn_settings));
        settingsTab.setContent(new TabContent(getBaseContext()));
        tHost.addTab(settingsTab);
        
        /** Defining tab builder for Apple tab */
        TabHost.TabSpec searchTab = tHost.newTabSpec("Search");
        searchTab.setIndicator("",getResources().getDrawable(R.drawable.btn_search));
        searchTab.setContent(new TabContent(getBaseContext()));
        tHost.addTab(searchTab);
        
        /** Defining tab builder for Apple tab */
        TabHost.TabSpec favoritesTab = tHost.newTabSpec("Faves");
        favoritesTab.setIndicator(null,getResources().getDrawable(R.drawable.btn_favorites));
        favoritesTab.setContent(new TabContent(getBaseContext()));
        tHost.addTab(favoritesTab);
 
    }

}
