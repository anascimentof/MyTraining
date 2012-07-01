package br.com.training.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBase extends SQLiteOpenHelper {
	
	public static final String BANCO				= "MyTraining.db";
	public static final int	VERSAO 					= 1;
	private SQLiteDatabase 		db;

	public static final String TBEQUIPAMENTO 		= "TbEquipamento";
	public static final String TBMARCAEQUIPAMENTO	= "TbMarcaEquipamento";
	public static final String TBCATEGORIAMUSCULAR	= "TbCategoriaMuscular";
	public static final String TBMUSCULOCATEGORIA  	= "TbMusculoCategoria";
	public static final String TBMUSCULO			= "TbMusculo";
	public static final String TBGRUPOEXERCIO  		= "TbGrupoExercicio";
	public static final String TBGRUPOXEXERCICIO 	= "TbGrupoXExercicio";
	public static final String TBEXERCICIO			= "TbExercicio";
	public static final String TBEXMUSCULACAO		= "TbExMusculacao";
	public static final String TBEQXEXERCICIO		= "TbEqXExercicio";
	public static final String TBMUSCXEXERCICIO		= "TbMuscXExercicio";
	public static final String TBSERIE				= "TbSerie";
	public static final String TBTREINO				= "TbTreino";
	public static final String TBTREINOXEXERCICIO	= "TbTreinoXExercicio";
	public static final String TBAGENDA				= "TbAgenda";
		
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
		sb.append("CREATE TABLE IF NOT EXISTS " + TBGRUPOEXERCIO + " ");
		sb.append(" (codigo integer PRIMARY KEY AUTOINCREMENT, "); 
		sb.append(" descricao text(50) NOT NULL ); "); 
		db.execSQL(sb.toString());	
		sb.delete(0, sb.length());
		sb.append("CREATE TABLE IF NOT EXISTS " + TBEXERCICIO + " ");
		sb.append(" (codigo integer PRIMARY KEY AUTOINCREMENT, "); 
		sb.append(" descricao text(50) NOT NULL );" );
		db.execSQL(sb.toString());	
		sb.delete(0, sb.length());		
		sb.append("CREATE TABLE IF NOT EXISTS " + TBGRUPOXEXERCICIO + " ");
		sb.append(" (cdGrupo integer, ");
		sb.append(" cdExercicio integer , " );
		sb.append(" CONSTRAINT [] PRIMARY KEY ([cdGrupo], [cdExercicio]) );" );
		db.execSQL(sb.toString());
		sb.delete(0, sb.length());		
		sb.append("CREATE TABLE IF NOT EXISTS " + TBEXMUSCULACAO + " ");
		sb.append(" ( codigo integer PRIMARY KEY AUTOINCREMENT, ");
		sb.append(" cdExercicio integer ,");
		sb.append(" velocidade text(1),"  );
		sb.append(" tempoDescanso time,");
		sb.append(" modoExecucao text(1),");
		sb.append(" tipoPegada text(1),");
		sb.append(" posicaoBanco text(1),");
		sb.append(" amplitude text(1),");
		sb.append(" obs text(50) );");
		db.execSQL(sb.toString());
		sb.delete(0, sb.length());
		sb.append("CREATE TABLE IF NOT EXISTS "+ TBEQXEXERCICIO + " " );
		sb.append(" ( cdEquipamento integer,");
		sb.append(" cdExMusculacao integer, ");
		sb.append(" CONSTRAINT [] PRIMARY KEY([cdEquipamento], [cdExMusculacao]) );");
		db.execSQL(sb.toString());
		sb.delete(0, sb.length());
		sb.append("CREATE TABLE IF NOT EXISTS " + TBMUSCXEXERCICIO + " ");
		sb.append(" ( cdMusculo integer,");
		sb.append(" cdExMusculacao integer, ");
		sb.append(" CONSTRAINT [] PRIMARY KEY([cdMusculo], [cdExMusculacao]) );");
		db.execSQL(sb.toString());
		sb.delete(0, sb.length());
		sb.append(" CREATE TABLE IF NOT EXISTS " + TBSERIE + " ");
		sb.append(" ( cdExMusculacao integer, ");
		sb.append(" sequencial integer,");
		sb.append(" repeticao integer,");
		sb.append(" peso DECIMAL,");
		sb.append(" unidadeMedida text(3) ,");
		sb.append(" CONSTRAINT [] PRIMARY KEY ([cdExMusculacao],[sequencial]) );");
		db.execSQL(sb.toString());
		sb.delete(0, sb.length());
		sb.append("CREATE TABLE IF NOT EXISTS " + TBAGENDA + " ");
		sb.append("( dataTreino date,");
		sb.append("cdTreino integer, ");
		sb.append("cdExMusculacao integer,");
		sb.append("sequencial integer,");
		sb.append("statusEx text(1),");
		sb.append("avaliacao integer,");
		sb.append("comentario text(50) );");
		db.execSQL(sb.toString());
		sb.delete(0, sb.length());
		sb.append("CREATE TABLE IF NOT EXISTS " + TBTREINO + " ");
		sb.append(" (codigo text(1) NOT NULL, "					);
		sb.append(" descricao text(50) NOT NULL, "				);
		sb.append(" tempoDuracao text(8), "						);
		sb.append(" CONSTRAINT [] PRIMARY KEY([codigo]));"		);
		db.execSQL(sb.toString());	
		sb.delete(0, sb.length());
		sb.append("CREATE TABLE IF NOT EXISTS " + TBTREINOXEXERCICIO + " ");
		sb.append(" ( cdTreino integer,");
		sb.append(" cdExMusculacao integer, ");
		sb.append(" CONSTRAINT [] PRIMARY KEY([cdTreino], [cdExMusculacao]) );");
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
		sb.append("DROP TABLE IF EXISTS " + TBAGENDA + "; ");
		sb.append("DROP TABLE IF EXISTS " + TBEQXEXERCICIO + "; ");
		sb.append("DROP TABLE IF EXISTS " + TBEXERCICIO + "; ");
		sb.append("DROP TABLE IF EXISTS " + TBEXMUSCULACAO + "; ");
		sb.append("DROP TABLE IF EXISTS " + TBGRUPOEXERCIO + ";");
		sb.append("DROP TABLE IF EXISTS " + TBGRUPOXEXERCICIO + ";");
		sb.append("DROP TABLE IF EXISTS " + TBMUSCXEXERCICIO + ";");
		sb.append("DROP TABLE IF EXISTS " + TBSERIE + ";");
		sb.append("DROP TABLE IF EXISTS " + TBTREINO + ";");
		sb.append("DROP TABLE IF EXISTS " + TBTREINOXEXERCICIO + ";");
		db.execSQL(sb.toString());
		onCreate(db);
	}
	
	public void close(){
		if ( db != null) db.close();
	}
	
}