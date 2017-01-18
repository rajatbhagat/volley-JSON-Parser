package com.example.rajatbhagat.volleytest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GameAdapter adapter;
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;

    private List<Game> gameList;
    private String[] gameID;
    private String[] gameTitles;
    private String[] gameDevelopers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeTextRequest();
        recyclerView = (RecyclerView) findViewById(R.id.game_recycler_view);
        adapter = new GameAdapter(this, gameList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Setup data for the recycler view
    public List<Game> getGameData() {
        List<Game> gameData = new ArrayList<>();
        for (int i = 4; i < gameTitles.length; i++) {
            Game current = new Game();
            current.setGameTitle(gameTitles[i]);
            current.setGameDeveloper(gameDevelopers[i]);
            gameData.add(current);
        }
        return gameData;
    }

    //Make the request for data from the server using Volley
    public void makeTextRequest() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Getting Data..");
        progressDialog.show();

        String user = Constants.USERNAME;
        String url = Constants.GAME_URL;
        Log.e("till", "some");
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    Log.e("onResponse: ", "got here");
                    gameID = new String[response.length()];
                    gameTitles = new String[response.length()];
                    gameDevelopers = new String[response.length()];

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject game = (JSONObject) response.get(i);
                        gameID[i] = game.getString("id");
                        gameTitles[i] = game.getString("game_name");
                        gameDevelopers[i] = game.getString("game_developer_name");
                        Log.e("Titles", gameTitles[i]);
                        Log.e("Developer", gameDevelopers[i]);
                    }

                    gameList = getGameData();
                    adapter = new GameAdapter(getApplicationContext(), gameList);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                } catch (Exception e) {
                    Log.e("onResponse: ", "Error in getting data: " + e.getMessage());
                }
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("errorListener", error.getMessage());
                Log.e("here", "owef");
                progressDialog.dismiss();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(arrayRequest);
    }
}
