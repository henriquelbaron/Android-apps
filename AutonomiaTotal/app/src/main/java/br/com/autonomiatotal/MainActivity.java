package br.com.autonomiatotal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText nomeText, kmText, gasolinaText;
    private TextView mediaFrota;
    private LinearLayout layout;
    private List<Double> resultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nomeText = findViewById(R.id.inputNome);
        kmText = findViewById(R.id.inputKm);
        gasolinaText = findViewById(R.id.inputGasolina);
        layout = findViewById(R.id.resultadosLayout);
        mediaFrota = findViewById(R.id.mediaFrota);
        resultados = new ArrayList<>();
    }

    public void limparAction(View view) {
        nomeText.setText("");
        kmText.setText("");
        gasolinaText.setText("");
    }

    public void calcularAction(View view) {
        String nome = nomeText.getText().toString();
        String gasolina = gasolinaText.getText().toString();
        String km = kmText.getText().toString();
        if (nome == null || nome.isEmpty()) {
            nomeText.setError("Campo Obrigatório");
            Toast.makeText(this, "Campo Obrigatório", Toast.LENGTH_SHORT).show();
        } else if (gasolina == null || gasolina.isEmpty()) {
            gasolinaText.setError("Campo Obrigatório");
            Toast.makeText(this, "Campo Obrigatório", Toast.LENGTH_SHORT).show();
        } else if (km == null || km.isEmpty()) {
            kmText.setError("Campo Obrigatório");
            Toast.makeText(this, "Campo Obrigatório", Toast.LENGTH_SHORT).show();
        } else {
            StringBuilder builder = new StringBuilder();
            Double kmDouble = Double.valueOf(km);
            Double gasolinaDouble = Double.valueOf(gasolina);
            Double resultado = kmDouble/gasolinaDouble;
            resultados.add(resultado);
            builder.append(nome);
            builder.append(" - ");
            builder.append(String.format("%.1f",resultado));
            builder.append(" km/L ");
            TextView textView = new TextView(this);
            textView.setGravity(Gravity.CENTER);
            textView.setText(builder.toString());
            layout.addView(textView);
            Double media = 0.0;
            for (Double res: resultados){
                media += res;
            }
            media = media/resultados.size();
            mediaFrota.setText(String.format("%.1f",media) + " km/L");
            limparAction(view);
        }
    }
}
