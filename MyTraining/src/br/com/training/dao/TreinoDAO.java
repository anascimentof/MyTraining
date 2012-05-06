package br.com.training.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.training.entidades.Treino;

public class TreinoDAO extends SQLiteOpenHelper {
	private ContentValues dados = null;
	
	public TreinoDAO(Context context) {
		super(context, MyDataBase.BANCO, null, MyDataBase.VERSAO);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {}

	public void listar(){
		
	}
	
	public void salvar(Treino treino){
		
	}
	
	public void deletar(Treino treino){
		
	}
	
	public ContentValues obterContentValues(Treino treino){
		ContentValues values = new ContentValues();
		if(treino.getCodigo() != "") values.put("codigo", treino.getCodigo());
		values.put("descricao", 	treino.getDescricao());
		values.put("tempoDuracao", 	treino.getTempoDuracao().toMillis(true) );
		return values;
	}
}
