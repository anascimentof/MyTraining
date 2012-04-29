package br.com.training.activity.grupoexercicio;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import br.com.training.activity.R;
import br.com.training.components.ConsistenciaMSG;
import br.com.training.dao.GrupoExercicioDAO;
import br.com.training.entidades.GrupoExercicio;

public class FormGrupoExercicio extends Activity {
	private EditText 			edtdescricao;
	private GrupoExercicio		grupoexercicio;
	private ImageButton			btnSalvar;
	private ImageButton			btnVoltar;
	private ConsistenciaMSG		consistencia;
	private GrupoExercicioDAO	grupoExercicioDAO = new GrupoExercicioDAO(FormGrupoExercicio.this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formgrupoexercicio);
		
		edtdescricao 		= (EditText) 	findViewById(R.formgrupoexercicio.edtdescricao);
		btnSalvar			= (ImageButton)	findViewById(R.formgrupoexercicio.btnsalvar);
		btnVoltar			= (ImageButton)	findViewById(R.formgrupoexercicio.btnvoltar);
		grupoexercicio		= (GrupoExercicio) getIntent().getSerializableExtra("grupoSelecionado");
		consistencia		= (ConsistenciaMSG) findViewById(R.formgrupoexercicio.consistenciaMSG1);
		
		if(grupoexercicio==null){
			grupoexercicio = new GrupoExercicio();
		}else{
			edtdescricao.setText(grupoexercicio.getDescricao());
		}
		
		btnSalvar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(validar()){
					grupoexercicio.setDescricao(edtdescricao.getText().toString().trim());
					grupoExercicioDAO.salvar(grupoexercicio);
					edtdescricao.setText("");
					edtdescricao.setFocusable(true);
					Toast.makeText(FormGrupoExercicio.this, R.string.msg_Grupo_Exercicio_salvo_com_sucesso , Toast.LENGTH_LONG).show();
				}
			}
		});
		
		btnVoltar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		
	}

	private boolean validar(){
		if(edtdescricao.getText().toString().trim().equals("")){
			consistencia.mensagem("Campo descrição em branco.", ConsistenciaMSG.ERRO);
			return false;
		}
		return true;
	}
	
	
}
