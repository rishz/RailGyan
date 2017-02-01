package com.rishabhshukla.rubio;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    private ViewPager mPager;
    public static final int NUM_PAGES=3;
    int[] layouts;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sPrefs = getSharedPreferences("mypref",MODE_PRIVATE);
        String username = sPrefs.getString("username","");
        String pass = sPrefs.getString("pass","");
        if(username==null||pass==null){
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }

        //setSupportActionBar(toolbar);
        layouts = new int[]{
                R.layout.fragment_slide1,
                R.layout.fragment_slide2,
                R.layout.fragment_slide3,
        };
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setClipToPadding(false);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
       // mPager.addOnPageChangeListener(viewPagerPageChangeListener);




    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        private LayoutInflater layoutInflater;
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:{
                    return new Slide1Fragment();
                }
                case 1:{
                    return new Slide2Fragment();
                }
                case 2:{
                    return new Slide3Fragment();
                }
                default:{
                    return new Slide1Fragment();
                }
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public float getPageWidth(int position) {
            return 1.0f;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
