package com.example.examination.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.example.examination.R;
import com.example.examination.view.fragment.CarFragment;
import com.example.examination.view.fragment.DongFragment;
import com.example.examination.view.fragment.FirstFragment;
import com.example.examination.view.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private RadioGroup group;
    private List<Fragment> flist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewpager);
        group = findViewById(R.id.group);
        flist = new ArrayList<>() ;
        flist.add(new FirstFragment());
        flist.add(new CarFragment());
        flist.add(new DongFragment());
        flist.add(new MineFragment());

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return flist.get(i);
            }

            @Override
            public int getCount() {
                return flist.size();
            }
        };

        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        group.check(R.id.button1);
                        break;
                    case 1:
                        group.check(R.id.button2);
                        break;
                    case 2:
                        group.check(R.id.button3);
                        break;
                    case 3:
                        group.check(R.id.button4);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.button1:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.button2:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.button3:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.button4:
                        viewPager.setCurrentItem(3);
                        break;
                }
            }
        });
    }
}
