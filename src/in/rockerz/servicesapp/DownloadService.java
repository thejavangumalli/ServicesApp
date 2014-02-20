package in.rockerz.servicesapp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;

public class DownloadService extends IntentService {

  public static final String URL = "urlpath";
  public static final String FILENAME = "filename";
  public static final String FILEPATH = "filepath";
  public static final String RESULT = "result";
  public static final String NOTIFICATION = "in.rockerz.servicesapp";

  public DownloadService() {
    super("DownloadService");
  }

  // will be called asynchronously by Android
  @Override
 /* protected void onHandleIntent(Intent intent) {
    String urlPath = intent.getStringExtra(URL);
    String fileName = intent.getStringExtra(FILENAME);
    File output = new File(Environment.getExternalStorageDirectory(),
        fileName);
    if (output.exists()) {
      output.delete();
    }

    InputStream stream = null;
    FileOutputStream fos = null;
    try {

      URL url = new URL(urlPath);
      stream = url.openConnection().getInputStream();
      InputStreamReader reader = new InputStreamReader(stream);
      fos = new FileOutputStream(output.getPath());
      int next = -1;
      while ((next = reader.read()) != -1) {
        fos.write(next);
      }
      // successfully finished
      result = Activity.RESULT_OK;
    }catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (stream != null) {
        try {
          stream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (fos != null) {
        try {
          fos.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    publishResults(output.getAbsolutePath(), result);
  }*/
  
  protected void onHandleIntent(Intent intent) {
	  File file = null;
      try {
    	  String[] urlPath = intent.getStringArrayExtra("URLs");
    	  for(int i=0;i<urlPath.length;i++)
    	  {
    	  System.out.println(urlPath[i]);
          URL url = new URL(urlPath[i]);
          //String fileName = intent.getStringExtra(FILENAME);
          file = new File(Environment.getExternalStorageDirectory(),
        	        "test"+i+".pdf");
          System.out.println(file.getAbsolutePath());
          URLConnection connection = url.openConnection();
          connection.connect();

          // this will be useful so that you can show a typical 0-100% progress bar
          
          int fileLength = connection.getContentLength();
          System.out.println("File Length"+fileLength);
          // download the file
          System.out.println(connection.getURL());
          InputStream input = new BufferedInputStream(url.openStream());
          OutputStream output = new FileOutputStream(file.getAbsolutePath());
         byte data[] = new byte[1024];
          long total = 0;
          int count;
          while ((count = input.read(data)) != -1) {
              total += count;
              int percent=(int)total*100/fileLength;
              if(percent%25==0)
            	  publishResult(percent);
              output.write(data, 0, count);
          }          
          output.flush();
          output.close();
          input.close();
      }
      }catch (IOException e) {
          e.printStackTrace();
      }
     // publishResults(file.getAbsolutePath(),Activity.RESULT_OK);
  }

  /*private void publishResults(String outputPath, int result) {
    Intent intent = new Intent(NOTIFICATION);
    intent.putExtra(FILEPATH, outputPath);
    intent.putExtra(RESULT, result);
    sendBroadcast(intent);
  }*/
  private void publishResult(int total) {
	    Intent intent = new Intent(NOTIFICATION);
	    intent.putExtra(RESULT, total);
	    sendBroadcast(intent);
	  }
} 