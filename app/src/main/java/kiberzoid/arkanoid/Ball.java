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
        if(yPos-radius<=0){
            ySpeed*=-1;
        }
        if(yPos+radius>=height){
            this.fail = true;
        }
        if(xPos-radius<=0){
            xSpeed*=-1;
        }
        if(xPos+radius>=width){
            xSpeed*=-1;
        }
       if((yPos>=platform.get_yStart()-platform.getStrokeWidth()-radius)&&(xPos>=platform.get_xStart())&&(xPos<=platform.get_xStop())){
            ySpeed*=-1;
        }
        xPos+=xSpeed;
        yPos+=ySpeed;
    }
    public void drawBall(Canvas canvas,Paint p){
        canvas.drawCircle(xPos,yPos,radius,p);
    }

}
