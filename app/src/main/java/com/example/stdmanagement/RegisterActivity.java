package com.example.stdmanagement;


import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterActivity extends AppCompatActivity {

    private EditText rgsid;
    private EditText rgspassword;
    private EditText rgsphone;
    Button btregister,checkid;
    private String stdid, stdpwd,stdphone;
    Boolean flag = false;
    AlertDialog dialog;
    String checkString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rgsid = (EditText)findViewById(R.id.et_rgsID);
        rgspassword = (EditText)findViewById(R.id.et_rgsPassword);
        rgsphone = (EditText)findViewById(R.id.et_rgsPhone);
        btregister = findViewById(R.id.bt_register2);
        checkid = findViewById(R.id.bt_checkid);


        checkid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stdid = rgsid.getText().toString().trim();
                if(stdid.isEmpty()){
                    rgsid.setError("id is required");
                    rgsid.requestFocus();
                    return;
                }
                Call<ResponseBody> check = RetrofitClient
                        .getInstance()
                        .getApi()
                        .CheckID(stdid);
                check.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            if(Boolean.valueOf(response.body().string())){
                                Toast.makeText(RegisterActivity.this, "사용가능한 아이디입니다.", Toast.LENGTH_SHORT).show();
                                checkString=stdid;
                                flag=true;
                            }
                            else{
                                Toast.makeText(RegisterActivity.this, "이미 있는 아이디입니다.", Toast.LENGTH_SHORT).show();
                                flag=false;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, t.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


        btregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stdid = rgsid.getText().toString().trim();
                stdpwd = rgspassword.getText().toString().trim();
                stdphone = rgsphone.getText().toString().trim();

                if(stdid.isEmpty()){ //check id
                    rgsid.setError("id is required");
                    rgsid.requestFocus();
                    return;
                }

                if(stdpwd.isEmpty()){ //check pwd
                    rgspassword.setError("pwd is required");
                    rgspassword.requestFocus();
                    return;
                }

                if(stdpwd.length()<6 || stdpwd.length()>12){ //check pwd length
                    rgspassword.setError("비밀번호는 6~12자리");
                    rgspassword.requestFocus();
                    return;
                }
                if(!(stdid.equals(checkString))){
                    Toast.makeText(RegisterActivity.this, "중복체크를 해주세요", Toast.LENGTH_SHORT).show();
                    flag=false;
                    return;
                }
                if(flag){
                    Call<ResponseBody> call = RetrofitClient
                            .getInstance()
                            .getApi()
                            .register(stdid,stdpwd,stdphone);


                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                            dialog = builder.setMessage("회원가입에 성공하셨습니다.")
                                    .create();
                            dialog.show();
                            new Handler().postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    finish();
                                }
                            }, 1000);

                        }
                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(RegisterActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
                } else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("중복체크를 해주세요.")
                            .setPositiveButton("확인",null)
                            .create();
                    dialog.show();
                    return;
                }
            }
        });

    }

    @Override
    protected  void onStop(){
        super.onStop();
        if(dialog!=null){
            dialog.dismiss();
            dialog=null;
        }
    }
}

/* class registDB extends AsyncTask<Void, Integer, Void> {

            @Override
            protected Void doInBackground(Void... unused) {

                *//* 인풋 파라메터값 생성 *//*
                String param = "stdID=" + stdid + "&stdPassword=" + stdpassword + "&stdPhone="+stdphone;
                Log.d("info_rgs9999",stdid+"/"+stdpassword+"/"+stdphone);

                try {
                    *//* 서버연결 *//*
                    URL url = new URL(
                            "http://192.168.0.15/Register.php");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    conn.setRequestMethod("POST");
                    conn.setDoInput(true);
                    conn.connect();

                    *//* 안드로이드 -> 서버 파라메터값 전달 *//*
                    OutputStream outs = conn.getOutputStream();
                    outs.write(param.getBytes("UTF-8"));
                    outs.flush();
                    outs.close();

                    *//* 서버 -> 안드로이드 파라메터값 전달 *//*
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
        });*/