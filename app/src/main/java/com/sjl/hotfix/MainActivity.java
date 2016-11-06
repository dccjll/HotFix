package com.sjl.hotfix;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toast(View view) {
        Util.toast(this);
    }

    public void fix(View view) {
        fixBug();
    }

    private String dexDir = "";

    private void fixBug() {
        // 无bug的classes.dex文件存放地址
        String sourceFile = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "lbox/dex/classes3.dex";
        // 系统的私有目录
        String targetFile = getDir("odex", Context.MODE_PRIVATE).getAbsolutePath() + File.separator + "classes3.dex";
        // 通过IO流将dex文件写到我们的缓存目录中去
        InputStream is = null;
        FileOutputStream fos = null;
        // 版权所有，未经许可请勿转载：猴子搬来的救兵http://blog.csdn.net/mynameishuangshuai
        try {
            is = new FileInputStream(sourceFile);
            fos = new FileOutputStream(targetFile);
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            File f = new File(targetFile);
            Log.i("WY", "filePath:" + f.getAbsolutePath());
            if (f.exists()) {
                Toast.makeText(this, "新的dex文件已经覆盖", Toast.LENGTH_LONG).show();
            }
            // 动态加载修复dex包
            FixDexUtils.loadFixDex(this);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
