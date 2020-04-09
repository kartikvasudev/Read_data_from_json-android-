package com.example.learnrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SeekBar;
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
    ListView listView;
    RecyclerView recyclerView;
    SeekBar seekBar;
    ArrayList<HashMap<String,String>> arrayList;
    String[] listViewArray={"Welcome to OOKA RADIO! TestUsername Kartik","Satellite : SATELLITE NAME TEST NAME","Your Days Remaining: 5000 DAYS"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listView);
        recyclerView=findViewById(R.id.recyclerView);
        seekBar=findViewById(R.id.seekbar);
        seekBar.setEnabled(false);
        arrayList=new ArrayList<>();
        getDatafromjson();
        setUpListView();
        setUpRecyclerView();
    }

    private void setUpRecyclerView()
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(arrayList);
        recyclerView.setAdapter(adapter);
    }

    private void setUpListView()
    {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.list_view_item,R.id.list_tv,listViewArray);
        listView.setAdapter(arrayAdapter);
    }

    private void getDatafromjson() {
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
            }
                catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
    }
}
