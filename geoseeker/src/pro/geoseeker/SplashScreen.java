package pro.geoseeker;

import pro.geoseeker.R;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

public class SplashScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);

		new AppLoader().execute();
	}

	/**
	 *  ласс, выполн€ющий асинхронную загрузку данных.
	 */
	private class AppLoader extends AsyncTask<Void, Void, Void> {

		/**
		 * јсинхронна€ задача.
		 */

		@Override
		protected Void doInBackground(Void... arg0) {

			// ќсуществл€ем загрузку.

			return null;
		}

		/**
		 * —обытие, вызываемое после завершени€ выполнени€ асинхронной задачи.
		 */
		@Override
		protected void onPostExecute(Void aVoid) {

			super.onPostExecute(aVoid);

			// создаем новый Intent дл€ перехода на MainActivity
			Intent intent = new Intent(SplashScreen.this, MainMenu.class);

			// запускаем новое Activity c Intent-ом.
			startActivity(intent);

			// завершаем работу потока
			finish();
		}

	}
}
