package br.com.exemplolistview.model.dao;

import android.content.Context;

import br.com.exemplolistview.model.helpers.DaoHelper;
import br.com.exemplolistview.model.vo.Estado;

public class EstadoDao extends DaoHelper<Estado> {

    public EstadoDao(Context c) {
        super(c, Estado.class);
    }

}
