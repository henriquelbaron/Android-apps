package br.com.henrique.block01;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonBlue, buttonRed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonBlue = (Button) findViewById(R.id.buttonBlue);
        buttonRed = new Button(getApplicationContext());
        buttonRed.setTextColor(Color.BLACK);
        buttonRed.setBackgroundColor(Color.parseColor("#FD9BF3"));
        buttonRed.setText("Aparece pop up");
        buttonRed.setOnClickListener(this);
        RelativeLayout relativeLayout = findViewById(R.id.relativeLayout_mainScreen);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, buttonBlue.getId());
        relativeLayout.addView(buttonRed, params);
    }

    public void toDo(View v) {
        if (v.equals(buttonBlue)) {
            v.setVisibility(View.INVISIBLE);
        }
        if (v.equals(buttonRed)) {
            Toast.makeText(getApplicationContext(), "to do to do to do...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        toDo(v);
    }
}
