package com.ucon.libraryview.tabselectview;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.ucon.libraryview.R;
import com.ucon.libraryview.model.TabInfo;
import com.ucon.libraryview.util.XmlUtils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhongqi.Shao on 2017/6/2.
 */
public class TabView extends LinearLayout implements View.OnClickListener {

    private List<View> mViewList = new ArrayList<View>();
    private List<TabInfo> mTargetInfoList;
    private int mBgColor;

    private BaseAdapter mAdapter;

    public TabView(Context context) {
        super(context);
        setOrientation(HORIZONTAL);
    }

    public TabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
        TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.TabSelectView);
        mBgColor = typeArray.getColor(R.styleable.TabSelectView_tab_back, 0);
        this.setBackgroundColor(mBgColor);
        typeArray.recycle();
    }

    public void setAdapter(BaseAdapter baseAdapter) {
        mViewList.clear();
        this.removeAllViews();
        if (mAdapter != null) {

            mAdapter.unregisterDataSetObserver(mObserVable);
        }
        mAdapter = baseAdapter;
        mAdapter.registerDataSetObserver(mObserVable);
        bindListView();
    }

    private void bindListView() {
        if (mAdapter == null) {
            return;
        }
        int count = mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            View itemView = mAdapter.getView(i, null, null);
            itemView.setOnClickListener(this);
            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
            layoutParams.gravity = Gravity.CENTER;
            itemView.setLayoutParams(layoutParams);
            this.addView(itemView);
        }
    }


    @Override
    public void onClick(View itemView) {
        String clickTag = (String) itemView.getTag();
        if (mTargetInfoList != null && mTargetInfoList.size() > 0 && !TextUtils.isEmpty(clickTag)) {
            for (TabInfo tag : mTargetInfoList) {
                if (tag.clickTag.equals(clickTag)) {
                    getContext().startActivity(tag.intent);
                }
            }
        }
    }

    private DataSetObserver mObserVable = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            mViewList.clear();
            bindListView();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
            mViewList.clear();
            bindListView();
        }
    };


    public void loadCategoriesFromResource(int resid, List<TabInfo> target) {
        mTargetInfoList = target;
        XmlResourceParser parser = null;
        try {
            parser = getResources().getXml(resid);
            AttributeSet attrs = Xml.asAttributeSet(parser);
            int type;
            while ((type = parser.next()) != XmlPullParser.END_DOCUMENT
                    && type != XmlPullParser.START_TAG) {
            }

            String nodeName = parser.getName();
            if (!"dashboard-categories".equals(nodeName)) {
                throw new RuntimeException(
                        "XML document must start with <preference-categories> tag; found"
                                + nodeName + " at " + parser.getPositionDescription());
            }

            Bundle curBundle = null;

            final int outerDepth = parser.getDepth();
            while ((type = parser.next()) != XmlPullParser.END_DOCUMENT
                    && (type != XmlPullParser.END_TAG || parser.getDepth() > outerDepth)) {
                if (type == XmlPullParser.END_TAG || type == XmlPullParser.TEXT) {
                    continue;
                }

                nodeName = parser.getName();
                if ("dashboard-category".equals(nodeName)) {

                    TypedArray sa = getContext().obtainStyledAttributes(
                            attrs, R.styleable.SiriusPreferenceHeader);

                    TypedValue tv = sa.peekValue(R.styleable.SiriusPreferenceHeader_title);
                    if (tv != null && tv.type == TypedValue.TYPE_STRING) {

                    }
                    sa.recycle();

                    final int innerDepth = parser.getDepth();
                    while ((type = parser.next()) != XmlPullParser.END_DOCUMENT
                            && (type != XmlPullParser.END_TAG || parser.getDepth() > innerDepth)) {
                        if (type == XmlPullParser.END_TAG || type == XmlPullParser.TEXT) {
                            continue;
                        }

                        String innerNodeName = parser.getName();
                        if (innerNodeName.equals("dashboard-tile")) {
                            TabInfo tile = new TabInfo();
                            Log.d("shao", "in tag");
                            sa = getContext().obtainStyledAttributes(
                                    attrs, R.styleable.SiriusPreferenceHeader);
                            tv = sa.peekValue(R.styleable.SiriusPreferenceHeader_title);
                            if (tv != null && tv.type == TypedValue.TYPE_STRING) {
                                tile.title = tv.string;
                            }

                            tv = sa.peekValue(R.styleable.SiriusPreferenceHeader_clickTag);
                            if (tv != null && tv.type == TypedValue.TYPE_STRING) {
                                tile.clickTag = tv.string;
                            }
                            tile.isSelectedTab = sa.getBoolean(R.styleable.SiriusPreferenceHeader_isSelectedTab, false);
                            tile.iconRes = sa.getDrawable(R.styleable.SiriusPreferenceHeader_icon);
                            tile.iconSelectedRes = sa.getDrawable(R.styleable.SiriusPreferenceHeader_selectIcon);
                            tile.fragment = sa.getString(R.styleable.SiriusPreferenceHeader_fragment);
                            sa.recycle();

                            if (curBundle == null) {
                                curBundle = new Bundle();
                            }

                            final int innerDepth2 = parser.getDepth();
                            while ((type = parser.next()) != XmlPullParser.END_DOCUMENT
                                    && (type != XmlPullParser.END_TAG || parser.getDepth() > innerDepth2)) {
                                if (type == XmlPullParser.END_TAG || type == XmlPullParser.TEXT) {
                                    continue;
                                }

                                String innerNodeName2 = parser.getName();
                                if (innerNodeName2.equals("extra")) {
                                    getResources().parseBundleExtra("extra", attrs, curBundle);
                                    XmlUtils.skipCurrentTag(parser);

                                } else if (innerNodeName2.equals("intent")) {
                                    tile.intent = Intent.parseIntent(getResources(), parser, attrs);

                                } else {
                                    XmlUtils.skipCurrentTag(parser);
                                }
                            }

                            if (curBundle.size() > 0) {
                                tile.fragmentArguments = curBundle;
                                curBundle = null;
                            }
                            target.add(tile);
                        } else {
                            XmlUtils.skipCurrentTag(parser);
                        }
                    }
                } else {
                    XmlUtils.skipCurrentTag(parser);
                }
            }

        } catch (XmlPullParserException e) {
            throw new RuntimeException("Error parsing categories", e);
        } catch (IOException e) {
            throw new RuntimeException("Error parsing categories", e);
        } finally {
            if (parser != null) parser.close();
        }
    }

}
