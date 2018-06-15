package com.fsc.newsnets.news.model;

import com.fsc.newsnets.bean.NewsBean;

import java.util.List;

/**
 * 新闻加载回调
 */
public interface OnLoadNewsListListener {
    //加载成功
    void onSuccess(List<NewsBean> list);

    //提示加载失败
    void onFailure(String msg,Exception e);
}
