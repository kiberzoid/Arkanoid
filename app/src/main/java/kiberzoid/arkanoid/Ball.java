package kiberzoid.arkanoid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by 1 on 25.11.2015.
 */
public class Ball {

    private int xPos;
    private int yPos;
    private int xSpeed;
    private int ySpeed;
    private int radius;

    public Ball(int xPos,int yPos, int radius,int xSpeed, int ySpeed){
        this.xPos = xPos;
        this.yPos = yPos;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.radius = radius;
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
    public void update(int width, int height){
        xPos+=xSpeed;
        yPos+=ySpeed;
        if(yPos<0){
            yPos = radius;
            ySpeed*=-1;
        }
        if(yPos>height){
            yPos = height-radius;
            ySpeed*=-1;
        }
        if(xPos<0){
            xPos = radius;
            xSpeed*=-1;
        }
        if(xPos>width){
            xPos = width-radius;
            xSpeed*=-1;
        }
    }
    public void drawBall(Canvas canvas,Paint p){
        canvas.drawColor(Color.WHITE);
        canvas.drawCircle(xPos,yPos,radius,p);
    }

}
