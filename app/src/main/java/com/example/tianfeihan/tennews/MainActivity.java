package com.example.tianfeihan.tennews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends Activity {
    private EditText usern;
    private EditText passwordn;
    private Button login;
    private Button signup;
    private String respond;
    private Handler myhandlerl;
    private boolean suc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usern=(EditText) findViewById(R.id.username1);
        passwordn=(EditText) findViewById(R.id.password1);
        login= (Button) findViewById(R.id.login);
        signup= (Button) findViewById(R.id.signup);
//        usern= (EditText) findViewById(R.id.Edit4);
//        passwordn= (EditText) findViewById(R.id.edit3);
        //点击登录按钮
        login.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if ("".equals(usern.getText().toString())&&"".equals(passwordn.getText().toString())){
                       Toast.makeText(MainActivity.this,"请将登录信息填写完整",Toast.LENGTH_SHORT).show();
                   }
                   else {
                       new Thread(new Runnable() {
                           @Override
                           public void run() {
                               login();
                               Message mess=myhandlerl.obtainMessage();
                               myhandlerl.sendMessage(mess);
                               if (suc){

                                       Intent login=new Intent(MainActivity.this,NewsList.class);
                                       startActivity(login);

                               }
                           }
                       }).start();
                   }


//


                      }
                }
        );

        myhandlerl=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (!suc){
                    Toast.makeText(MainActivity.this,"用户名或密码错误，请重新登录",Toast.LENGTH_SHORT).show();
                }

                super.handleMessage(msg);
            }
        };

        //点击注册按钮
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup=new Intent(MainActivity.this,User.class);
                startActivity(signup);
            }
        });






    }
public void login(){
    String url = "http://120.24.44.222:8080/KstServerInterface/user/login";
    HttpUrlConnect httpUrlConnect = new HttpUrlConnect();
    String charset = "utf-8";
    Map<String,Object> params = new HashMap<>();
    params.put("username", usern.getText().toString());
    params.put("password", passwordn.getText().toString());

    respond = httpUrlConnect.post(url, params);
    Pattern p=Pattern.compile("name");
    Matcher m=p.matcher(respond);
    suc=m.find();
}


}
