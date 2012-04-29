package br.com.training.activity.musculo;

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
import br.com.training.dao.CategoriaMuscularDAO;
import br.com.training.dao.MusculoDAO;
import br.com.training.entidades.CategoriaMuscular;
import br.com.training.entidades.Musculo;

public class FormMusculo extends Activity {
	private Musculo						musculo;
	private EditText					edtDescricao;
	private ConsistenciaMSG				txtConsistencia;
	private ListView 					lstSelecaoCategorias;
	private List<CategoriaMuscular>		listaCategoriaMuscular = new ArrayList<CategoriaMuscular>();;
			
	@Override
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);
		setContentView(R.layout.formmusculo);
		
		lstSelecaoCategorias	= (ListView) 		findViewById(R.formmusculo.lista);
		txtConsistencia 		= (ConsistenciaMSG) findViewById(R.formmusculo.txtconsistecia);
		edtDescricao			= (EditText)		findViewById(R.formmusculo.edtdescricao);

		musculo	= (Musculo) getIntent().getSerializableExtra("musculoSelecionado");
		if(musculo == null){
			musculo = new Musculo();
		}else{
			edtDescricao.setText(musculo.getDescricao());
		}
		
		carregarListaCategorias();
		
		ImageButton btnSalvar = (ImageButton) findViewById(R.formmusculo.btmsalvar);
		btnSalvar.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				carregarItensSelecionados();
				if(validar()){
					musculo.setDescricao(edtDescricao.getText().toString().trim());
					MusculoDAO musculoDAO = new MusculoDAO(FormMusculo.this);
					musculoDAO.salvar(musculo);
					edtDescricao.setText("");
					edtDescricao.setFocusable(true);
					Toast.makeText(FormMusculo.this, "Músculo salvo com sucesso!", Toast.LENGTH_LONG).show();
					limparItensSelecionado();
				}
			}
		});

		ImageButton btnVoltar= (ImageButton) findViewById(R.formmusculo.btnvoltar);
		btnVoltar.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				finish();
			}
		});
		
	}

	public boolean validar(){
		if(edtDescricao.getText().toString().trim().equals("")){
			txtConsistencia.mensagem( getString(R.string.msg_Campo_Descricao_em_branco), ConsistenciaMSG.ERRO);
			return false;
		}
		if(musculo.getCategMuscular().isEmpty()){
			txtConsistencia.mensagem("Selecione pelo menos uma categoria.", ConsistenciaMSG.ERRO);
			return false;
		}
		return true;
	}
	
	public void carregarListaCategorias(){
		int count = 0;

		CategoriaMuscularDAO ctgDAO = new CategoriaMuscularDAO(FormMusculo.this);
		
		listaCategoriaMuscular = ctgDAO.listar();

		ArrayAdapter<CategoriaMuscular> adapter = new ArrayAdapter<CategoriaMuscular>(this, 
												  android.R.layout.simple_list_item_multiple_choice, 
												  listaCategoriaMuscular);
		
		lstSelecaoCategorias.setItemsCanFocus(false);
		lstSelecaoCategorias.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		lstSelecaoCategorias.setAdapter(adapter);
		
		//Caso o musculo já existir ( modo de ALTERAÇÃO ) a rotina abaixo retorna as categorias do musculos já selecionadas
		if(musculo.getCodigo() != 0){
			
			count = lstSelecaoCategorias.getAdapter().getCount();
			
			Iterator<CategoriaMuscular> i = musculo.getCategMuscular().iterator();
			while ( i.hasNext() ) {
				CategoriaMuscular categoria  = (CategoriaMuscular) i.next();
				for (int j = 0; j < count; j++) {
					CategoriaMuscular listaCategoria = (CategoriaMuscular) lstSelecaoCategorias.getAdapter().getItem(j);
					if(categoria.getCodigo() == listaCategoria.getCodigo()){
						lstSelecaoCategorias.setItemChecked(j, true);
						break;
					}
				}
			}

		}
		
	}
	
	public void carregarItensSelecionados(){
		int count = lstSelecaoCategorias.getAdapter().getCount();
		musculo.limparCategorias();
		for (int i = 0; i < count; i++) {
			if(lstSelecaoCategorias.isItemChecked(i)){
				CategoriaMuscular categoria = (CategoriaMuscular) lstSelecaoCategorias.getItemAtPosition(i);
				musculo.adicionarCategoria(categoria);
			}
		}
	}
	
	public void limparItensSelecionado(){
		int count = lstSelecaoCategorias.getAdapter().getCount();
		for (int i = 0; i < count; i++) {
			lstSelecaoCategorias.setItemChecked(i, false);
		}
	}
	
}
