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
import br.com.training.entidades.Treino;

public class TreinoDAO extends SQLiteOpenHelper {
	private List<Treino> 			listaTreino  = new ArrayList<Treino>();
	private static final String[]	COLUNAS		 = {"codigo", "descricao", "tempoDuracao"};
	private static final int		CODIGO		 = 0;
	private static final int		DESCRICAO	 = 1;
	private static final int		TEMPODURACAO = 2;
	
	
	public TreinoDAO(Context context) {
		super(context, MyDataBase.BANCO, null, MyDataBase.VERSAO);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {}

	public boolean existeCodigoTreino(String codigoTreino){
		
		SQLiteDatabase 	db 		= getReadableDatabase();
		Cursor 			cursor	= null;
		
		try{
			cursor = db.query(MyDataBase.TBTREINO, COLUNAS, "codigo=?", new String[]{codigoTreino}, null, null, null);
			
			if(cursor.getCount()>0){
				return true;
			}
			
		}catch (SQLException e) {
			Log.e("Erro existeTreino", e.toString());
		}finally{
			if(cursor!=null)	cursor.close();
			if(db!=null) 		db.close();
		}

		return false;
		
	}
	
	public List<Treino> listar(){
		
		SQLiteDatabase 	db 		= getReadableDatabase();
		Cursor 			cursor 	= null;

		try{
			listaTreino.clear();
			cursor = db.query(MyDataBase.TBTREINO, COLUNAS, null, null, null, null, null);
			while (cursor.moveToNext()) {
				Treino treino = new Treino();
				treino.setCodigo(cursor.getString(CODIGO));
				treino.setDescricao(cursor.getString(DESCRICAO));
				treino.setTempoDuracao(cursor.getString(TEMPODURACAO));
				listaTreino.add(treino);
			}
			
		}catch (Exception e) {
			Log.e("Erro ao listar treino", e.toString());
		}finally{
			if(cursor!=null) cursor.close();
			if(db    !=null) db.close();		
		}
	
		return listaTreino;
		
	}
	
	public void salvar(Treino treino, String modo){

		SQLiteDatabase 	db = getWritableDatabase();
		ContentValues	dados;
		
		try {
			db.beginTransaction();
			
			dados = obterContentValues(treino);

			if(modo.equals("N")){
				db.insert( MyDataBase.TBTREINO, null, dados );
			}else{
				if(modo.equals("A")){
				db.update( MyDataBase.TBTREINO, dados, "codigo=?", new String[]{ treino.getCodigo() } );
				}
			}
			
			db.setTransactionSuccessful();
			
		} catch (Exception e) {
			Log.e("Erro em salvar treino", e.toString());
		} finally{
			db.endTransaction();
			if(db != null) db.close();
		}
		
	}
	
	public void deletar(Treino treino){
		SQLiteDatabase db = getWritableDatabase();
		
		try {
			db.beginTransaction();
			db.delete(MyDataBase.TBTREINO, "codigo=?",new String[]{treino.getCodigo()});						
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			Log.e("Erro ao deletar treino", e.toString());
		} finally{
			db.endTransaction();
			if(db!=null) db.close();
		}
	}
	
	public ContentValues obterContentValues(Treino treino){
		ContentValues values = new ContentValues();
		if(treino.getCodigo() != "") values.put("codigo", treino.getCodigo());
		values.put("descricao", 	treino.getDescricao());
		values.put("tempoDuracao", 	treino.getTempoDuracao() );
		return values;
	}
}
