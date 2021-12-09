package com.example.lullaby.videos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.lullaby.R;
import com.example.lullaby.data.AccountData;
import com.example.lullaby.data.GlobalVariable;

public class AsmrPagerActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 2;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager2 viewPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private FragmentStateAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asmr_pager);

        // Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = findViewById(R.id.pager);
        pagerAdapter = new AsmrPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        String category1 = new String();
        String category2 = new String();
        category1 = AccountData.getInstance().profiles.get(AccountData.getInstance().getUserSelected()).getCategory1();
        category2 = AccountData.getInstance().profiles.get(AccountData.getInstance().getUserSelected()).getCategory2();
        if(category1.contains("재즈")||category1.contains("클래식")){
            category1 = "기본";
        }
        if(category2.contains("재즈")||category2.contains("클래식")){
            category2 = "기본";
        }

        if(category1.equals("기본")&& category2.equals("기본"))
            category2 = "";

        TextView asmrCate = findViewById(R.id.asmr_category);
        asmrCate.setText("Category | " + category1 + " " + category2);
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class AsmrPagerAdapter extends FragmentStateAdapter {
        public AsmrPagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            switch (position){
                case 0 :
                    return new AsmrFragment1();
                default :
                    return new AsmrFragment2();
            }
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }
}

