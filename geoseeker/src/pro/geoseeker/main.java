package pro.geoseeker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class main extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.button1:
			
			Intent intent = new Intent(this, DGisMapMainScreen.class);
			startActivity(intent);
			
			break;
			
		case R.id.button2:
			Intent intent2 = new Intent(this, GoogleMapMainScreen.class);
			startActivity(intent2);
			
			break;
		}

	}
}
