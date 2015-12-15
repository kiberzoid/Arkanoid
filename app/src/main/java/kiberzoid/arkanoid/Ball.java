package kiberzoid.arkanoid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.SoundPool;

import java.util.ArrayList;

public class Ball {

    private float xPos;
    private float yPos;
    private int xSpeed;
    private int ySpeed;
    private int radius;
    private boolean fail;

    public Ball(float xPos,float yPos, int radius,int xSpeed, int ySpeed){
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
    public float get_x(){
        return this.xPos;
    }
    public float get_y(){
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

    public void intersectionPlatform(Platform platform,SoundPool sp,int p_otskok_id){
        if(yPos < platform.get_bottom()){ //центр сверху
            if(xPos < platform.get_left()) { // Если центр в левом углу
                if ((xPos - platform.get_left()) * (xPos - platform.get_left()) + (yPos - platform.get_bottom()) * (yPos - platform.get_bottom()) <= radius * radius) {
                    ySpeed *= -1;
                    sp.play(p_otskok_id,1,1,0,0,1);
                }
                return;
            }
            if(xPos > platform.get_right()) { // Если центр в правом углу
                if ((xPos - platform.get_right()) * (xPos - platform.get_right()) + (yPos - platform.get_bottom()) * (yPos - platform.get_bottom()) <= radius * radius) {
                    ySpeed *= -1;
                    sp.play(p_otskok_id,1,1,0,0,1);
                }
                return;
            }
            if(platform.get_bottom()-yPos < radius) {
                ySpeed *= -1;
                sp.play(p_otskok_id,1,1,0,0,1);
            }
            return;
        }
        if(yPos > platform.get_top()){ //центр сверху
            if(xPos < platform.get_left()) { // Если центр в левом углу
                if ((xPos - platform.get_left()) * (xPos - platform.get_left()) + (yPos - platform.get_top()) * (yPos - platform.get_top()) <= radius * radius) {
                    ySpeed *= -1;
                    sp.play(p_otskok_id,1,1,0,0,1);
                }
                return;
            }
            if(xPos > platform.get_right()) { // Если центр в правом углу
                if ((xPos - platform.get_right()) * (xPos - platform.get_right()) + (yPos - platform.get_top()) * (yPos - platform.get_top()) <= radius * radius) {
                    ySpeed *= -1;
                    sp.play(p_otskok_id,1,1,0,0,1);
                }
                return;
            }
            if(yPos-platform.get_top() < radius) {
                ySpeed *= -1;
                sp.play(p_otskok_id,1,1,0,0,1);
            }
            return;
        }
        if(xPos < platform.get_left()){ //центр слева
            if(platform.get_left()-xPos < radius) {
                xSpeed *= -1;
                sp.play(p_otskok_id,1,1,0,0,1);
            }
            return;
        }
        if (xPos > platform.get_right()){ //центр справа
            if(xPos-platform.get_right() < radius) {
                xSpeed *= -1;
                sp.play(p_otskok_id,1,1,0,0,1);
            }
            return;
        }
    }
    //метод определяет принадлежность точки прямоугольнику
    public boolean isPointInRect (float x0, float y0, float xLeft, float xRight, float yBottom, float yTop ){
        if ( (x0<=xRight) && (x0>=xLeft) && (y0<=yTop) && (y0>=yBottom) ) return true;
        return false;

    }
    public void intersectionBlocks(ArrayList<Block> blocks){
        float leftBlock, rightBlock, topBlock,bottomBlock;
        for (int i=0;i<blocks.size(); i++){
            leftBlock = blocks.get(i).getLeft();
            rightBlock = blocks.get(i).getRight();
            topBlock = blocks.get(i).getTop();
            bottomBlock = blocks.get(i).getBottom();
            if ( (isPointInRect(xPos,yPos,leftBlock,rightBlock,bottomBlock-radius,bottomBlock)) ||
                    (isPointInRect(xPos,yPos,leftBlock,rightBlock,topBlock,topBlock+radius) ) ) {
                ySpeed*=-1;
            }

            if ( isPointInRect(xPos,yPos,leftBlock-radius,leftBlock,bottomBlock,topBlock) ||
                    isPointInRect(xPos,yPos,rightBlock,rightBlock+radius,bottomBlock,topBlock) ){
                xSpeed*=-1;
            }
        }


    }

    public void update(float width, float height, Platform platform, ArrayList<Block> blocks,SoundPool sp, int p_otskok_id){
        boolean x_need_return = false;
        boolean y_need_return = false;

        //отскок от стен
        if((yPos-radius)<=0){
            ySpeed*=-1;
        }
        if(yPos+radius>=height){
            this.fail = true;
        }
        if((xPos-radius)<=0){
            xSpeed*=-1;
        }
        if((xPos+radius)>=width){
            xSpeed*=-1;
        }

        if((yPos-radius)+ySpeed<=0){
            yPos = radius;
            y_need_return = true;
        }
        if((xPos-radius)+xSpeed<=0){
            xPos = radius;
            x_need_return = true;
        }
        if((xPos+radius)+xSpeed>=width){
            xPos = width-radius;
            x_need_return = true;
        }

        //отскок от платформы
        intersectionPlatform(platform,sp,p_otskok_id);

        //отскок от блоков
        intersectionBlocks(blocks);

        //обновление координат
        if(x_need_return&&y_need_return)
            return;
        if(x_need_return){
            yPos+=ySpeed;
            return;
        }
        if(y_need_return){
            xPos+=xSpeed;
            return;
        }
        xPos+=xSpeed;
        yPos+=ySpeed;
    }
    public void drawBall(Canvas canvas,Paint p){
        canvas.drawCircle(xPos,yPos,radius,p);
    }
}
