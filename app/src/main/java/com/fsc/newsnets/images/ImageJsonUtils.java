package com.fsc.newsnets.images;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.fsc.newsnets.beans.ImageBean;
import com.fsc.newsnets.utils.JsonUtils;
import com.fsc.newsnets.utils.LogUtils;
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
    public static List<ImageBean> readJsonImageBeans(String res) {
        List<ImageBean> beans = new ArrayList<>();
        try {
            JsonParser parser = new JsonParser();
            JsonArray jsonArray = parser.parse(res).getAsJsonArray();
            for (int i = 1 ; i < jsonArray.size() ; i++) {
                JsonObject jo = jsonArray.get(i).getAsJsonObject();
                ImageBean news = JsonUtils.deseralize(jo,ImageBean.class);
                beans.add(news);
            }
        } catch (Exception exception) {
            LogUtils.e(TAG, "readJsonImageBeans error", e);
        }
        return beans;
    }

}
