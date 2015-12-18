package kiberzoid.arkanoid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String APP_PREFERENCES = "arkanoidSettings";
    public static final String APP_PREFERENCES_SPEED = "speed_value";
    private SharedPreferences aSettings;

    private int speed = 20;
    private SeekBar s_bar;
    private TextView speed_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        speed_view = (TextView) findViewById(R.id.speed_view);
        Button ok_button = (Button) findViewById(R.id.setting_ok_button);
        ok_button.setOnClickListener(this);
        aSettings = this.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        s_bar = (SeekBar) findViewById(R.id.seekBar);
        speed_view.setText(s_bar.getProgress()+"");
        s_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                speed = progress;
                speed_view.setText(progress+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = aSettings.edit();
        editor.putInt(APP_PREFERENCES_SPEED,speed);
        editor.apply();
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.setting_ok_button:
                Intent intent_start = new Intent(this, StartActivity.class);
                startActivity(intent_start);
                break;
            default:
                break;
        }
    }
}
