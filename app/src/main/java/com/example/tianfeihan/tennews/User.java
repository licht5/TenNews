package com.example.tianfeihan.tennews;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import com.example.tianfeihan.tennews.HttpUrlConnect;
/**
 * Created by tianfeihan on 17/4/5.
 */

public class User extends Activity {
    private EditText user_name;
    private EditText pass_word;
    //private EditText repass_word;
    private EditText descibe_self;
    private RadioGroup gender_group;
    private RadioButton gender_male;
    private RadioButton gender_female;
    private EditText email_self;
    private EditText phone_number;
    private Button submit_info;
    private Date curdate;
    private String sur;
    private String str;
    private int gender;
    private String sex;
    private String result ;
    private Handler handler;
    private Button comeback;
    private boolean success;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        user_name=(EditText) findViewById(R.id.nameedit);//账号
        pass_word=(EditText) findViewById(R.id.pwdedit);//密码
        //repass_word=(EditText) findViewById(R.id.repwdedit);    //再次密码
        descibe_self=(EditText) findViewById(R.id.describeedit);    //描述
        gender_group=(RadioGroup) findViewById(R.id.sexgroup);  //性别组
        gender_female=(RadioButton)findViewById(R.id.female_sex);   //女
        gender_male=(RadioButton)findViewById(R.id.MALE_SEX);   //男
        email_self= (EditText) findViewById(R.id.emaiedit); //邮箱
        phone_number=(EditText) findViewById(R.id.phoneedit);   //电话
        submit_info=(Button) findViewById(R.id.submit); //提交
        comeback=(Button)findViewById(R.id.comeback);
        curdate=new Date();//日期
        sur=curdate.toString();
        str=sur.replace("GMT+08:00","CST");
        if (gender_female.isChecked()){
            gender=0;
        }
        else {
            gender=0;
        }

        submit_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(user_name.getText().toString())|"".equals(pass_word.getText().toString())) {
                    Toast.makeText(User.this, "请将注册信息填写完整", Toast.LENGTH_SHORT).show();
                    return;
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        register();
                        Message m=handler.obtainMessage();
                        handler.sendMessage(m);


                    }
                }).start();
                //register();






            }
        });
        handler =new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (success){
                    Toast.makeText(User.this,"注册成功",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(User.this,"注册失败，请重新注册",Toast.LENGTH_SHORT).show();
                }
                super.handleMessage(msg);
            }
        };


        comeback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comeback=new Intent(User.this,MainActivity.class);
                startActivity(comeback);
            }
        });




    }



    public void register(){
        String url = "http://120.24.44.222:8080/KstServerInterface/user/register";
        HttpUrlConnect httpUrlConnect = new HttpUrlConnect();
        String charset = "utf-8";
        Map<String,Object> params = new HashMap<>();
//        String username = "tongxuanpingq";
//        String password = "1451767523";
//        int gender = 1;
//        Date date = new Date();
//        String str = "Thu Apr 06 10:18:03 GMT+08:00 2017";
//        str=str.replace("GMT+08:00","CST");
        params.put("username", user_name.getText().toString());
        params.put("password", pass_word.getText().toString());

        params.put("gender", gender);
        params.put("date", str);
        params.put("describe", descibe_self.getText().toString());
        params.put("email", email_self.getText().toString());
        params.put("phone", phone_number.getText().toString());
        result = httpUrlConnect.post(url, params);
        Pattern p=Pattern.compile("success");
        Matcher m=p.matcher(result);
        success=m.find();
        //Toast.makeText(User.this,result,Toast.LENGTH_SHORT).show();
    }
}
