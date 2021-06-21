package com.example.asm4;
//tao model teacher
public class Teacher {

    public Teacher(String id,String name, String img) {
        this.name = name;
        this.img = img;
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    private String name;
    private String img;
    private String id;



}
