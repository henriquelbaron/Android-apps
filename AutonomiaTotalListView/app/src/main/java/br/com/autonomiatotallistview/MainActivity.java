package br.com.autonomiatotallistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText nomeText, kmText, gasolinaText;
    private TextView mediaFrota;
    private List<Double> resultados;
    private List<String> resultadosString;
    private ListView lvResultados;
    private ArrayAdapter<String> stringArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nomeText = findViewById(R.id.inputNome);
        kmText = findViewById(R.id.inputKm);
        gasolinaText = findViewById(R.id.inputGasolina);
        lvResultados = findViewById(R.id.list);
        mediaFrota = findViewById(R.id.mediaFrota);
        resultados = new ArrayList<>();
        resultadosString = new ArrayList<>();
        stringArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                resultadosString
        );
        lvResultados.setAdapter(stringArrayAdapter);
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
            Double resultado = kmDouble / gasolinaDouble;
            resultados.add(resultado);
            builder.append(nome);
            builder.append(" - ");
            builder.append(String.format("%.1f", resultado));
            builder.append(" km/L ");
            stringArrayAdapter.add(builder.toString());
            Double media = 0.0;
            for (Double res : resultados) {
                media += res;
            }
            media = media / resultados.size();
            mediaFrota.setText(String.format("%.1f", media) + " km/L");
            limparAction(view);
        }
    }
}
