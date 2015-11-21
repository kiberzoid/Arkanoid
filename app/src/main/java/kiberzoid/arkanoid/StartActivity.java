package kiberzoid.arkanoid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button btn_start = (Button) findViewById(R.id.start_button);
        Button btn_settings = (Button) findViewById(R.id.settings_button);

        btn_start.setOnClickListener(this);
        btn_settings.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.start_button:
                Intent intent_start = new Intent(this, GameActivity.class);
                startActivity(intent_start);
                break;
            case R.id.settings_button:
                Intent intent_settings = new Intent(this,SettingsActivity.class);
                startActivity(intent_settings);
                break;
            default:
                break;
        }
    }
}
