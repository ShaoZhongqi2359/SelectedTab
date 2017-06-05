package com.ucon.libraryview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ucon.libraryview.R;
import com.ucon.libraryview.model.TabInfo;

import java.util.List;

/**
 * Created by Zhongqi.Shao on 2017/6/2.
 */
public class TabAdapter extends BaseAdapter {

    private Context mContext;

    private List<TabInfo> mListTabInfo;

    public TabAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<TabInfo> listTabInfo) {
        mListTabInfo = listTabInfo;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mListTabInfo == null ? 0 : mListTabInfo.size();
    }

    @Override
    public Object getItem(int position) {
        return mListTabInfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TabInfo itemInfo = mListTabInfo.get(position);
        convertView = LayoutInflater.from(mContext).inflate(R.layout.bg_tab_layout, null);
        ImageView tabImageview = (ImageView) convertView.findViewById(R.id.iv_tab);
        TextView tabInfo = (TextView) convertView.findViewById(R.id.tv_tab);
        if (itemInfo.isSelectedTab) {
            tabImageview.setImageDrawable(itemInfo.iconSelectedRes);
            tabInfo.setTextColor(mContext.getResources().getColor(android.R.color.white));
        } else {
            tabInfo.setTextColor(mContext.getResources().getColor(R.color.nav_normal));
            tabImageview.setImageDrawable(itemInfo.iconRes);
        }
        tabInfo.setText(itemInfo.title);
        convertView.setTag(itemInfo.clickTag);
        return convertView;
    }

}
