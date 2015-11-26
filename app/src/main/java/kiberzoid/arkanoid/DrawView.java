package kiberzoid.arkanoid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawView extends SurfaceView implements SurfaceHolder.Callback {
    private Paint p;
    private DrawThread d_thread;
    private Ball ball;
    private int width;
    private int height;

    public DrawView(Context context) {
        super(context);
        this.getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Rect surfaceFrame = holder.getSurfaceFrame();
        width = surfaceFrame.width();
        height = surfaceFrame.height();
        p = new Paint();
        p.setColor(Color.BLUE);
        p.setStrokeWidth(10);
        ball = new Ball(width / 2, 30, 30, 20, 20);
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

            }
        }
    }

    class DrawThread extends Thread {
        private boolean running = false;
        private SurfaceHolder holder;

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
                    DrawView.this.ball.update(DrawView.this.width,DrawView.this.height);
                    DrawView.this.ball.drawBall(canvas, DrawView.this.p);
                } finally {
                    if (canvas != null)
                        holder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
