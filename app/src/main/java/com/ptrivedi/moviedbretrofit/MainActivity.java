package com.ptrivedi.moviedbretrofit;

import android.app.LauncherActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity{

    private EditText txtSearch;
    private Button btnSearch;
    private ListView listResut;
    private RecyclerView recyclerView;
    private RvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSearch = (EditText) findViewById(R.id.txt_search);
        btnSearch = (Button)findViewById(R.id.btn_search);
        recyclerView = (RecyclerView)findViewById(R.id.rec_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        final List<MovieList> AllData = new ArrayList<>();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Retrofit retrofit = new Retrofit.
                        Builder().baseUrl("https://api.themoviedb.org/3/").
                        addConverterFactory(GsonConverterFactory.create()).build();

                RequestService requestService = retrofit.create(RequestService.class);
                Map<String,String> params = new HashMap<>();
                params.put("query",txtSearch.getText().toString());
                params.put("api_key", "28791d80ad154260fe80188b966a2530");

                Call<Bean> call = requestService.getKey(params);
                call.enqueue(new Callback<Bean>() {
                    @Override
                    public void onResponse(Call<Bean> call, Response<Bean> beanResponse) {
                        if(beanResponse.isSuccessful()){

                            if (!AllData.isEmpty()){
                                AllData.clear();
                                //adapter.notifyDataSetChanged();
                            }

                            for (int i = 0; i<beanResponse.body().getResults().size();i++){
                                Bean.ResultsBean bean = beanResponse.body().getResults().get(i);

                                MovieList movieData = new MovieList();
                                movieData.setMovieName(bean.getOriginal_title());
                                movieData.setMovieImagePath(bean.getPoster_path());
                                movieData.setMovieReview(bean.getOverview());
                                movieData.setMovieRate(bean.getVote_average());
                                AllData.add(movieData);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Bean> call, Throwable t) {
                        Log.i("LHD","Request fails");
                    }
                });

                adapter = new RvAdapter(AllData,MainActivity.this);
                recyclerView.setAdapter(adapter);

            }
        });

    }

}
