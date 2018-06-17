package com.fsc.newsnets.news.presenter;
import android.content.Context;

import com.lauren.simplenews.beans.NewsDetailBean;
import com.lauren.simplenews.news.model.NewsModel;
import com.lauren.simplenews.news.model.NewsModelImpl;
import com.lauren.simplenews.news.model.OnLoadNewsDetailListener;
import com.lauren.simplenews.news.view.NewsDetailView;

/**news 详情presenter接口
 * Created by fsc on 2018/6/17.
 */

public class NewsDetailPresenterImpl implements NewsDetailPresenter,OnLoadNewsDetailListener{
    private static final String TAG = "NewsDetailPresenterImpl";
    private Context mContext;
    private NewsModel mNewsModel;
    private NewsDetailView mNewsDetailView;

    public NewsDetailPresenterImpl(Context context,NewsDetailView newsDetailView) {
        this.mContext = context;
        this.mNewsDetailView = newsDetailView;
        this.mNewsModel = new NewsModelImpl();
    }

    @Override
    public void loadNewsDetail(final String docId) {
        mNewsDetailView.showProgress();
        mNewsModel.loadNewsDetail(docId,this);
    }
    @Override
    public void onSuccess(NewsDetailBean newsDetailBean) {
        if (newsDetailBean != null) {
            mNewsDetailView.showNewsDetailContent(newsDetailBean.getBody());
        }
        mNewsDetailView.hideProgress();
    }
    @Override void onFailure(String msg,Exception e) {
        mNewsDetailView.hideProgress();
    }
}
