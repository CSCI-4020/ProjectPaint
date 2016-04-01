package edu.apsu.csci.projectpaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Frank on 4/1/2016.
 */
public class PaintView extends View {

    private Paint backgroundPaint;

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

    private void setup(AttributeSet attr){
        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.WHITE);
        backgroundPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPaint(backgroundPaint);
    }
}
