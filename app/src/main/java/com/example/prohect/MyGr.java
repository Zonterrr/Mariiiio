package com.example.prohect;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;


public class MyGr extends View {

    public static int height=1024, width=800;
    float x=100,y = 10, dy=0;
    float ground = 300;
    float gravity = 2;
    // картинка героя
    Bitmap enemy,bitmap,background;
    float xE=1600,yE = 460, dxE=-10;
    float xB=1600,yB = 460, dxB=-10;

    public MyGr(Context context) {
        super(context);
        Resources resources = getContext().getResources();
        background = BitmapFactory.decodeResource( resources,R.drawable.background1);
        background = Bitmap.createScaledBitmap(background,width, height,true);
        bitmap = BitmapFactory.decodeResource( resources,R.drawable.mario1333);
        bitmap = Bitmap.createScaledBitmap(bitmap,width/8, height/4,true);
        enemy = BitmapFactory.decodeResource( resources,R.drawable.zombie);
        enemy = Bitmap.createScaledBitmap(enemy,width/9, height/6,true);
        MyTimer timer = new MyTimer(1000000, 20);
        timer.start();
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        height = metrics.heightPixels;
        width = metrics.widthPixels;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        canvas.drawBitmap(background,xB,yB,pB);
        canvas.drawBitmap(bitmap,x,y,p);
        canvas.drawBitmap(enemy,xE,yE,p);
        move();
        moveEnemy();
    }
//движение героя
    public void move() {
        dy += gravity;
        y += dy;
        if (y > ground) y = ground;
    }
        //движение врага
    public void moveEnemy(){
       xE+=dxE;
       if(xE<-100) xE=width;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       if(y==ground) dy = -30;
        return true;
    }


    public class MyTimer extends CountDownTimer{
        public MyTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {

            invalidate();
        }

        @Override
        public void onFinish() {

        }
    }
}
