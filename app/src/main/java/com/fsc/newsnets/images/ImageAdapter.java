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
import fsc.newsnets.beans.ImageBean;
import fsc.newsnets.utils.ImageLoaderUtils;
import fsc.newsnets.utils.ToolUtil;
/**
 * 图片的适配器
 * Created by fsc on 2018/6/17.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ItemViewHolder> {
    private List<ImageBean> mData;
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
    public void setmData(List<ImageBean> data) {
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
        ImageBean imageBean = mData.get(position);
        if (imageBean == null) {
            return;
        }
        holder.mTitle.setText(imageBean.getTitle);
        float scale = (float) imageBean.getWidth() / (float) mMaxWidth;
        int height = (int)(imageBean.getHeight() / scale);
        if(height > mMaxHeight){
            height = mMaxHeight;
        }
        holder.mTmage.setLayoutParams(new LinearLayout.LayoutParams(mMaxWidth, height));
        ImageLoaderUtils.display(mContext,holder.mImage,imageBean.getThumburl());
    }
    @Override
    public int getItemCount() {
        if(mData == null){
            return 0;
        }
        return mData.size();
    }
    public ImageBean getItem(int position) {
        return mData = null ? null : mData.get(position);
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
            mImage = (ImageView) v.findViewById(R.id.tvImage);
            v.setOnClickListener(this);
        }
        @Override
        public void OnClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view,this.getPosition());
            }
        }
    }
}
