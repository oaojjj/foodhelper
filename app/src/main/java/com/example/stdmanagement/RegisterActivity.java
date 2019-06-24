package com.example.stdmanagement;


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class RegisterActivity extends AppCompatActivity {

    private EditText rgsid;
    private EditText rgspassword;
    private EditText rgsphone;
    Button btregister;
    String stdid, stdpassword,stdphone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rgsid = (EditText)findViewById(R.id.et_rgsID);
        rgspassword = (EditText)findViewById(R.id.et_rgsPassword);
        rgsphone = (EditText)findViewById(R.id.et_rgsPhone);
        btregister = findViewById(R.id.bt_register2);

        class registDB extends AsyncTask<Void, Integer, Void> {

            @Override
            protected Void doInBackground(Void... unused) {

                /* 인풋 파라메터값 생성 */
                String param = "stdID=" + stdid + "&stdPassword=" + stdpassword + "&stdPhone="+stdphone;
                Log.d("info_rgs9999",stdid+"/"+stdpassword+"/"+stdphone);

                try {
                    /* 서버연결 */
                    URL url = new URL(
                            "http://192.168.0.15/insert.php");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    conn.setRequestMethod("POST");
                    conn.setDoInput(true);
                    conn.connect();

                    /* 안드로이드 -> 서버 파라메터값 전달 */
                    OutputStream outs = conn.getOutputStream();
                    outs.write(param.getBytes("UTF-8"));
                    outs.flush();
                    outs.close();

                    /* 서버 -> 안드로이드 파라메터값 전달 */
                    InputStream is = null;
                    BufferedReader in = null;
                    String data = "";

                    is = conn.getInputStream();
                    in = new BufferedReader(new InputStreamReader(is), 8 * 1024);
                    String line = null;
                    StringBuffer buff = new StringBuffer();
                    while ( ( line = in.readLine() ) != null )
                    {
                        buff.append(line + "\n");
                    }
                    data = buff.toString().trim();
                    Log.e("RECV DATA",data);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

        }


        btregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stdid = rgsid.getText().toString();
                stdpassword= rgspassword.getText().toString();
                stdphone = rgsphone.getText().toString();

                registDB rdb = new registDB();
                rdb.execute();
            }
        });




    }
}

