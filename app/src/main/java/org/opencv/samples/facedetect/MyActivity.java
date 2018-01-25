package org.opencv.samples.facedetect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import static android.content.ContentValues.TAG;

/**
 * Created by wephone on 18-1-25.
 * Mat类
 * 保存图像像素信息的是数据结构Mat阵，它包含两部分：矩阵头和一个指向像素数据的矩阵针
 * 矩阵头主要包含：矩阵尺寸、存储方式、存储地址、引用计数等
 * 矩阵头的大小是一定的，不会随着图像的大小改变而改变
 * 通常图像像素数据都会很大，因此，在图像的复制和传递过程中不需要复制整个Mat数据，只复制矩阵头和指向像素矩阵的指针即可
 */

public class MyActivity extends Activity implements CameraBridgeViewBase.CvCameraViewListener2 {

    private Button deal;

    //OpenCV的相机接口
    private CameraBridgeViewBase mCVCamera;
    //缓存相机每帧输入的数据
    private Mat mRgba;
    //当前处理状态
    private static int Cur_State = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_activity);
        deal = (Button) findViewById(R.id.deal_btn);
        mCVCamera = (CameraBridgeViewBase) findViewById(R.id.camera_view);
        //CAMERA_ID_FRONT前置摄像头 CAMERA_ID_BACK后置摄像头
        mCVCamera.setCameraIndex(CameraBridgeViewBase.CAMERA_ID_FRONT);
        deal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(Cur_State<1){
                    //切换状态
                    Cur_State ++;
                }else{
                    //恢复初始状态
                    Cur_State = 0;
                }
            }

        });
        mCVCamera.setCvCameraViewListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.e("log_wons", "OpenCV init error");
            // Handle initialization error
        }
        mCVCamera.enableView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mCVCamera != null) {
            mCVCamera.disableView();
        }
    };

    @Override
    public void onCameraViewStarted(int width, int height) {
        //初始化
        mRgba = new Mat(height, width, CvType.CV_8UC4);
    }

    @Override
    public void onCameraViewStopped() {
        mRgba.release();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        mRgba=inputFrame.rgba();
        switch (Cur_State) {
            case 1:
                Log.i("test",Cur_State+"");
                //灰度化
                Imgproc.cvtColor(inputFrame.gray(), mRgba, Imgproc.COLOR_GRAY2RGBA, 4);
                break;
            default:
                mRgba = inputFrame.rgba();
        }
        return mRgba;
    }
}
