package com.ruanyun.updatelib;




import okhttp3.OkHttpClient;

/**
 * Created by star on 2016/5/28 0028.
 */
public class RyUpdateHelper  {
   private static OkHttpClient okHttpClient;

    public static void init(OkHttpClient client){
        okHttpClient=client;
    }

    public static OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public static void destroy(){
        okHttpClient=null;
    }
}
