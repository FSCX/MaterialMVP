package com.fsc.newsnets.news.model;

import com.fsc.newsnets.bean.NewsBean;
import com.fsc.newsnets.news.NewsJsonUtils;
import com.fsc.newsnets.utils.OkHttpUtils;

import java.util.List;

/**
 * 新闻model的具体实现类
 */
public class NewsModelImpl implements NewsModel{

    //加载新闻列表
    @Override
    public void loadNews(String url, final int type, final OnLoadNewsListListener listener) {
        OkHttpUtils.ResultCallback<String> loadNewsCallback= new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                //此处待完善(已处理)
                List<NewsBean> newsBeanList = NewsJsonUtils.readJsonNewsBeans(response, getID(type));
                listener.onSuccess(newsBeanList);
            }

            @Override
            public void onFailure(Exception e) {
                listener.onFailure("load news list failure",e);
            }
        };
        OkHttpUtils.get(url,loadNewsCallback);
    }



    //加载新闻详情
    @Override
    public void loadNewsDetail(String docId, OnLoadNewsDetailListener listener) {


    }

    private String getID(int type) {
        String id;
        switch (type) {
            //case NewsFragment.
        }
        return "";
    }
}