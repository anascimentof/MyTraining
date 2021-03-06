package br.com.training.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import br.com.training.entidades.CategoriaMuscular;
import br.com.training.entidades.Musculo;

public class MusculoDAO extends SQLiteOpenHelper {
	
	private List<Musculo>			listaMusculo		= new ArrayList<Musculo>();
	private static final int		CODIGOMUSCULO		= 0;
	private static final int		DESCRICAOMUSCULO	= 1;
	private static final int		CODIGOCATEGORIA		= 2;
	private static final int		DESCRICAOCATEGORIA	= 3;
	private static final String[]	COLS				= {"codigo", "descricao"};
	
	public MusculoDAO(Context context) {
		super(context, MyDataBase.BANCO, null, MyDataBase.VERSAO);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {}
	
	// METODO DE SALVAR ( INCLUIR / ALTERAR ) NA BASE
	public void salvar(Musculo musculo){
		ContentValues	dados			= null;
		Cursor			c 				= null;
		SQLiteDatabase	db 				= getWritableDatabase();
		int 			codigoMusculo	= 0;
		
		try {
			db.beginTransaction();
			dados = obterContentValuesMusculo(musculo);
			
			if( musculo.getCodigo() != 0){
				// Atualiza a descri��o do musculo na tabela TBMUSCULO
				db.update(MyDataBase.TBMUSCULO, dados, "codigo=?", new String[]{ musculo.getCodigoString() } );
				dados.clear();
				// Deleta todas as refer�ncias entre musculo e as categorias gravadas
				db.delete(MyDataBase.TBMUSCULOCATEGORIA, "cdmusculo=?", new String[]{ musculo.getCodigoString() } );
				// Carrega cada categoria selecionada do musculo para inserir na tabela TBMUSCULOCATEGORIA
				Iterator<CategoriaMuscular> i = musculo.getCategMuscular().iterator();
				while(i.hasNext()){
					CategoriaMuscular categoria = (CategoriaMuscular) i.next();
					dados.putAll( obterContentValuesMusculoCategoria(musculo.getCodigo(), categoria.getCodigo()) );
					db.insert(MyDataBase.TBMUSCULOCATEGORIA, null, dados);
				}

			}else{
				
				// Inserir registro na tabela Musculo 
				db.insert(MyDataBase.TBMUSCULO, null, dados);
				dados.clear();
				// Retornar o �ltimo codigo do musculo para passar para o obterContentValuesMusculoCategoria
				c = db.rawQuery("Select codigo From " + MyDataBase.TBMUSCULO + " Order By codigo Desc Limit 1", null);
				if (c != null && c.moveToFirst() ){
					codigoMusculo = c.getInt(CODIGOMUSCULO);
				}
				// Ler as categrias do musculo e gravar na tabela TBMusculoCategoria
				Iterator<CategoriaMuscular> i = musculo.getCategMuscular().iterator();
				while (i.hasNext()) {
					CategoriaMuscular categoria = (CategoriaMuscular) i.next();
					dados.putAll( obterContentValuesMusculoCategoria( codigoMusculo , categoria.getCodigo()) );
					db.insert(MyDataBase.TBMUSCULOCATEGORIA, null, dados);					
				}
				
			}
			
			db.setTransactionSuccessful();
			
		} catch (SQLException e) {
			Log.e("Musculo", "Erro inserir/alterar: " + e.toString());
		}finally{
			db.endTransaction();
			if(c !=null) c.close();
			if(db!=null) db.close();
		}
	}
	// METODO DE EXCLUS�O DE CATEGORIA NO BANCO
	public void deletar(Musculo musculo){
		SQLiteDatabase db = getWritableDatabase();
		try {
			db.beginTransaction();
			db.delete(MyDataBase.TBMUSCULO, "codigo=?", new String[] { musculo.getCodigoString() } );
			db.delete(MyDataBase.TBMUSCULOCATEGORIA, "cdmusculo=?", new String[]{musculo.getCodigoString()});
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			Log.e("Musculo", "Erro deletar: " + e.toString());
		} finally {
			db.endTransaction();
			if (db != null) db.close();
		}
	}
	// METODO DE RETORNAR UMA LISTA DE MUSCULOS DA BASE
	public List<Musculo> listar(){
		SQLiteDatabase	db		= null;
		Cursor 			cursor	= null;
		try {
			db = getReadableDatabase();
			listaMusculo.clear();
			cursor = db.query(MyDataBase.TBMUSCULO, COLS, null, null, null, null, null);
			while (cursor.moveToNext()) {
				Musculo musculo = new Musculo();
				musculo.setCodigo(cursor.getInt(CODIGOMUSCULO));
				musculo.setDescricao(cursor.getString(DESCRICAOMUSCULO));
				listaMusculo.add(musculo);	
			}
		} catch (SQLException e) {
			Log.e("MusculoDAO", "Erro listar " + e.getStackTrace() );
		}finally{
			if (cursor	!=	null) cursor.close() ;
			if (db		!=	null) db.close() ;			
		}
		return listaMusculo;
	}
	// METODO DE RETORNAR UMA LISTA DE MUSCULOS E CATEGORIAS DA BASE
	public List<Musculo> listarMusculoCategoria(){
		SQLiteDatabase	db		= null;
		Cursor 			cursor	= null;
		Musculo 		musculo = null;
		
		try {
			db		= getReadableDatabase();
			listaMusculo.clear();
			cursor	= db.rawQuery("select a.codigo, a.descricao , c.codigo, c.descricao from " + MyDataBase.TBMUSCULO + " a inner join " +
					MyDataBase.TBMUSCULOCATEGORIA + " b on a.codigo = b.cdmusculo inner join " + MyDataBase.TBCATEGORIAMUSCULAR + " c on " +
					"b.cdctg = c.codigo order by a.codigo ", null);
			int codigoAux = 0;
			while(cursor.moveToNext()){
				
				if (codigoAux != cursor.getInt(CODIGOMUSCULO)){
					musculo = new Musculo();
					musculo.setCodigo(cursor.getInt(CODIGOMUSCULO));
					musculo.setDescricao(cursor.getString(DESCRICAOMUSCULO));
				}
				
				codigoAux = cursor.getInt(CODIGOMUSCULO);
				
				if(musculo != null && musculo.getCodigo() != 0){
					CategoriaMuscular categoria = new CategoriaMuscular();
					categoria.setCodigo(cursor.getInt(CODIGOCATEGORIA));
					categoria.setDescricao(cursor.getString(DESCRICAOCATEGORIA));
					musculo.adicionarCategoria(categoria);
				}
				
				if ( !listaMusculo.contains(musculo) ){
					listaMusculo.add(musculo);
				}
				
			}
		} catch (SQLException e) {
			Log.e("MusculoDAO", "Erro listarMusculoCategoria: " + e.getStackTrace());
		} finally{
			if(cursor	!= null) cursor.close();
			if(db		!= null) db.close();
		}
		
		return listaMusculo;
	}
	//METODO POPULAR O CONTENTVALUES COM PARAMENTOS E VALORES PARA GRAVAR/ATUALIZAR (MUSCULO)
	public ContentValues obterContentValuesMusculo(Musculo musculo){
		ContentValues values = new ContentValues();
		if(musculo.getCodigo() != 0) values.put("codigo", musculo.getCodigo());
		values.put("descricao", musculo.getDescricao());
		return values;
	}
	//METODO POPULAR O CONTENTVALUES COM PARAMENTOS E VALORES PARA GRAVAR/ATUALIZAR (MUSCULO E CATEORIA)
	public ContentValues obterContentValuesMusculoCategoria(int codigoMusculo, int codigoCateria ){
		ContentValues values = new ContentValues();
		if(codigoMusculo != 0 && codigoCateria != 0) {
			values.put("cdmusculo", codigoMusculo);
			values.put("cdctg", 	codigoCateria);
		}
		return values;
	}
}
