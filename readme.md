## Opencv For Android 人脸识别demo
### Mat类
- 保存图像像素信息的是数据结构Mat阵，它包含两部分：矩阵头和一个指向像素数据的矩阵针
- 矩阵头主要包含：矩阵尺寸、存储方式、存储地址、引用计数等
- 矩阵头的大小是一定的，不会随着图像的大小改变而改变
- 通常图像像素数据都会很大，因此，在图像的复制和传递过程中不需要复制整个Mat数据，只复制矩阵头和指向像素矩阵的指针即可
### CameraBridgeViewBase.CvCameraViewListener
#### onCameraViewStarted
- This method is invoked when camera preview has started.
- After this method is invoked the frames will start to be delivered to client via the onCameraFrame() callback.
- 当相机启动预览时该方法被调用
- 该方法调用后，图像的每一帧都将通过onCameraFrame方法回调给用户
- 主要用于初始化(图像矩阵)操作 包括：长度、宽度和图像类型标志
#### onCameraFrame
- This method is invoked when delivery of the frame needs to be done.
- The returned values - is a modified frame which needs to be displayed on the screen.
- TODO: pass the parameters specifying the format of the frame (BPP, YUV or RGB and etc)
- 当有图像帧需要处理时回调此方法
- 返回值是你处理过后的，要在屏幕上显示的图像帧
- 也就是这个方法是做图像处理的主要方法，相机捕获到的图像矩阵都在这里被回调处理
#### onCameraViewStopped
- This method is invoked when camera preview has been stopped for some reason.
- No frames will be delivered via onCameraFrame() callback after this method is called.
- 当相机预览因为某些原因停止时该方法被调用
- 这个方法调用后，onCameraFrame方法将不再接收到图像帧
- 用于释放一些资源