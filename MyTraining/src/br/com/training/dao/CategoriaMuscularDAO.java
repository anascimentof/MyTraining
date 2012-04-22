package br.com.training.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import br.com.training.entidades.CategoriaMuscular;

public class CategoriaMuscularDAO extends SQLiteOpenHelper {
	private static final String 	BANCO					= "MyTraining.db";
	private static final int 		VERSAO					= 1;
	private static final String		TABELA					= "TbCategoriaMuscular";
	private List<CategoriaMuscular> listaCategMuscular		= new ArrayList<CategoriaMuscular>();
	private static final int 		CODIGO 					= 0;
	private static final int 		DESCRICAO 				= 1;
	private ContentValues 			values					= new ContentValues();
	private static final String[]	COLS					= { "codigo" , "descricao" };
	
	public CategoriaMuscularDAO(Context context){
		super(context, BANCO , null , VERSAO);
	}
	@Override	
	public void onCreate(SQLiteDatabase arg0) {}
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {}

	// METODO DE SALVAR ( INCLUIR / ALTERAR ) NA BASE
	public void salvar(CategoriaMuscular ctm){
		SQLiteDatabase db = getWritableDatabase();
		db.beginTransaction();
		long id = ctm.getCodigo();
		try {
			values = obterContentValues(ctm);
			if (id != 0){
				db.update(TABELA, values, "codigo=?", new String[] { ctm.getCodigoString() } );
			}else{
				db.insert(TABELA, null, values);
			}
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			Log.e("CategoriaMuscular", "Erro inserir/atualizar " + e.getStackTrace() );
		}finally{
			db.endTransaction();
			if (db!= null) db.close();			
		}
	}
	// METODO DE EXCLUSÃO DE CATEGORIA NO BANCO
	public void deletar(CategoriaMuscular ctm){
		SQLiteDatabase db = getWritableDatabase();
		db.beginTransaction();
		try {
			db.delete(TABELA, "codigo=?", new String[] { ctm.getCodigoString() } ); 
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			Log.e("CategoriaMuscular", "Erro ao deletar: " + e.getStackTrace() );
		}finally{
			db.endTransaction();
			if (db != null) db.close();
		}
		
	}
	// METODO DE RETORNAR UMA LISTA DE CATEGORIAS MUSCULARES DA BASE
	public List<CategoriaMuscular> listar(){
		SQLiteDatabase	db		= null;
		Cursor 			cursor	= null;
		try{
			db	=	getReadableDatabase();
			listaCategMuscular.clear();
			cursor = db.query(TABELA, COLS, null, null, null, null, null);
			while (cursor.moveToNext()) {
				CategoriaMuscular ctm = new CategoriaMuscular();
				ctm.setCodigo(cursor.getInt(CODIGO));
				ctm.setDescricao(cursor.getString(DESCRICAO));
				listaCategMuscular.add(ctm);
			}
		}catch(SQLiteException e){
			Log.e("Erro listar Categoria Muscular", e.toString());
		}finally{
			if (cursor != null) cursor.close();
			if (db != null) 	db.close();
		}
		return listaCategMuscular;
	}
	
	//METODO POPULAR O CONTENTVALUES COM PARAMENTOS E VALORES PARA GRAVAR/ATUALIZAR NO BANCO
	public ContentValues obterContentValues(CategoriaMuscular ctm){
		if (ctm.getCodigo() != 0) values.put("codigo", ctm.getCodigo());
		values.put("descricao", ctm.getDescricao());
		return values;		
	}
	
}
