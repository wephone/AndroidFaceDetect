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
### Imgproc.cvtColor方法
- 作用：转换图像的颜色，彩色 灰度，HSV 等等
- InputArray src: 输入图像即要进行颜色空间变换的原图像，可以是Mat类
- OutputArray dst: 输出图像即进行颜色空间变换后存储图像，也可以Mat类
- int code: 转换的代码或标识例如Imgproc.COLOR_GRAY2RGBA，即在此确定将什么制式的图片转换成什么制式的图片，后面会详细将
- int dstCn = 0: 目标图像通道数，如果取值为0，则由src和code决定
### Imgproc.rectangle方法
- 作用在原有图上绘制一个矩形
- img 图像
- pt1 矩形上一个顶点
- pt2 矩形对角线上的另一个顶点
- color 线条颜色 (RGB) 或亮度（灰度图像 ）(grayscale image）
- thickness 组成矩形的线条的粗细程度。取负值时（如 CV_FILLED）函数绘制填充了色彩的矩形。
- line_type 线条的类型。见cvLine的描述
- shift 坐标点的小数点位数。
### Scalar
- 代表一个4元素的向量。Scalar类型广泛用于OpenCV中，用于传递像素值。
- 广泛使用它来表示BGR颜色值（3个参数）。如果不使用最后一个参数，则无需定义最后一个参数。
### Rect 矩形类
- rect.tl();       //返回rect的左上顶点的坐标
- rect.br();       //返回rect的右下顶点的坐标
### CascadeClassifier.detectMultiScale
- 作用：检测多个符合的目标
- image 待检测图片，一般为灰度图像加快检测速度
- objects 被检测物体的矩形框向量组
- scaleFactor 表示在前后两次相继的扫描中，搜索窗口的比例系数。默认为1.1即每次搜索窗口依次扩大10%
- minNeighbors 最小邻居数 表示构成检测目标的相邻矩形的最小个数,如果组成检测目标的小矩形的个数和小于 min_neighbors - 1 都会被排除,这种设定值一般用在用户自定义对检测结果的组合程序上
- flags 兼容老版本的一个参数
- minSize 最小尺寸，检测出的人脸最小尺寸
- maxSize 最大尺寸，检测出的人脸最大尺寸

## 利用opencv训练基于Haar特征、LBP特征、Hog特征的分类器cascade.xml