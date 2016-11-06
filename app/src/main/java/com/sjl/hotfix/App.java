package com.sjl.hotfix;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import java.io.File;

/**
 * Created by SJL on 2016/11/6.
 */

public class App extends MultiDexApplication {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
        String targetFile = getDir("odex", Context.MODE_PRIVATE).getAbsolutePath() + File.separator + "classes3.dex";
        if(new File(targetFile).exists()){
            FixDexUtils.loadFixDex(this);
        }
    }
}
