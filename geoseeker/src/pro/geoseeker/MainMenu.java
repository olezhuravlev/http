package pro.geoseeker;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenu extends FragmentActivity implements OnClickListener {

	static final String TAG = "myLogs";
	static final int PAGE_COUNT = 3;

	ViewPager pager;
	PagerAdapter pagerAdapter;

	Button buttonOpenMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu_screen);

		// Создание экземпляра адаптера и установка его ЭУ ViewPager.
		pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
		pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(pagerAdapter);

		// Установка слушателя для ЭУ ViewPager.
		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// Log.d(TAG, "onPageSelected, position = " + position);
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				// Log.d(TAG, "onPageScrolled, position = " + position
				// + ", positionOffset = " + positionOffset
				// + ", positionOffsetPixels = " + positionOffsetPixels);
			}

			@Override
			public void onPageScrollStateChanged(int state) {
				// Log.d(TAG, "onPageScrollStateChanged, state = " + state);
			}
		});

	}

	/**
	 * Класс адаптера фрагмента.
	 */
	private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

		// Конструктор.
		public MyFragmentPagerAdapter(FragmentManager fm) {

			super(fm);

			Log.d(TAG, "MyFragmentPagerAdapter::MyFragmentPagerAdapter");
		}

		/**
		 * Возвращает экземпляр класса Fragment для указанного номера страницы.
		 */
		@Override
		public Fragment getItem(int position) {

			Log.d(TAG, "MyFragmentPagerAdapter::getItem(" + position + ")");

			return PageFragment.newInstance(position);
		}

		/**
		 * Возвращает общее количество страниц.
		 */
		@Override
		public int getCount() {

			// Log.d(TAG, "MyFragmentPagerAdapter::getCount() = " + PAGE_COUNT);

			return PAGE_COUNT;
		}

		/**
		 * Возвращает строку, содержащую заголовок фрагмента.
		 */
		@Override
		public CharSequence getPageTitle(int position) {

			// Log.d(TAG, "MyFragmentPagerAdapter::getPageTitle(" + position +
			// ")");
			Resources res = getResources();

			CharSequence title = "Title " + position;
			switch (position) {
			case 0:
				title = res.getString(R.string.list_title);
				break;
			case 1:
				title = res.getString(R.string.googlemap_title);
				break;
			case 2:
				title = res.getString(R.string.dgis_title);
				break;
			}

			return title;
		}
	}

	// Обработчик нажатия кнопок, находящихся в лайауте main_menu_screen.
	// (почему-то нужно размещать обработчик здесь, а не в классах,
	// к которым привязаны лайауты, отображаемые в pager).
	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.button1:

			Intent intent = new Intent(this, DGisMapMainScreen.class);
			startActivity(intent);

			break;

		}

	}
}
