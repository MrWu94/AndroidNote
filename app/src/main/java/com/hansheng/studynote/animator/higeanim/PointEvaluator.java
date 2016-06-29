package com.hansheng.studynote.animator.higeanim;

import android.animation.TypeEvaluator;

import com.hansheng.studynote.animator.higeanim.Point;

/**
 * Created by hansheng on 2016/6/23.
 */
public class PointEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {

        Point start = (Point) startValue;
        Point endPoint = (Point) endValue;
        float x = start.getX() + fraction * (endPoint.getX() - start.getX());
        float y = start.getY() + fraction * (endPoint.getY() - start.getY());
        Point point = new Point(x, y);
        return point;
    }
}
