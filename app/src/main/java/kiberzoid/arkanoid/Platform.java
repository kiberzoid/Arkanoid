package kiberzoid.arkanoid;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Platform {
    private float xStart;
    private float yStart;
    private float xStop;
    private float yStop;
    private float strokeWidth;

    public Platform(float xStart, float yStart, float xStop, float yStop, int strokeWidth) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xStop = xStop;
        this.yStop = yStop;
        this.strokeWidth = strokeWidth;
    }

    public void drawPlatform(Canvas canvas, Paint p) {
        canvas.drawColor(Color.WHITE);
        canvas.drawLine(xStart, yStart, xStop, yStop, p);
    }

    public void update(float xStart, float xStop,int width) {
        float platformWidth = this.getWidth();
        if(xStart + platformWidth>width){
            this.xStart = width - platformWidth;
            this.xStop = this.xStart + platformWidth;
        }else {
            this.xStart = xStart;
            this.xStop = xStop;
        }
    }

    public float getStrokeWidth() {
        return this.strokeWidth;
    }

    public float getWidth() {
        return xStop - xStart;
    }

    public float get_xStart() {
        return xStart;
    }

    public float get_yStart() {
        return yStart;
    }

    public float get_xStop() {
        return xStop;
    }

    public float get_yStop() {
        return yStop;
    }

}
