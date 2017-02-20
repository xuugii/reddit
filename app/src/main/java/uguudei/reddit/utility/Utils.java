package uguudei.reddit.utility;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.net.InetAddress;

/**
 * Created by uguud on 2/20/2017.
 */

public class Utils {

    public static boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            return !ipAddr.equals("");
        } catch (Exception e) {
            return false;
        }
    }

    public synchronized static void showDailogMessage(final AlertDialog.Builder builder, Activity activity) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog dialog = builder.create();
                dialog.show();
            }});
    }

    /**
     * TODO use dialog for loaiding
     * @param activity
     * @param text
     */
    public synchronized static void showDialog(final Activity activity, final String text){
        activity.runOnUiThread(new Runnable() {
            public void run() {
                ProgressDialog dialog = ProgressDialog.show(activity, "",
                        text, true);
            }
        });
    }

    public synchronized static void showToast(final Activity activity, final String text){
        activity.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(activity, text,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
