package com.fsc.newsnets.main.widget;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.FrameLayout;

import com.fsc.newsnets.R;
import com.fsc.newsnets.main.presenter.MainPresenter;
import com.fsc.newsnets.main.presenter.MainPresenterImpl;
import com.fsc.newsnets.main.view.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.fragment_content)
    FrameLayout fragmentContent;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private ActionBarDrawerToggle mDrawerToggle;
    private MainPresenter IMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        mDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_clos);
        mDrawerToggle.syncState();
        drawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerContent(navigationView);

        //初始化MainPresenter接口(多态)
        IMainPresenter = new MainPresenterImpl(this);

        //给toolbar设置左上角回退箭头
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //toolbar.setOnMenuItemClickListener(this);

    }

    //设置menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //设置DrawerContent内容
    public void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //后续补上，还缺少MainPresenter(已实现)
                IMainPresenter.switchNavigation(item.getItemId());
                item.setCheckable(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }
    /*以下就MainView接口实现*/
    @Override
    public void switchNews() {
        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, new NewsFragment()).commit();
        toolbar.setTitle(R.string.navigation_news);
    }

    @Override
    public void switchImages() {

    }

    @Override
    public void switchWeather() {

    }

    @Override
    public void switchAbout() {

    }

    /*@Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return false;
    }*/
}
