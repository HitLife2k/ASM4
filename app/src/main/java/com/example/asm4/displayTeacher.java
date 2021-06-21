package com.example.asm4;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class displayTeacher extends RecyclerView.Adapter<displayTeacher.ViewHolder> {
    List<Teacher> listTeacherName;
    Context context;
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView rowName;// khoi tao TextView
        ImageView rowImage;// khoi tao ImageView
        LinearLayout itemVIew;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            rowName = itemView.findViewById(R.id.teacherName);//tim kiem TextView boi id teacherName de cai dat noi dung cho no
            rowImage =  itemView.findViewById(R.id.teacherImg);//tim kiem ImageView boi id teacherImg de cai dat noi dung cho no
            rowImage.setOnCreateContextMenuListener(this);//set menu hien len khi nguoi dung nhan giu
        }
        //tao menu luc nguoi dung nhan giu tren anh
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(this.getAdapterPosition(),1,0,"Update");
            menu.add(this.getAdapterPosition(),2,0,"Delete");
        }

    }
    //Tao constructor de xu li du lieu
    public displayTeacher(Context context , List<Teacher> listTeacherName) {
        this.context = context;
        this.listTeacherName = listTeacherName;
    }
    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    //Tao phuong thuc tao moi cho getItemCount
    public displayTeacher.ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_item,parent,false);// su dung layout recycler_view_item de cai dat hien thi cho tung item trong RecyclerView
        ViewHolder viewHolder = new ViewHolder(view);// Tao moi ViewHolder
        return viewHolder;
    }

    @Override
    //Tao phuong thuc gan data vào View
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull displayTeacher.ViewHolder holder, int position) {
        holder.rowName.setText(listTeacherName.get(position).getName());//set text cho teacherName
        //set hinh anh cho teacherName
        Picasso.get().load(listTeacherName.get(position).getImg()).into(holder.rowImage);
    }

    @Override
    //dem so luong can de tao item cho RecyclerView
    public int getItemCount() {

        return listTeacherName.size();
    }

}
