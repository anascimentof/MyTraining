package br.com.training.activity.musculo;

import android.R;
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
import br.com.training.dao.MusculoDAO;
import br.com.training.entidades.Musculo;


public class ListaMusculo extends ListActivity {
	private static final int	NOVO	= 0;
	private static final int	VOLTAR	= 1;
	
	private static final int	ALTERAR	= 0;
	private static final int	DELETAR = 1;
	
	private Musculo 			musculoSelecionado;
	private MusculoDAO			musculoDAO = new MusculoDAO(ListaMusculo.this);
	
	@Override
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);
		
		getListView().setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id){
				musculoSelecionado = (Musculo) adapter.getItemAtPosition(posicao);
				registerForContextMenu(getListView());
				return false;
			}
		});
		
	}
	
	public void onResume(){
		super.onResume();
		listarMusculos();
	}
	
	public void listarMusculos(){
		setListAdapter(new ArrayAdapter<Musculo>(this, 
												R.layout.simple_list_item_1, 
												musculoDAO.listarMusculoCategoria()));
		if(getListAdapter().isEmpty()) Toast.makeText(this, "Lista vazia.", Toast.LENGTH_LONG).show();
	}
	
	public void onListItemClick(ListView l, View v, int position, long id){
		super.onListItemClick(l, v, position, id);
	}

	public boolean onCreateOptionsMenu(Menu menu){
		super.onCreateOptionsMenu(menu);
		MenuItem item = menu.add(0 , NOVO, 0 , "Novo");
		item.setIcon(br.com.training.activity.R.drawable.novo);
		item = menu.add(0, VOLTAR, 0 , "Voltar");
		item.setIcon(br.com.training.activity.R.drawable.cancelar);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()) {
		case NOVO:
			startActivity(new Intent(this, FormMusculo.class));
			return true;
		case VOLTAR:
			finish();
			return true;
		}
		return false;
	}
	
	public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo){
		super.onCreateContextMenu(menu, view, menuInfo);
		menu.add(0, ALTERAR, 0, "Alterar");
		menu.add(0, DELETAR, 1, "Deletar");
	}
	
	public boolean onContextItemSelected(MenuItem item){
		switch (item.getItemId()) {
		case ALTERAR:
			Intent alteracao = new Intent(this, FormMusculo.class);
			alteracao.putExtra("musculoSelecionado", (Musculo) musculoSelecionado );
			startActivity(alteracao);
			break;
		case DELETAR:
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setMessage("Deseja deletar o musculo ?")
			.setTitle("MyTraining")
			.setCancelable(false)
			.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface arg0, int arg1) {
					musculoDAO.deletar(musculoSelecionado);
					listarMusculos();
				}
			})
			.setNegativeButton("Não", null);
			alert.show();
			break;
		}
		return true;
	}
}
