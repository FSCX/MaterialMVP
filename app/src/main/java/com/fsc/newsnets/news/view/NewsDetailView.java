package com.fsc.newsnets.news.view;

/**
 * 新闻详情view接口
 */
public interface NewsDetailView {
    //显示新闻详情内容
    void showNewsDetailContent(String newsDetailContent);

    //显示进度条
    void showProgress();

    //隐藏进度条
    void hideProgress();
}
