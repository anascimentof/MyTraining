package br.com.training.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBase extends SQLiteOpenHelper {
	
	private static final String BANCO				= "MyTraining.db";
	private static final String TBEQUIPAMENTO 		= "TbEquipamento";
	private static final String TBMARCAEQUIPAMENTO	= "TbMarcaEquipamento";
	private static final String TBCATEGORIAMUSCULAR	= "TbCategoriaMuscular";
	private static final String TBMUSCULOCATEGORIA  = "TbMusculoCategoria";
	private static final String TBMUSCULO			= "TbMusculo";
	private static final int	VERSAO 				= 1;
	private SQLiteDatabase db;
	
	public MyDataBase(Context context ) {
		super(context, BANCO, null, VERSAO);
		this.db = getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE IF NOT EXISTS " + TBEQUIPAMENTO + " ");
		sb.append(" (codigo integer PRIMARY KEY AUTOINCREMENT, "); 
		sb.append(" descricao text(50) NOT NULL , ");
		sb.append(" cdmarca integer NOT NULL ); ");
		db.execSQL(sb.toString());	
		sb.delete(0, sb.length());
		sb.append("CREATE TABLE IF NOT EXISTS " + TBMARCAEQUIPAMENTO + " ");
		sb.append(" (codigo integer PRIMARY KEY AUTOINCREMENT, "); 
		sb.append(" descricao text(50) NOT NULL ); "); 
		db.execSQL(sb.toString());	
		sb.delete(0, sb.length());
		sb.append("CREATE TABLE IF NOT EXISTS " + TBCATEGORIAMUSCULAR + " ");
		sb.append(" (codigo integer PRIMARY KEY AUTOINCREMENT, ");
		sb.append(" descricao text(50) NOT NULL ); ");
		db.execSQL(sb.toString());
		sb.delete(0, sb.length());
		sb.append("CREATE TABLE IF NOT EXISTS " + TBMUSCULO + " " );
		sb.append( "(codigo integer PRIMARY KEY AUTOINCREMENT," );
		sb.append(" descricao text(50) NOT NULL );"  );
		db.execSQL(sb.toString());
		sb.delete(0, sb.length());
		sb.append("CREATE TABLE IF NOT EXISTS " + TBMUSCULOCATEGORIA + " ");
		sb.append(" (cdctg integer, ");
		sb.append(" cdmusculo integer , " );
		sb.append(" CONSTRAINT [] PRIMARY KEY ([cdctg], [cdmusculo]) );" );
		db.execSQL(sb.toString());
		sb.delete(0, sb.length());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)  {
		StringBuilder sb = new StringBuilder();
		sb.append("DROP TABLE IF EXISTS " + TBEQUIPAMENTO + " ;");
		sb.append("DROP TABLE IF EXISTS " + TBMARCAEQUIPAMENTO + " ;");
		sb.append("DROP TABLE IF EXISTS " + TBCATEGORIAMUSCULAR + " ;");
		sb.append("DROP TABLE IF EXISTS " + TBMUSCULO + " ;");
		sb.append("DROP TABLE IF EXISTS " + TBMUSCULOCATEGORIA + "; ");
		db.execSQL(sb.toString());
		onCreate(db);
	}
	
	public void close(){
		if ( db != null) db.close();
	}
	
}