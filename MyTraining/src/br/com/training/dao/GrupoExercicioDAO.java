package br.com.training.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import br.com.training.entidades.GrupoExercicio;

public class GrupoExercicioDAO extends SQLiteOpenHelper {
	private static final String[]	COLS				= {"codigo", "descricao"};
	private List<GrupoExercicio> 	listaGrupoExercicio	= new ArrayList<GrupoExercicio>();
	private static final int 		CODIGO				= 0;
	private static final int 		DESCRICAO			= 1;
	
	public GrupoExercicioDAO(Context context) {
		super(context, MyDataBase.BANCO, null, MyDataBase.VERSAO);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {}

	public void salvar(GrupoExercicio grupoexercicio){
		ContentValues 	dados;
		long			id = grupoexercicio.getCodigo();
		SQLiteDatabase	db = getWritableDatabase();
		db.beginTransaction();
		
		try {
			dados = obterContentValues(grupoexercicio);
			if (id != 0){
				db.update(MyDataBase.TBGRUPOEXERCIO, dados , "codigo=?",new String[]{ grupoexercicio.getCodigoStrign() } );
			}else{
				db.insert(MyDataBase.TBGRUPOEXERCIO, null, dados);
			}
		} catch (SQLException e) {
			Log.e("Erro ao inserir TBGRUPOEXERCICIO", e.toString());
		}
	}
	
	public ContentValues obterContentValues(GrupoExercicio grupoexercicio){
		ContentValues values = new ContentValues();
		if(grupoexercicio.getCodigo() != 0) values.put("codigo", grupoexercicio.getCodigo());
		values.put("descricao", grupoexercicio.getDescricao());
		return values;
	}
	
	public List<GrupoExercicio> listar () {
		SQLiteDatabase 	db 		= null;
		Cursor 			cursor 	= null; 
		
		try {
			db 		= getReadableDatabase();
			cursor 	= db.query(MyDataBase.TBGRUPOEXERCIO, COLS, null, null, null, null, null);
			while (cursor.moveToNext()) {
				GrupoExercicio grupo = new GrupoExercicio();
				grupo.setCodigo(cursor.getInt(CODIGO));
				grupo.setDescricao(cursor.getString(DESCRICAO));
				listaGrupoExercicio.add(grupo);
			}
		} catch (SQLException e) {
			Log.e("Erro no metodo litar", e.toString());
			return null;
		}finally{
			if (cursor  != null) cursor.close();
			if (db 		!= null) db.close();	
		}
		
		return listaGrupoExercicio;
	}
	
	public void excluir(GrupoExercicio grupoexercicio){
		SQLiteDatabase db = getWritableDatabase();
		db.beginTransaction();
		try {
			db.delete(MyDataBase.TBGRUPOEXERCIO, "codigo=?", new String[]{grupoexercicio.getCodigoStrign()} );
			db.setTransactionSuccessful();
		} catch (Exception e) {
			Log.e("Erro ao excluir grupo exercicio", e.toString());
		}finally{
			db.endTransaction();
			if (db != null) db.close();
		}
	}
	
}
