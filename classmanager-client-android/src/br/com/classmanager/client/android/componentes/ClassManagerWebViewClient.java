package br.com.classmanager.client.android.componentes;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import br.com.classmanager.client.android.utils.Propriedades;

public class ClassManagerWebViewClient extends WebViewClient {

	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		if (Uri.parse(url).getHost().contains(Propriedades.getDominio())) {
			return false;
		}

		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		((Application) view.getContext()).startActivity(intent);
		return true;
	}

}
