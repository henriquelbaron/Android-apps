package br.com.exemplolistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.sql.SQLException;

import br.com.exemplolistview.control.CrudPaisControl;

public class CrudPaisActivity extends AppCompatActivity {

    private ListView listView;
    private EditText nome;
    private CrudPaisControl control;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_pais);
        nome = findViewById(R.id.nome);
        listView = findViewById(R.id.listPaises);
        control = new CrudPaisControl(this);
    }


    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    public EditText getNome() {
        return nome;
    }

    public void setNome(EditText nome) {
        this.nome = nome;
    }

    public void voltarAction(View view) {
        this.finish();
    }

    public void salvarAction(View view) {
        try {
            control.salvar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
