package com.ruanyun.updatelib;




import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Okio;

/**
 * Created by star on 2016/5/28 0028.
 */
public class OkhttpDowloader implements Runnable{
   private OkHttpClient okHttpClient;
   private String downLoadUrl="";
   private String donwLoadPath="";
   private downLoadListener downloadListener ;
   private File downFile;
    private long toolSize;
    private long completedSize;
    @Override
    public void run() {
        Request request=new Request.Builder()
                .url(downLoadUrl)
                .build();
        InputStream inputStream = null;
       // BufferedInputStream bis = null;
        BufferedSink bsk=null;
        try {
            Response response=okHttpClient.newCall(request).execute();
            ResponseBody responseBody=response.body();
            if(responseBody!=null){
                inputStream = responseBody.byteStream();
               // bis = new BufferedInputStream(inputStream);
                bsk=Okio.buffer(Okio.sink(downFile));

                byte[] buffer = new byte[2 * 1024];
                int length = 0;
               // int buffOffset = 0;
              while ((length=inputStream.read())>0){
                 bsk.write(buffer,0,length);
                  completedSize+=length;
                  downloadListener.onDownLoading(getPercent());
              }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream!=null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if(bsk!=null)
                try {
                    bsk.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
    }

    public void startDownload(){
        new Thread(this).start();
    }

    public float getPercent() {
        return completedSize * 100 / toolSize;
    }
}
