package com.ruanyun.updatelib;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import okhttp3.OkHttpClient;

/**
 * Created by star on 2016/5/28 0028.
 */
public class DowloadService extends Service implements downLoadListener {

    private OkhttpDowloader dowloader;
    private static final int NOTIFICATION_ID = 0x123;
    private Notification notification = null;
    private NotificationManager manager = null;
    private DownLoadInfo downLoadInfo = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //return new DownloadBinder();
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        if (intent != null) {
            downLoadInfo = intent.getParcelableExtra(DownLoadInfo.DOWNLOAD_INFO);
            dowloader = new OkhttpDowloader(downLoadInfo.downloadUrl,
                    new OkHttpClient().newBuilder().build(), downLoadInfo.downloadPath);
            dowloader.setDownloadListener(this);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (dowloader != null) {
            dowloader.startDownload();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDownLoading(float percent) {

    }

    @Override
    public void onDownloadComplete() {
        stopSelf();
    }

    @Override
    public void onError() {
        stopSelf();
    }


  /*  public class DownloadBinder extends Binder{
        DowloadService getService(){
            return DowloadService.this;
        }
    }*/
}
