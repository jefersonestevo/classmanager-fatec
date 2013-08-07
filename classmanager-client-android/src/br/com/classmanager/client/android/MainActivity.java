package br.com.classmanager.client.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.WebView;
import br.com.classmanager.client.android.componentes.ClassManagerJavascriptInterface;
import br.com.classmanager.client.android.componentes.ClassManagerWebViewClient;
import br.com.classmanager.client.android.utils.Propriedades;

public class MainActivity extends Activity {

	private WebView webView;

	@Override
	@SuppressLint("SetJavaScriptEnabled")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		String url = "http://" + Propriedades.getDominio() + "/"
				+ Propriedades.getContexto() + "/pages/mobile/index.jsf";

		webView = (WebView) findViewById(R.id.webview);
		webView.loadUrl(url);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.addJavascriptInterface(
				new ClassManagerJavascriptInterface(this), "Android");
		webView.setWebViewClient(new ClassManagerWebViewClient());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
			webView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
