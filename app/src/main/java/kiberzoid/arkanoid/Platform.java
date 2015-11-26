package kiberzoid.arkanoid;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Platform {

    private Rect rect;

    public Platform(int top,int bottom,int left,int right){
        rect = new Rect(left,top,right,bottom);
    }

    public void drawPlatform(Canvas canvas,Paint p){
        canvas.drawRect(rect,p);
    }

    public int getLeft(){
        return this.rect.left;
    }
    public int getTop(){
        return this.rect.top;
    }
    public int getRight(){
        return this.rect.right;
    }
    public int getBottom(){
        return this.rect.bottom;
    }

    public void update(){

    }
}
