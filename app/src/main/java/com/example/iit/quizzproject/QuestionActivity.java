package com.example.iit.quizzproject;

import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.iit.quizzproject.core.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class QuestionActivity extends AppCompatActivity  {


    public  Vector<Question> questionList = new Vector<>();
    Toolbar toolbar;
    public ViewPager viewPager;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(com.example.iit.quizzproject.R.layout.activity_question);
        Resources res = getResources();
        readJson();


        ProgressBar mProgress = (ProgressBar) findViewById(com.example.iit.quizzproject.R.id.circularProgressbar);
        ObjectAnimator animation = ObjectAnimator.ofInt(mProgress, "progress", 100);
        animation.setDuration(50000);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();
        // Locate the viewpager in activity_main.xml
       viewPager = (ViewPager) findViewById(com.example.iit.quizzproject.R.id.pager);
        // Set the ViewPagerAdapter into ViewPager
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),questionList));

    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("questionJson");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    void readJson() {

        try {

            JSONObject obj = new JSONObject(loadJSONFromAsset());

            JSONArray m_jArry = obj.getJSONArray("question");

            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;


            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Question question = new Question();
                Log.d("Details-->", jo_inside.getString("proposition1"));
                String quest = jo_inside.getString("text_question");


                String prop_1 = jo_inside.getString("proposition1");
                String prop_2 = jo_inside.getString("proposition2");
                String prop_3 = jo_inside.getString("proposition3");
                String answer = jo_inside.getString("answer");

                question.setText_question(quest);
                question.setProposition1(prop_1);
                question.setProposition2(prop_2);
                question.setProposition3(prop_3);
                question.setAnswer(answer);

                questionList.add(question);

                //Add your values in your `ArrayList` as below:
                m_li = new HashMap<String, String>();
                m_li.put("text_question", quest);
                m_li.put("proposition1", prop_1);
                m_li.put("proposition2", prop_2);
                m_li.put("proposition3", prop_3);
                m_li.put("answer", answer);

                formList.add(m_li);
            }
            for (int i = 0; i < formList.size(); i++) {

                Log.v("Samir tarhouni ", formList.get(i).toString());
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


}




