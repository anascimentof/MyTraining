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
import br.com.training.entidades.Teste;

public class TesteDAO extends SQLiteOpenHelper  {
	
	private static final String[] 	COLS	= {"cdTeste" , "dsTeste"};
	private static final int		CDTESTE	= 0;
	private static final int		DSTESTE	= 1;
	private List<Teste>				listaTeste = new ArrayList<Teste>();
	
	public TesteDAO(Context context) {
		super(context, MyDataBase.BANCO, null, MyDataBase.VERSAO);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
 
	public void salvar(Teste teste){
		ContentValues 	dados;
		long			id = teste.getCdTeste();
		SQLiteDatabase	db = getWritableDatabase();
		db.beginTransaction();
		try{
			dados = obterContentValues(teste);
			if (id != 0){
				db.update(MyDataBase.TBTESTE, dados, "cdTeste = ?", new String[]{teste.getCdTesteString()});
			}else{
				db.insert(MyDataBase.TBTESTE, null, dados);
			}
			db.setTransactionSuccessful();
		}catch (SQLException e) {
			Log.e("Erro ao inserir/update na tbTeste.", e.toString());
		}finally{
			db.endTransaction();
			if (db != null) db.close();
		}
	}
	
	public ContentValues obterContentValues(Teste teste){
		ContentValues values = new ContentValues();
		if (teste.getCdTeste() != 0)
			values.put("cdTeste", teste.getCdTeste());
		values.put("dsTeste", teste.getDsTeste());
		return values;
	}

	public List<Teste> listar(){
		SQLiteDatabase  db = null;
		Cursor			cursor = null;
		try{
			db = getReadableDatabase();
			cursor = db.query(MyDataBase.TBTESTE, COLS, null, null, null , null, null);
			while (cursor.moveToNext()){
				Teste teste = new Teste();
				teste.setCdTeste(cursor.getInt(CDTESTE));
				teste.setDsTeste(cursor.getString(DSTESTE));
				listaTeste.add(teste);
			}
		}catch(SQLException e){
			Log.e("Erro ao consultar lista teste: ", e.toString());
			return null;
		}finally{
			if(cursor != null) cursor.close();
			if(db != null) db.close();
		}
		return listaTeste;
	}
	
	public void deletar(Teste teste){
		SQLiteDatabase db = getWritableDatabase();
		db.beginTransaction();
		try{
			db.delete(MyDataBase.TBTESTE, "cdteste=?", new String[]{teste.getCdTesteString()} );
			db.setTransactionSuccessful();
		}catch(SQLException e){
			Log.e("Deletar teste", e.toString());			
		}finally{
			db.endTransaction();
			if(db != null) db.close();
		}
	}
	
}

