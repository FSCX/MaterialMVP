package com.fsc.newsnets.news.presenter;
import android.content.Context;

import com.fsc.newsnets.bean.NewsDetailbean;
import com.fsc.newsnets.news.model.NewsModel;
import com.fsc.newsnets.news.model.NewsModelImpl;
import com.fsc.newsnets.news.model.OnLoadNewsDetailListener;
import com.fsc.newsnets.news.view.NewsDetailView;

/**news 详情presenter接口
 * Created by fsc on 2018/6/17.
 */

public class NewsDetailPresenterImpl implements NewsDetailPresenter,OnLoadNewsDetailListener {
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
    public void onSuccess(NewsDetailbean newsDetailBean) {
        if (newsDetailBean != null) {
            mNewsDetailView.showNewsDetailContent(newsDetailBean.getBody());
        }
        mNewsDetailView.hideProgress();
    }
    @Override
        public void onFailure(String msg,Exception e) {
        mNewsDetailView.hideProgress();
    }
}
