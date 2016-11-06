package com.sjl.hotfix;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by SJL on 2016/11/6.
 */

public class Util {
    public static void toast(Context context){
        int i=0;
        Toast.makeText(context, "error:"+1/i, Toast.LENGTH_SHORT).show();
    }
}
