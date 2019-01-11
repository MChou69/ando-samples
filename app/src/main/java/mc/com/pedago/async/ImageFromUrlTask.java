package mc.com.pedago.async;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class ImageFromUrlTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;
    public ImageFromUrlTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        Bitmap bmp = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            bmp = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("async", "Error : "+e.getMessage());
        }
        return bmp;
    }
    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
        bmImage.setMinimumHeight(60);
    }
}

