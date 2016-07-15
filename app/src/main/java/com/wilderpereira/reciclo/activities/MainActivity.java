package com.wilderpereira.reciclo.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.wilderpereira.reciclo.adapters.ViewPagerAdapter;
import com.wilderpereira.reciclo.fragments.ListFragment;
import com.wilderpereira.reciclo.fragments.StockFragment;
import com.wilderpereira.reciclo.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setupViewPager(final ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new StockFragment(), getString(R.string.stock));
        adapter.addFragment(ListFragment.newInstance(ListFragment.LIST_MODE_DEFAULT, viewPager), getString(R.string.recycle));
        adapter.addFragment(ListFragment.newInstance(ListFragment.LIST_MODE_FAVORITES, viewPager), getString(R.string.favorites));
        viewPager.setAdapter(adapter);
    }
}
