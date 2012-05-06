package br.com.training.activity.exercicio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import br.com.training.activity.R;
import br.com.training.components.ConsistenciaMSG;
import br.com.training.dao.ExercicioDAO;
import br.com.training.dao.GrupoExercicioDAO;
import br.com.training.entidades.Exercicio;
import br.com.training.entidades.GrupoExercicio;

public class FormExercicio extends Activity {
	private EditText 				edtDescricao 			= null;
	private ConsistenciaMSG			txtConsistencia			= null;
	private ImageButton				btnSalvar				= null;
	private ImageButton 			btnVoltar				= null;
	private Exercicio				exercicio			  	= null;
	private ListView				listaGrupo				= null;
	private List<GrupoExercicio>	listaGrupoExercicio		= new ArrayList<GrupoExercicio>();
	private ExercicioDAO			exercicioDAO			= new ExercicioDAO(FormExercicio.this);
	private GrupoExercicioDAO		grupoDAO				= new GrupoExercicioDAO(FormExercicio.this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.formexercicio);
		
		edtDescricao	= (EditText) 	findViewById(R.formexercicio.edtDescricao);
		btnSalvar		= (ImageButton)	findViewById(R.formexercicio.btmsalvar);
		btnVoltar		= (ImageButton)	findViewById(R.formexercicio.btnvoltar);
		listaGrupo		= (ListView)	findViewById(R.formexercicio.lista);
		txtConsistencia	= (ConsistenciaMSG) findViewById(R.formexercicio.consistenciaMSG1);
		
		exercicio = (Exercicio) getIntent().getSerializableExtra("exercicioSelecionado");
		if(exercicio == null){
			exercicio = new Exercicio();
		}else{
			edtDescricao.setText(exercicio.getDescricao());
		}
		
		carregarListaGrupo();
		
		btnSalvar.setOnClickListener( new OnClickListener() {
			public void onClick(View v) {
				carregarItensSelecionados();
				txtConsistencia.setText("");
				if(validar()){
					exercicio.setDescricao(edtDescricao.getText().toString().trim());
					exercicioDAO.salvar(exercicio);
					edtDescricao.setText("");
					edtDescricao.setFocusable(true);
					Toast.makeText(FormExercicio.this, "Exercício salvo com sucesso!", Toast.LENGTH_LONG).show();
					limparItensSelecionado();
				}
				
			}
		});
		
		btnVoltar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		
	}

	public void carregarListaGrupo(){
		int count = 0;
		
		listaGrupoExercicio = grupoDAO.listar();

		listaGrupo.setItemsCanFocus(false);
		listaGrupo.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		listaGrupo.setAdapter( new ArrayAdapter<GrupoExercicio>(this, 	
							   android.R.layout.simple_list_item_multiple_choice,
							   listaGrupoExercicio) ) ;
		
		if(exercicio.getCodigo() != 0){
			
			count = listaGrupo.getAdapter().getCount();
			
			Iterator<GrupoExercicio>  i = exercicio.getGrupoxExercicio().iterator();
			while (i.hasNext()) {
				GrupoExercicio grupo = (GrupoExercicio) i.next();
				for (int j = 0; j < count; j++) {
					GrupoExercicio listagrp = (GrupoExercicio) listaGrupo.getAdapter().getItem(j);
					if(grupo.getCodigo() == listagrp.getCodigo()){
						listaGrupo.setItemChecked(j, true);
						break;
					}
				}
			}
		}
	}
	
	public void carregarItensSelecionados(){
		int count = listaGrupo.getAdapter().getCount();
		exercicio.limparGrupoExercicio();
		for (int i = 0; i < count; i++) {
			if(listaGrupo.isItemChecked(i)){
				GrupoExercicio grupo = (GrupoExercicio) listaGrupo.getItemAtPosition(i);
				exercicio.adicionarGrupoExercicio(grupo);
			}
		}
	}
	
	public void limparItensSelecionado(){
		int count = listaGrupo.getAdapter().getCount();
		for (int i = 0; i < count; i++) {
			listaGrupo.setItemChecked(i, false);
		}
	}
	
	public boolean validar(){
		if(edtDescricao.getText().toString().trim().equals("")){
			txtConsistencia.mensagem( getString(R.string.msg_Campo_Descricao_em_branco), ConsistenciaMSG.ERRO);
			return false;
		}
		if(exercicio.getGrupoxExercicio().isEmpty()){
			txtConsistencia.mensagem("Selecione pelo menos um grupo de exercício.", ConsistenciaMSG.ERRO);
			return false;
		}
		return true;
	}
	
}
