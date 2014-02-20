package in.rockerz.servicesapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.net.MalformedURLException;
import java.net.URL;
import android.widget.Toast;


public class MyService extends Service {
	  private final IBinder mBinder = new MyBinder();
	  public static final String RESULT = "result";
	  public static final String NOTIFICATION = "com.example.servicesapp";

	    	
	@Override
	  public int onStartCommand(Intent intent, int flags, int startId) {

		    // will be called asynchronously by Android
		  Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
		  String[] urls=intent.getStringArrayExtra("URLs");
		  int flag=intent.getIntExtra("length",0);
		  try {
			  if(flag==0){
			new BackgroundDownload().execute(new URL(urls[0]),
			  new URL(urls[1]),
			  new URL(urls[2]),
			  new URL(urls[3]),
			  new URL(urls[4]));
			  }
			  else if(flag==1)
			  {
				  new BackgroundDownload().execute(new URL(urls[0]),
					new URL(urls[1]),
					new URL(urls[2]),
					new URL(urls[3]),
					new URL(urls[4]),
			  		new URL(urls[5]),
			  		new URL(urls[6]),
			  		new URL(urls[7]),
			  		new URL(urls[8]),
			  		new URL(urls[9]));
				  
			  }
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
		    return Service.START_STICKY;
		    
	  }
	  		
		  @Override
		  public IBinder onBind(Intent arg0) {
		    return mBinder;
		  }		  
		

		  public class MyBinder extends Binder {
		    MyService getService() {
		      return MyService.this;
		    }
		  }
		/* protected  void publishResult(int total) {
			    Intent intent = new Intent(NOTIFICATION);
			    intent.putExtra(RESULT, total);
			    sendBroadcast(intent);
			  }*/
		
}
