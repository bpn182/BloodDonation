package com.example.user.blooddonation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ReceiverActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    String fetchUrl = "https://cosmos182.herokuapp.com/donation/getdoners";
    ActionBar toolbar;

    AQuery aquery;
    private List<Doners> donersList;
    private RecyclerView recyclerView;
    private ReceiverAdapter mAdadpter;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);

        recyclerView = findViewById(R.id.recycler_view);
        aquery = new AQuery(this);

        toolbar = getSupportActionBar();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        donersList = new ArrayList<>();

        mAdadpter = new ReceiverAdapter(donersList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdadpter);

        fetchData();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query){
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText){
        newText = newText.toLowerCase();
        ArrayList<Doners> newList = new ArrayList<>();
        for(Doners doner: donersList){
            String name = doner.getDistrict().toLowerCase();
            if(name.contains(newText)){
                newList.add(doner);
            }

        }
        mAdadpter.setFilters(newList);
        return true;
    }



    public void fetchData() {

        aquery.ajax(fetchUrl, JSONArray.class, new AjaxCallback<JSONArray>() {
            @Override
            public void callback(String url, JSONArray array, AjaxStatus status) {
                super.callback(url, array, status);


                Log.i("response", url + "respone:" + array);

                for (int i = 0; i < array.length(); i++) {

                    try {
                        JSONObject object = array.getJSONObject(i);
                        Doners doner = new Doners();

                        doner.name = object.getString("name");
                        doner.phone = object.getString("phone");
                        doner.group = object.getString("group");
                        doner.district = object.getString("district");

                        donersList.add(doner);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mAdadpter.notifyDataSetChanged();
            }
        });

    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.donate:
                    Intent intent = new Intent(ReceiverActivity.this,DonersActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.receive:
                    toolbar.setTitle("Receive");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }
}
