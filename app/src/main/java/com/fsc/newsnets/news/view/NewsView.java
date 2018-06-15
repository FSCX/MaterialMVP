package com.fsc.newsnets.news.view;

import com.fsc.newsnets.bean.NewsBean;

import java.util.List;

/**
 * 新闻列表的接口
 */
public interface NewsView {
    void showProgress();//显示进度条

    void addNews(List<NewsBean> newsList);//显示新闻

    void hideProgress();//隐藏进度条

    void showLoadFailMsg();//加载失败消息
}
