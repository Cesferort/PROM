package com.example.ejpestanasyactionbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.ejpestanasyactionbar.R;
import com.google.android.material.tabs.TabLayout;

public class PestanasViewPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pestanas_view_page);

        ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
        viewPager.setAdapter(new PageAdapter());

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(android.R.drawable.ic_dialog_info);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_android);
    }

    class PageAdapter extends PagerAdapter {
        private LinearLayout pestana1;
        private LinearLayout pestana2;
        private LinearLayout pestana3;
        private final int[] pestanas ={R.string.tab1, R.string.tab2, R.string.tab3 };


        @Override
        public int getCount() {
            return 3;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            String s = getString(pestanas[position]);
            return s;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View page;
            switch (position){
                case 0:
                    if (pestana1 == null){
                        pestana1 = (LinearLayout) LayoutInflater.from(PestanasViewPageActivity.this).inflate(R.layout.pestana1, container,false );
                    }
                    page= pestana1;
                    break;
                case 1:
                    if (pestana2 == null) {
                        pestana2 = (LinearLayout)LayoutInflater.from(PestanasViewPageActivity.this).inflate(R.layout.pestana2,container,false);
                    }
                    page=pestana2;
                    break;
                default:
                    if (pestana3 == null) {
                        pestana3 = (LinearLayout)LayoutInflater.from(PestanasViewPageActivity.this).inflate(R.layout.pestana3,container,false);
                    }
                    page=pestana3;
                    break;
            }
            container.addView(page, 0);
            return page;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View)object);
        }
    }
}