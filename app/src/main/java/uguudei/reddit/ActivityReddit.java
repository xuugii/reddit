package uguudei.reddit;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

public class ActivityReddit extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reddit);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.reddit_recycle_view);
        new RedditList(this, recyclerView);
    }

}
