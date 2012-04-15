package br.com.training.activity;

import android.R;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.training.activity.categoriamuscular.ListaCategoriaMuscular;
import br.com.training.activity.equipamento.ListaEquipamento;
import br.com.training.activity.marcaequipamento.ListaMarcaEquipamento;
import br.com.training.activity.musculo.ListaMusculo;
import br.com.training.activity.teste.ListaTeste;
import br.com.training.activity.treino.ListaTreino;

public class MenuManutencao extends ListActivity {
	private String[] opcoes = new String[] { "Categoria Muscular" , 
											 "Equipamento" , 
											 "Marca Equipamento",
											 "Músculo",
											 "Treino",
											 "Teste",
											 "Voltar"};	
	private static final int CATEGORIA_MUSCULAR = 0;
	private static final int EQUIPAMENTO 		= 1;
	private static final int MARCA_EQUIPAMENTO	= 2;
	private static final int MUSCULO 			= 3;
	private static final int TREINO				= 4;
	private static final int TESTE				= 5;
	
	@Override
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);
		setListAdapter(new ArrayAdapter<String>(this ,	R.layout.simple_list_item_1 , opcoes));
	}
	
	//METODO CLIQUE DA LISTA
	protected void onListItemClick(ListView l, View v, int position, long id){
		super.onListItemClick(l, v, position, id);
		switch (position) {
		case CATEGORIA_MUSCULAR:
			startActivity(new Intent(MenuManutencao.this, ListaCategoriaMuscular.class));
			break;
		case EQUIPAMENTO:
			startActivity(new Intent(MenuManutencao.this, ListaEquipamento.class));
			break;
		case MARCA_EQUIPAMENTO:	
			startActivity(new Intent(MenuManutencao.this, ListaMarcaEquipamento.class )); 
			break;
		case MUSCULO:
			startActivity(new Intent(MenuManutencao.this, ListaMusculo.class));
			break;
		case TREINO:
			startActivity(new Intent(MenuManutencao.this, ListaTreino.class));
			break;
		case TESTE:
			startActivity(new Intent(MenuManutencao.this, ListaTeste.class));
			break;
		default:
			finish();
			break;
		}
	}
	
}
