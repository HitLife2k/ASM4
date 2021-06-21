package com.example.asm4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class url_image extends AppCompatActivity {
    EditText UrlImagetxt,Nametxt;// khoi tao textbox
    TextView TeacherIDtxt;
    Button Okbtn,Cancelbtn;// khoi tao button
    List<Teacher> listTeacher;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_image);
        DAOTeacher daoTeacher = new DAOTeacher(url_image.this);
        listTeacher= daoTeacher.getAllTeacher();
        Intent intent = getIntent();
        id = intent.getIntExtra("id",-1);
        UrlImagetxt = findViewById(R.id.urlImagetxt);//tim kiem textbox voi ten la urlImagetxt
        Nametxt = findViewById(R.id.nametxt);//tim kiem textbox voi ten la nametxt
        TeacherIDtxt = (TextView) findViewById(R.id.idTeachertxt);
        if(id!=-1) {
            UrlImagetxt.setText(listTeacher.get(id).getImg());
            Nametxt.setText(listTeacher.get(id).getName());
            TeacherIDtxt.setText(String.valueOf(id + 1));
        }
        Okbtn = findViewById(R.id.okBtn);//tim kiem button voi ten la okBtn
        Okbtn.setOnClickListener(new View.OnClickListener() {//cai dat su kien cho button
            @Override
            public void onClick(View v) {
                String urlLink = UrlImagetxt.getText().toString().trim();//lay du lieu tu UrlImagetxt
                String nameTeacher = Nametxt.getText().toString().trim();//lay du lieu tu Nametxt
                if(nameTeacher.isEmpty()){//neu nameImg rong thi bat nguoi dung nhap vao
                    Toast.makeText(getApplicationContext(), "Please enter Name Teacher !!", Toast.LENGTH_SHORT).show();
                }
                else if(urlLink.isEmpty()) {//neu urlLink rong thi bat nguoi dung nhap vao
                    Toast.makeText(getApplicationContext(), "Please enter Url Image link !!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Teacher teacher = new Teacher("",nameTeacher,urlLink);
                    if(id!=-1){
                        boolean success = daoTeacher.updateTeacher(listTeacher.get(id).getId(),teacher);
                        Toast.makeText(getApplicationContext(), "Update Teacher Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(url_image.this,MainActivity.class);//su  dung intent de chuyen doi cac layout
                        startActivity(intent);
                    }
                    else {// va neu khong rong thi them giao vien vao danh sach
                        listTeacher.add(teacher);
                        boolean success = daoTeacher.insertTeacher(teacher);
                        Toast.makeText(getApplicationContext(), "Add new Teacher Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(url_image.this,MainActivity.class);//su  dung intent de chuyen doi cac layout
                        startActivity(intent);
                    }
                }
            }
        });
        Cancelbtn= findViewById(R.id.cancelBtn);//tim kiem button voi ten la okBtn
        Cancelbtn.setOnClickListener(new View.OnClickListener() { // //cai dat su kien cho button
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(url_image.this, MainActivity.class);//su  dung intent de chuyen doi cac layout
                startActivity(intent);
            }
        });
    }
}