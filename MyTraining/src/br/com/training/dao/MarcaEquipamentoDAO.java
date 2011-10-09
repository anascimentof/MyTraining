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
import br.com.training.entidades.MarcaEquipamento;

public class MarcaEquipamentoDAO extends SQLiteOpenHelper {

	private static final String 	BANCO  = "MyTraining.db"	;
	private static final String 	TABELA = "TbMarcaEquipamento";
	private static final int 		VERSAO = 1;
	private static final String[]	COLS = {"codigo", "descricao" };
	private List<MarcaEquipamento>	listaMarcaEquipamento = new ArrayList<MarcaEquipamento>();
	private static final int		CODIGO = 0;
	private static final int		DESCRICAO = 1;
	
	// CONSTRUTOR DA CLASSE
	public MarcaEquipamentoDAO(Context context) {
		super(context, BANCO, null, VERSAO);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
	
	//METODO DO SALVAR (INSERIR / ATUALIZAR) 
	public void salvar(MarcaEquipamento marcaequipamento){
		ContentValues 	dados;
		long 			id = marcaequipamento.getCodigo();
		SQLiteDatabase 	db = getWritableDatabase();
		try{
			dados = obterContetValues(marcaequipamento);
			if (id != 0) { 
				db.update(TABELA, dados, "codigo=?", new String[] {marcaequipamento.getCodigoString()} );
			} else {			 
				db.insert(TABELA, null, dados);
			}
		}catch (SQLException e) {
			Log.e("MarcaEquipamento", "Erro Inserir: " + e.toString());
		}finally{
			if(db != null) db.close();
		}
	}
	
	//METODO PARA POPULAR CONTENTVALUES O QUAL SERÁ UTILIZADO PARA PASSAR PARAMETROS E VALORES
	public ContentValues obterContetValues(MarcaEquipamento marcaequipamento){
		ContentValues values = new ContentValues();
		if (marcaequipamento.getCodigo() != 0) values.put("codigo", marcaequipamento.getCodigo());
		values.put("descricao", marcaequipamento.getDescricao());
		return values;
	}
	
	//METODO DE LISTAR AS MARCAS DO BANCO
	public List<MarcaEquipamento> listar(){
		SQLiteDatabase 	db = null;	
		Cursor 			cursor = null;
		try{			
			db = getReadableDatabase();
			cursor = db.query(TABELA, COLS, null, null, null, null, null);
			while (cursor.moveToNext()) {
				MarcaEquipamento marcaequipamento = new MarcaEquipamento();
				marcaequipamento.setCodigo(cursor.getInt(CODIGO)); 		
				marcaequipamento.setDescricao(cursor.getString(DESCRICAO)); 	
				listaMarcaEquipamento.add(marcaequipamento);
			}
		}catch (SQLException e){
			Log.e("MarcaEquipamento", "Erro Listar: " + e.toString());
			return null;
		}finally{
			if (cursor 	!= null) cursor.close();
			if (db 		!= null) db.close();
		}
		return listaMarcaEquipamento;		
	}
	
	//METODO DE RETORNAR UMA MARCA PELO CODIGO
	public MarcaEquipamento getMarcaPorCodigo(int codigo){
		SQLiteDatabase 		db = null;	
		Cursor 				cursor = null;
		MarcaEquipamento 	me = null;
		try {
			db 		= getReadableDatabase();
			cursor	= db.query(TABELA, COLS, "codigo=?",	new String[] { String.valueOf(codigo) }  , null, null, null);		
			if ( cursor.moveToFirst() ){
				me	= new MarcaEquipamento();
				me.setCodigo(cursor.getInt(CODIGO));
				me.setDescricao(cursor.getString(DESCRICAO));
			}
		} catch (SQLException e) {
			Log.e("MarcaEquipamento", "Erro getMarcaPorCodigo: " + e.toString());
			return null;
		}finally{
			if (cursor	!= null) cursor.close();
			if (db 		!= null) db.close();
		}
		if (me != null)
			return me;
		else
			return null;
	}
	
	//METODO DE DELETAR MARCA NO BANCO
	public void deletar(MarcaEquipamento marcaequipamento){
		SQLiteDatabase db = getWritableDatabase();
		try{
			db.delete(TABELA, "codigo=?", new String[] { String.valueOf(marcaequipamento.getCodigo()) }  );
		}catch(SQLException e){
			Log.e("Marca Equipamento", "Erro Deletar: " + e.toString());
		}finally{
			if (db != null) db.close();
		}
	}

}
