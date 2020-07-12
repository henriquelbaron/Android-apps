package br.com.exemplolistview.control;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import br.com.exemplolistview.MainActivity;
import br.com.exemplolistview.model.dao.EstadoDao;
import br.com.exemplolistview.model.dao.PaisDao;
import br.com.exemplolistview.model.vo.Estado;
import br.com.exemplolistview.model.vo.Pais;

public class MainControl {

    private MainActivity activity;
    private ArrayAdapter<Estado> adapterEstado;
    private ArrayAdapter<Pais> adapterPais;
    private List<Estado> estados;
    private Estado estado;

    private PaisDao paisDao;
    private EstadoDao estadoDao;

    public MainControl(MainActivity activity) {
        this.activity = activity;
        paisDao = new PaisDao(this.activity);
        estadoDao = new EstadoDao(this.activity);
        configListViewEstados();
        configSpinner();
    }

    public void configSpinner() {
        try {
            paisDao.getDao().createIfNotExists(new Pais(1, "Brasil"));
            paisDao.getDao().createIfNotExists(new Pais(2, "Argentina"));
            paisDao.getDao().createIfNotExists(new Pais(3, "Uruguai"));
            adapterPais = new ArrayAdapter<>(
                    activity,
                    android.R.layout.simple_list_item_1,
                    paisDao.getDao().queryForAll()
            );
            activity.getSpPaises().setAdapter(adapterPais);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void configListViewEstados() {

        try {
            estados = estadoDao.getDao().queryForAll();
            adapterEstado = new ArrayAdapter<>(
                    activity,
                    android.R.layout.simple_list_item_1,
                    estados
            );
            activity.getLvEstado().setAdapter(adapterEstado);
            addCliqueCurtoLvEstado();
            addCliqueLongoLvEstado();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void addCliqueLongoLvEstado() {
        activity.getLvEstado().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                estado = adapterEstado.getItem(position);
                confirmarExclusaoAction(estado);
                return true;
            }
        });
    }

    private void addCliqueCurtoLvEstado() {
        activity.getLvEstado().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                estado = adapterEstado.getItem(position);
                AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
                alerta.setTitle("Editar Estado");
                alerta.setMessage("Deseja editar " + estado + "?");
                alerta.setIcon(android.R.drawable.ic_menu_edit);
                alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        estado = null;
                    }
                });
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        popularFormAction(estado);
                    }
                });
                alerta.show();
            }
        });
    }

    public void cadastrar() {
        Estado estado = getEstadoForm();
        try {
            estadoDao.getDao().createIfNotExists(estado);
            adapterEstado.add(estado);
            limparForm();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Estado getEstadoForm() {
        Estado e = new Estado();
        e.setNome(activity.getEditNome().getText().toString());
        e.setUf(activity.getEditUF().getText().toString());
        e.setPais((Pais) activity.getSpPaises().getSelectedItem());
        return e;
    }

    public void limparForm() {
        activity.getEditNome().setText("");
        activity.getEditUF().setText("");
    }

    public void popularFormAction(Estado e) {
        activity.getEditNome().setText(e.getNome());
        activity.getEditUF().setText(e.getUf());
    }

    public void editar(Estado newEstado) {
        estado.setNome(newEstado.getNome());
        estado.setUf(newEstado.getUf());
        estado.setPais(newEstado.getPais());
        adapterEstado.notifyDataSetChanged();
        try {
            if (estadoDao.getDao().update(estado) > 0) {
                Toast.makeText(activity, "Sucesso", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity, "Tente Novamente", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        estado = null;
    }


    public void confirmarExclusaoAction(final Estado e) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
        alerta.setTitle("Excluir estado");
        alerta.setMessage("Deseja realmente excluir o estado " + e.getNome() + "?");
        alerta.setIcon(android.R.drawable.ic_delete);
        alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                estado = null;
            }
        });
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    estadoDao.getDao().delete(e);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                adapterEstado.remove(e);
                estado = null;
            }
        });
        alerta.show();
    }

    public void salvarAction() {
        if (estado == null) {
            cadastrar();
        } else {
            editar(getEstadoForm());
        }
        limparForm();
    }
}
