package kiberzoid.arkanoid;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends AppCompatActivity {
    private DrawView dv;
    public static final String APP_PREFERENCES = "arkanoidSettings";
    public static final String APP_PREFERENCES_SPEED = "speed_value";
    private SharedPreferences aSettings;

    private int speed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        aSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        speed = aSettings.getInt(APP_PREFERENCES_SPEED, 20);
        setContentView(new DrawView(this,speed));
       // dv = new DrawView(this);
        //View gameView = dv;
        //setContentView(R.layout.activity_game);
        //View view =(findViewById(R.id.DW));
        //((ViewGroup) view).addView(gameView);
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(aSettings==null)
            aSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        speed = aSettings.getInt(APP_PREFERENCES_SPEED, 20);
    }
}