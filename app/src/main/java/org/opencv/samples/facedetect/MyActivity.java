package org.opencv.samples.facedetect;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

/**
 * Created by wephone on 18-1-25.
 * Mat类
 * 保存图像像素信息的是数据结构Mat阵，它包含两部分：矩阵头和一个指向像素数据的矩阵针
 * 矩阵头主要包含：矩阵尺寸、存储方式、存储地址、引用计数等
 * 矩阵头的大小是一定的，不会随着图像的大小改变而改变
 * 通常图像像素数据都会很大，因此，在图像的复制和传递过程中不需要复制整个Mat数据，只复制矩阵头和指向像素矩阵的指针即可
 */

public class MyActivity extends Activity implements CameraBridgeViewBase.CvCameraViewListener {

    private Button deal;
    private Button changeCamera;
    //缓存相机每帧输入的数据
    private Mat mRgba;
    /**
     * 当前处理状态
     * Cur_State与图像处理的每个类型对应，0对应默认状态，也就是显示原图，
     * 1-7分别对应：灰化、Canny边缘检测、Hist直方图计算、Sobel边缘检测、SEPIA(色调变换)、ZOOM放大镜、PIXELIZE像素化
     */
    private static int Cur_State = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_activity);
        deal = (Button) findViewById(R.id.deal_btn);
        deal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(Cur_State<8){
                    //切换状态
                    Cur_State ++;
                }else{
                    //恢复初始状态
                    Cur_State = 0;
                }
            }

        });
    }

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
    public Mat onCameraFrame(Mat inputFrame) {
        return null;
    }
}
