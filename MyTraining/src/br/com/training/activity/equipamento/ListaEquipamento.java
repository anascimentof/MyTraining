package br.com.training.activity.equipamento;

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
import br.com.training.dao.EquipamentoDAO;
import br.com.training.entidades.Equipamento;

public class ListaEquipamento extends ListActivity{
	private static final int NOVO = 0;
	private static final int VOLTAR = 1;
	
	private static final int ALTERAR = 0;
	private static final int DELETAR = 1;
	
	private Equipamento eqSelecionado;
	private EquipamentoDAO eqDAO;
	private List<Equipamento> lista;
	
	@Override
	public void onCreate(Bundle icicle){
			
		super.onCreate(icicle);
		
		getListView().setOnItemLongClickListener(new OnItemLongClickListener() {
			
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id){
				
				eqSelecionado = (Equipamento) adapter.getItemAtPosition(posicao);
				registerForContextMenu(getListView());
				return false;
				
			}
		});
	
	}
	
	public void onResume(){
		super.onResume();
		listarEquipamentos();
	}
	
	private void listarEquipamentos(){
		
		EquipamentoDAO equipamentodao = new EquipamentoDAO(ListaEquipamento.this);
		
		lista = equipamentodao.listar();
		
		setListAdapter(new ArrayAdapter<Equipamento>(ListaEquipamento.this , 
														android.R.layout.simple_list_item_1 , 
														lista));
		
		if(lista.isEmpty()) Toast.makeText(this, "Lista vazia", Toast.LENGTH_LONG).show();
	}
		
	// Clique nas opções
	protected void onListItemClick(ListView l, View v, int position, long id){
		super.onListItemClick(l, v, position, id);
	}
	
	//Menu 
	public boolean onCreateOptionsMenu(Menu menu){
		super.onCreateOptionsMenu(menu);
		MenuItem item = menu.add(0 , NOVO , 0 , "Novo");
		item.setIcon(R.drawable.novo);
		item = menu.add(0 , VOLTAR, 0 , "Voltar");
		item.setIcon(R.drawable.cancelar);
		return true;
	}

	//Clique nos itens do menu
	public boolean onOptionsItemSelected( MenuItem item){
		switch (item.getItemId()) {
		case NOVO:
			startActivity(new Intent(this, FormEquipamento.class));
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
			Intent alteracao = new Intent(ListaEquipamento.this, FormEquipamento.class );
			alteracao.putExtra("eqSelecionado", (Equipamento) eqSelecionado ) ;
			startActivity(alteracao);
			break;
		case DELETAR:
			AlertDialog.Builder alert = new AlertDialog.Builder(ListaEquipamento.this);
			alert.setMessage("Deseja deletar o equipamento ?")
			.setTitle("MyTraining")
			.setCancelable(false)
			.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					eqDAO = new EquipamentoDAO(ListaEquipamento.this);
					eqDAO.deletar(eqSelecionado);
					listarEquipamentos();					
				}
			})
			.setNegativeButton("Não", null);
			alert.show();	
			break;
		}
		return true;
	}
}

