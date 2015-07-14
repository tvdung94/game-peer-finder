package com.org.dungtranvu.local_gaming_peers_finder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Feed_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Feed_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Feed_Fragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    View view;
    Context context;
    List<Message> message_list = new ArrayList<Message>();
    List<Message> message_list2 = new ArrayList<Message>();
    ListView lv;
    ListView lv2;
    FloatingActionButton fab;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Feed_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Feed_Fragment newInstance(String param1, String param2) {
        Feed_Fragment fragment = new Feed_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Feed_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_feed_, container, false);
        TabHost tb = (TabHost) v.findViewById(R.id.tabHost);
        tb.setup();


        TabHost.TabSpec ts = tb.newTabSpec("New");
        ts.setContent(R.id.tab_new);
        ts.setIndicator("New");
        tb.addTab(ts);

        TabHost.TabSpec ts2 = tb.newTabSpec("Hot");
        ts2.setContent(R.id.tab_hot);
        ts2.setIndicator("Hot");
        tb.addTab(ts2);

        context = getActivity();
        view = v;

        message_list.add(new Message("tvdung", "Today is a nice day! Anyone wanna hangout? ", 10, "abc: Ye I do!!\njoshi: nah i'd rather play smash..\n"
        ,"tvdung joshi "));
        message_list.add(new Message("uk96","Anyone wanna play fifa?", 0,"\n",""));
         lv = (ListView) v.findViewById(R.id.listView);
        Message_list_adapter mla = new Message_list_adapter();
        lv.setAdapter(mla);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Message current_message = message_list.get(position);
                Intent next = new Intent(getActivity(), showFeedDetails.class);
                next.putExtra("Username", current_message.getUsername());
                next.putExtra("Likes", current_message.getLike());
                next.putExtra("Replies", current_message.getReplies());
                next.putExtra("Replies_count", current_message.getReplies_count());
                next.putExtra("Content", current_message.getContent());
                //next.putExtra("Liked_users", current_message.getLiked_users());
                startActivity(next);
            }
        });
        fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.attachToListView(lv);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  next = new Intent(getActivity(), WriteReply.class);
                Toast.makeText(getActivity().getApplicationContext(), "You just clicked float active button", Toast.LENGTH_SHORT).show();
                startActivity(next);
            }
        });

        message_list2.add(new Message("tvdung", "Today is a nice day! Anyone wanna hangout? ", 10, "abc: Ye I do!!\njoshi: nah i'd rather play smash..\n"
                ,"tvdung joshi "));
        lv2 = (ListView) v.findViewById(R.id.listView3);
        lv2.setAdapter((new Message_list_adapter2()));
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Message current_message = message_list2.get(position);
                Intent next = new Intent(getActivity(), showFeedDetails.class);
                next.putExtra("Username", current_message.getUsername());
                next.putExtra("Likes", current_message.getLike());
                next.putExtra("Replies", current_message.getReplies());
                next.putExtra("Replies_count", current_message.getReplies_count());
                next.putExtra("Content", current_message.getContent());
                //next.putExtra("Liked_users", current_message.getLiked_users());
                startActivity(next);
            }
        });
        //(new UpdateFeedUI()).execute();
        //lv.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.new_feed_list_view, message_list));

        return v;
    }

    private class UpdateFeedUI extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                while (true) {
                    publishProgress();
                    Thread.sleep(5000);
                }
            }
            catch (InterruptedException e) {
                    Log.e("Error", "InterruptedException");
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... params) {
            ((BaseAdapter) lv.getAdapter()).notifyDataSetChanged();
            ((BaseAdapter) lv2.getAdapter()).notifyDataSetChanged();
        }
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public boolean check(String username, Message message) {
        return !message.getLiked_users().contains(username);
    }
    private class Message_list_adapter extends ArrayAdapter<Message>  {
        public Message_list_adapter() {
            super(context, R.layout.new_feed_list_view, message_list);
        }

        @Override
        public View getView(int position, View v, ViewGroup parent) {
            if (v==null) {
                v = getActivity().getLayoutInflater().inflate(R.layout.new_feed_list_view, parent, false);
            }
            Message message = message_list.get(position);

            TextView username = (TextView) v.findViewById(R.id.textView_NewFeed_Username);
            username.setText(message.getUsername());

            TextView content = (TextView) v.findViewById(R.id.content);
            content.setText(message.getContent());

            TextView specs = (TextView) v.findViewById(R.id.specs);
            specs.setText(Integer.toString(message.getLike()) + " Likes " + Integer.toString(message.getReplies_count())
            + " Replies");
            Button like_button = (Button) v.findViewById(R.id.button_like);
            like_button.setTag(position);
            like_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getContext(), "You just clicked the like button", Toast.LENGTH_SHORT).show();
                    Bundle extra = getActivity().getIntent().getExtras();
                    String current_user = extra.getString("username");
                    int cur_pos = (Integer) v.getTag();
                    Message cur_mess = message_list.get(cur_pos);
                    if (check(current_user, cur_mess)) {
                        cur_mess.addLike();
                        cur_mess.addLikedUser(current_user);
                        ((BaseAdapter) lv.getAdapter()).notifyDataSetChanged();
                        Log.d("Message no." + Integer.toString(cur_pos), Integer.toString(cur_mess.getLike()));
                    }

                }
            });
            return v;
        }
    }

    private class Message_list_adapter2 extends ArrayAdapter<Message>  {
        public Message_list_adapter2() {
            super(context, R.layout.hot_feed_list_view, message_list2);
        }

        @Override
        public View getView(int position, View v, ViewGroup parent) {
            if (v==null) {
                v = getActivity().getLayoutInflater().inflate(R.layout.hot_feed_list_view, parent, false);
            }
            Message message = message_list2.get(position);

            TextView username = (TextView) v.findViewById(R.id.textView_HotFeed_Username);
            username.setText(message.getUsername());

            TextView content = (TextView) v.findViewById(R.id.textView_HotFeed_Content);
            content.setText(message.getContent());

            TextView specs = (TextView) v.findViewById(R.id.textView_HotFeed_Specs);
            specs.setText(Integer.toString(message.getLike()) + " Likes " + Integer.toString(message.getReplies_count())
                    + " Replies");
            Button like_button = (Button) v.findViewById(R.id.button_like2);
            like_button.setTag(position);
            like_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getContext(), "You just clicked the like button", Toast.LENGTH_SHORT).show();
                    Bundle extra = getActivity().getIntent().getExtras();
                    String current_user = extra.getString("username");
                    int cur_pos = (Integer) v.getTag();
                    Message cur_mess = message_list2.get(cur_pos);
                    if (check(current_user, cur_mess)) {
                        cur_mess.addLike();
                        cur_mess.addLikedUser(current_user);
                        ((BaseAdapter) lv2.getAdapter()).notifyDataSetChanged();
                        Log.d("Message no." + Integer.toString(cur_pos), Integer.toString(cur_mess.getLike()));
                    }

                }
            });
            return v;
        }
    }




    @Override
    public void onDestroyView() {
        view =null;
        context = null;
        message_list.clear();
        super.onDestroyView();

    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
