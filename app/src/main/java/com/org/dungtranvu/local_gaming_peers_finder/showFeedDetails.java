package com.org.dungtranvu.local_gaming_peers_finder;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class showFeedDetails extends ActionBarActivity {

    TextView tv_username;
    TextView tv_content;
    TextView tv_specs;
    TextView tv_send;
    EditText ed_reply;
    ListView lv;
    List<String> replies_list = new ArrayList<String>();
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_feed_details);
        tv_username = (TextView) findViewById(R.id.textView_showFeedDetails_Username);
        tv_content = (TextView) findViewById(R.id.textView_showFeedDetails_Content);
        tv_specs = (TextView) findViewById(R.id.textView_showFeedDetails_Specs);
        lv = (ListView) findViewById(R.id.listView_showFeedDetails_replies);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        final EditText ed_reply = (EditText) findViewById(R.id.editText_showFeedDetails_reply);
        tv_send = (TextView) findViewById(R.id.textView_showFeedDetails_send);
        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reply = ed_reply.getText().toString();
                Toast.makeText(getBaseContext(), "Your msg has been sent", Toast.LENGTH_SHORT).show();
            }
        });
        (new UpdateFeedDetails()).execute();
    }
    private class UpdateFeedDetails extends AsyncTask<Void, Void, Void> {

        String username;
        String content;
        String replies;
        String liked_users;
        int likes;
        int replies_count;
        @Override
        protected Void doInBackground(Void... params) {
            Bundle extra = getIntent().getExtras();
            username = extra.getString("Username");
            content = extra.getString("Content");
            replies = extra.getString("Replies");
            likes = extra.getInt("Likes");
            replies_count = extra.getInt("Replies_count");
            liked_users = extra.getString("Liked_users");
            int start = 0;
            for (int i = 0; i < replies.length(); i++) {
                if (replies.charAt(i) == '\n') {
                    replies_list.add(replies.substring(start, i));
                    start = i + 1;
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void params) {
            tv_username.setText(username);
            tv_content.setText(content);
            tv_specs.setText(Integer.toString(likes)+ " Likes " + Integer.toString(replies_count)+ " Replies");
            lv.setAdapter(new ArrayAdapter<String>(getBaseContext(), R.layout.showfeeddetails_replies_listview, replies_list));

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_feed_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
