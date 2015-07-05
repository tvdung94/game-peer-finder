package com.org.dungtranvu.local_gaming_peers_finder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DashBoard_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DashBoard_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashBoard_Fragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    Context context;
    List<User> Users = new ArrayList<User>();
    ListView DashBoard;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashBoard_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashBoard_Fragment newInstance(String param1, String param2) {
        DashBoard_Fragment fragment = new DashBoard_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public DashBoard_Fragment() {
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
        View v = inflater.inflate(R.layout.fragment_dash_board_, container, false);
        context = getActivity();
         DashBoard = (ListView) v.findViewById(R.id.listView_DashBoard);
        DashBoard.setAdapter(new DashBoardAdapter());
        (new UpdateDashBoard()).execute();
        DashBoard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id ) {
                    Intent next = new Intent(getActivity(), showDetails.class);
                    next.putExtra("SummonerName", Users.get(position).getSummonerID());
                    next.putExtra("Region", Users.get(position).getRegion());
                startActivity(next);
            }
        }

        );
        return v;
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

    private class DashBoardAdapter extends ArrayAdapter<User> {
        public DashBoardAdapter() {
            super(context, R.layout.dashboard_listview, Users);
        }
        @Override
        public View getView(int position, View v, ViewGroup parent) {
            if (v==null)
                v = getActivity().getLayoutInflater().inflate(R.layout.dashboard_listview, parent, false);
            User user = Users.get(position);
            TextView sid = (TextView) v.findViewById(R.id.textView_SummonerID);
            sid.setText("SummonnerID: " + user.getSummonerID());
            TextView username = (TextView) v.findViewById(R.id.textView_Username);
            username.setText("Username: " +user.getUsername());
            String wr = user.getWinrate();
            TextView winrate = (TextView) v.findViewById(R.id.textView_Winrate);
            winrate.setText("Winrate: " + wr);
            return v;
        }
    }
    private class UpdateDashBoard extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {

            //clear the users list



            //Receive data from server after an interval


            //Get additional data from riot


            Users.add(new User("tvdung","illegal summoner", 1.00, 1.00, "100%", "NA"));
            Users.add(new User("linda","1mEther", 2.00, 2.00, "50%", "NA"));
            Users.add(new User("hans","hans001", 2.00, 1.00, "75%", "NA"));
            publishProgress();
            return null;
        }
        protected void onProgressUpdate(Void... progress) {
            //update the UI
        }
        @Override
        protected void onPostExecute(Void result) {
            ((BaseAdapter) DashBoard.getAdapter()).notifyDataSetChanged();
        }
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
