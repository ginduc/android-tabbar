package com.dynobjx;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import butterknife.InjectView;
import butterknife.Views;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.dynobjx.fragments.HomeFragment;
import com.dynobjx.fragments.TopRatedFragment;

public class MainActivity extends SherlockFragmentActivity implements OnTabChangeListener {
	@InjectView(android.R.id.tabhost) TabHost tabHost;
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Views.inject(this);
        tabHost.setup();
        
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.app_name);
        actionBar.setDisplayShowHomeEnabled(false);

        tabHost.setOnTabChangedListener(this);
 
        addTab("Top", getResources().getDrawable(R.drawable.btn_toprated));
        addTab("Search", getResources().getDrawable(R.drawable.btn_search));
        addTab("Home", getResources().getDrawable(R.drawable.btn_home));
        addTab("Settings", getResources().getDrawable(R.drawable.btn_settings));
        addTab("Faves", getResources().getDrawable(R.drawable.btn_favorites));
        tabHost.setCurrentTab(2);
    }
    
    @Override
    public void onTabChanged(String tabId) {
        FragmentManager fm = getSupportFragmentManager();
        HomeFragment home = (HomeFragment) fm.findFragmentByTag("Home");
        TopRatedFragment top = (TopRatedFragment) fm.findFragmentByTag("Top");
        
        FragmentTransaction ft = fm.beginTransaction();

        /** Detaches the androidfragment if exists */
        if(home!=null)
            ft.detach(home);

        /** Detaches the applefragment if exists */
        if(top!=null)
            ft.detach(top);

        /** If current tab is android */
        if(tabId.equalsIgnoreCase("Home")){

            if(home==null){
                /** Create AndroidFragment and adding to fragmenttransaction */
                ft.add(R.id.realtabcontent,new TopRatedFragment(), "Top");
            }else{
                /** Bring to the front, if already exists in the fragmenttransaction */
                ft.attach(home);
            }

        }else{    /** If current tab is apple */
            if(top==null){
                /** Create AppleFragment and adding to fragmenttransaction */
                ft.add(R.id.realtabcontent,new HomeFragment(), "Home");
             }else{
                /** Bring to the front, if already exists in the fragmenttransaction */
                ft.attach(top);
            }
        }
        ft.commit();
    }
    
    private void addTab(String label, Drawable d) {
    	TabHost.TabSpec tab = tabHost.newTabSpec(label);
    	tab.setIndicator(null, d);
    	tab.setContent(new TabContent(getBaseContext()));
        tabHost.addTab(tab);
    }

}
