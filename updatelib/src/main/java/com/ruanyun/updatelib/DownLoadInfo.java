package com.ruanyun.updatelib;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by star on 2016/6/1 0001.
 */
public class DownLoadInfo implements Parcelable {
  public static final String DOWNLOAD_INFO="download_info";

  public String downloadUrl;
  public String downloadPath;
  public String fileName;

  public DownLoadInfo(String downloadUrl, String downloadPath, String fileName) {
    this.downloadUrl = downloadUrl;
    this.downloadPath = downloadPath;
    this.fileName = fileName;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.downloadUrl);
    dest.writeString(this.downloadPath);
    dest.writeString(this.fileName);
  }

  protected DownLoadInfo(Parcel in) {
    this.downloadUrl = in.readString();
    this.downloadPath = in.readString();
    this.fileName = in.readString();
  }

  public static final Parcelable.Creator<DownLoadInfo> CREATOR = new Parcelable.Creator<DownLoadInfo>() {
    @Override
    public DownLoadInfo createFromParcel(Parcel source) {
      return new DownLoadInfo(source);
    }

    @Override
    public DownLoadInfo[] newArray(int size) {
      return new DownLoadInfo[size];
    }
  };
}
