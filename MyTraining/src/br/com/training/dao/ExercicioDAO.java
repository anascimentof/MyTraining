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
import br.com.training.entidades.Exercicio;
import br.com.training.entidades.GrupoExercicio;

public class ExercicioDAO extends SQLiteOpenHelper {
	private List<Exercicio> 		listaexercicio		= new ArrayList<Exercicio>();
	private static final int 		CODIGOEXERCICIO		= 0;
	private static final int		DESCRICAOEXERCICO	= 1;
	private static final int		CODIGOGRUPO			= 2;
	private static final int		DESCRICAOGRUPO		= 3;
	private static final String[]	COLS				= {"codigo", "descricao"};
	
	public ExercicioDAO(Context context){
		super(context, MyDataBase.BANCO, null, MyDataBase.VERSAO);
	}
	
	@Override
	public void onCreate(SQLiteDatabase arg0) {	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
	
	public void salvar(Exercicio exercicio){
		SQLiteDatabase 	db 		= null;
		Cursor 			cursor	= null;
		ContentValues  	dados	= null;
		int codigoExercicio		= 0;
		
		try{
			db = getWritableDatabase();
			db.beginTransaction();
			
			dados = obterContentValuesExercicio(exercicio);
			
			if (exercicio.getCodigo() != 0){
				
				//ATUALIZAR
				db.update(MyDataBase.TBEXERCICIO, dados, "codigo=?", new String[]{ exercicio.getCodigoString() } );
				dados.clear();
				db.delete(MyDataBase.TBGRUPOXEXERCICIO, "cdExercicio=?", new String[]{ exercicio.getCodigoString() } );
				Iterator<GrupoExercicio> i = exercicio.getGrupoxExercicio().iterator();
				while(i.hasNext()){
					GrupoExercicio grupo = (GrupoExercicio) i.next();
					dados.putAll(obterContentValuesGrupoExercico(exercicio.getCodigo(), grupo.getCodigo()));
					db.insert(MyDataBase.TBGRUPOXEXERCICIO, null, dados);
				}

			}else{
				
				//INSERIR
				db.insert(MyDataBase.TBEXERCICIO, null, dados);
				dados.clear();
				cursor = db.rawQuery("Select codigo from " + MyDataBase.TBEXERCICIO + " order by codigo Desc Limit 1", null);
				if( cursor != null && cursor.moveToFirst() ) {
					codigoExercicio = cursor.getInt(CODIGOEXERCICIO);
				}
				Iterator<GrupoExercicio> i = exercicio.getGrupoxExercicio().iterator();
				while(i.hasNext()){
					GrupoExercicio grupo = (GrupoExercicio) i.next();
					dados.putAll( obterContentValuesGrupoExercico( codigoExercicio, grupo.getCodigo() ) );
					db.insert(MyDataBase.TBGRUPOXEXERCICIO, null, dados);
				}
				
			}
			
			db.setTransactionSuccessful();
			
		}catch(SQLException e){
			Log.e("Erro insert EXERCICIO", e.toString());
		}finally{
			db.endTransaction();
			if(cursor	!=null) cursor.close();
			if(db		!=null) db.close();
		}
	}

	public void deletar(Exercicio exercicio){
		SQLiteDatabase db = getWritableDatabase();
		try {
			db.beginTransaction();
			db.delete(MyDataBase.TBEXERCICIO, "codigo=?", new String[] { exercicio.getCodigoString() } );
			db.delete(MyDataBase.TBGRUPOXEXERCICIO, "cdExercicio=?", new String[] { exercicio.getCodigoString() } );
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			Log.e("Erro ao deletar exercicio", e.toString());
		}finally{
			db.endTransaction();
			if(db != null) db.close();
		}
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
	
	public List<Exercicio> listarExercicioGrupo(){
		SQLiteDatabase	db 			= null;
		Cursor			cursor 		= null;
		Exercicio		exercicio	= null;
		
		try {
			
			listaexercicio.clear();
			db = getReadableDatabase();
			cursor = db.rawQuery( "select a.codigo, a.descricao, c.codigo , c.descricao from " + MyDataBase.TBEXERCICIO + 
					" a inner join " + MyDataBase.TBGRUPOXEXERCICIO + " b on a.codigo = b.cdExercicio inner join " +
					MyDataBase.TBGRUPOEXERCIO + " c on b.cdGrupo = c.codigo order by a.codigo " , null);
			
			int codigoAux = 0;
			
			while (cursor.moveToNext()) {
				
				if(codigoAux != cursor.getInt(CODIGOEXERCICIO)){
					exercicio = new Exercicio();
					exercicio.setCodigo(cursor.getInt(CODIGOEXERCICIO));
					exercicio.setDescricao(cursor.getString(DESCRICAOEXERCICO));
				}
				
				codigoAux = cursor.getInt(CODIGOEXERCICIO);
				
				if(exercicio != null && exercicio.getCodigo() != 0){
					GrupoExercicio grupo = new GrupoExercicio();
					grupo.setCodigo(cursor.getInt(CODIGOGRUPO));
					grupo.setDescricao(cursor.getColumnName(DESCRICAOGRUPO));
					exercicio.adicionarGrupoExercicio(grupo);
				}
				
				if ( !listaexercicio.contains(exercicio) ) {
					listaexercicio.add(exercicio);
				}
				
			}
		} catch (SQLException e) {
			Log.e("ExercicioDAO", "Erro ao listar exercicio " + e.toString());
		}finally{
			if(cursor 	!= null) cursor.close();
			if(db 		!= null) db.close();		
		}
		
		return listaexercicio;
	}
	
	public ContentValues obterContentValuesExercicio(Exercicio exercicio){
		ContentValues values = new ContentValues();
		if (exercicio.getCodigo() != 0) values.put("codigo", exercicio.getCodigo());
		values.put("descricao", exercicio.getDescricao());
		return values;
	}
	
	public ContentValues obterContentValuesGrupoExercico(int codigoExercicio, int codigoGrupo){
		ContentValues values = new ContentValues();
		if(codigoExercicio!=0 && codigoGrupo!=0){
			values.put("cdGrupo", codigoGrupo);
			values.put("cdExercicio", codigoExercicio);
		}
		return values;
	}
	
}
