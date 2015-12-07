package kiberzoid.arkanoid;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class Platform {
    RectF rect;

    public Platform(float left, float top, float right, float bottom) {
        rect = new RectF(left,top,right,bottom);
    }

    public void drawPlatform(Canvas canvas, Paint p) {
        canvas.drawColor(Color.WHITE);
        canvas.drawRect(rect,p);
    }

    public void update(float left, float right,int width) {
        float platformWidth = this.getWidth();
        if(left + platformWidth>width){
            this.rect.left = width - platformWidth;
            this.rect.right = this.rect.left + platformWidth;
        }else if(right - platformWidth<0){
            this.rect.left = 0;
            this.rect.right = platformWidth;
        }
        else {
            this.rect.left = left;
            this.rect.right = right;
        }
    }

    public float getWidth() {
        return this.rect.right - this.rect.left;
    }

    public float getHeight() {
        return this.rect.top - this.rect.bottom;
    }

    public float get_left() {
        return this.rect.left;
    }

    public float get_top() {
        return this.rect.top;
    }

    public float get_right() {
        return this.rect.right;
    }

    public float get_bottom() {
        return this.rect.bottom;
    }

}
