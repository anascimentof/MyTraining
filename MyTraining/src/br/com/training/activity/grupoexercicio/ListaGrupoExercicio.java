package br.com.training.activity.grupoexercicio;

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
import br.com.training.dao.GrupoExercicioDAO;
import br.com.training.entidades.GrupoExercicio;

public class ListaGrupoExercicio extends ListActivity {
	private static final int NOVO	= 0;
	private static final int VOLTAR	= 1;
	
	private static final int ALTERAR = 0;
	private static final int DELETAR = 1;
	
	private GrupoExercicio 			grupoSelecionado;
	private GrupoExercicioDAO 		grupoDAO 			= new GrupoExercicioDAO(ListaGrupoExercicio.this); 

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getListView().setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id){
				grupoSelecionado = (GrupoExercicio) adapter.getItemAtPosition(posicao);
				registerForContextMenu(getListView());
				return false;
			}
		});	
	}

	@Override
	public void onResume() {
		super.onResume();
		listar();
	}
	
	public void listar(){
		setListAdapter(new ArrayAdapter<GrupoExercicio>(ListaGrupoExercicio.this, 
				android.R.layout.simple_list_item_1,
				grupoDAO.listar()));		
		if(getListAdapter().isEmpty()) Toast.makeText(ListaGrupoExercicio.this, "Lista Vazia", Toast.LENGTH_LONG).show();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuItem item = menu.add(0, NOVO , 0 , "Novo");
		item.setIcon(R.drawable.novo);
		item = menu.add(0, VOLTAR, 0, "Voltar");
		item.setIcon(R.drawable.cancelar);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case NOVO:
			startActivity(new Intent(ListaGrupoExercicio.this, FormGrupoExercicio.class));
			break;
		case VOLTAR:
			finish();
			break;
		}
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0 , ALTERAR, 0, "Alterar");
		menu.add(0 , DELETAR, 0, "Deletar");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case ALTERAR:
			Intent alteracao  = new Intent(ListaGrupoExercicio.this, FormGrupoExercicio.class);
			alteracao.putExtra("grupoSelecionado", (GrupoExercicio) grupoSelecionado);
			startActivity(alteracao);
			break;
		case DELETAR:
			AlertDialog.Builder alert = new AlertDialog.Builder(ListaGrupoExercicio.this);
			alert.setMessage("Deseja deletar o grupo de exercicio ?")
			.setTitle("MyTraining")
			.setCancelable(false)
			.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					grupoDAO.excluir(grupoSelecionado);
					listar();
				}
			})
			.setNegativeButton("Não", null);
			alert.show();	
			break;			
		}
		return true;
	}

}
