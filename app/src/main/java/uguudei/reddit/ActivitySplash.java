package uguudei.reddit;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import uguudei.reddit.utility.ThreadSimple;
import uguudei.reddit.utility.Utils;

import java.util.concurrent.TimeUnit;

public class ActivitySplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final Activity activity = this;
        LinearLayout lin = (LinearLayout) findViewById(R.id.activity_splash_linear);
        lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startMainActivity(0,activity);
            }
        });
        startMainActivity(1, this);
    }

    private void startMainActivity(final int wait, final Activity activity) {
        // Update during list
        RedditList.update();
        Runnable cont = new Runnable() {
            public void run() {

                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(activity);
                builder.setIcon(android.R.drawable.ic_dialog_info);
                builder.setTitle("No internet!");
                builder.setMessage("Need internet connection");
                builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        startMainActivity(0, activity);
                    }
                });

                // process data
                if (!Utils.isInternetAvailable()){
                    Utils.showDailogMessage(builder, activity);
                    return;
                }
                // start reddit
                startActivity(new Intent(getApplicationContext(), ActivityReddit.class));
                }
            };
        ThreadSimple.getWorker().schedule(cont, wait, TimeUnit.SECONDS);
    }
}
