package pro.geoseeker;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class DGisMapMainScreen extends FragmentActivity {

	static final String TAG = "myLogs";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.dgis_main_screen);

		WebView webView = (WebView) findViewById(R.id.map);

		// –азрешение использовать JavaScript.
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);

		// «агрузка веб-страницы, сохранненной в активах.
		webView.loadUrl("file:///android_asset/dgis_map.html");

		Log.d(TAG, "DGisMapMainScreen::onCreate");
	}
}
