package com.example.retrofit2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button get;
    EditText name,sex;
    String inputname;
    String inputsex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        get = (Button)findViewById(R.id.get);
        name = (EditText) findViewById(R.id.name);
        sex = (EditText)findViewById(R.id.sex);


        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputname = name.getText().toString().trim();
                inputsex = sex.getText().toString().trim();

                Call<ResponseBody> call = Client
                        .getInstance().getApi().createUser(inputname,inputsex);

                call.enqueue(new Callback<ResponseBody>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String s = response.body().string();
////                        Toast.makeText(getApplicationContext(), s , Toast.LENGTH_LONG).show();
//                          JSONArray jsonArray = new JSONArray(s);
//
//                          String res = jsonArray
//                          Toast.makeText(getApplicationContext(),res,Toast.LENGTH_LONG).show();

//                            ArrayList<String> stringArray = new ArrayList<String>();
//                            JSONArray jsonArray = new JSONArray(s);
//                            for(int i = 0, count = jsonArray.length(); i< count; i++)
//                            {
//                                try {
//                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//                                    stringArray.add(jsonObject.toString());
//                                }
//                                catch (JSONException e) {
//                                    e.printStackTrace();
//                                } }

                            JSONArray arr = new JSONArray(s);
                            JSONObject jObj = arr.getJSONObject(0);
                            String date = jObj.getString("parentno");
                            if (date != "") {
                                Toast.makeText(getApplicationContext(),""+date.trim(),Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"null",Toast.LENGTH_LONG).show();
                            }


                            }
                        catch (IOException e){
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Failure"+t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }




}
