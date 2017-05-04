线性公式

给定点P0、P1，线性贝兹曲线只是一条两点之间的直线。这条线由下式给出：
B(t) = P0 + (P1 - P0) * t = (1 - t) * P0 + t * P1, t ∈ [0, 1]
且其等同于线性插值。

二次方公式

二次方贝兹曲线的路径由给定点P0、P1、P2的函数B（t）追踪：
B(t) = (1 - t)^2 * P0 + 2t * (1 - t) * P1 + t^2 * P2, t ∈ [0,1]
TrueType字型就运用了以贝兹样条组成的二次贝兹曲线。

###初识Path类
```
Path.moveTo(float x, float y) // Path的初始点
Path.lineTo(float x, float y) // 线性公式的贝赛尔曲线, 其实就是直线
Path.quadTo(float x1, float y1, float x2, float y2) // 二次方公式的贝赛尔曲线
Path.cubicTo(float x1, float y1, float x2, float y2, float x3, float y3) // 三次方公式的贝赛尔曲线...
```
绘制贝赛尔曲线
![](http://upload-images.jianshu.io/upload_images/1990324-6a1fe3f60c880f40.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

实现一个三次方贝赛尔曲线Evaluator，已知公式为：B(t) = P0 * (1-t)^3 + 3 * P1 * t * (1-t)^2 + 3 * P2 * t^2 * (1-t) + P3 * t^3
 代码如下：
```
public class BezierEvaluator implements TypeEvaluator<PointF> {

    private PointF points[];

    public BezierEvaluator(PointF... points) {
        if (points.length != 4) {
            throw new IllegalArgumentException("只演示三次方贝赛尔曲线");
        }
        this.points = points;
    }

    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        // B(t) = P0 * (1-t)^3 + 3 * P1 * t * (1-t)^2 + 3 * P2 * t^2 * (1-t) + P3 * t^3

        float t = fraction;
        float one_t = 1.0f - t;

        PointF P0 = points[0];
        PointF P1 = points[1];
        PointF P2 = points[2];
        PointF P3 = points[3];

        float x = (float) (P0.x * Math.pow(one_t, 3) + 3 * P1.x * t * Math.pow(one_t, 2) + 3 * P2.x * Math.pow(t, 2) * one_t + P3.x * Math.pow(t, 3));
        float y = (float) (P0.y * Math.pow(one_t, 3) + 3 * P1.y * t * Math.pow(one_t, 2) + 3 * P2.y * Math.pow(t, 2) * one_t + P3.y * Math.pow(t, 3));

        PointF pointF = new PointF(x, y);

        return pointF;
    }

}

```




