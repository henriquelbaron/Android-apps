package br.com.henrique.convertmilestokm;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Button btConverteKmParaMilhas, btCorverteMilhasParaKm,btLimparCampos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btConverteKmParaMilhas = findViewById(R.id.btConverteKmParaMilhas);
        btCorverteMilhasParaKm = findViewById(R.id.btCorverteMilhasParaKm);
        btLimparCampos = findViewById(R.id.btLimpar);
    }

    public void toDo(View v) {
        EditText textBoxMiles = findViewById(R.id.miles);
        EditText textBoxKm = findViewById(R.id.kilometros);
        DecimalFormat format = new DecimalFormat("#.##");
        try {
            if (v.equals(btCorverteMilhasParaKm)) {
                Double vMiles = Double.valueOf(textBoxMiles.getText().toString().replace(",", "."));
                double vKm = vMiles / 0.62137;
                textBoxKm.setText(format.format(vKm));
            }
            if (v.equals(btConverteKmParaMilhas)) {
                Double vKm = Double.valueOf(textBoxKm.getText().toString().replace(",", "."));
                double vMiles = vKm * 0.62137;
                textBoxMiles.setText(format.format(vMiles));
            }
            if(v.equals(btLimparCampos)){
                textBoxKm.setText("");
                textBoxMiles.setText("");
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Número inválido!", Toast.LENGTH_LONG).show();
        }
    }
}
