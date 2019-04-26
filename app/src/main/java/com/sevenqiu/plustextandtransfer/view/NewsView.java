package com.sevenqiu.plustextandtransfer.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.sevenqiu.plustextandtransfer.R;

import static com.sevenqiu.plusxfermodedemo.util.Util.dip2Pixl;

/**
 * @author Seven Qiu
 * @date 2019-04-25
 */
public class NewsView extends View {
    private static final float SIZE = dip2Pixl(200f);
    private static final float PADDING_TOP = dip2Pixl(100f);
    String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean justo sem, sollicitudin in maximus a, vulputate id magna. Nulla non quam a massa sollicitudin commodo fermentum et est. Suspendisse potenti. Praesent dolor dui, dignissim quis tellus tincidunt, porttitor vulputate nisl. Aenean tempus lobortis finibus. Quisque nec nisl laoreet, placerat metus sit amet, consectetur est. Donec nec quam tortor. Aenean aliquet dui in enim venenatis, sed luctus ipsum maximus. Nam feugiat nisi rhoncus lacus facilisis pellentesque nec vitae lorem. Donec et risus eu ligula dapibus lobortis vel vulputate turpis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In porttitor, risus aliquam rutrum finibus, ex mi ultricies arcu, quis ornare lectus tortor nec metus. Donec ultricies metus at magna cursus congue. Nam eu sem eget enim pretium venenatis. Duis nibh ligula, lacinia ac nisi vestibulum, vulputate lacinia tortor.";

    private Paint mPaint;
    private Bitmap mBitmap;
    private TextPaint mTextPaint;
    private float[] measureWidth = new float[1];
    private Paint.FontMetrics mFontMetrics = new Paint.FontMetrics();

    public NewsView(Context context) {
        super(context);
    }

    public NewsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NewsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitmap = getBitmapBySize((int) SIZE);
        mTextPaint = new TextPaint();
        mTextPaint.setTextSize(dip2Pixl(20));
       mTextPaint.getFontMetrics(mFontMetrics);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmap, getWidth() - mBitmap.getWidth(), PADDING_TOP, mPaint);

        int usableWidth = 0;
        float fontSpacing = mTextPaint.getFontSpacing();
        for (int start = 0, count = 0; start < text.length(); start += count, fontSpacing += mTextPaint.getFontSpacing()) {
            float top = mFontMetrics.ascent + fontSpacing;
            float bottom = mFontMetrics.descent + fontSpacing;

            if (hasImage(top, bottom)) {
                usableWidth = (int) (getWidth() - SIZE);
            } else {
                usableWidth = getWidth();
            }
            count = mTextPaint.breakText(text, start, text.length(), true, usableWidth, measureWidth);
            canvas.drawText(text, start, start + count, 0, fontSpacing, mTextPaint);
        }

    }

    private Bitmap getBitmapBySize(int size) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.tang, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = size;
        return BitmapFactory.decodeResource(getResources(), R.drawable.tang, options);
    }

    private boolean hasImage(float top, float bottom) {
        return top > PADDING_TOP && top < PADDING_TOP + SIZE ||
                bottom > PADDING_TOP && bottom < PADDING_TOP + SIZE;
    }
}
