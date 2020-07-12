package br.com.exemplolistview.model.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import br.com.exemplolistview.model.vo.Estado;
import br.com.exemplolistview.model.vo.Pais;

public class MyORMLiteHelper extends OrmLiteSqliteOpenHelper{
    //Configuração do banco de dados
    private static final String DATABASE_NAME = "atlas.db";
    private static final int DATABASE_VERSION = 2;

    public MyORMLiteHelper(Context c){
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Pais.class);
            TableUtils.createTableIfNotExists(connectionSource, Estado.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, Pais.class, true);
            TableUtils.dropTable(connectionSource, Estado.class, true);

            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}