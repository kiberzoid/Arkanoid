package kiberzoid.arkanoid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by Kirill on 07.12.2015.
 */
public class Block {
    private float xCenter;
    private float yCenter;
    private float width;
    private float height;
    private float left;
    private float right;
    private float top;
    private float bottom;
    private RectF rect;



    public Block(float xCenter, float yCenter, float width, float height) {
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.width = width;
        this.height = height;

        this.left=xCenter-width/2;
        this.right=xCenter+width/2;
        this.top=yCenter+height/2;
        this.bottom=yCenter-height/2;

        rect = new RectF(left,top,right,bottom);
    }

    public void drawBlock(Canvas canvas, Paint p) {
        canvas.drawRect(this.rect,p);
    }

    public float getxCenter() {
        return xCenter;
    }

    public float getyCenter() {
        return yCenter;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getLeft() {
        return left;
    }

    public float getRight() {
        return right;
    }

    public float getTop() {
        return top;
    }

    public float getBottom() {
        return bottom;
    }
}
