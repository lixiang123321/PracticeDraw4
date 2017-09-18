package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice02ClipPathView extends View {
    Paint paint = new Paint();
    Bitmap bitmap;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);
    Path mPath1 = new Path();
    Path mPath2 = new Path();


    public Practice02ClipPathView(Context context) {
        super(context);
    }

    public Practice02ClipPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice02ClipPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private final int bitmapWidth;
    private final int bitmapHeight;

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
        bitmapWidth = bitmap.getWidth();
        bitmapHeight = bitmap.getHeight();

//        mPath1.moveTo(point1.x + 0.2f * bitmapWidth, point1.y);
        mPath1.addCircle(point1.x + 0.7f * bitmapWidth,
                point1.y + 0.65f * bitmapHeight,
                0.45f * bitmapWidth,
                Path.Direction.CW);

        mPath2.setFillType(Path.FillType.INVERSE_EVEN_ODD);
        mPath2.addCircle(point2.x + 0.7f * bitmapWidth,
                point2.y + 0.65f * bitmapHeight,
                0.45f * bitmapWidth,
                Path.Direction.CW);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.clipPath(mPath1);
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();

        canvas.save();
        canvas.clipPath(mPath2);
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();

        // debug to locate the circle location.
        //canvas.drawPath(mPath1, paint);



    }
}
