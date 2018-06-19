package com.fsc.newsnets.news.presenter;

import com.fsc.newsnets.bean.NewsBean;
import com.fsc.newsnets.commons.Urls;
import com.fsc.newsnets.news.model.NewsModel;
import com.fsc.newsnets.news.model.NewsModelImpl;
import com.fsc.newsnets.news.model.OnLoadNewsListListener;
import com.fsc.newsnets.news.view.NewsView;
import com.fsc.newsnets.news.widget.NewsFragment;
import com.fsc.newsnets.utils.LogUtils;

import java.util.List;

/**news 列表presenter接口实现类
 * Created by fsc on 2018/6/17.
 */

public class NewsPresenterImpl implements NewsPresenter,OnLoadNewsListListener {
    private static final String TAG = "NewsPresenterImpl";
    private NewsView mNewsView;
    private NewsModel mNewsModel;


    public NewsPresenterImpl(NewsView newsView){
        this.mNewsView = newsView;
        this.mNewsModel = new NewsModelImpl();
    }
    @Override
    public void loadNews(final int type,final int pageIndex){
        String url = getUrl(type, pageIndex);
        LogUtils.d(TAG, url);
        //只有第一页的或者刷新的时候才显示刷新进度条
        if(pageIndex == 0){
            mNewsView.showProgress();
        }
        mNewsModel.loadNews(url,type,this);
    }

    /**
     * 根据类别和页面索引创建url
     * @param type
     * @param pageIndex
     * @return
     */
    private String getUrl(int type,int pageIndex){
        StringBuffer sb = new StringBuffer();
        switch (type) {
            case NewsFragment.NEWS_TYPE_TOP:
                sb.append(Urls.TOP_URL).append(Urls.TOP_URL);
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                sb.append(Urls.COMMON_URL).append(Urls.NBA_ID);
                break;
            case NewsFragment.NEWS_TYPE_CARS:
                sb.append(Urls.COMMON_URL).append(Urls.CAR_ID);
                break;
            case NewsFragment.NEWS_TYPE_JOKES:
                sb.append(Urls.COMMON_URL).append(Urls.JOKE_ID);
                break;
            default:
                sb.append(Urls.TOP_URL).append(Urls.TOP_URL);
                break;
        }
        sb.append("/").append(pageIndex).append(Urls.END_URL);
        return sb.toString();
    }
    @Override
    public void onSuccess(List<NewsBean> list){
        mNewsView.hideProgress();
        mNewsView.addNews(list);
    }
    @Override
    public void onFailure(String msg,Exception e){
        mNewsView.hideProgress();
        mNewsView.showLoadFailMsg();
    }
}
