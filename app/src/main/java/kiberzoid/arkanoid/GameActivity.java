package kiberzoid.arkanoid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new DrawView(this));
    }

    class DrawView extends SurfaceView implements SurfaceHolder.Callback{
        DrawThread dthread;

        public DrawView(Context context){
            super(context);
            this.getHolder().addCallback(this);
        }

        @Override
        public void surfaceCreated (SurfaceHolder holder){
            dthread = new DrawThread(holder);
            dthread.setRunning(true);
            dthread.start();
        }

        @Override
        public void surfaceChanged (SurfaceHolder holder, int format, int width, int height){

        }

        @Override
        public void surfaceDestroyed (SurfaceHolder holder){
            boolean retry = true;
            while(retry){
                try{
                    dthread.join();
                    retry = false;
                }catch(InterruptedException e){

                }
            }
        }

    }

    class DrawThread extends Thread{
        private boolean running = false;
        private SurfaceHolder holder;

        public DrawThread(SurfaceHolder holder){
            this.holder = holder;
        }
        public void setRunning(boolean running){
            this.running = running;
        }

        @Override
        public void run(){
            Canvas canvas;
            Paint p = new Paint();
            p.setColor(Color.BLUE);
            p.setStrokeWidth(10);
            int i = 50;
            while(running){
                canvas = null;
                try {
                    canvas = holder.lockCanvas();
                    if (canvas == null)
                        continue;
                    canvas.drawColor(Color.WHITE);
                    while(i<500) {
                        //try{Thread.sleep(2000);}catch(InterruptedException r){}
                       // canvas.drawColor(Color.WHITE);
                        canvas.drawCircle(i, 100, 30, p);
                        //canvas.drawColor(Color.WHITE);
                        i+=50;
                    }
                    setRunning(false);
                }finally{
                    if(canvas!=null)
                        holder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }


}
