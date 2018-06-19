package com.fsc.newsnets.news.widget;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.fsc.newsnets.R;
import com.fsc.newsnets.bean.NewsBean;
import com.fsc.newsnets.news.presenter.NewsDetailPresenter;
import com.fsc.newsnets.news.presenter.NewsDetailPresenterImpl;
import com.fsc.newsnets.news.view.NewsDetailView;
import com.fsc.newsnets.utils.ImageLoaderUtils;
import com.fsc.newsnets.utils.ToolUtil;
import com.topwise.mylibrary.SwipeBackLayout;
import com.topwise.mylibrary.app.SwipeBackActivity;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 新闻详情界面
 */
public class NewsDetailActivity extends SwipeBackActivity implements NewsDetailView, View.OnClickListener {
    @BindView(R.id.ivImage)
    ImageView mImageView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.progress)
    ProgressBar mProgressBar;
    @BindView(R.id.htNewsContent)
    HtmlTextView mHtmNewsContent;

    private NewsBean mNewsBean;
    private SwipeBackLayout mSwipeBackLayout;
    private NewsDetailPresenter mNewsDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(this);

        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeSize(ToolUtil.getWidthInPx(this));
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);

        mNewsBean = (NewsBean) getIntent().getSerializableExtra("news");

        mCollapsingToolbarLayout.setTitle(mNewsBean.getTitle());
        ImageLoaderUtils.display(getApplicationContext(),(ImageView)findViewById(R.id.ivImage),mNewsBean.getImgsrc());

        mNewsDetailPresenter = new NewsDetailPresenterImpl(getApplication(), this);
        mNewsDetailPresenter.loadNewsDetail(mNewsBean.getDocId());
    }
    @Override
    public void onClick(View v) {
        onBackPressed();
    }

    @Override
    public void showNewsDetailContent(String newsDetailContent) {
        mHtmNewsContent.setHtmlFromString(newsDetailContent,new HtmlTextView.LocalImageGetter());
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }
}
