package mc.com.pedago.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import java.io.InputStream;
import java.net.URL;

public class RssFeedAsyncTask extends AsyncTask<String, Integer, Boolean> {
    private static final String TAG ="async" ;
    private RssResponse response;
    private Context context;
    private ProgressDialog dialog;
    private IRssConsumer consumer;
    public RssFeedAsyncTask(IRssConsumer activity){
        dialog = new ProgressDialog((Activity)activity);
        this.consumer=activity;
    }
    @Override
    protected void onPreExecute() {
        dialog.setMessage("Rss Feed, please wait..");
        dialog.show();
    }

    @Override
    protected Boolean doInBackground(String... params) {
        //progressDialog.show();
        try {
            String utl_text=params[0];
            URL url = new URL(utl_text);
            InputStream inputStream = url.openConnection().getInputStream();

            response = new RssFeedParser().Parse(inputStream);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error", e);
        }
        return false;
    }

    @Override
    protected void onProgressUpdate(Integer... value) {
        super.onProgressUpdate(value);
    }

    @Override
    protected void onPostExecute(Boolean success) {

        Log.i(TAG, "onPostExecute - Items : "+response.getItems().size());
        if (dialog.isShowing())
            dialog.dismiss();

        this.consumer.getRssData(response);
    }

}
