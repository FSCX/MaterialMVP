package com.fsc.newsnets.news.model;

/**
 * 新闻列表和新闻详情加载
 */
public interface NewsModel {
    //新闻列表加载
    void loadNews(String url,int type,OnLoadNewsListListener listener);

    //新闻详情加载
    void loadNewsDetail(String docId, OnLoadNewsDetailListener listener);
}
