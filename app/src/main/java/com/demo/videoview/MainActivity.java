package com.demo.videoview;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;
import java.util.List;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    private int REQUEST_READ_EXTERNAL_STORAGE = 123;
    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getReadPermission();//获取读取权限
        initViews();
    }

    private void initViews() {
       mVideoView = (VideoView)findViewById(R.id.videoview);

        MediaController controller = new MediaController(this);  //控制器
        mVideoView.setVideoPath("http://flashmedia.eastday.com/newdate/news/2016-11/shznews1125-19.mp4");//网络url,在线播放
        controller.setMediaPlayer(mVideoView);
        mVideoView.setMediaController(controller);

    }

    public void getReadPermission(){
        if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)){

        }else {
            EasyPermissions.requestPermissions(this,"需要读取权限",REQUEST_READ_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this,perms)){
                new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);

    }
}
