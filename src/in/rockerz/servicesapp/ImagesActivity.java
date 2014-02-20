package in.rockerz.servicesapp;

import java.net.URL;

import in.rockerz.servicesapp.MyService.MyBinder;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;

public class ImagesActivity extends Activity {

	URL url;
	MyService ms;
	boolean b =false;
	Intent intent;
 
		
	  private ServiceConnection mConnection = new ServiceConnection() {

	        @Override
	        public void onServiceConnected(ComponentName className,
	                IBinder service) {
	            // We've bound to LocalService, cast the IBinder and get LocalService instance
	            MyBinder binder = (MyBinder) service;
	            ms = binder.getService();
	            b = true;
	        }

	        @Override
	        public void onServiceDisconnected(ComponentName arg0) {
	            b = false;
	        }
	    };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_images);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.images, menu);
		return true;
	}
	
	@Override
	protected void onStart()
	{
		super.onStart();
		intent = new Intent(this,MyService.class);
		//bindService(intent, mConnection,Context.BIND_AUTO_CREATE);
		bindService(intent,mConnection, Context.BIND_ADJUST_WITH_ACTIVITY);
	}
	@Override
	protected void onStop()
	{
		super.onStop();
		if(b)
		{
			unbindService(mConnection);
			b=false;
		}
	}
	
	public void download(View v)
	{
		
		String []urls ={"http://blogs.sjsu.edu/today/files/2014/01/mima-mounds-1l9fs40.jpg",
			    "http://blogs.sjsu.edu/today/files/2014/01/Gragera-inpost-11xdqr8.jpg",
			    "http://blogs.sjsu.edu/today/files/2014/01/spider-inpost-285iz3l.jpg",
			    "http://www.engr.sjsu.edu/files/images/exceed-image-14-120815.thumbnail.jpg",
			    "http://www.engr.sjsu.edu/files/images/exceed-group-1-120815.thumbnail.jpg",
			    "http://blogs.sjsu.edu/today/files/2014/02/0005_proam-1uawpew.jpg",
			    "http://blogs.sjsu.edu/today/files/2013/11/dance_0003_20131101-396-0045.jpg-zq3m7w.jpg",
			    "http://blogs.sjsu.edu/today/files/2013/11/dance_0009_20131101-396-0355.jpg-16qimv6.jpg",
			    "http://blogs.sjsu.edu/today/files/2013/11/dance_0022_20131101-396-0822.jpg-1qyfyhk.jpg", 
			    "http://blogs.sjsu.edu/today/files/2013/11/dance_0027_20131101-396-0996.jpg-2klbnkv.jpg"
						};	
              intent.putExtra("URLs",urls);
              intent.putExtra("length", 1);
              startService(intent);
	}
}
