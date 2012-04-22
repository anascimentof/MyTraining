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
import br.com.training.entidades.Equipamento;
import br.com.training.entidades.MarcaEquipamento;

public class EquipamentoDAO extends SQLiteOpenHelper {
	
	private static final String BANCO 				= "MyTraining.db"	;
	private static final String TABELA				= "TbEquipamento";
	private static final int 	VERSAO				= 1;
	private List<Equipamento> 	listaEquipamento 	= new ArrayList<Equipamento>();
	private static final int 	CODIGO 				= 0;
	private static final int 	DESCRICAO 			= 1;
	private static final int 	CDMARCA 			= 2;
	private static final int 	DSMARCA 			= 3;

	public EquipamentoDAO(Context context ) {
		super(context, BANCO, null, VERSAO);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	
	//METODO DO SALVAR (INSERIR / ATUALIZAR) 
	public void salvar(Equipamento equipamento){
		ContentValues 	dados;
		long 			id = equipamento.getCodigo();
		SQLiteDatabase 	db = getWritableDatabase();
		db.beginTransaction();
		try{
			dados = obterContetValues(equipamento);
			if (id != 0) { 
				db.update(TABELA, dados, "codigo=?", new String[] {String.valueOf(equipamento.getCodigo()) } );
			} else {			 
				db.insert(TABELA, null, dados);
			}
			db.setTransactionSuccessful();
		}catch (SQLException e) {
			Log.e("Equipamento", "Erro Inserir: " + e.toString());
		}finally{
			db.endTransaction();
			if(db != null) db.close();
		}
	}
	
	//METODO POPULAR O CONTENTVALUES COM PARAMETROS E VALORES PARA GRAVAR/ATUALIZAR NO BANCO
	public ContentValues obterContetValues(Equipamento equipamento){
		ContentValues values = new ContentValues();
		if (equipamento.getCodigo() != 0) values.put("codigo", equipamento.getCodigo());
		values.put("descricao", equipamento.getDescricao());
		values.put("cdmarca", equipamento.getMarca().getCodigo() );
		return values;
	}
	
	//METODO LISTAR EQUIPAMENTOS
	public List<Equipamento> listar(){
		SQLiteDatabase	db		= null;	
		Cursor 			cursor	= null;
		try{			
			db = getReadableDatabase();
			listaEquipamento.clear();
			cursor = db.rawQuery( "select a.codigo , a.descricao , b.codigo , b.descricao from TbEquipamento a " +
								  "inner join TbMarcaEquipamento b on " +
								  "a.cdMarca = b.codigo", null);
			while (cursor.moveToNext()) {
				Equipamento equipamento = new Equipamento();
				equipamento.setCodigo(cursor.getInt(CODIGO)); 		
				equipamento.setDescricao(cursor.getString(DESCRICAO)); 	
				MarcaEquipamento me = new MarcaEquipamento(cursor.getInt(CDMARCA) ,	cursor.getString(DSMARCA) );
				equipamento.setMarca(me);							
				listaEquipamento.add(equipamento);
			}
		}catch (SQLException e){
			Log.e("Equipamento", "Erro Listar: " + e.toString());
			return null;
		}finally{
			if (cursor	!= null) cursor.close();
			if (db 		!= null) db.close();
		}
		return listaEquipamento;		
	}
	
	//METODO DELETAR EQUIPAMENTOS NO BANCO
	public void deletar(Equipamento equipamento){
		SQLiteDatabase db = getWritableDatabase();
		db.beginTransaction();
		try{
			db.delete(TABELA, "codigo=?", new String[] { String.valueOf(equipamento.getCodigo()) }  );
			db.setTransactionSuccessful();
		}catch(SQLException e){
			Log.e("Equipamento", "Erro Deletar: " + e.toString());
		}finally{
			db.endTransaction();
			if(db != null) db.close();
		}
	}
	
}
