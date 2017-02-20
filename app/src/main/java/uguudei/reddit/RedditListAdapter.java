package uguudei.reddit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.toolbox.NetworkImageView;
import java.util.List;

import uguudei.reddit.utility.Utils;

public class RedditListAdapter extends RecyclerView.Adapter<RedditListAdapter.MyHolder>{

    final Activity activity;
    private LayoutInflater inflater;
    private List<RedditListItem> redditListItems;
    private RedditListAdapter listAdapter;
    private RedditList redditList;

    public RedditListAdapter(Activity activity, List<RedditListItem> RedditListItems, RedditList list) {
        this.activity = activity;
        this.redditListItems = RedditListItems;
        this.redditList = list;
    }

    public void syncData(){
        notifyDataSetChanged();
    }

    public List<RedditListItem> getRedditListItems() {
        return redditListItems;
    }

    public synchronized void setRedditListItems(List<RedditListItem> temp_conf) {
        redditListItems = temp_conf;
    }


    @Override
    public RedditListAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_reddit_item, parent, false);
        RedditListAdapter.MyHolder itemViewHolder = new RedditListAdapter.MyHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder,int position) {

        final RedditListItem item = redditListItems.get(position);
        holder.title.setText(item.getTitle());
        holder.score.setText(item.getScore());
        holder.subreddit.setText(item.getSubreddit());

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getUrl()));
                        activity.startActivity(browserIntent);
                    }
                });

            }
        });

        if (position == redditListItems.size()- 1) {
            Utils.showToast(activity,"Loading ...");
            // load more data
            redditList.update();
        }
    }

    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous

        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return redditListItems.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder
    {
        public final TextView title;
        public final TextView score;
        public final TextView subreddit;
        public final LinearLayout container;

        public MyHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.list_reddit_title);
            score = (TextView) itemView
                    .findViewById(R.id.list_reddit_score);
            subreddit = (TextView) itemView
                    .findViewById(R.id.list_reddit_subreddit);
            container = (LinearLayout) itemView.findViewById(R.id.reddit_holder);
        }
    }
}