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
public class PaintView extends View implements View.OnTouchListener{

    private Paint backgroundPaint;
    private Paint linePaint;

    private float downX,upX,downY,upY;//Stores the coordinates for user input

    private ArrayList<Float> lines = new ArrayList<>();//Stores the coordines of the lines


// Constructors
    public PaintView(Context context){
        super(context);
        setup(null);

    }

   public PaintView(Context context,AttributeSet attr){
       super(context,attr);
       setup(attr);

   }

    public PaintView(Context context,AttributeSet attr,int defStyleAttr){
        super(context, attr, defStyleAttr);
        setup(attr);

    }
//setup the paint view
    private void setup(AttributeSet attr){
        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.WHITE);
        backgroundPaint.setStyle(Paint.Style.FILL);

        linePaint = new Paint();
        linePaint.setColor(Color.BLACK);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(10);

        this.setOnTouchListener(this);

    }
//draws on the paint view
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPaint(backgroundPaint);

       // canvas.drawLine(0, 0, getWidth() - 1, getHeight() - 1, linePaint);//draws a line accross the screen for testing purposes
      for (int l=0; l< lines.size(); l+=4) {//supposed to output the coordinates of the lines arraylist

          canvas.drawLine(lines.get(l), lines.get(l + 1), lines.get(l + 2), lines.get(l + 3), linePaint);
          Log.i("arrayDraw", "downx" + lines.get(l) + ", downy" + lines.get(l + 1) + ", upx" + lines.get(l + 2) + ", upy" + lines.get(+3));

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

    @Override//on touch method cathe's user input
    public boolean onTouch(View v, MotionEvent event) {

        Log.i("onTouch","In onTouch");
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                Log.i("onTouch","downX"+downX+", downY"+downY);
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                upX = event.getX();
                upY = event.getY();
                Log.i("onTouch","upX"+upX+", upY"+upY);
                arrayUPDate(downX, downY, upX, upY);


                break;
            case MotionEvent.ACTION_CANCEL:
                break;

        }
        Log.i("onTouch","End of onTouch");
        return true;

    }
//up dates the lanes array currently
    private void arrayUPDate(float downx, float downy,float upx,float upy){


        lines.add(downx);
        lines.add(downy);
        lines.add(upx);
        lines.add(upy);




        Log.i("arrayUPDate()", "downx" + downx + ", downy" + downy + ", upx" + upx + ", upy" + upy);
        invalidate();
    }


}
