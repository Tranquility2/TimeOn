package com.vnr.timeon;

import android.app.Activity;
import android.app.ListFragment;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;

import com.vnr.models.CurrTime;
import com.vnr.sql.CurrTimeDataSource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProgressFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProgressFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ProgressFragment extends ListFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

//    private OnFragmentInteractionListener mListener;

    /*
    Timer code that was imported from Activity
     */
    private CurrTimeDataSource datasource;
    private static long timeWhenStopped = 0;
    public static long getTimeWhenStopped() {
        return timeWhenStopped;
    }
    public static void setTimeWhenStopped(long timeWhenStopped) {
        ProgressFragment.timeWhenStopped = timeWhenStopped;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProgressFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProgressFragment newInstance(String param1, String param2) {
        ProgressFragment fragment = new ProgressFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public ProgressFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    /*
    Timer code that was imported from Activity
     */
        datasource = new CurrTimeDataSource(getActivity());
        datasource.open();

        List<CurrTime> values = datasource.getAllTimes();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<CurrTime> adapter = new ArrayAdapter<CurrTime>(getActivity(), android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_progress, container, false);

        Button buttonStart = (Button) view.findViewById(R.id.Start);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickFragment(v);
            }
        });
        Button buttonPause = (Button) view.findViewById(R.id.Pause);
        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickFragment(v);
            }
        });
        Button buttonStop = (Button) view.findViewById(R.id.Stop);
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickFragment(v);
            }
        });

        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        public void onFragmentInteraction(Uri uri);
//    }

    @Override
    public void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    public void onPause() {
        datasource.close();
        super.onPause();
    }

    // Will be called via the onClick attribute
    // of the buttons in main.xml
    public void onClickFragment(View view) {
        @SuppressWarnings("unchecked")

        ArrayAdapter<CurrTime> adapter = (ArrayAdapter<CurrTime>) getListAdapter();
        CurrTime time = null;
        String btn_name = ((Button)view).getText().toString();
        String chrono_val = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date current_time = new Date();
        Chronometer chrono_timer = (Chronometer) getActivity().findViewById(R.id.chronometer_timer);

        chrono_val = chrono_timer.getText().toString();
        time = datasource.createCurrentTime(dateFormat.format(current_time), btn_name, chrono_val);
        // save the new current time to the database
        adapter.add(time);

        switch (view.getId()) {
            case R.id.Start:
                // Start the timer
                chrono_timer.setBase(SystemClock.elapsedRealtime() + getTimeWhenStopped());
                chrono_timer.start();
                break;

            case R.id.Pause:
                // Pause the timer
                setTimeWhenStopped(chrono_timer.getBase() - SystemClock.elapsedRealtime());
                chrono_timer.stop();
                break;

            case R.id.Stop:
                // Stop and Reset the timer
                chrono_timer.setBase(SystemClock.elapsedRealtime());
                chrono_timer.stop();
                setTimeWhenStopped(0);
                break;

            // TODO: Add default case
        }

        adapter.notifyDataSetChanged();
    }
}
