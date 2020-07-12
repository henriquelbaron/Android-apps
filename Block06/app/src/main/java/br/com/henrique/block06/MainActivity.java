package br.com.henrique.block06;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView imageView_grande = findViewById(R.id.imagem_grande);
        imageView_grande.setImageResource(R.drawable.mysql);

        ImageView imageView_pequena = findViewById(R.id.imagem_pequena);
        SeekBar tuner = findViewById(R.id.seekBar_tuner);
        tuner.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int lastProgress;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                lastProgress = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                imageView_grande.setColorFilter(Color.argb(255,0,lastProgress,255-lastProgress));
            }
        });
    }
}
