package com.sevenqiu.plustextandtransfer.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import static com.sevenqiu.plustextandtransfer.util.Util.dip2Pixl;

/**
 * @author Seven Qiu
 * @date 2019-04-25
 */
public class SportsView extends View {
    private static final String TAG = SportsView.class.getSimpleName();

    private static final float RADIUS = dip2Pixl(150f);

    private Paint mPaint;
    private Point mMiddlePoint;
    private Paint.FontMetrics mFontMetrics = new Paint.FontMetrics();
    private RectF mRectF;

    public SportsView(Context context) {
        super(context);
    }

    public SportsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SportsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(dip2Pixl(20));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);

        mPaint.setTextSize(dip2Pixl(50));
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.getFontMetrics(mFontMetrics);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMiddlePoint = new Point(getWidth() / 2, getHeight() / 2);
        mRectF = new RectF(mMiddlePoint.x - RADIUS, mMiddlePoint.y - RADIUS,
                mMiddlePoint.x + RADIUS, mMiddlePoint.y + RADIUS);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(mMiddlePoint.x, mMiddlePoint.y, RADIUS, mPaint);

        mPaint.setColor(Color.RED);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(mRectF, 0, 200, false, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        float yOffset = (mFontMetrics.descent + mFontMetrics.ascent) / 2;
        canvas.drawText("bag", mMiddlePoint.x, mMiddlePoint.y - yOffset, mPaint);
    }

}
