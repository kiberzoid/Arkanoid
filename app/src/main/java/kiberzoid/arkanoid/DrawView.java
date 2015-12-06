package kiberzoid.arkanoid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawView extends SurfaceView implements SurfaceHolder.Callback {
    private Paint pCircle;
    private Paint pRect;
    private DrawThread d_thread;
    private Ball ball;
    private Platform platform;
    private float width;
    private float height;
    private int speed;

    public DrawView(Context context,int speed) {
        super(context);
        this.speed = speed;
        this.getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Rect surfaceFrame = holder.getSurfaceFrame();
        width = surfaceFrame.width();
        height = surfaceFrame.height();
        ball = new Ball(width / 2, 40, 20, speed, speed); //Ball(int xPos,int yPos, int radius,int xSpeed, int ySpeed)
        platform = new Platform(width/4,height-30,width/4+150,height-60); //Platform(float left, float top, float right, float bottom)
        pCircle = new Paint();
        pRect = new Paint();

        pCircle.setColor(Color.BLUE);
        pCircle.setStrokeWidth(10);
        pRect.setColor(Color.GREEN);
        //pRect.setStyle(Paint.Style.STROKE);

        d_thread = new DrawThread(holder);
        d_thread.setRunning(true);
        d_thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        d_thread.setRunning(false);
        while (retry) {
            try {
                d_thread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            float xCenter = event.getX();
            float yCenter = event.getY();
            platform.update(xCenter,DrawView.this.width);
        }
        return false;
    }

    private class DrawThread extends Thread {
        public  boolean running = false;
        public SurfaceHolder holder;

        public DrawThread(SurfaceHolder holder) {
            this.holder = holder;
        }

        public void setRunning(boolean running) {
            this.running = running;
        }

        @Override
        public void run() {
            Canvas canvas;
            while (running) {
                canvas = null;
                try {
                    canvas = holder.lockCanvas();
                    if (canvas == null)
                        continue;
                    DrawView.this.platform.drawPlatform(canvas, DrawView.this.pRect);
                    DrawView.this.ball.update(DrawView.this.width, DrawView.this.height, DrawView.this.platform);
                    DrawView.this.ball.drawBall(canvas, DrawView.this.pCircle);
                    if(DrawView.this.ball.get_fail()){
                        setRunning(false);
                    }
                } finally {
                    if (canvas != null)
                        holder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
