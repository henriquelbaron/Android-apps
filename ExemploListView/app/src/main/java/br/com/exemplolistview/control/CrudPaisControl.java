package br.com.exemplolistview.control;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.sql.SQLException;
import java.util.List;

import br.com.exemplolistview.CrudPaisActivity;
import br.com.exemplolistview.model.dao.PaisDao;
import br.com.exemplolistview.model.vo.Pais;

public class CrudPaisControl {

    private CrudPaisActivity activity;
    private ArrayAdapter<Pais> paisArrayAdapter;
    private List<Pais> paises;
    private Pais pais;

    private PaisDao paisDao;

    public CrudPaisControl(CrudPaisActivity activity) {
        this.activity = activity;
        paisDao = new PaisDao(this.activity);
        confListView();
    }

    public void confListView() {
        try {
            paises = paisDao.queryForAll();
            paisArrayAdapter = new ArrayAdapter<>(
                    activity,
                    android.R.layout.simple_list_item_1,
                    paises
            );
            activity.getListView().setAdapter(paisArrayAdapter);
            addCliqueCurtoListView();
            addCliqueLongoListView();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addCliqueLongoListView() {
        activity.getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                pais = paisArrayAdapter.getItem(position);
                confirmarExclusaoAction(pais);
                return true;
            }
        });
    }

    private void confirmarExclusaoAction(final Pais e) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
        alerta.setTitle("Excluir País");
        alerta.setMessage("Deseja realmente excluir o País " + e.getNome() + "?");
        alerta.setIcon(android.R.drawable.ic_delete);
        alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                pais = null;
            }
        });
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deletaPedido(e);
            }
        });
        alerta.show();
    }

    private void deletaPedido(final Pais p) {
        try {
            paisDao.getDao().delete(p);
            paises.remove(p);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        paisArrayAdapter.remove(p);
        pais = null;
    }

    private void addCliqueCurtoListView() {
        activity.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pais = paisArrayAdapter.getItem(position);
                AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
                alerta.setMessage("Deseja alterar " + pais + "?");
                alerta.setIcon(android.R.drawable.ic_menu_edit);
                alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pais = null;
                    }
                });
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getForm();
                    }
                });
                alerta.show();
            }
        });
    }

    private void getForm() {
        pais.setNome(activity.getNome().getText().toString());
    }

    private void edit() {
        try {
            paisDao.getDao().update(pais);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        paisArrayAdapter.notifyDataSetChanged();
        pais = null;
    }

    public void cadastrar() throws SQLException {
        pais = new Pais();
        getForm();
        paisDao.getDao().createIfNotExists(pais);
        paisArrayAdapter.add(pais);
    }

    public void salvar() throws SQLException {
        if (pais == null || pais.getId() == null) {
            cadastrar();
        } else {
            getForm();
            edit();
        }
        pais = null;
        activity.getNome().setText("");
    }

}
