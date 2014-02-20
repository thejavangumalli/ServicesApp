package in.rockerz.servicesapp;

import java.net.URL;

import in.rockerz.servicesapp.MyService.MyBinder;


import android.os.Bundle;
import android.os.IBinder;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class PDFActivity extends Activity {
	URL url;
	MyService ms;
	boolean b =false;
	Intent intent;
	/*private BroadcastReceiver receiver = new BroadcastReceiver(){
		
		@Override
	    public void onReceive(Context context, Intent intent) {
	      Bundle bundle = intent.getExtras();
	      if (bundle != null) {
	        String string = bundle.getString(DownloadService.FILEPATH);
	        int resultCode = bundle.getInt(DownloadService.RESULT);
	        if (resultCode == RESULT_OK) {
	          Toast.makeText(PDFActivity.this,
	              "Download complete. Download URI: " + string,
	              Toast.LENGTH_LONG).show();
	        } else {
	          Toast.makeText(PDFActivity.this, "Download failed",
	              Toast.LENGTH_LONG).show();
	        }
	      }
	    }
	  };*/
		
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
		setContentView(R.layout.activity_pdf);
	}
	
	/*@Override
	  protected void onResume() {
	    super.onResume();
	    registerReceiver(receiver, new IntentFilter(MyService.NOTIFICATION));
	  }
	  @Override
	  protected void onPause() {
	    super.onPause();
	    unregisterReceiver(receiver);
	  }
*/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pd, menu);
		return true;
	}
	
	
	@SuppressLint("InlinedApi")
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
	
		public void download(View v) {
			String []urls ={"http://www.sjsu.edu/registrar/docs/name_change.pdf",
							"http://as.sjsu.edu/steinbeck/documents/BIOGRAPHY_Biography_in_Depth.pdf",
							"http://my.sjsu.edu/docs/hr/recruiting/UG_RS_SJHR_Open_Recruitment_Attachments.pdf",
							"http://www.engr.sjsu.edu/media/pdf/svls/f09/pres_om_nalamasu_092409.pdf",
							"http://www.engr.sjsu.edu/gaojerry/IEEEMobileCloud2013/shuttle%20info.pdf"};	
			intent.putExtra("URLs",urls);
			intent.putExtra("length", 0);
			startService(intent);

			}
				
				
}
