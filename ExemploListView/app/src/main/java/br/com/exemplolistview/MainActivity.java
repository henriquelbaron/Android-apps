package br.com.exemplolistview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import br.com.exemplolistview.control.MainControl;
import br.com.exemplolistview.model.vo.Estado;

public class MainActivity extends AppCompatActivity {

    private MainControl control;
    private EditText editNome, editUF;
    private ListView lvEstado;
    private Estado estado;
    private Spinner spPaises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        control.configSpinner();
    }
    private void initialize() {
        spPaises = findViewById(R.id.spinner);
        editNome = findViewById(R.id.editEstado);
        editUF = findViewById(R.id.uf);
        lvEstado = findViewById(R.id.list);
        control = new MainControl(this);
    }

    public Spinner getSpPaises() {
        return spPaises;
    }

    public void setSpPaises(Spinner spPaises) {
        this.spPaises = spPaises;
    }

    public void salvar(View view) {
        control.salvarAction();
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public MainControl getControl() {
        return control;
    }

    public void setControl(MainControl control) {
        this.control = control;
    }

    public EditText getEditNome() {
        return editNome;
    }

    public void setEditNome(EditText editNome) {
        this.editNome = editNome;
    }

    public EditText getEditUF() {
        return editUF;
    }

    public void setEditUF(EditText editUF) {
        this.editUF = editUF;
    }

    public ListView getLvEstado() {
        return lvEstado;
    }

    public void setLvEstado(ListView lvEstado) {
        this.lvEstado = lvEstado;
    }

    public void novoPaisAction(View view) {
        Intent intent = new Intent(this, CrudPaisActivity.class);
        this.startActivity(intent);
    }
}