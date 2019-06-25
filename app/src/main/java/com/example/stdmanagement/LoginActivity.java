package com.example.stdmanagement;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    //TODO SharedPreference를 이용한 자동로그인 기능 만들기
     Button rgsbutton,loginbutton;
     EditText etstdid,etstdpwd;
     String stdid,stdpwd;
     String flagCode;
     AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        rgsbutton =  (Button)findViewById(R.id.bt_register);
        loginbutton = (Button)findViewById(R.id.bt_login);
        etstdid = (EditText)findViewById(R.id.et_stdID);
        etstdpwd = (EditText)findViewById(R.id.et_stdPassword);

        rgsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                LoginActivity.this.startActivity(intent);
            }
        });

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stdid=etstdid.getText().toString().trim();
                stdpwd=etstdpwd.getText().toString().trim();

                if(stdid.isEmpty()){
                    etstdid.setError("plz enter id");
                    etstdid.requestFocus();
                    return;
                }
                if(stdpwd.isEmpty()){
                    etstdpwd.setError("plz enter pwd");
                    etstdpwd.requestFocus();
                    return;
                }

                Call<ResponseBody> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .Login(stdid,stdpwd);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            //custom error code : 1000 - Success Login
                            //                    1001 - Can not found id
                            //                    1002 - Incorrect password

                            flagCode = response.body().string();
                            Log.d("test_flagcode",flagCode);
                            if(flagCode.equals("1001")){
                                Toast.makeText(LoginActivity.this, "아이디를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                if (flagCode.equals("1000")){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                    dialog = builder.setMessage("로그인에 성공하셨습니다.")
                                            .create();
                                    dialog.show();
                                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                    LoginActivity.this.startActivity(intent);
                                    finish();
                                }
                                else if(flagCode.equals("1002")){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                    dialog = builder.setMessage("비밀번호를 틀렸습니다.")
                                            .setPositiveButton("확인",null)
                                            .create();
                                    dialog.show();
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    @Override
    protected void onStop(){
        super.onStop();
        if(dialog!=null){
            dialog.dismiss();
            dialog=null;
        } //dismiss 필수!!!!
    }
}
