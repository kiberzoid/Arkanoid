package kiberzoid.arkanoid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Ball {

    private int xPos;
    private int yPos;
    private int xSpeed;
    private int ySpeed;
    private int radius;
    private boolean fail;
    boolean leftFlag  = false;
    boolean rightFlag  = false;
    boolean flag = false;
    int count = 0;

    public Ball(int xPos,int yPos, int radius,int xSpeed, int ySpeed){
        this.xPos = xPos;
        this.yPos = yPos;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.radius = radius;
        this.fail = false;
    }

    public void set_xSpeed(int xSpeed){
        this.xSpeed = xSpeed;
    }
    public void set_ySpeed(int ySpeed){
        this.ySpeed = ySpeed;
    }
    public int get_x(){
        return this.xPos;
    }
    public int get_y(){
        return this.yPos;
    }
    public void set_x(int xPos){
        this.xPos = xPos;
    }
    public void set_y(int yPos){
        this.yPos = yPos;
    }
    public boolean get_fail(){
        return this.fail;
    }
    public void set_fail(boolean fail){
        this.fail = fail;
    }

    public void update(int width, int height, Platform platform){
        boolean leftFlag  = false;
        boolean rightFlag  = false;
        if(yPos-radius<=0){
            ySpeed*=-1;
            flag = false;
        }
        if(yPos+radius>=height){
            this.fail = true;
        }
        if(xPos-radius<=0){
            xSpeed*=-1;
            flag = false;
        }
        if(xPos+radius>=width){
            xSpeed*=-1;
            flag = false;
        }
        /*
        if ((yPos>=platform.get_bottom()-radius)
                &&(xPos>=platform.get_left()-platform.getHeight()-radius)
                && (xPos<=platform.get_left())
                && (ySpeed>0))
                {
                    leftFlag = true;
        }
        if ((yPos>=platform.get_bottom()-radius)
                &&(xPos<=platform.get_right()+platform.getHeight()+radius)
                && (xPos>=platform.get_right())
                && ySpeed<0)
        {
            rightFlag = true;
        }
        */
        if (((yPos>=platform.get_bottom()- radius) && (yPos<platform.get_top()+ radius) &&(xPos>=platform.get_left()-radius) && (xPos<platform.get_left()) && !flag) ||
        ((yPos>=platform.get_bottom()- radius)&& (yPos<platform.get_top()+ radius) &&(xPos<=platform.get_right()+radius) && (xPos>platform.get_right()) &&!flag))
        {
            xSpeed*=-1;
            flag = true;

        }
       if((yPos>=platform.get_bottom()- radius)&&(xPos>platform.get_left())&&(xPos<platform.get_right())){
           ySpeed*=-1;
           count++;
           if (count==3) {
               if (xSpeed<0)
                    xSpeed-=3; else xSpeed+=3;
               if (ySpeed<0)
                    ySpeed-=3; else ySpeed+=3;
               count=0;
           }



        }
        xPos+=xSpeed;
        yPos+=ySpeed;
    }
    public void drawBall(Canvas canvas,Paint p){
        canvas.drawCircle(xPos,yPos,radius,p);
    }

}
