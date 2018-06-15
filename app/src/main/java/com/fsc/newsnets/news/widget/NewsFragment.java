package com.fsc.newsnets.news.widget;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fsc.newsnets.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 新闻列表
 */
public class NewsFragment extends Fragment {
    private static final int NEWS_TYPE_TOP = 0;
    private static final int NEWS_TYPE_NBA = 1;
    private static final int NEWS_TYPE_CARS = 2;
    private static final int NEWS_TYPE_JOKES = 3;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, null);
        unbinder = ButterKnife.bind(this, view);

        viewpager.setOffscreenPageLimit(3);//最小是3屏
        setupViewPager(viewpager);

        tabLayout.addTab(tabLayout.newTab().setText(R.string.top));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.nba));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.cars));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.jokes));
        tabLayout.setupWithViewPager(viewpager);
        return view;
    }

    public void setupViewPager(ViewPager upViewPager) {
        NewsPagerAdapter adapter = new NewsPagerAdapter(getChildFragmentManager());
        //带完善，NewsListFragment类写完来补充
        //adapter.addFragment(NewsListFragment);
        viewpager.setAdapter(adapter);
    }

    public static class NewsPagerAdapter extends FragmentPagerAdapter{
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();


        public NewsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        private void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
