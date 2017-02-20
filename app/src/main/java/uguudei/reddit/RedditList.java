package uguudei.reddit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import uguudei.reddit.utility.ThreadSimple;

/**
 * Created by uguud on 2/19/2017.
 */

public class RedditList {
    public static final String TAG = RedditList.class.getSimpleName();
    public RecyclerView recyclerView;
    public static  RedditListAdapter adapter;
    public static final String URL_REDDIT = "https://www.reddit.com/r/gaming/top.json?limit=25";
    public static final String URL_REDDIT_AFTER = "https://www.reddit.com/r/gaming/top.json?after=&limit=25";
    public static String LastID = "";

    public RedditList(AppCompatActivity activity, RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.recyclerView.setHasFixedSize(true);
        adapter = new RedditListAdapter(activity, Global.getInstance().getList(), this);
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(activity));
    }

    public static void update(){
        // making fresh volley request and getting json
        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
                (LastID.isEmpty())?URL_REDDIT:fixUrlWithLastID(), null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                VolleyLog.d(TAG, "Response: " + response.toString());
                if (response != null) {
                    parseJsonFeed(response);
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });
        // Adding request to volley request queue
        AppController.getInstance().addToRequestQueue(jsonReq);

    }

    private static String fixUrlWithLastID(){
        String change = URL_REDDIT_AFTER;
        change = change.substring(0,change.indexOf("&")) + LastID + change.substring(change.indexOf("&"));
        return change;
    }


    /**
     * Parsing json reponse and passing the data to feed view list adapter
     * */
    private static void parseJsonFeed(JSONObject response) {
        try {
            JSONArray data = response.getJSONObject("data").getJSONArray("children");

            for (int i = 0; i < data.length(); i++) {
                JSONObject item = ((JSONObject) data.get(i)).getJSONObject("data");

                RedditListItem itemList = new RedditListItem();
                itemList.setTitle(item.getString("title"));
                itemList.setScore(item.getString("score"));
                itemList.setSubreddit(item.getString("subreddit_name_prefixed"));
                itemList.setSeturl(item.getString("url"));

                if (!Global.getInstance().redditItems.contains(itemList)){
                    Global.getInstance().redditItems.add(itemList);
                    if (i == data.length()-1){
                        LastID = response.getJSONObject("data").getString("after");
                    }
                }
            }
            // notify data changes to list adapater
            if (adapter != null)
                adapter.syncData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
