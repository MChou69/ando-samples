package mc.com.pedago.ws_rss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import mc.com.pedago.R;
import mc.com.pedago.adapters.RssFeedAdapter;
import mc.com.pedago.async.BackgroundTask;
import mc.com.pedago.async.IRssConsumer;
import mc.com.pedago.async.RssFeedAsyncTask;
import mc.com.pedago.async.RssResponse;

public class RssReadActivity extends AppCompatActivity implements IRssConsumer {
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_rss_layout);
       setTitle("Flux Rss (News)");

       Button read=findViewById(R.id.btnReadRss);
       read.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               url = getSelectedUrl();
               Log.i("async", "Read Rss - url =  "+url);

               //new BackgroundTask(RssReadActivity.this).execute();
               new RssFeedAsyncTask(RssReadActivity.this).execute(url);
           }
       });


    }

    private String getSelectedUrl() {
        Spinner combo=findViewById(R.id.rssUrl);
        String title_url = combo.getSelectedItem().toString();
        return title_url.substring(title_url.indexOf("[")+1,title_url.length()-1);
    }

    @Override
    public void getRssData(RssResponse response) {

        //Toast.makeText(this, "hello from rss : "+response.getTitle()+" "+response.getItems().size(), Toast.LENGTH_LONG).show();

        ((TextView)findViewById(R.id.headerTitle)).setText(response.getTitle());
        ((TextView)findViewById(R.id.headerDescription)).setText(response.getDescription());
        ((TextView)findViewById(R.id.headerLink)).setText(response.getLink());

        Log.i("async", "***************************************************");
        Log.i("async", "getRssData: "+response.getItems().size());

        RecyclerView recyclerView =(RecyclerView)findViewById(R.id.rssRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new RssFeedAdapter(response.getItems()));
        Log.i("async", "***************************************************");
    }
}
