package kiberzoid.arkanoid;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Platform {
    private float xStart;
    private float yStart;
    private float xStop;
    private float yStop;
    private int strokeWidth;

    public Platform(float xStart, float yStart, float xStop, float yStop, int strokeWidth) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xStop = xStop;
        this.yStop = yStop;
        this.strokeWidth = strokeWidth;
    }

    public void drawPlatform(Canvas canvas, Paint p) {
        canvas.drawLine(xStart, yStart, xStop, yStop, p);
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
