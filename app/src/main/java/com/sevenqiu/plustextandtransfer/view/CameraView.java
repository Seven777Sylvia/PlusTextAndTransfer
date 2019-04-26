package com.sevenqiu.plustextandtransfer.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.sevenqiu.plustextandtransfer.R;

import static com.sevenqiu.plustextandtransfer.util.Util.dip2Pixl;
import static com.sevenqiu.plustextandtransfer.util.Util.getCameraZ;

/**
 * @author Seven Qiu
 * @date 2019-04-25
 */
public class CameraView extends View {
    private static final float SIZE = dip2Pixl(300);

    private Bitmap mBitmap;
    private Camera mCamera;
    private Point mMiddlePoint;
    private Paint mPaint;
    private Path mUpperPart;
    private Path mLowerPart;

    public CameraView(Context context) {
        super(context);
    }

    public CameraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CameraView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitmap = getBitmapInSize((int) SIZE);
        mCamera = new Camera();
        mCamera.rotateX(30);
        mCamera.setLocation(0, 0, getCameraZ());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mMiddlePoint = new Point(getWidth() / 2, getHeight() / 2);

        mUpperPart = new Path();
        mUpperPart.addRect(mMiddlePoint.x - SIZE, mMiddlePoint.y - SIZE,
                mMiddlePoint.x + SIZE, mMiddlePoint.y + SIZE, Path.Direction.CW);

        mLowerPart = new Path();
        mLowerPart.addRect(-SIZE, 0f, SIZE, SIZE, Path.Direction.CW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //上半部分
        canvas.save();
        canvas.clipPath(mUpperPart);
        canvas.drawBitmap(mBitmap, mMiddlePoint.x - SIZE / 2, mMiddlePoint.y - SIZE / 2, mPaint);
        canvas.restore();

        canvas.translate(mMiddlePoint.x, mMiddlePoint.y);
        canvas.rotate(-30);
        mCamera.save();
        mCamera.applyToCanvas(canvas);
        mCamera.restore();
        canvas.save();
        canvas.clipPath(mLowerPart);
        canvas.rotate(30);
        canvas.translate(-mMiddlePoint.x, -mMiddlePoint.y);
        canvas.drawBitmap(mBitmap, mMiddlePoint.x - SIZE / 2, mMiddlePoint.y - SIZE / 2, mPaint);
        canvas.restore();
    }

    private Bitmap getBitmapInSize(int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.tang, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(getResources(), R.drawable.tang, options);
    }
}
