package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice12CameraRotateFixedView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);

    public Practice12CameraRotateFixedView(Context context) {
        super(context);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private final Camera camera;
    private final int bitmapWidth;
    private final int bitmapHeight;
    private final Matrix matrix;

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
        camera = new Camera();
        bitmapWidth = bitmap.getWidth();
        bitmapHeight = bitmap.getHeight();
        matrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        camera.save();
        camera.rotateX(30);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-(point1.x + .5f * bitmapWidth), -(point1.y + .5f * bitmapHeight));
        matrix.postTranslate((point1.x + .5f * bitmapWidth), (point1.y + .5f * bitmapHeight));
        canvas.setMatrix(matrix);
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();

        canvas.save();
        camera.save();
        camera.rotateY(30);
        matrix.reset();
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-(point2.x + .5f * bitmapWidth), -(point2.y + .5f * bitmapHeight));
        matrix.postTranslate((point2.x + .5f * bitmapWidth), (point2.y + .5f * bitmapHeight));
        canvas.setMatrix(matrix);
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();

        // remember 1: camera should restore too, or the previous operation still exist.
        // remember 2: camera rotate center : the axis center, the left top one.
        // remember 3: to fix 2, if u want to rotate the bitmap center -> u should move the rotate center to bitmap
        //      but ? why pre translate move to the opposite direction works?
        //      we may think this:
        //          1) as we draw at point.x, and point.y
        //          2) first the matrix has pre translate, to move the draw point at axis center.
        //          3) now the bitmap at center, and do the camera rotate.
        //          4) after rotate, we move it to its ori location.
        //          I think this thought is right, because, when I comment the postTranslate one,
        //          the bitmap will location at axis center with rotated.

        // camera center at (0, 0, -8) inch.
    }
}
