package com.hencoder.hencoderpracticedraw4.practice;

import com.hencoder.hencoderpracticedraw4.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice01ClipRectView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;

    public Practice01ClipRectView(Context context) {
        super(context);
    }

    public Practice01ClipRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice01ClipRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private final int bitmapWidth;
    private final int bitmapHeight;

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);

        bitmapWidth = bitmap.getWidth();
        bitmapHeight = bitmap.getHeight();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int left = (getWidth() - bitmap.getWidth()) / 2;
        int top = (getHeight() - bitmap.getHeight()) / 2;

        // about canvas save and restore

        /**
         * Saves the current matrix and clip onto a private stack.
         * <p>
         * Subsequent calls to translate,scale,rotate,skew,concat or clipRect,
         * clipPath will all operate as usual, but when the balancing call to
         * restore() is made, those calls will be forgotten, and the settings that
         * existed before the save() will be reinstated.
         *
         * @return The value to pass to restoreToCount() to balance this save()
         */

        canvas.save();
        canvas.clipRect(left + .2f * bitmapWidth, top + 0.1f * bitmapHeight,
                left + 0.9f * bitmapWidth, top + 0.5f * bitmapHeight);
        canvas.drawBitmap(bitmap, left, top, paint);
        canvas.restore();


        // debug
        paint.setStrokeWidth(5);
        paint.setColor(Color.RED);
        canvas.drawPoint(left + .1f * bitmapWidth, top + 0.05f * bitmapHeight, paint); // point out of the clip Rect

        // when doesnot restore, the point donot show ->
        //      because now the canvas is clip, and the point is out of the clip rect.

        // when restore. the point show ->
        //      because the canvas now is not clip (recovery to the save time)


    }
}
