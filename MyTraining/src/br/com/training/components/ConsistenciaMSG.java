package br.com.training.components;

import br.com.training.activity.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class ConsistenciaMSG extends TextView {
	public static final String ERRO 	= "E";
	public static final String AVISO	= "A";
	
	public ConsistenciaMSG(Context context) {
		super(context);
	}

	public ConsistenciaMSG(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void mensagem(String texto, String consistencia) {
		if ( texto != null ) {
			setText(texto);	
			if (consistencia.equals(ERRO) ){
				setTextColor(getResources().getColor(R.color.VERMELHO));
				if (consistencia.equals(AVISO)){
					setTextColor(getResources().getColor(R.color.AMARELO));
				}
			}
		}	
	}
	
}
