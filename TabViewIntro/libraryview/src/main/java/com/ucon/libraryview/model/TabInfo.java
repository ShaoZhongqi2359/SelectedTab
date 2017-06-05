package com.ucon.libraryview.model;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import java.io.Serializable;

/**
 * Created by Zhongqi.Shao on 2017/6/2.
 */
public class TabInfo implements Serializable {

    public int titleRes;

    /**
     * Title of the tile that is shown to the user.
     *
     * @attr ref android.R.styleable#PreferenceHeader_title
     */
    public CharSequence title;

    public CharSequence clickTag;

    /**
     * Resource ID of optional summary describing what this tile controls.
     *
     * @attr ref android.R.styleable#PreferenceHeader_summary
     */
    public int summaryRes;

    /**
     * Optional summary describing what this tile controls.
     *
     * @attr ref android.R.styleable#PreferenceHeader_summary
     */
    public CharSequence summary;

    /**
     * Optional icon resource to show for this tile.
     *
     * @attr ref android.R.styleable#PreferenceHeader_icon
     */
    public Drawable iconRes;

    public Drawable iconSelectedRes;

    public boolean isSelectedTab;

    /**
     * Full class name of the fragment to display when this tile is
     * selected.
     *
     * @attr ref android.R.styleable#PreferenceHeader_fragment
     */
    public String fragment;

    /**
     * Optional arguments to supply to the fragment when it is
     * instantiated.
     */
    public Bundle fragmentArguments;

    /**
     * Intent to launch when the preference is selected.
     */
    public Intent intent;

    /**
     * Optional additional data for use by subclasses of the activity
     */
    public Bundle extras;

    public TabInfo() {
        // Empty
    }


}
