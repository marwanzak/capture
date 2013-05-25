package com.marwan.capture;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
public class MainActivity extends Activity {
	String result = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button login_but = (Button) findViewById(R.id.login_but);
		Button exit_but = (Button) findViewById(R.id.exit_but);
		final EditText username_text = (EditText) findViewById(R.id.username_text);
		final EditText password_text = (EditText) findViewById(R.id.password_text);
		final EditText result_text = (EditText) findViewById(R.id.result_text);
		exit_but.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				
			}
		});
		login_but.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				URL url;
				try{
					url = new URL("http://alaqsa.edu.sa/dbaqsa/andro/login?"+"username="+username_text.getText()+"&password="+password_text.getText());
					BufferedReader in = new BufferedReader(
							new InputStreamReader(
									url.openStream()));
					String inputLine;

					while ((inputLine = in.readLine()) != null){
						result = inputLine;
					}
					in.close();
					if(result == "true")
					{
						startActivity(new Intent(MainActivity.this, Capture.class));
					}
					else {
						result_text.setText(result);
					}
				}
				catch (MalformedURLException n) {
					// TODO Auto-generated catch block
					n.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}