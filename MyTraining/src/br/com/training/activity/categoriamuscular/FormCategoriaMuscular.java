package br.com.training.activity.categoriamuscular;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import br.com.training.activity.R;
import br.com.training.components.ConsistenciaMSG;
import br.com.training.dao.CategoriaMuscularDAO;
import br.com.training.entidades.CategoriaMuscular;

public class FormCategoriaMuscular extends Activity{
	private ConsistenciaMSG		textoConsistencia;
	private EditText			telaDescricao;
	private ImageButton			btSalvar;
	private ImageButton			btVoltar;
	private CategoriaMuscular	ctm;
	
	@Override
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);
		setContentView(R.layout.formcategoriamuscular);
		
		//CRIAR OS OBJETOS DOS COMPONENTES DO FORM
		textoConsistencia	= (ConsistenciaMSG) findViewById(R.formcategoriamuscular.textoconsistencia);
		telaDescricao		= (EditText) 		findViewById(R.formcategoriamuscular.descricao);
		btSalvar			= (ImageButton)		findViewById(R.formcategoriamuscular.salvar);
		btVoltar			= (ImageButton)		findViewById(R.formcategoriamuscular.voltar);		
		
		// RECEBER A CATEGORIA MUSCULAR DA TELA DE LISTA CATEGORIA MUSCULAR PARA ALTERAÇÃO
		ctm = (CategoriaMuscular) getIntent().getSerializableExtra("ctmSelecionada");
		if(ctm == null){
			ctm = new CategoriaMuscular();
		}else{
			telaDescricao.setText(ctm.getDescricao());
		}

		// EVENTO DE CLIQUE DO BOTÃO SALVAR
		btSalvar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(validar()){
					ctm.setDescricao(telaDescricao.getEditableText().toString().trim());
					CategoriaMuscularDAO ctgDAO = new CategoriaMuscularDAO(FormCategoriaMuscular.this);
					ctgDAO.salvar(ctm);
					telaDescricao.setText("");
					Toast.makeText(FormCategoriaMuscular.this, "Categoria Muscular foi salva com sucesso!", Toast.LENGTH_SHORT).show();
				}
				telaDescricao.setFocusable(true);				
			}
		});
		// EVENTO DE CLIQUE DO BOTÃO VOLTAR
		btVoltar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}
	// ROTINA DE VALIDAÇÃO DOS CAMPOS DO FORM	
	public boolean validar(){
		if (telaDescricao.getText().toString().trim().equals("")){
			textoConsistencia.mensagem( getString(R.string.msg_Campo_Descricao_em_branco) , ConsistenciaMSG.ERRO);
			return false;
		}
		return true;
	}
}
