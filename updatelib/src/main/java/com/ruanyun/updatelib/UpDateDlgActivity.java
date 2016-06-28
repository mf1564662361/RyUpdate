package com.ruanyun.updatelib;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

/**
 * Description:
 * author: jery on 2016/6/24 9:47.
 */
public class UpDateDlgActivity extends AppCompatActivity implements View.OnClickListener,IConstants {
   private DownLoadInfo downLoadInfo;
   private TextView mTvCancel,mTvUpdate,mTvUpdateContent;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_update);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initView();
    }

    private void initView() {
       mTvCancel= (TextView) findViewById(R.id.tv_cancel);
       mTvUpdate= (TextView) findViewById(R.id.tv_update);
       mTvUpdateContent= (TextView) findViewById(R.id.tv_update_content);
       mTvCancel.setOnClickListener(this);
       mTvUpdate.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int viewId=v.getId();
      if(viewId==R.id.tv_update){
          Intent intent=new Intent(this,DowloadService.class);
          intent.setAction(SERVICE_ACTION);
          intent.putExtra(KEY_DOWN_INFO,downLoadInfo);
          startService(intent);
          finish();
      }else if(viewId==R.id.tv_cancel){
          finish();
      }
    }
}
