package com.example.asm4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button addOne;// khoi tao button
    List<Teacher> listTeacher;
    RecyclerView recyclerView;// khoi tao RecyclerView
    RecyclerView.Adapter programAdapter;// khoi tao RecyclerView Adapter
    RecyclerView.LayoutManager layoutManager;// khoi tao RecyclerView LayoutManager
    DAOTeacher daoTeacher = new DAOTeacher(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DAOTeacher daoTeacher = new DAOTeacher(MainActivity.this);
        listTeacher = daoTeacher.getAllTeacher();
        recyclerView = findViewById(R.id.listTeacherRV);//goi recyclerView de tim recyclerView voi ten la listTeacherRV
        recyclerView.setHasFixedSize(true);//hanh vi de ngan thay doi chieu ngang rong va chi duoc thay doi noi dung
        layoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.listTeacherRV);
        recyclerView.setLayoutManager(layoutManager);//sap xep cac item trong RecylerView
        programAdapter = new displayTeacher(this, listTeacher);//lay giu lieu ten giao vien
        recyclerView.setAdapter(programAdapter);
        addOne = findViewById(R.id.addOneBtn);// cai dat su kien cho button khi nguoi dung nhap vao
        addOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, url_image.class);//su  dung intent de chuyen doi cac layout
                startActivity(intent);
            }
        });

    }

    //khi nguoi dung nhan chon item tren menu
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Intent intent = new Intent(MainActivity.this, url_image.class);//su  dung intent de chuyen doi cac layout
                intent.putExtra("id", item.getGroupId());//truyen id cho url class
                startActivity(intent);
                Toast.makeText(this, "User Select Update Info", Toast.LENGTH_SHORT).show();
                return true;
            case 2:
                //tao dialog yes no de chac chan nguoi dung muon xoa thong tin giao vien
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            //khi nguoi dung dong y thi xoa thong tin giao vien
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
                                Toast.makeText(MainActivity.this, "User Select Delete Info", Toast.LENGTH_SHORT).show();
                                daoTeacher.deleteTeacher(listTeacher.get(item.getGroupId()).getId());
                                Intent intent = new Intent(MainActivity.this, MainActivity.class);//su  dung intent de chuyen doi cac layout
                                startActivity(intent);
                                break;
                            //khi nguoi dung khong dong y
                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                Toast.makeText(MainActivity.this, "User Select Cancel Delete Info", Toast.LENGTH_SHORT).show();

                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you want to delete teacher with name '"+listTeacher.get(item.getGroupId()).getName()+"' ?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
                return true;
        }
        return super.onContextItemSelected(item);
    }
}