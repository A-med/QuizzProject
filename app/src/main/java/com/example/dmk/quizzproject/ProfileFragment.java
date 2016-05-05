package com.example.dmk.quizzproject;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmk.quizzproject.core.Person;
import com.example.dmk.quizzproject.ui.PersonAdapter;

import java.util.ArrayList;

/**
 * Created by dmk on 29/04/16.
 */
/**
 * To commit content_fragment.xml
 * **/
public class ProfileFragment extends Fragment implements View.OnClickListener {
    private static ArrayList<Person> mPersonsList = new ArrayList<Person>();
    ListView androidListView;
    ImageView profil_Toolbar;
    private Button btnPlay;
    private Button btnp ;
    private ListView mListView;
    private PersonAdapter mPersonAdapter;
      private  TextView textAnim ;

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance(ArrayList<Person> personsList) {
        ProfileFragment fragment = new ProfileFragment();
        mPersonsList = personsList;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.v("------->","----00000--")   ;
        View view = inflater.inflate(R.layout.profil, container, false);

        mListView = (ListView) view.findViewById(R.id.list_view);
        btnp = (Button)  view.findViewById(R.id.wins);
        btnp.setOnClickListener(this);
       // textAnim = (TextView) view.findViewById(R.id.anim);
        //textAnim.startAnimation(AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_out));
        mPersonAdapter = new PersonAdapter(getActivity().getApplicationContext(), mPersonsList);
        fillAdapter();
        mListView.setAdapter(mPersonAdapter);
        profil_Toolbar = (ImageView) view.findViewById(R.id.profil_Toolbar);
        profil_Toolbar.setOnClickListener(this);

//        for (int i = 0; i < mListView.getCount()+1; i++)
//            Log.v("------->","------"+mListView.getCount()+"")   ;
        return view;

    }

    private void fillAdapter() {
        for (int i = 0; i < 3; i++) {
            mPersonsList.add(new Person("Friend " +i, i, i));
        }

        mPersonAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.wins)
        {    Intent i= new Intent(getActivity().getApplicationContext(),QuestionActivity.class);
            Toast.makeText(getActivity(), "aaaaaaa", Toast.LENGTH_SHORT).show();
            startActivity(i);


        }
    }


}
