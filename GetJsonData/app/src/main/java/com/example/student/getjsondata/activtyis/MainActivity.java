package com.example.student.getjsondata.activtyis;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.student.getjsondata.R;
import com.example.student.getjsondata.adapters.JsonAdapter;
import com.example.student.getjsondata.models.JsonModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<JsonModel> list = new ArrayList();
    private JsonModel jsonModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        JsonAcyncTask acyncTask = new JsonAcyncTask();
        acyncTask.execute("");


    }

    public JsonModel getJsonModel() {
        return jsonModel;
    }

    public void setJsonModel(JsonModel jsonModel) {
        this.jsonModel = jsonModel;
    }

    private void setRecaclarView() {
        JsonAdapter adapter = new JsonAdapter(MainActivity.this, list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    class JsonAcyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL("https://jsonplaceholder.typicode.com/photos");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream input = new BufferedInputStream(connection.getInputStream());
                BufferedReader bf = new BufferedReader(new InputStreamReader(input));
                String line;
                StringBuffer stringBuffer = new StringBuffer();
                while ((line = bf.readLine()) != null) {
                    stringBuffer.append(line);
                }
                JSONArray jsonArray = new JSONArray(stringBuffer.toString());
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject object = jsonArray.getJSONObject(i);
                    JsonModel jsonModel = new JsonModel();
                    jsonModel.setAlbumId(Integer.valueOf(object.getString("albumId")));
                    jsonModel.setId(Integer.valueOf(object.getString("id")));
                    jsonModel.setUrl(object.getString("url"));
                    jsonModel.setTitle(object.getString("title"));
                    jsonModel.setThumbnailUrl(object.getString("thumbnailUrl"));
                    list.add(jsonModel);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            setRecaclarView();
        }
    }


}
