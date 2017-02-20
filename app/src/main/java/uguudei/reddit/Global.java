package uguudei.reddit;


import java.util.List;
import java.util.Vector;

/**
 * Created by uguud on 2/20/2017.
 */

public class Global {
    private static Global global;
    List<RedditListItem> redditItems;
    private Global(){
        redditItems = new Vector<>();
    }

    public static Global getInstance(){
        if (global == null){
            global = new Global();
        }
        return global;
    }

    public List<RedditListItem> getList(){
        return redditItems;
    }


}
