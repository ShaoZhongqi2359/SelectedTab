package com.ucon.libraryview.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

public class NavigationUtils {

    private static final String TAG = NavigationUtils.class.getSimpleName();

    public static final String NAV_SIRIUS_VIDEO = "com.tencent.mm";
    public static final String NAV_SIRIUS_LAUNCHER = "cn.com.broadlink.econtrol.plus";
//    public static final String NAV_SIRIUS_LAUNCHER = "cc.wulian.smarthomev5";
//     public static final String NAV_SIRIUS_LAUNCHER = "cn.com.broadlink.econtrol.plus";


    public static final String NAV_PACKAGE_SIRIUS_FIRST = "com.chronocloud.bodyscale";
    public static final String NAV_PACKAGE_SIRIUS_SECOND = "com.ruiqu.lege";
    public static final String NAV_PACKAGE_SIRIUS_THREE = "com.hezhong.airpal";
    public static final String NAV_PACKAGE_SIRIUS_FOUR = "com.seblong.idream";
    public static final String NAV_PACKAGE_SIRIUS_FIVE = "com.omesoft.blt.bp";


    public static void navigateToExtensionApp(Context context) {
        Log.d(TAG, "navigation to extension app is called");
        PackageManager manager = context.getPackageManager();
        try {
            Intent i = manager.getLaunchIntentForPackage(NAV_SIRIUS_LAUNCHER);
            if (i == null) {
                throw new PackageManager.NameNotFoundException();
            }
            i.addCategory(Intent.CATEGORY_LAUNCHER);
            context.startActivity(i);
            return;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return;
        }
    }

    public static void navigateHome(Context context) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void navigateToVideoApp(Context context) {
        Log.d(TAG, "navigation to extension app is called");
        PackageManager manager = context.getPackageManager();
        try {
            Intent i = manager.getLaunchIntentForPackage(NAV_SIRIUS_VIDEO);
            if (i == null) {
                throw new PackageManager.NameNotFoundException();
            }
            i.addCategory(Intent.CATEGORY_LAUNCHER);
            context.startActivity(i);
            return;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return;
        }
    }

    public static void navigateToPackage(Context context, String packageName) {
        Log.d(TAG, "navigation to engineer mode is called");
        PackageManager manager = context.getPackageManager();
        try {
            Intent i = manager.getLaunchIntentForPackage(packageName);
            if (i == null) {
                throw new PackageManager.NameNotFoundException();
            }
            i.addCategory(Intent.CATEGORY_LAUNCHER);
            context.startActivity(i);
            return;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return;
        }
    }
}
