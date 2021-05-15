package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements onItemClicked{
    private NewsListAdapter newsListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetchData();
        newsListAdapter=new NewsListAdapter(this);
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(newsListAdapter);
    }
    private void fetchData(){
      String url="https://newsapi.org/v2/top-headlines?country=us&apiKey=dd8a43ea1ad547079ae4b1d7e43f4a7f";
      final ArrayList<News> arrayList=new ArrayList<>();
        final JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                JSONArray jsonArray=response.optJSONArray("articles");
                for(int i=0;i<jsonArray.length();i++) {
                    JSONObject jsonObject = jsonArray.optJSONObject(i);



                        String title = jsonObject.optString("title");
                        String url=jsonObject.optString("url");
                        String imageurl=jsonObject.optString("urlToImage");
                        String author=jsonObject.optString("author");
                        arrayList.add(new News(title,url,imageurl,author));
                        newsListAdapter.update(arrayList);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {@Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("User-Agent", "Mozilla/5.0");
            return headers;
        }
        };
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void onItemClick(News s) {
        CustomTabsIntent.Builder customTabsIntent=new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent1= customTabsIntent.build();
        customTabsIntent1.launchUrl(this, Uri.parse(s.getUrl()));
    }
}