package br.com.training.activity.treino;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import br.com.training.activity.R;
import br.com.training.components.ConsistenciaMSG;
import br.com.training.dao.TreinoDAO;
import br.com.training.entidades.Treino;

public class FormTreino extends Activity {
	private String			modo;
	private	EditText		edtDescricao;
	private EditText		edtCodigo;
	private	Spinner			spnTempoDuracao;
	private	ImageButton		btnSalvar;
	private	ImageButton		btnVoltar;
	private	Treino			treinoSelecionado;
	private ConsistenciaMSG	txtConsistencia;
	private TreinoDAO		treinoDAO = new TreinoDAO(FormTreino.this);
	
	private ArrayAdapter<String> adaptador; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.formtreino);
		spnTempoDuracao		= (Spinner)			findViewById(R.formtreino.spntempoduracao);
		edtDescricao		= (EditText)		findViewById(R.formtreino.edtdescricao);
		edtCodigo			= (EditText)		findViewById(R.formtreino.edtCodigo);
		btnSalvar			= (ImageButton)		findViewById(R.formtreino.btnsalvar);
		btnVoltar			= (ImageButton) 	findViewById(R.formtreino.btnvoltar);
		txtConsistencia		= (ConsistenciaMSG) findViewById(R.formtreino.txtconsistenciaMSG);
		
		edtCodigo.setFocusable(true);
		spnTempoDuracao.setPrompt(getString(R.string.label_tempo_de_duracao));
		adaptador = new ArrayAdapter<String>(FormTreino.this, 
											android.R.layout.simple_spinner_item, 
											getResources().getStringArray(R.array.spn_tempos_duracao));
		adaptador.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		spnTempoDuracao.setAdapter(adaptador);
		
		treinoSelecionado 	= (Treino) getIntent().getSerializableExtra("treinoSelecionado");
		modo 			  	= (String) getIntent().getSerializableExtra("modo");
		
		if(modo.equals("N")){
			treinoSelecionado = new Treino();
		}else{
			if(modo.equals("A")){
			edtCodigo.setText(treinoSelecionado.getCodigo());	
			edtDescricao.setText(treinoSelecionado.getDescricao());
			spnTempoDuracao.setSelection(retornarPosicaoCombo(treinoSelecionado.getTempoDuracao()));
			}
		}
		
		btnSalvar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(validar()){
					treinoSelecionado.setCodigo(edtCodigo.getEditableText().toString().trim());
					treinoSelecionado.setDescricao(edtDescricao.getEditableText().toString().trim());
					treinoSelecionado.setTempoDuracao(spnTempoDuracao.getSelectedItem().toString());
					
					treinoDAO.salvar(treinoSelecionado, modo);
					
					edtCodigo.setText("");
					edtDescricao.setText("");
					Toast.makeText(FormTreino.this, "Treino salvo com sucesso!", Toast.LENGTH_LONG).show();
					edtCodigo.setFocusable(true);
					spnTempoDuracao.setSelection(0);
				}
			}
		});
		
		btnVoltar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		
		spnTempoDuracao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				treinoSelecionado.setTempoDuracao(arg0.getSelectedItem().toString());
			}

			public void onNothingSelected(AdapterView<?> arg0) {}		
			
		});
	}
	
	public int retornarPosicaoCombo(String tempo){
		
			if(tempo.equals("00:30")){
				return 1;
			}else{
			 	if(tempo.equals("01:00")){
					return 2;
				}else{
					if(tempo.equals("01:30")){
						return 3;
					}else{
						if(tempo.equals("02:00")){
							return 4;
						}else{
							//TODO Chamar Dialog Custom --> http://developer.android.com/guide/topics/ui/dialogs.html
							return 5;
						}
					}
				}
			}
	}
	
	public boolean validar(){
		txtConsistencia.setText("");
		if(edtCodigo.getText().toString().trim().equals("")){
			txtConsistencia.mensagem(getString(R.string.msg_Campo_Codigo_em_branco), ConsistenciaMSG.ERRO);
			return false;
		}
		
		if( this.treinoDAO.existeCodigoTreino(edtCodigo.getText().toString().trim()) ){
			txtConsistencia.mensagem(getString(R.string.msg_Codigo_Ja_Existe), ConsistenciaMSG.ERRO);
			return false;
		}
		
		if(edtDescricao.getText().toString().trim().equals("")){
			txtConsistencia.mensagem(getString(R.string.msg_Campo_Descricao_em_branco), ConsistenciaMSG.ERRO);
			return false;
		}
		
		return true;
	}
	
}
