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
import br.com.training.activity.exercicio.ListaExercicio;
import br.com.training.activity.grupoexercicio.ListaGrupoExercicio;
import br.com.training.activity.marcaequipamento.ListaMarcaEquipamento;
import br.com.training.activity.musculo.ListaMusculo;

public class MenuManutencao extends ListActivity {
	private String[] opcoes = new String[] { "Categoria Muscular" ,
											 "Músculo",
											 "Marca Equipamento",
											 "Equipamento" , 
											 "Grupo Exercício",
											 "Exercício",
											 "Voltar"};
	
	private static final int CATEGORIA_MUSCULAR = 0;
	private static final int MUSCULO 			= 1;
	private static final int EQUIPAMENTO 		= 2;
	private static final int MARCA_EQUIPAMENTO	= 3;
	private static final int GRUPO_EXERCICIO	= 4;
	private static final int EXERCICIO			= 5;
	
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
		case MUSCULO:
			startActivity(new Intent(MenuManutencao.this, ListaMusculo.class));
			break;			
		case EQUIPAMENTO:
			startActivity(new Intent(MenuManutencao.this, ListaEquipamento.class));
			break;
		case MARCA_EQUIPAMENTO:	
			startActivity(new Intent(MenuManutencao.this, ListaMarcaEquipamento.class )); 
			break;
		case GRUPO_EXERCICIO:
			startActivity(new Intent(MenuManutencao.this, ListaGrupoExercicio.class));
			break;
		case EXERCICIO:
			startActivity(new Intent(MenuManutencao.this, ListaExercicio.class));
			break;
		default:
			finish();
			break;
		}
	}
	
}
