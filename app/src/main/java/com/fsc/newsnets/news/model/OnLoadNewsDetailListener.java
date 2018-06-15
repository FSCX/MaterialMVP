package com.fsc.newsnets.news.model;

import com.fsc.newsnets.bean.NewsDetailbean;

/**
 * 新闻详情加载回调
 */
public interface OnLoadNewsDetailListener {
    //新闻详情加载成功
    void onSuccess(NewsDetailbean newsDetailbean);

    //提示新闻详情加载失败
    void onFailure(String msg,Exception e);
}
