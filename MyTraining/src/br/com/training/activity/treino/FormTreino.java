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
	private	EditText		edtDescricao;
	private EditText		edtCodigo;
	private	Spinner			spnTempoDuracao;
	private	ImageButton		btnSalvar;
	private	ImageButton		btnVoltar;
	private	Treino			treinoSelecionado;
	private ConsistenciaMSG	txtConsistencia;
	private TreinoDAO		treinoDAO = new TreinoDAO(FormTreino.this);
	private static final String[] itens = new String[]{ "Selecione",//0
														"00:30" ,  //1
													    "01:00" ,  //2
													    "01:30" ,  //3
													    "02:00" ,  //4
													    "Informar outro tempo" //5
														};
	private ArrayAdapter<String> adaptador; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.formtreino);
		spnTempoDuracao		= (Spinner)		findViewById(R.formtreino.spntempoduracao);
		edtDescricao		= (EditText)	findViewById(R.formtreino.edtdescricao);
		edtCodigo			= (EditText)	findViewById(R.formtreino.edtCodigo);
		btnSalvar			= (ImageButton)	findViewById(R.formtreino.btnsalvar);
		btnVoltar			= (ImageButton) findViewById(R.formtreino.btnvoltar);
		txtConsistencia		= (ConsistenciaMSG) findViewById(R.formtreino.txtconsistenciaMSG);
		
		
		spnTempoDuracao.setPrompt(getString(R.string.label_tempo_de_duracao));
		adaptador = new ArrayAdapter<String>(FormTreino.this, android.R.layout.simple_spinner_item, itens);
		adaptador.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		spnTempoDuracao.setAdapter(adaptador);
		
		treinoSelecionado = (Treino) getIntent().getSerializableExtra("treinoSelecionado");
		
		if(treinoSelecionado == null){
			treinoSelecionado = new Treino();
		}else{
			edtDescricao.setText(treinoSelecionado.getDescricao());
			spnTempoDuracao.setSelection(retornarPosicaoCombo(treinoSelecionado.getTempoDuracao()));
		}
		
		btnSalvar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(validar()){
					treinoSelecionado.setCodigo(edtCodigo.getEditableText().toString().trim());
					treinoSelecionado.setDescricao(edtDescricao.getEditableText().toString().trim());
					treinoSelecionado.setTempoDuracao(spnTempoDuracao.getSelectedItem().toString());
					
					treinoDAO.salvar(treinoSelecionado);
					
					edtDescricao.setText("");
					Toast.makeText(FormTreino.this, "Treino salvo com sucesso!", Toast.LENGTH_LONG).show();
					edtDescricao.setFocusable(true);
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
		
		if(tempo.equals("Selecione")){
			return 0;
		}else{
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
							return 5;
						}
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
		
		if(edtDescricao.getText().toString().trim().equals("")){
			txtConsistencia.mensagem(getString(R.string.msg_Campo_Descricao_em_branco), ConsistenciaMSG.ERRO);
			return false;
		}
		if(spnTempoDuracao.getSelectedItemPosition()==0){
			txtConsistencia.mensagem("Selecione um Tempo de duração", ConsistenciaMSG.ERRO);
			return false;
		}
		return true;
	}
	
}
