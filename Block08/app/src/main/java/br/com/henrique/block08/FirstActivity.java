package br.com.henrique.block08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enter(View v) {
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        float rating = ratingBar.getRating();
        Intent goToSecond = new Intent();
        goToSecond.setClass(this,SecondActivity.class);
        goToSecond.putExtra("nbStarts",rating);
        startActivity(goToSecond);
        finish();
    }
}
