package br.com.henrique.block04;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    MediaPlayer musicas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        musicas = MediaPlayer.create(this, R.raw.black_coast);
        Switch loop = findViewById(R.id.loop);
        loop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                musicas.setLooping(isChecked);
            }
        });
    }

    public void playMusic(View v){
        musicas.start();
    }
    public void pauseMusic(View v){
        if(musicas.isPlaying()){
            musicas.pause();
        }
    }
}
