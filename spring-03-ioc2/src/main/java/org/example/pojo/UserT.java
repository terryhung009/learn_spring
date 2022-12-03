package org.example.pojo;

public class UserT {
    private String name;

    public UserT(){
        System.out.println("UserT被創建了");
    }

    public UserT(String name){
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
    public void show(){
        System.out.println("name= " + name);
    }
}
