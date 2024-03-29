package com.example.asus.internship.controller;

import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.asus.internship.R;
import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.internship.Adapter;

import com.example.asus.internship.api.Client;
import com.example.asus.internship.api.Date;
import com.example.asus.internship.api.Service;
import com.example.asus.internship.model.Item;
import com.example.asus.internship.model.ItemResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    TextView Disconnected;
    private Item item;
    ProgressDialog pd;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        getSupportActionBar().hide();


        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(){
                loadJSON();
                Toast.makeText(MainActivity.this, "Github Repos Refreshed", Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void initViews(){
        pd = new ProgressDialog(this);
        pd.setMessage("Fetching Github Repos...");
        pd.setCancelable(false);
        pd.show();
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.smoothScrollToPosition(0);
        loadJSON();
    }

    private void loadJSON(){
        Disconnected = (TextView) findViewById(R.id.disconnected);
        try{
            Date last30days= new Date();
            int year =last30days.getYear();
            int month = last30days.getMonth();
            int day = last30days.getDay();
            String url ="/search/repositories?q=created>"+year+"-"+month+"-"+day+"&sort=stars&order=desc";
          //  String url ="/search/repositories?q=created=2019-06-05&sort=stars&order=desc";
           // Log.e("safaa", "date : "+year+"-"+month+"-"+day);

            Client Client = new Client();
            Service apiService =
                    Client.getClient().create(Service.class);
            Call<ItemResponse> call = apiService.getItems(url);
            call.enqueue(new Callback<ItemResponse>() {
                @Override
                public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                    List<Item> items = response.body().getItems();
                    recyclerView.setAdapter(new Adapter(getApplicationContext(), items));
                    recyclerView.smoothScrollToPosition(0);
                    swipeContainer.setRefreshing(false);
                    pd.hide();
                }

                @Override
                public void onFailure(Call<ItemResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(MainActivity.this, "Error Fetching Data!", Toast.LENGTH_SHORT).show();
                    Disconnected.setVisibility(View.VISIBLE);
                    pd.hide();

                }
            });

        }catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
