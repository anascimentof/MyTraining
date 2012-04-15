package br.com.training.activity.teste;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import br.com.training.activity.R;
import br.com.training.components.ConsistenciaMSG;
import br.com.training.dao.TesteDAO;
import br.com.training.entidades.Teste;

public class FormTeste extends Activity {
	private ConsistenciaMSG textoConsistencia;
	private EditText		edtDescricao;
	private ImageButton		btnSalvar;
	private ImageButton		btnVoltar;
	private Teste			teste;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formteste);
		
		//CRIAR OBJETOS DOS COMPONENTES DO FORM
		textoConsistencia		=	(ConsistenciaMSG)	findViewById(R.formteste.textoconsistencia);
		edtDescricao			=	(EditText)			findViewById(R.formteste.edtDescricao);
		btnSalvar				=	(ImageButton)		findViewById(R.formteste.btnSalvar);
		btnVoltar				=	(ImageButton)		findViewById(R.formteste.btnVoltar);
		
		//RECEBER TESTE DA LISTATESTE PARA ALTERACAO
		teste	=	(Teste) getIntent().getSerializableExtra("testeSelecionado");
		if(teste == null){
			teste = new Teste();
		}else{
			edtDescricao.setText(teste.getDsTeste());
		}
		
		//BOTAO SALVAR
		btnSalvar.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				if (validar()){
					teste.setDsTeste(edtDescricao.getEditableText().toString().trim());
					TesteDAO testedao = new TesteDAO(FormTeste.this);
					testedao.salvar(teste);
					edtDescricao.setText("");
					Toast.makeText(FormTeste.this, "Teste salvo com sucesso", Toast.LENGTH_LONG).show();
				}
				
			}
		});
		
		// EVENTO DE CLIQUE DO BOTÃO VOLTAR
		btnVoltar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		
	}

	public boolean validar(){
		if (edtDescricao.getText().toString().isEmpty() || 
			edtDescricao.getText().toString().trim().equals("")){
			textoConsistencia.mensagem("Campo descricação não pode ser vazio", ConsistenciaMSG.ERRO);
			return false;
		}
		return true;
	}
	
}
