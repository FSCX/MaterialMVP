package com.fsc.newsnets.news;

import com.fsc.newsnets.bean.NewsBean;
import com.fsc.newsnets.bean.NewsDetailbean;
import com.fsc.newsnets.utils.JsonUtils;
import com.fsc.newsnets.utils.LogUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * 新闻json解析工具类
 */
public class NewsJsonUtils {
    private static final String TAG = "NewsJsonUtils";

    /**
     * 将获取到的json转换为新闻列表对象
     */
    public static List<NewsBean> readJsonNewsBeans(String res,String value) {
        List<NewsBean> beans = new ArrayList<>();
        try {
            JsonParser parser = new JsonParser();
            JsonObject jsonObj = parser.parse(res).getAsJsonObject();
            JsonElement jsonElement = jsonObj.get(value);
            if (jsonElement == null) {
                return null;
            }
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (int i = 1 ; i < jsonArray.size() ; i++) {
                JsonObject jo = jsonArray.get(i).getAsJsonObject();
                if(jo.has("skipType") && "special".equals(jo.get("skipType").getAsString())){
                    continue;
                }
                if (jo.has("TAGS") && !jo.has("TAG")) {
                    continue;
                }
                if (!jo.has("imgextra")) {
                    NewsBean news = JsonUtils.deserialize(jo, NewsBean.class);
                    beans.add(news);
                }
            }
        } catch (Exception e) {
            LogUtils.e(TAG,"readJsonNewsBeans Error",e);
        }
        return beans;
    }
    /**
     * 获取新闻详情content
     */
    public static NewsDetailbean readJsonNewsDetailBeans(String res,String docId) {
        NewsDetailbean newsDetailbean = null;
        try {
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(res).getAsJsonObject();
            JsonElement jsonElement = jsonObject.get(docId);
            if(jsonElement == null){
                return null;
            }
            newsDetailbean = JsonUtils.deserialize(jsonElement.getAsJsonObject(), NewsDetailbean.class);
        } catch (Exception e) {
            LogUtils.e(TAG,"readJsonNewsDetailBeans error",e);
        }
        return newsDetailbean;
    }
}
