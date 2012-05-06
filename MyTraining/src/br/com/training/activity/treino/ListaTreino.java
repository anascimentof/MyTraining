package br.com.training.activity.treino;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import br.com.training.dao.TreinoDAO;
import br.com.training.entidades.Treino;

public class ListaTreino extends ListActivity {
	private static final int NOVO 		= 0;
	private static final int VOLTAR		= 1;
	private static final int ALTERAR	= 0;
	private static final int DELETAR	= 1;
	private Treino treinoSelecionado;
	private TreinoDAO treinodao = new TreinoDAO(ListaTreino.this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getListView().setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id){
				treinoSelecionado = (Treino) adapter.getItemAtPosition(posicao);
				registerForContextMenu(getListView());
				return false;
			}
		});
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		return super.onContextItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
	
	public void listaTreino(){
		
	//	setListAdapter(new ArrayAdapter<Treino>(ListaTreino.this, android.R.layout.simple_list_item_1, ))
		
	}
}
