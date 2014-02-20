package in.rockerz.servicesapp;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
    public void pdf(View v){	
    	Intent pdfintent=new Intent(this,PDFActivity.class);
		startActivity(pdfintent);
    }
    public void images(View v){	
		Intent imagesintent=new Intent(this,ImagesActivity.class);
		startActivity(imagesintent);
}
    public void textfiles(View v){	
		Intent textintent=new Intent(this,TextActivity.class);
		startActivity(textintent);
}
    public void close(View v){	
		finish();
}
}
