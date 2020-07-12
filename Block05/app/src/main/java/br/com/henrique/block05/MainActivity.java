package br.com.henrique.block05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import java.util.concurrent.ConcurrentHashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences("COLOR_PREF", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

        final RelativeLayout layout = findViewById(R.id.layout);
        if (preferences.contains("colorId")) {
            layout.setBackgroundColor(preferences.getInt("colorId", 0));
        }
        RadioGroup colors = findViewById(R.id.radioGroupColors);
        if (preferences.contains("radioSelected")) {
            colors.check(preferences.getInt("radioSelected", 0));
        }

        colors.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int colorCode = 0;
                switch (checkedId) {
                    case R.id.btAmarelo:
                        colorCode = Color.YELLOW;
                        break;
                    case R.id.btAzul:
                        colorCode = Color.BLUE;
                        break;
                    case R.id.btVermelho:
                        colorCode = Color.RED;
                        break;
                }
                layout.setBackgroundColor(colorCode);
                editor.putInt("colorId", colorCode);
                editor.putInt("radioSelected", checkedId);
                editor.commit();
            }
        });
    }
}
