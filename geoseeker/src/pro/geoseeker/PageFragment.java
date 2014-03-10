package pro.geoseeker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Класс, представляющий страницу элемента управления pager.
 * 
 * 
 */
public class PageFragment extends Fragment {

	static final String TAG = "myLogs";

	static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
	static final String SAVE_PAGE_NUMBER = "save_page_number";

	int pageNumber;

	/**
	 * Статическая функция-конструктор для создания нового экземпляра
	 * PageFragment с указанным значением аргумента номера страницы. По этому
	 * аргументу впоследствии фрагмент будет определять, какое именно содержимое
	 * создавать во фрагменте.
	 */
	static PageFragment newInstance(int page) {

		PageFragment pageFragment = new PageFragment();

		Bundle arguments = new Bundle();
		arguments.putInt(ARGUMENT_PAGE_NUMBER, page);

		pageFragment.setArguments(arguments);

		return pageFragment;
	}

	/**
	 * Событие, вызываемое при создании нового экземпляра.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		// Извлекли значение страницы из аргументов
		// (номер страницы был помещен в аргументы статической функцией
		// newInstance).
		pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);

		// Если в сохраненном состоянии содержался номер страницы, то он
		// извлекается и размещается в переменной savedPageNumber.
		int savedPageNumber = -1; // Нигде не используется.
		// if (savedInstanceState != null) {
		// savedPageNumber = savedInstanceState.getInt(SAVE_PAGE_NUMBER);
		// }

		Log.d(TAG, "PageFragment::onCreate, pageNumber = [" + pageNumber
				+ "], savedPageNumber = [" + savedPageNumber + "]");
	}

	/**
	 * Событие, вызываемое при создании формы фрагмента.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Раздувание формы.
		// В зависимости от того, какой номер имеет создаваемая страница, она
		// наполняется разным содержимым.
		View view;
		switch (pageNumber) {
		case 0:
			
			// Экран, содержащий список.
			view = inflater.inflate(R.layout.list_screen, null);
			
			Log.d(TAG, "PageFragment::onCreateView, pageNumber = " + pageNumber);
			
			break;
			
		case 1:
			
			// Экран, содержащий карту Google.
			view = inflater.inflate(R.layout.googlemap_main_screen, null);
			
			Log.d(TAG, "PageFragment::onCreateView, pageNumber = " + pageNumber);
			
			break;
			
		case 2:
			
			// Экран, содержащий карту 2ГИС.
			view = inflater.inflate(R.layout.dgis_main_screen, null);
			
			Log.d(TAG, "PageFragment::onCreateView, pageNumber = " + pageNumber);
			
			break;
			
		default:
			
			// Если номер страницы неизвестен, то используется страница заставки.
			view = inflater.inflate(R.layout.splash_screen, null);
		}

		Log.d(TAG, "PageFragment::onCreateView, pageNumber = [" + pageNumber
				+ "]");

		return view;
	}

	/**
	 * Событие вызваемое при сохранении состояния фрагмента.
	 */
	@Override
	public void onSaveInstanceState(Bundle outState) {

		super.onSaveInstanceState(outState);

		// outState.putInt(SAVE_PAGE_NUMBER, pageNumber);

		Log.d(TAG, "PageFragment::onSaveInstanceState, pageNumber = ["
				+ pageNumber + "]");
	}

	/**
	 * Событие вызываемое при уничтожении экземляра PageFragment.
	 */
	@Override
	public void onDestroy() {

		super.onDestroy();

		Log.d(TAG, "PageFragment::onDestroy, pageNumber = [" + pageNumber + "]");
	}
}
