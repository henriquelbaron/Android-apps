package br.com.exemplolistview.model.dao;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

import br.com.exemplolistview.model.helpers.DaoHelper;
import br.com.exemplolistview.model.vo.Pais;

public class PaisDao extends DaoHelper<Pais> {

    public PaisDao(Context c) {
        super(c, Pais.class);
    }

    public List<Pais> queryForAll() throws SQLException {
        return getDao().queryForAll();
    }

}
