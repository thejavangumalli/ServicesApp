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

public class BackgroundDownload extends AsyncTask<URL, Integer, Long> {

    public static final String URL = "urlpath";
    public static final String FILENAME = "filename";
    public static final String FILEPATH = "filepath";
    public static final String RESULT = "result";
    public static final String NOTIFICATION = "com.example.servicesapp";

	@Override
	protected Long doInBackground(URL... urls) {
		File file = null;
        try {
      	  URL[] urlPath = urls;
      	  for(int i=0;i<urlPath.length;i++)
      	  {
      	  System.out.println(urlPath[i]);
            URL url = urlPath[i];
            //String fileName = intent.getStringExtra(FILENAME);
            String fileName=url.toString().substring(url.toString().lastIndexOf("/"));
            
            file = new File(Environment.getExternalStorageDirectory(),
          	        fileName);
           // System.out.println(file.getAbsolutePath());
            URLConnection connection = url.openConnection();
            connection.connect();

            // this will be useful so that you can show a typical 0-100% progress bar
            
            int fileLength = connection.getContentLength();
            //System.out.println("File Length"+fileLength);
            // download the file
            //System.out.println(connection.getURL());
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
