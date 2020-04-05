package com.example.learnrecyclerview;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    LinearLayout ll;
    ArrayList<HashMap<String,String>> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll =(LinearLayout) findViewById(R.id.ll);
        arrayList=new ArrayList<>();

        Log.d("h", " ok till array list creation ");
        try {
        InputStream inputStream=this.getAssets().open("data.json");
        int size= 0;

            size = inputStream.available();
            byte b[]=new byte[size];
            inputStream.read(b);
            inputStream.close();
            String jsonString=new String(b,"UTF-8");

                Log.d("h", " ok till reading json string");
                JSONObject jsonObject=new JSONObject(jsonString);
                JSONObject response=jsonObject.getJSONObject("response");
                JSONObject data=response.getJSONObject("data");
                JSONArray array=data.getJSONArray("tracks");
                for (int i=0;i<array.length();i++)
                {
                    JSONObject track=array.getJSONObject(i);
                    String title,duration;
                    title=track.getString("title");
                    duration=track.getString("durationString");
                    HashMap<String,String> hashMap=new HashMap<>();
                    hashMap.put("title",title);
                    hashMap.put("duration",duration);
                    arrayList.add(hashMap);

                }




        }catch (IOException e)
        {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int i=0;i<arrayList.size();i++)
        {
            TextView tv=new TextView(this);
            tv.setText(""+(i+1)+".\t"+arrayList.get(i).toString());
            ll.addView((View)tv);
        }
    }
}
