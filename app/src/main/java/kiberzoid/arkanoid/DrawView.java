package kiberzoid.arkanoid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

public class DrawView extends SurfaceView implements SurfaceHolder.Callback {
    private Context context;
    //кисти
    private Paint pCircle;
    private Paint pRect;
    private Paint pBlock;

    private DrawThread d_thread;
    private Ball ball;
    private Platform platform;
    private float width; // ширина экрана
    private float height;// высота экрана
    private float widthBlock=100; //ширинаблока
    private float heightBlock=30; //высота блока
    private int speed;
    private ArrayList<Block> blocks; // массив блоков
    private int countBlocks = 5; // кол-во блоков
    private SoundPool sp;
    MediaPlayer mediaPlayer;

    public DrawView(Context context,int speed) {
        super(context);
        this.context = context;
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
        sp = new SoundPool(3, AudioManager.STREAM_MUSIC,0);
        mediaPlayer = MediaPlayer.create(context,R.raw.background);
        blocks = new ArrayList<>();
        randomBlocks();

        pCircle = new Paint();
        pRect = new Paint();
        pBlock = new Paint();

        pCircle.setColor(Color.BLUE);
        pCircle.setStrokeWidth(10);
        pRect.setColor(Color.GREEN);
        pBlock.setColor(Color.RED);
        sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {

            }
        });
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

    //метод случайно заполняет массив блоков
    public void randomBlocks(){
        Random random = new Random();
        float x=0;
        float y=0;
        for (int i=0;i<countBlocks;i++) {
            do {
                x = random.nextFloat() * (width - widthBlock) + widthBlock / 2;
                y = random.nextFloat() * (height / 2 - heightBlock) + heightBlock / 2;
            } while (intersectionBlocks(x,y,widthBlock,heightBlock));
            blocks.add(new Block(x, y, widthBlock, heightBlock));
        }
    }

    //метод вернет истину если новый блок накладывается на уже существующий
    public boolean intersectionBlocks (float x, float y, float widthBlock, float heightBlock){
        for (int i=0;i<blocks.size();i++){
            float x1 = blocks.get(i).getRight() - (x - widthBlock/2);
            float x2 = (x + widthBlock/2) - blocks.get(i).getLeft();

            float y1 = blocks.get(i).getTop() - (y - heightBlock/2);
            float y2 = (y + heightBlock/2) - blocks.get(i).getBottom();

            if ( ( Math.max(Math.abs(x1),Math.abs(x2))<=2*widthBlock ) &&
                (Math.max(Math.abs(y1),Math.abs(y2))<=2*heightBlock)){
                return  true;
            }
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
            int p_otskok_id = sp.load(DrawView.this.context, R.raw.bulbpop, 1);
            int fail_id = sp.load(DrawView.this.context, R.raw.fail, 1);
            Canvas canvas;
            mediaPlayer.start();
            while (running) {
                canvas = null;
                try {
                    canvas = holder.lockCanvas();

                    if (canvas == null)
                        continue;
                    DrawView.this.platform.drawPlatform(canvas, DrawView.this.pRect);
                    DrawView.this.ball.update(DrawView.this.width, DrawView.this.height, DrawView.this.platform, DrawView.this.blocks, DrawView.this.sp, p_otskok_id, fail_id);
                    DrawView.this.ball.drawBall(canvas, DrawView.this.pCircle);
                    for (int i=0; i<blocks.size();i++){
                        DrawView.this.blocks.get(i).drawBlock(canvas,pBlock);
                    }
                    if(DrawView.this.ball.get_fail()){
                        setRunning(false);
                    }
                } finally {
                    if (canvas != null)
                        holder.unlockCanvasAndPost(canvas);
                }
            }
            mediaPlayer.stop();
        }
    }
}
