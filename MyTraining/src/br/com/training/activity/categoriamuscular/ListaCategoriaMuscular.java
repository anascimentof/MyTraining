package br.com.training.activity.categoriamuscular;

import java.util.ArrayList;
import java.util.List;

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import br.com.training.activity.R;
import br.com.training.dao.CategoriaMuscularDAO;
import br.com.training.entidades.CategoriaMuscular;

public class ListaCategoriaMuscular extends ListActivity {
	private List<CategoriaMuscular> listaCtm	= new ArrayList<CategoriaMuscular>();
	private static final int NOVO				= 0;
	private static final int VOLTAR				= 1;
	private static final int ALTERAR			= 0;
	private static final int DELETAR			= 1;	
	private CategoriaMuscularDAO ctmDAO 		= new CategoriaMuscularDAO(ListaCategoriaMuscular.this);
	private CategoriaMuscular ctgSelecionada;
	
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);
		
		getListView().setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id){
				ctgSelecionada = (CategoriaMuscular) adapter.getItemAtPosition(posicao);
				registerForContextMenu(getListView());
				return false;
			}
		});
		
	}

	public void onResume(){
		super.onResume();
		setListAdapter(new ArrayAdapter<CategoriaMuscular>( ListaCategoriaMuscular.this, 
				android.R.layout.simple_list_item_1 ,
				ctmDAO.listar()));
		if(listaCtm.isEmpty()) Toast.makeText(this, "Lista vazia", Toast.LENGTH_LONG).show();
	}
	
	public void onListItemClick(ListView l, View v, int position, long id){
		super.onListItemClick(l, v, position, id);
	}
	// 
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
			startActivity(new Intent(this, FormCategoriaMuscular.class));			
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
	//Quando um item é selecionado no menu de contexto
	public boolean onContextItemSelected(MenuItem item){
		switch (item.getItemId()) {
		case ALTERAR:
			Intent alteracao = new Intent(ListaCategoriaMuscular.this, FormCategoriaMuscular.class);
			alteracao.putExtra("ctmSelecionada" , (CategoriaMuscular) ctgSelecionada);
			startActivity(alteracao);
			break;
		case DELETAR:
			AlertDialog.Builder alert = new AlertDialog.Builder(ListaCategoriaMuscular.this);
			alert.setMessage("Deseja deletar a Categoria Muscular ?")
			.setTitle("MyTraining")
			.setCancelable(false)
			.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					ctmDAO.deletar(ctgSelecionada);
					setListAdapter(new ArrayAdapter<CategoriaMuscular>( ListaCategoriaMuscular.this, 
							android.R.layout.simple_list_item_1 ,
							ctmDAO.listar()));
				}
			})
			.setNegativeButton("Não", null);
			alert.show();
			break;
		}
		return true;
	}
}
