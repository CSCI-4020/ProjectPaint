package edu.apsu.csci.projectpaint;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Frank on 4/3/2016.
 */
public class LinePaint  {
    private int id;
    private float x1;
    private float y1;
    private float x2;
    private float y2;
    private String color;
    private int width;
    Paint paint  =new Paint();




    public LinePaint(float x1, float y1, float x2, float y2,String c,int t,int id) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.id = id;
        this.color = c;
        this.width=t;
        paint.setColor(Color.parseColor(c));
        paint.setStrokeWidth(t);

    }

    public float getX1() {
        return x1;
    }



    public float getY1() {
        return y1;
    }



    public float getX2() {
        return x2;
    }


    public float getY2() {
        return y2;
    }

    public int getId(){
        return id;
    }

    public int getWidth(){
        return width;
    }

    public String getColor(){
        return color;
    }





}
