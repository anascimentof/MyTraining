package br.com.training.activity.marcaequipamento;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import br.com.training.activity.R;
import br.com.training.dao.MarcaEquipamentoDAO;
import br.com.training.entidades.MarcaEquipamento;

public class FormMarcaEquipamento extends Activity {
	private MarcaEquipamento marcaequipamento;
	private EditText		 telaDescricao;
	
	@Override
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);
		setContentView(R.layout.formmarcaequipamento);
		telaDescricao 		= (EditText) findViewById(R.formMarcaEquipamento.descricao);
		marcaequipamento	= (MarcaEquipamento) getIntent().getSerializableExtra("marcaSelecionada");
		
		if(marcaequipamento == null){
			marcaequipamento = new MarcaEquipamento();
		}else{
			telaDescricao.setText(marcaequipamento.getDescricao());
		}
		
		//ROTINA DO BOTAO SALVAR DO FORM
		ImageButton btSalvar = (ImageButton) findViewById(R.formMarcaEquipamento.salvar);
		btSalvar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(validar()){
					marcaequipamento.setDescricao(telaDescricao.getEditableText().toString().trim());
					MarcaEquipamentoDAO marcaequipamentodao = new MarcaEquipamentoDAO(FormMarcaEquipamento.this);
					marcaequipamentodao.salvar(marcaequipamento);
					telaDescricao.setText("");
					telaDescricao.setFocusable(true);
					Toast.makeText(FormMarcaEquipamento.this, "Marca de Equipamento salvo com sucesso!", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		//ROTINA DO BOTAO CANCELAR DO FORM
		ImageButton btCancelar = (ImageButton) findViewById(R.formMarcaEquipamento.cancelar);
		btCancelar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();				
			}
		});
	}

	//METODO DE VALIDAR OS CAMPOS DO FORM
	private boolean validar(){
		if (telaDescricao.getText().toString().trim().equals("") ){
			Toast.makeText(FormMarcaEquipamento.this, "Campo Descrição não pode ser vazio.", Toast.LENGTH_LONG).show();
			telaDescricao.setFocusable(true);
			return false ;
		}
		return true;
	}

}