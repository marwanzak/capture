package com.marwan.capture;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Capture extends Activity {
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	static Uri capturedImageUri=null;
	Long filename;
	Calendar cal = Calendar.getInstance();
	String pathToOurFile = Environment.getExternalStorageDirectory().getPath()+"/"+filename+".jpg";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.capture);
		Button capture_but = (Button) findViewById(R.id.capture_but);
		capture_but.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				Long filename = (Long) cal.getTimeInMillis();
				File file = new File(Environment.getExternalStorageDirectory(),  (filename+".jpg"));
				pathToOurFile = Environment.getExternalStorageDirectory().getPath()+"/"+filename+".jpg";
				if(!file.exists()){
					try {
						file.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					file.delete();
				}
			    capturedImageUri = Uri.fromFile(file);
			    Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			    i.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageUri);
			    startActivityForResult(i, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

			}
		});
		

	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent data) {
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
			//Bundle extras = data.getExtras();
            Bitmap mImageBitmap;
			ImageView picView = (ImageView) findViewById(R.id.picView);

			try {
				mImageBitmap = MediaStore.Images.Media.getBitmap( getApplicationContext().getContentResolver(),  capturedImageUri);
				picView.setImageBitmap(mImageBitmap);

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

}
