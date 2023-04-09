package com.workshop.onebytenpredictors.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.workshop.onebytenpredictors.adapter.Adapter;
import com.workshop.onebytenpredictors.R;
import com.workshop.onebytenpredictors.api.ApiUtilities;
import com.workshop.onebytenpredictors.api.ModelClass;
import com.workshop.onebytenpredictors.api.NewsArrayClass;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment {

    String api="7cb2b436829844d884370c59671ee9ca";
    ArrayList<ModelClass> modelClassArrayList;
    Adapter adapter;
    String country="in";
    private RecyclerView recyclerNews;
    private String category="Business";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_news, container, false);

        recyclerNews=v.findViewById(R.id.recyclerNews);
        modelClassArrayList= new ArrayList<>();
        recyclerNews.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new Adapter(getContext(), modelClassArrayList);

        recyclerNews.setAdapter(adapter);

        findNews();


        return v;
    }

    private void findNews() {
        ApiUtilities.getApiInterface().getCategoryNews(country, category,100,api).enqueue(new Callback<NewsArrayClass>() {
            @Override
            public void onResponse(Call<NewsArrayClass> call, Response<NewsArrayClass> response) {
                if(response.isSuccessful()){
                    modelClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<NewsArrayClass> call, Throwable t) {

            }
        });
    }
}