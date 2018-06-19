package com.fsc.newsnets.images;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fsc.newsnets.R;
import com.fsc.newsnets.bean.ImagesBean;
import com.fsc.newsnets.utils.ImageLoaderUtils;
import com.fsc.newsnets.utils.ToolUtil;

import java.util.List;

/**
 * 图片的适配器
 * Created by fsc on 2018/6/17.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ItemViewHolder> {
    private List<ImagesBean> mData;
    private Context mContext;
    private int mMaxWidth;
    private int mMaxHeight;

    private OnItemClickListener mOnItemClickListener;

    public ImageAdapter(Context context) {
        this.mContext = context;
        mMaxWidth = ToolUtil.getWidthInPx(mContext) - 20;
        mMaxHeight = ToolUtil.getHeightInPx(mContext) - ToolUtil.getStatusHeight(mContext)
                - ToolUtil.dip2px(mContext,96);
    }
    public void setmData(List<ImagesBean> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }

    @Override
    public ImageAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        ItemViewHolder vh = new ItemViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ImageAdapter.ItemViewHolder holder, int position) {
        ImagesBean imagesBean = mData.get(position);
        if (imagesBean == null) {
            return;
        }
        holder.mTitle.setText(imagesBean.getTitle());
        float scale = (float) imagesBean.getWidth() / (float) mMaxWidth;
        int height = (int)(imagesBean.getHeight() / scale);
        if(height > mMaxHeight){
            height = mMaxHeight;
        }
        holder.mImage.setLayoutParams(new LinearLayout.LayoutParams(mMaxWidth, height));
        ImageLoaderUtils.display(mContext,holder.mImage,imagesBean.getThumburl());
    }
    @Override
    public int getItemCount() {
        if(mData == null){
            return 0;
        }
        return mData.size();
    }
    public ImagesBean getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    public void setmOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    //点击的回调接口
    public interface  OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTitle;
        public ImageView mImage;

        public ItemViewHolder(View v){
            super(v);
            mTitle = (TextView) v.findViewById(R.id.tvTitle);
            mImage = (ImageView) v.findViewById(R.id.ivImage);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v,this.getPosition());
            }
        }
    }
}
