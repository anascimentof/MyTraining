package br.com.training.activity.treino;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import br.com.training.activity.R;
import br.com.training.dao.CategoriaMuscularDAO;
import br.com.training.entidades.Treino;

public class ListaTreino extends ListActivity {
	private static final int NOVO	= 0;
	private static final int VOLTAR	= 1;
	
	private static final int ALTERAR = 0;
	private static final int DELETAR = 1;
	
	private Treino treinoSelecionado;
	
	@Override
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);
		//TODO
	}

	public void onResume(){
		super.onResume();
	}
	
	public void onListItemClick(ListView l, View v, int position, long id){
		super.onListItemClick(l, v, position, id);
	}
	public boolean onCreateOptionsMenu(Menu menu){
		super.onCreateOptionsMenu(menu);
		MenuItem item = menu.add(0, NOVO, 0, "Novo");
		item.setIcon(R.drawable.novo);
		item = menu.add(0, VOLTAR, 1, "Voltar");
		item.setIcon(R.drawable.cancelar);
		return true;
	}
	// Evento as opções do MENU
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()) {
		case NOVO:
			startActivity(new Intent(this, FormTreino.class));			
			return true;
		case VOLTAR:
			finish();
			return true;
		}
		return false;
	}
	//Menu de contexto
	public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo){
		super.onCreateContextMenu(menu, view, menuInfo);
		menu.add(0, ALTERAR, 0, "Alterar");
		menu.add(0, DELETAR, 0 , "Deletar");
	}
	public boolean onContextItemSelected(MenuItem item){
		switch (item.getItemId()) {
		case ALTERAR:
			Intent alteracao = new Intent(ListaTreino.this, FormTreino.class);
			//alteracao.pute
			startActivity(alteracao);
			break;
		case DELETAR:
			AlertDialog.Builder alert = new AlertDialog.Builder(ListaTreino.this);
			alert.setMessage("Deseja deletar a Categoria Muscular ?")
			.setTitle("MyTraining")
			.setCancelable(false)
			.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					CategoriaMuscularDAO ctmDAO = new CategoriaMuscularDAO(ListaTreino.this);
					//ctmDAO.deletar(treinoSelecionado);
				}
			})
			.setNegativeButton("Não", null);
			alert.show();
			break;
		}
		return true;
	}
}