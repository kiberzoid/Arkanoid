package kiberzoid.arkanoid;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Platform {

    private RectF rect;

    public Platform(float left, float top, float right, float bottom) {
        rect = new RectF(left,top,right,bottom);
    }

    public void drawPlatform(Canvas canvas, Paint p) {
        canvas.drawColor(Color.WHITE);
        canvas.drawRect(this.rect,p);
    }

    public void update(float xCenter,float width) {
        float platformWidth = this.rect.width();
        if((xCenter + platformWidth/2)>width){
            this.rect.left = width - platformWidth;
            this.rect.right = width;
        }else if(xCenter - platformWidth/2 < 0) {
            this.rect.left = 0;
            this.rect.right = platformWidth;
        }else{
            this.rect.left = xCenter-platformWidth/2;
            this.rect.right = xCenter+platformWidth/2;;
        }
    }

    public float getWidth() {
        return this.rect.width();
    }

    public float getHeight() {
        return this.rect.height();
    }

    public float get_left() {
        return this.rect.left;
    }

    public float get_bottom() {
        return this.rect.bottom;
    }

    public float get_right() {
        return this.rect.right;
    }

    public float get_top() {
        return this.rect.top;
    }

}
