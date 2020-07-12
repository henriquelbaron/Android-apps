package br.com.henrique.block02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listAnimais = findViewById(R.id.list);
        final ArrayAdapter<CharSequence> adapterAnimais = ArrayAdapter.createFromResource(
                this,R.array.animaisArray, android.R.layout.simple_list_item_1);
        listAnimais.setAdapter(adapterAnimais);

        listAnimais.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String mensagem = getString(R.string.toastMensagem) + adapterAnimais.getItem(position);
                Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
