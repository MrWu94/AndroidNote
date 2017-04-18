package com.hansheng.studynote.ImageViewandDrawable.SVG;

import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.eftimoff.androipathview.PathView;
import com.hansheng.studynote.R;


/**
 * Created by hansheng on 2016/8/1.
 * SVG 可被非常多的工具读取和修改（比如记事本）
 * SVG 与 JPEG 和 GIF 图像比起来，尺寸更小，且可压缩性更强。
 * SVG 是可伸缩的
 * SVG 图像可在任何的分辨率下被高质量地打印
 * SVG 可在图像质量不下降的情况下被放大
 * SVG 图像中的文本是可选的，同时也是可搜索的（很适合制作地图）
 * SVG 可以与 Java 技术一起运行
 * SVG 是开放的标准
 * SVG 文件是纯粹的 XML
 */
public class SVGActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.svg_layout);
        final PathView pathView = (PathView) findViewById(R.id.pathView);
//        final Path path = makeConvexArrow(50, 100);
//        pathView.setPath(path);
        //       pathView.setFillAfter(true);
        //  pathView.useNaturalColors();
        pathView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pathView.getPathAnimator().
                        //pathView.getSequentialPathAnimator().
                                delay(100).
                        duration(1500).
                        interpolator(new AccelerateDecelerateInterpolator()).
                        start();
            }
        });
    }

    private Path makeConvexArrow(float length, float height) {
        final Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.lineTo(length / 4f, 0.0f);
        path.lineTo(length, height / 2.0f);
        path.lineTo(length / 4f, height);
        path.lineTo(0.0f, height);
        path.lineTo(length * 3f / 4f, height / 2f);
        path.lineTo(0.0f, 0.0f);
        path.close();
        return path;
    }


}
