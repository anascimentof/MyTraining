package br.com.training.activity.exercicio;


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
import br.com.training.activity.musculo.FormMusculo;
import br.com.training.dao.ExercicioDAO;
import br.com.training.entidades.Exercicio;

public class ListaExercicio extends ListActivity {
	private final static int NOVO 			= 0;
	private final static int VOLTAR 		= 1;
	private final static int ALTERAR		= 0;
	private final static int DELETAR		= 1;
	private ExercicioDAO exerciciodao 		= new ExercicioDAO(ListaExercicio.this);
	private Exercicio exercicioSelecionado 	= null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getListView().setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id){
				exercicioSelecionado = (Exercicio) adapter.getItemAtPosition(posicao);
				registerForContextMenu(getListView());
				return false;
			}
		});
		
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, ALTERAR, 0, "Alterar");
		menu.add(0, DELETAR, 1, "Deletar");
	}

	public boolean onContextItemSelected(MenuItem item){
		switch (item.getItemId()) {
		case ALTERAR:
			Intent alteracao = new Intent(this, FormExercicio.class);
			alteracao.putExtra("exercicioSelecionado", (Exercicio) exercicioSelecionado );
			startActivity(alteracao);
			break;
		case DELETAR:
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setMessage("Deseja deletar o exercício ?")
			.setTitle("MyTraining")
			.setCancelable(false)
			.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface arg0, int arg1) {
					exerciciodao.deletar(exercicioSelecionado);
					listar();
				}
			})
			.setNegativeButton("Não", null);
			alert.show();
			break;
		}
		return true;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuItem item = menu.add(0 , NOVO, 0 , "Novo");
		item.setIcon(br.com.training.activity.R.drawable.novo);
		item = menu.add(0, VOLTAR, 0 , "Voltar");
		item.setIcon(br.com.training.activity.R.drawable.cancelar);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case NOVO:
			startActivity(new Intent(this, FormExercicio.class));
			return true;
		case VOLTAR:
			finish();
			return true;
		}
		return false;		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
	}

	@Override
	protected void onResume() {
		super.onResume();
		listar();
	}
	
	public void listar(){
	
		setListAdapter(new ArrayAdapter<Exercicio>(ListaExercicio.this, 
													R.layout.simple_list_item_1,
													exerciciodao.listarExercicioGrupo()));
		
		if(getListAdapter().isEmpty()) Toast.makeText(ListaExercicio.this, 
													  br.com.training.activity.R.string.msg_lista_vazia, 
													  Toast.LENGTH_LONG).show();
	}
	
	
}
