package mc.com.pedago.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import mc.com.pedago.R;
import mc.com.pedago.async.RssModel;

import java.util.List;

public class RssFeedAdapter extends RecyclerView.Adapter<RssFeedAdapter.FeedModelViewHolder> {
    private List<RssModel> items;

    public static class FeedModelViewHolder extends RecyclerView.ViewHolder {
        private View rssFeedView;

        public FeedModelViewHolder(View v) {
            super(v);
            rssFeedView = v;
        }
    }

    public RssFeedAdapter(List<RssModel> items) {
        this.items = items;
        Log.i("async", "RssFeedAdapter: "+this.items.size());
    }

    @Override
    public FeedModelViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rss_item_layout, parent, false);
        FeedModelViewHolder holder = new FeedModelViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(FeedModelViewHolder holder, int position) {
        final RssModel item = items.get(position);
        ((TextView)holder.rssFeedView.findViewById(R.id.rssItemTitle)).setText(item.title);
        ((TextView)holder.rssFeedView.findViewById(R.id.rssItemDesc)).setText(item.description);
        ((TextView)holder.rssFeedView.findViewById(R.id.rssItemDate)).setText(""+item.date);

        TextView link=holder.rssFeedView.findViewById(R.id.rssItemLink);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        link.setText(Html.fromHtml("<a href=\""+ item.link +"\"> Read..</a>"));

        /*TextView link = holder.rssFeedView.findViewById(R.id.rssItemLink);
        SpannableString ss = new SpannableString("Read more..");
        //ss.setSpan(new StyleSpan(Typeface.BOLD), 0, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new URLSpan(item.link), 0, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        link.setText(ss);
        //link.setText(Html.fromHtml("<a href=\""+ item.link +"\">read!</a>"));
        link.setMovementMethod(LinkMovementMethod.getInstance());*/
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
