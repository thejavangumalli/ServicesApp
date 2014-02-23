package in.rockerz.servicesapp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

public class BackgroundDownload extends AsyncTask<URL, Integer, Long> {

    public static final String URL = "urlpath";
    public static final String FILENAME = "filename";
    public static final String FILEPATH = "filepath";
    public static final String RESULT = "result";
    public static final String NOTIFICATION = "in.rockerz.servicesapp";
    MyService ms=new MyService();

	@Override
	protected Long doInBackground(URL... urls) {
		File file = null;
        try {
      	  URL[] urlPath = urls;
      	  for(int i=0;i<urlPath.length;i++)
      	  {
      	  Log.d("URL",urlPath[i].toString());
      	    URL url = urlPath[i];
            String fileName=url.toString().substring(url.toString().lastIndexOf("/")+1);
            String activity=url.toString().substring(url.toString().lastIndexOf(".")+1);
            if(activity.equalsIgnoreCase("pdf")){
            	Log.d("Activity","PDFActivity");            	
			            }
            else if(activity.equalsIgnoreCase("jpg")){
            	Log.d("Activity","ImageActivity");
			            }
            else if(activity.equalsIgnoreCase("doc")){
            	Log.d("Activity","Text Activity");
			}
            
            file = new File(Environment.getExternalStorageDirectory(),fileName);
           Log.d("fileName", fileName);
            URLConnection connection = url.openConnection();
            connection.connect();
            
            int fileLength = connection.getContentLength();
            InputStream input = new BufferedInputStream(url.openStream());
            OutputStream output = new FileOutputStream(file.getAbsolutePath());
           byte data[] = new byte[1024];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                total += count;
                int percent=(int)total*100/fileLength;
                if(percent%25==0)
              	  publishProgress(percent);
                output.write(data, 0, count);
            }          
            output.flush();
            output.close();
            input.close();
        }
        }catch (IOException e) {
            e.printStackTrace();
        }
		return null;
	}
	/*protected void onProgressUpdate(Integer... progress) {
	 MyService ms=new MyService();
	      ms.publishResult(progress[0]);
	    }*/
	
}
