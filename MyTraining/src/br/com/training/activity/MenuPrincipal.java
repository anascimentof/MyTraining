package br.com.training.activity;

import android.R;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import br.com.training.dao.MyDataBase;

public class MenuPrincipal extends ListActivity {
	private MyDataBase 			db 			= null;	
	private String[] 			opcoes 		= new String[] { "Agenda" , "Plano" , "Manutenção", "Sair" };	
	private static final int 	AGENDA		= 0;
	private static final int 	PLANO		= 1;
	private static final int 	MANUTENCAO	= 2;
		
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);
		try {
			db 		= new MyDataBase(MenuPrincipal.this);
		} catch (SQLiteException e) {
			Log.e("Erro Criar BD", e.toString());
		}finally{
			if (db	!= null) db.close();
		}
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this ,	R.layout.simple_list_item_1 , opcoes);
		setListAdapter(arrayAdapter);
	}
	
	//METODO DE CLIQUE NA LISTA
	protected void onListItemClick(ListView l, View v, int position, long id){
		super.onListItemClick(l, v, position, id);
		switch (position) {
		case AGENDA:
			Toast.makeText(this, "Agenda", Toast.LENGTH_SHORT).show();
			break;
		case PLANO:
			Toast.makeText(this, "Plano", Toast.LENGTH_SHORT).show();
			break;
		case MANUTENCAO:
			startActivity(new Intent(this , MenuManutencao.class) ) ;
			break;
		default:
			AlertDialog.Builder alert = new AlertDialog.Builder(MenuPrincipal.this);
			alert.setMessage("Deseja sair ?");
			alert.setTitle("MyTraining");
			alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					finish();					
				}
			});
			alert.setNegativeButton("Não", null);
			alert.show();	
			break;
		}
	}
}
