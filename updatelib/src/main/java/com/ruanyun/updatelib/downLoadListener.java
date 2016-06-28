package com.ruanyun.updatelib;

/**
 * Created by star on 2016/5/28 0028.
 */
public interface DownLoadListener {

    void onStart();
    void onDownLoading(float percent);
    void onDownloadComplete();
    void onError();
}
