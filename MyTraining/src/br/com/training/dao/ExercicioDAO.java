package br.com.training.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import br.com.training.entidades.Exercicio;

public class ExercicioDAO extends SQLiteOpenHelper {
	private List<Exercicio> 		listaexercicio		= new ArrayList<Exercicio>();
	private static final int 		CODIGOEXERCICIO		= 0;
	private static final int		DESCRICAOEXERCICO	= 1;
	private static final int		CODIGOGRUPO			= 2;
	private static final int		DESCRICAOGRUPO		= 3;
	private static final String[]	COLS				= {"codigo", "descricao"};
	
	public ExercicioDAO(Context context){
		super(context, MyDataBase.TBEXERCICIO, null, MyDataBase.VERSAO);
	}
	
	@Override
	public void onCreate(SQLiteDatabase arg0) {	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
	
	public void salvar(Exercicio exercicio){
		
	}

	public void excluir(Exercicio exercicio){
		
	}
	
	public List<Exercicio> listarExercicio(){
		SQLiteDatabase 	db 		= null;
		Cursor			cursor 	= null;
		listaexercicio.clear();
		try {
			db = getReadableDatabase();
			cursor = db.query(MyDataBase.TBEXERCICIO, COLS, null,null, null, null, null);
			while(cursor.moveToNext()){
				Exercicio exercicio = new Exercicio();
				exercicio.setCodigo(cursor.getInt(CODIGOEXERCICIO));
				exercicio.setDescricao(cursor.getString(DESCRICAOEXERCICO));
				listaexercicio.add(exercicio);
			}
		} catch (SQLException e) {
			Log.e("Erro na consulta EXERCICIO", e.toString());
		}finally{
			if (cursor 	!= null) cursor.close();
			if (db		!= null) db.close();
		}
		return listaexercicio;
	}
}
