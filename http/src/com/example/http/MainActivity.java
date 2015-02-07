package com.example.http;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	TextView textView1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textView1 = (TextView) findViewById(R.id.textView1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View view) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					f();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
	}

	public void f() throws IOException {

		String fileName = "myFile.txt";

		String upLoadServerUri = "http://express.nsk.ru:9999/erfile.php";
		String twoHyphens = "--";
		String boundary = ";";
		String lineEnd = "|";

		String description = "Description about the image";

		int maxBufferSize = 500;

		// Поток на чтение файла.
		// File sourceFile = new File(fileName);
		// FileInputStream fileInputStream = new FileInputStream(sourceFile);
		AssetManager assetManaget = getAssets();
		InputStream fileInputStream = assetManaget.open(fileName);

		// Открываем ХТТП-соединение.
		URL url = new URL(upLoadServerUri);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		// Соединение разрешает ввод.
		conn.setDoInput(true);

		// Соединение разрешает вывод.
		conn.setDoOutput(true);

		// Кэш не использовать.
		conn.setUseCaches(false);

		// Команда запроса, которая будет отправлена на удалённый ХТТП-сервер.
		conn.setRequestMethod("POST");

		// Значения полей заголовка.
		conn.setRequestProperty("Connection", "Keep-Alive");
		conn.setRequestProperty("ENCTYPE", "multipart/form-data");
		conn.setRequestProperty("Content-Type", "multipart/form-data;boundary="
				+ boundary);
		conn.setRequestProperty("uploaded_file", fileName);

		// Поток вывода.
		DataOutputStream dos = new DataOutputStream(conn.getOutputStream());

		// Запись байтов.
		dos.writeBytes(twoHyphens + boundary + lineEnd);
		dos.writeBytes("Content-Disposition: form-data; name=\"description\""
				+ lineEnd);
		dos.writeBytes("Content-Type: text/plain; charset=UTF-8" + lineEnd);
		dos.writeBytes("Content-Length: " + description.length() + lineEnd);
		dos.writeBytes(lineEnd);
		dos.writeBytes(description + lineEnd);
		dos.writeBytes(twoHyphens + boundary + lineEnd);
		dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
				+ fileName + "\"" + lineEnd);
		dos.writeBytes(lineEnd);

		// Количество байт, которые могут быть прочитаны или пропущены без
		// блокировки дальнейшего ввода.
		// Метод даёт слабую гарантию и не очень полезен на практике.
		// Нельзя использовать этот метод для задания размера контейнера
		int bytesAvailable = fileInputStream.available();

		// Буфер достаточного размера.
		int bufferSize = Math.min(bytesAvailable, maxBufferSize);
		byte[] buffer = new byte[bufferSize];

		// Чтение первой порции данных из файла и размещение её в буфере.
		int bytesRead = fileInputStream.read(buffer, 0, bufferSize);

		while (bytesRead > 0) {

			// Запись порции данных в поток.
			dos.write(buffer, 0, bufferSize);

			bytesAvailable = fileInputStream.available();
			bufferSize = Math.min(bytesAvailable, maxBufferSize);

			// Чтение следующей порции данных.
			bytesRead = fileInputStream.read(buffer, 0, bufferSize);
		}

		// Запись байт.
		dos.writeBytes(lineEnd);
		dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

		fileInputStream.close();
	}

	public int uploadFile() {

		// String fileName = sourceFileUri;
		final String filepath = "";
		File sourceFile = new File(filepath);

		String upLoadServerUri = "http://express.nsk.ru:9999/erfile.php";

		HttpURLConnection conn = null;
		DataOutputStream dos = null;
		String lineEnd = "\r\n";
		String twoHyphens = "--";
		String boundary = "*****";
		int bytesRead, bytesAvailable, bufferSize;
		byte[] buffer;
		int maxBufferSize = 1 * 1024 * 1024;

		int serverResponseCode = 0;

		if (!sourceFile.isFile()) {

			// dialog.dismiss();

			Log.e("uploadFile", "Source File not exist :" + filepath);

			runOnUiThread(new Runnable() {
				public void run() {
					textView1.setText("Source File not exist :" + filepath);
				}
			});

			return 0;

		} else {

			try {

				FileInputStream fileInputStream = new FileInputStream(
						sourceFile);
				URL url = new URL(upLoadServerUri);
				conn = (HttpURLConnection) url.openConnection();
				conn.setDoInput(true); // Allow Inputs
				conn.setDoOutput(true); // Allow Outputs
				conn.setUseCaches(false); // Don't use a Cached Copy
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Connection", "Keep-Alive");
				// conn.setRequestProperty("ENCTYPE", "multipart/form-data");
				conn.setRequestProperty("Content-Type",
						"multipart/form-data;boundary=" + boundary);
				conn.setRequestProperty("uploaded_file", filepath);

				dos = new DataOutputStream(conn.getOutputStream());

				dos.writeBytes(twoHyphens + boundary + lineEnd);

				// Adding Parameter name
				dos.writeBytes("Content-Disposition: form-data; name=\"name\""
						+ lineEnd);
				// dos.writeBytes("Content-Type: text/plain; charset=UTF-8" +
				// lineEnd);
				// dos.writeBytes("Content-Length: " + name.length() + lineEnd);
				dos.writeBytes(lineEnd);
				dos.writeBytes("some name");
				dos.writeBytes(lineEnd);

				dos.writeBytes(twoHyphens + boundary + lineEnd);

				// Adding Parameter phone
				dos.writeBytes("Content-Disposition: form-data; name=\"phone\""
						+ lineEnd);
				// dos.writeBytes("Content-Type: text/plain; charset=UTF-8" +
				// lineEnd);
				// dos.writeBytes("Content-Length: " + name.length() + lineEnd);
				dos.writeBytes(lineEnd);
				dos.writeBytes("1234567890");
				dos.writeBytes(lineEnd);

				// Json_Encoder encode=new Json_Encoder();
				// call to encode method and assigning response data to variable
				// 'data'
				// String data=encode.encod_to_json();
				// response of encoded data
				// System.out.println(data);

				// Adding Parameter filepath

				dos.writeBytes(twoHyphens + boundary + lineEnd);
				// String filepath = "http://192.168.1.110/echo/uploads" +
				// fileName;

				dos.writeBytes("Content-Disposition: form-data; name=\"filepath\""
						+ lineEnd);
				// dos.writeBytes("Content-Type: text/plain; charset=UTF-8" +
				// lineEnd);
				// dos.writeBytes("Content-Length: " + name.length() + lineEnd);
				dos.writeBytes(lineEnd);
				dos.writeBytes(filepath);
				dos.writeBytes(lineEnd);

				// Adding Parameter media file(audio,video and image)

				dos.writeBytes(twoHyphens + boundary + lineEnd);

				dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
						+ filepath + "\"" + lineEnd);
				dos.writeBytes(lineEnd);
				// create a buffer of maximum size
				bytesAvailable = fileInputStream.available();
				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				buffer = new byte[bufferSize];
				// read file and write it into form...
				bytesRead = fileInputStream.read(buffer, 0, bufferSize);

				while (bytesRead > 0) {
					dos.write(buffer, 0, bufferSize);
					bytesAvailable = fileInputStream.available();
					bufferSize = Math.min(bytesAvailable, maxBufferSize);
					bytesRead = fileInputStream.read(buffer, 0, bufferSize);
				}

				// send multipart form data necesssary after file data...
				dos.writeBytes(lineEnd);
				dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

				serverResponseCode = conn.getResponseCode();
				String serverResponseMessage = conn.getResponseMessage();

				Log.i("uploadFile", "HTTP Response is : "
						+ serverResponseMessage + ": " + serverResponseCode);

				if (serverResponseCode == 200) {

					runOnUiThread(new Runnable() {
						public void run() {

							String msg = "File Upload Completed.\n\n See uploaded file here : \n\n"
									+ "c:/wamp/www/echo/uploads";
							textView1.setText(msg);
							Toast.makeText(MainActivity.this,
									"File Upload Complete.", Toast.LENGTH_SHORT)
									.show();
						}
					});
				}

				// close the streams //
				fileInputStream.close();
				dos.flush();
				dos.close();

			} catch (MalformedURLException ex) {

				// dialog.dismiss();
				ex.printStackTrace();

				runOnUiThread(new Runnable() {
					public void run() {
						textView1
								.setText("MalformedURLException Exception : check script url.");
						Toast.makeText(MainActivity.this,
								"MalformedURLException", Toast.LENGTH_SHORT)
								.show();
					}
				});

				Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
			} catch (final Exception e) {

				// dialog.dismiss();
				e.printStackTrace();

				runOnUiThread(new Runnable() {

					public void run() {

						textView1.setText("Got Exception : " + e.toString());
						Toast.makeText(MainActivity.this,
								"Got Exception : see logcat ",
								Toast.LENGTH_SHORT).show();
					}
				});
				Log.e("Upload file to server Exception",
						"Exception : " + e.getMessage(), e);
			}
			// dialog.dismiss();
			return serverResponseCode;
		}
	}

}
