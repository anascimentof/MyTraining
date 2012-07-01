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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import br.com.training.activity.R;
import br.com.training.dao.TreinoDAO;
import br.com.training.entidades.Treino;

public class ListaTreino extends ListActivity {
	private static final int NOVO 		= 0;
	private static final int VOLTAR		= 1;
	private static final int ALTERAR	= 0;
	private static final int DELETAR	= 1;
	private Treino treinoSelecionado;
	private TreinoDAO treinoDAO = new TreinoDAO(ListaTreino.this);
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
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
	protected void onResume() {
		super.onResume();
		listaTreino();
	}
	
	public void listaTreino(){
		
		setListAdapter( new ArrayAdapter<Treino>( ListaTreino.this, android.R.layout.simple_list_item_1, treinoDAO.listar() ) );
		if(getListAdapter().isEmpty()) Toast.makeText(ListaTreino.this, R.string.msg_lista_vazia, Toast.LENGTH_LONG).show();
	}
	
	protected void onListItemClick(ListView l, View v, int position, long id){
		super.onListItemClick(l, v, position, id);
	}
	
	public boolean onCreateOptionsMenu(Menu menu){
		super.onCreateOptionsMenu(menu);
		MenuItem item = menu.add(0 , NOVO , 0 , "Novo");
		item.setIcon(R.drawable.novo);
		item = menu.add(0 , VOLTAR, 0 , "Voltar");
		item.setIcon(R.drawable.cancelar);
		return true;
	}

	public boolean onOptionsItemSelected( MenuItem item){
		switch (item.getItemId()) {
		case NOVO:
			Intent novo = new Intent(ListaTreino.this, FormTreino.class);
			novo.putExtra("modo", "N");
			startActivity(novo);
			return true;
		case VOLTAR:
			finish();
			return true;
		}
		return false;
	}

	//Menu de contexto
	public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo ){
		super.onCreateContextMenu(menu, view, menuInfo);
		menu.add(0, ALTERAR , 0 , "Alterar");
		menu.add(0, DELETAR , 1 , "Deletar");
	}
	
	// Quando um item é selecionado no menu de contexto
	public boolean onContextItemSelected(MenuItem item){
		
		switch (item.getItemId()) {
		case ALTERAR:
			Intent alteracao = new Intent(ListaTreino.this, FormTreino.class );
			alteracao.putExtra("treinoSelecionado", (Treino) treinoSelecionado ) ;
			alteracao.putExtra("modo", "A");
			startActivity(alteracao);
			break;
		case DELETAR:
			AlertDialog.Builder alert = new AlertDialog.Builder(ListaTreino.this);
			alert.setMessage("Deseja deletar o equipamento ?")
			.setTitle("MyTraining")
			.setCancelable(false)
			.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					treinoDAO.deletar(treinoSelecionado);
					listaTreino();					
				}
			})
			.setNegativeButton("Não", null);
			alert.show();	
			break;
		}
		return true;
	}

	
}
