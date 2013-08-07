package br.com.classmanager.client.android.componentes;

import android.content.Context;
import android.widget.Toast;

public class ClassManagerJavascriptInterface {

	Context context;

	public ClassManagerJavascriptInterface(Context context) {
		this.context = context;
	}

	/** Show a toast from the web page */
	public void showToast(String toast) {
		Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
	}

}
