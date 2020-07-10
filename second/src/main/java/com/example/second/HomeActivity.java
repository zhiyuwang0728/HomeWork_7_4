package com.example.second;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.adapter.HomeAdapter;
import com.example.fragment.HomeFragment;
import com.example.fragment.MeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.mTitle)
    TextView mTitle;
    @BindView(R.id.mTool)
    Toolbar mTool;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    @BindView(R.id.mTab)
    TabLayout mTab;
    private HomeAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        setSupportActionBar(mTool);
        mTool.setTitle("");

        List<Fragment> data=new ArrayList<>();
        data.add(new HomeFragment());
        data.add(new MeFragment());

        homeAdapter = new HomeAdapter(getSupportFragmentManager(), data);
        mViewPager.setAdapter(homeAdapter);

        mTab.setupWithViewPager(mViewPager);

        mTab.getTabAt(0).setText("首页");
        mTab.getTabAt(1).setText("收藏");

        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position==0){
                    mTitle.setText("首页");
                }else{
                    mTitle.setText("收藏");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
