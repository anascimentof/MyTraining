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
import br.com.training.activity.R;
import br.com.training.entidades.Treino;

public class FormTreino extends Activity {
	private	EditText		edtDescricao;
	private	Spinner			spnTempoDuracao;
	private	ImageButton		btnSalvar;
	private	ImageButton		btnVoltar;
	private	Treino			treinoSelecionado;
	private static final String[] itens = new String[]{ "00:30" ,  //0
													    "01:00" ,  //1
													    "01:30" ,  //2
													    "02:00" ,  //3
													    "Informar outro tempo"
														};
	private ArrayAdapter<String> adaptador = new ArrayAdapter<String>(FormTreino.this, android.R.layout.simple_spinner_dropdown_item, itens);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.formtreino);
		spnTempoDuracao		= (Spinner)		findViewById(R.formtreino.spntempoduracao);
		edtDescricao		= (EditText)	findViewById(R.formtreino.edtdescricao);
		btnSalvar			= (ImageButton)	findViewById(R.formtreino.btnsalvar);
		btnVoltar			= (ImageButton) findViewById(R.formtreino.btnvoltar);
		
		spnTempoDuracao.setPrompt("Tempo de Duração (HH:MM)");
		adaptador.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		spnTempoDuracao.setAdapter(adaptador);
		
		treinoSelecionado = (Treino) getIntent().getSerializableExtra("treinoSelecionado");
		
		if(treinoSelecionado != null){
			treinoSelecionado = new Treino();
		}else{
			edtDescricao.setText(treinoSelecionado.getDescricao());
			spnTempoDuracao.setSelection(retornarPosicaoCombo(treinoSelecionado.getTempoDuracao()));
		}
		
		btnSalvar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
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
		
		if(tempo=="00:30"){
			return 0;
		}else{
			if(tempo=="01:00"){
				return 1;
			}else{
				if(tempo=="01:30"){
					return 2;
				}else{
					if(tempo=="02:00"){
						return 3;
					}else{
						return 99;
					}
				}
			}
		}
		
	}
	
}
