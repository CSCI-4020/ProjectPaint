package edu.apsu.csci.projectpaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Frank on 4/1/2016.
 */
public class PaintView extends View implements View.OnTouchListener {

    private Paint backgroundPaint;

    LinePaint linepaint;


    private ArrayList<LinePaint> linePaint = new ArrayList<>(); //May come in handy with LinePaint class


    // Constructors
    public PaintView(Context context) {
        super(context);
        setup(null);

    }

    public PaintView(Context context, AttributeSet attr) {
        super(context, attr);
        setup(attr);

    }

    public PaintView(Context context, AttributeSet attr, int defStyleAttr) {
        super(context, attr, defStyleAttr);
        setup(attr);

    }

    //setup the paint view
    private void setup(AttributeSet attr) {
        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.WHITE);
        backgroundPaint.setStyle(Paint.Style.FILL);



        this.setOnTouchListener(this);

    }


    //draws on the paint view
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPaint(backgroundPaint);

        // canvas.drawLine(0, 0, getWidth() - 1, getHeight() - 1, linePaint);//draws a line accross the screen for testing purposes

        for (LinePaint l : linePaint) {

            canvas.drawLine(l.getX1(), l.getY1(), l.getX2(), l.getY2(), l.paint);

        }


    }

    @Override //Does magical stuff as described by Dr. Nicholson and stack over flow
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredWidth = 100;
        int desiredHeight = 100;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }

        //MUST CALL THIS
        setMeasuredDimension(width, height);
    }

    private float downX, upX, downY, upY;//Stores the coordinates for user input

    @Override//on touch method catche's user input
    public boolean onTouch(View v, MotionEvent event) {

        Log.i("onTouch", "In onTouch");
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                Log.i("onTouch", "downX" + downX + ", downY" + downY);
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                upX = event.getX();
                upY = event.getY();
                Log.i("onTouch", "upX" + upX + ", upY" + upY);
                arrayUPDate(downX, downY, upX, upY);


                break;
            case MotionEvent.ACTION_CANCEL:
                break;

        }
        Log.i("onTouch", "End of onTouch");
        return true;

    }

    static String color = "BLACK";
    static int thickness = 10;
    public static void setCurrentColor(String c) {
      color=c;
    }

    public static void setCurrentThickness(int t) {
        thickness = t;
    }


    //up dates the lines array currently
    private void arrayUPDate(float downx, float downy, float upx, float upy) {
        linepaint = new LinePaint(downx, downy, upx, upy,color,thickness);//May come in handy with LinePaint class
        linePaint.add(linepaint); //May come in handy with LinePaint class



        Log.i("arrayUPDate()", "downx" + downx + ", downy" + downy + ", upx" + upx + ", upy" + upy);
        invalidate();
    }


}
