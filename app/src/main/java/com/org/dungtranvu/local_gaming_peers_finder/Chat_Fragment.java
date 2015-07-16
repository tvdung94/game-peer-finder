package com.org.dungtranvu.local_gaming_peers_finder;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Chat_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Chat_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Chat_Fragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    List<ChatRoom> room_list = new ArrayList<ChatRoom>();
    private static final int SERVERPORT = 6000;
    private static final String SERVER_IP = "192.168.100.4";
    Socket socket;
    ListView lv2;
    ListView lv;
    Message_list_adapter_2 mla2;
    ArrayList<String> friend_list;
    ArrayAdapter<String> aa;
    String username;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Chat_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Chat_Fragment newInstance(String param1, String param2) {
        Chat_Fragment fragment = new Chat_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Chat_Fragment() {
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
        View v = inflater.inflate(R.layout.fragment_chat_, container, false);
        TabHost tabHost = (TabHost) v.findViewById(R.id.tabHost2);
        tabHost.setup();

        TabHost.TabSpec ts = tabHost.newTabSpec("Message");
        ts.setContent(R.id.tab_message);
        ts.setIndicator("Message");
        tabHost.addTab(ts);

        TabHost.TabSpec ts2 = tabHost.newTabSpec("Friends");
        ts2.setContent(R.id.tab_friends);
        ts2.setIndicator("Friends");
        tabHost.addTab(ts2);

        Bundle b = getActivity().getIntent().getExtras();
        username = b.getString("username");
        Log.d("username", username);
        ChatRoom chatroom = new ChatRoom("tvdung", "uk");
        chatroom.addMessage(new ChatMessage("tvdung", "hello madafaker"));
        room_list.add(chatroom);
        lv2 = (ListView) v.findViewById(R.id.listView2);
        //new Thread(new ClientThread()).start();
        mla2 = new Message_list_adapter_2();
        lv2.setAdapter(mla2);

        lv = (ListView) v.findViewById(R.id.listView4);
        friend_list = new ArrayList<String>();
        aa = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.friends_list_view, friend_list);
        lv.setAdapter(aa);
        //message_list_2.add(new Message("tvdung","nooooooo", 2));

        return v;
    }

    class ClientThread implements Runnable {

        @Override
        public void run() {

            try {
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);

                socket = new Socket(serverAddr, SERVERPORT);

                BufferedReader in =
                        new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter (socket.getOutputStream(), true);
                String msg = in.readLine();



                // THIS LINE WRITES OUT TO THE SERVER===
                out.write("dung\r\n");//<<<<<<<<<<<<<<<<<<<<<<<<<<<<Problem here
                out.flush();
                msg = in.readLine();
                Log.d("here", msg);
                //======================================


                while ((msg = in.readLine()) != null) {
                    Log.d("Server said", msg);

                   // message_list_2.add(new Message("tvdung", msg, 0));
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // This code will always run on the UI thread, therefore is safe to modify UI elements.
                            ((BaseAdapter) lv2.getAdapter()).notifyDataSetChanged();
                        }
                    });

                    //mla2 = new Message_list_adapter_2();
                    //lv2.setAdapter(mla2);
                }
                in.close();
                out.close();

            } catch (UnknownHostException e1) {
                e1.printStackTrace();
                //Log.e("ngu", "vai loz");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

    }

    private class Message_list_adapter_2  extends ArrayAdapter<ChatRoom> {
        public Message_list_adapter_2() {
            super(getActivity(), R.layout.message_list_view, room_list);
        }

        @Override
        public View getView(int position, View v, ViewGroup parent) {

            if (v == null) {
                v = getActivity().getLayoutInflater().inflate(R.layout.message_list_view, parent, false);
            }
            ChatRoom chatroom = room_list.get(position);
            TextView friend = (TextView) v.findViewById(R.id.textView_MessageListView_Username);
            TextView content = (TextView) v.findViewById(R.id.textView_MessageListView_content);
            friend.setText(chatroom.getFriendsName(username));
            ChatMessage most_recent_message = chatroom.getMostRecentMessage();
            content.setText(most_recent_message.getAuthor()+ ": " + most_recent_message.getContent());
            return v;
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

    @Override
    public void onDestroyView() {
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
