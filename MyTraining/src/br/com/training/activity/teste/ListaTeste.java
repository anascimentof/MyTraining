package br.com.training.activity.teste;

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
import android.widget.Toast;
import br.com.training.dao.TesteDAO;
import br.com.training.entidades.Teste;

public class ListaTeste extends ListActivity {
	public static final int NOVO	= 0;
	public static final int VOLTAR	= 1;
	
	public static final int ALTERAR = 0;
	public static final int DELETAR = 1;
	
	public Teste teste;
	public Teste testeSelecionado;
	public List<Teste> listaTeste;
	public TesteDAO testeDAO;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getListView().setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id){
				testeSelecionado = (Teste) adapter.getItemAtPosition(posicao);
				registerForContextMenu(getListView());
				return false;
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		listaTeste();
	}

	public void listaTeste(){
		TesteDAO testedao = new TesteDAO(ListaTeste.this);
		listaTeste = testedao.listar();
		setListAdapter(new ArrayAdapter<Teste>(ListaTeste.this, android.R.layout.simple_list_item_1, listaTeste));
		if (listaTeste.isEmpty()) Toast.makeText(this, "Lista de teste vazia.", Toast.LENGTH_LONG).show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuItem item = menu.add(0, NOVO, 0, "Novo");
		item.setIcon(br.com.training.activity.R.drawable.novo);
		item = menu.add(0, VOLTAR, 0, "Voltar");
		item.setIcon(br.com.training.activity.R.drawable.cancelar);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
		case NOVO:
			startActivity(new Intent(ListaTeste.this, FormTeste.class));
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
		menu.add(0, ALTERAR, 0, "Alterar");
		menu.add(0, DELETAR, 1, "Deletar");
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		super.onContextItemSelected(item);
		switch (item.getItemId()){
		case ALTERAR:
			Intent alteracao = new Intent(ListaTeste.this, FormTeste.class);
			alteracao.putExtra("testeSelecionado", (Teste) testeSelecionado);
			startActivity(alteracao);
			break;
		case DELETAR:
			AlertDialog.Builder alert = new AlertDialog.Builder(ListaTeste.this);
			alert.setMessage("Deseja deletar o teste ?")
			.setTitle("MyTraining")
			.setCancelable(false)
			.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					testeDAO = new TesteDAO(ListaTeste.this);
					testeDAO.deletar(testeSelecionado);
					listaTeste();
				}
			}).setNegativeButton("Não", null);
			alert.show();
		}
		return true;
	}	
}
