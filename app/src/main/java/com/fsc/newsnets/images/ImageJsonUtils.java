package com.fsc.newsnets.images;
import com.fsc.newsnets.bean.ImagesBean;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.fsc.newsnets.utils.JsonUtils;
import com.fsc.newsnets.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片json解析工具
 * Created by fsc on 2018/6/17.
 */

public class ImageJsonUtils{
    private static final String TAG = "ImageJsonUtils";

    /**
     * 将获取到的json转换为图片列表对象
     * @param res
     * @return
     */
    public static List<ImagesBean> readJsonImageBeans(String res) {
        List<ImagesBean> beans = new ArrayList<>();
        try {
            JsonParser parser = new JsonParser();
            JsonArray jsonArray = parser.parse(res).getAsJsonArray();
            for (int i = 1 ; i < jsonArray.size() ; i++) {
                JsonObject jo = jsonArray.get(i).getAsJsonObject();
                ImagesBean news = JsonUtils.deserialize(jo,ImagesBean.class);
                beans.add(news);
            }
        } catch (Exception e) {
            LogUtils.e(TAG, "readJsonImageBeans error", e);
        }
        return beans;
    }

}
