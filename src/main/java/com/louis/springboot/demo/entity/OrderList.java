package com.louis.springboot.demo.entity;
//1.在实体类的包下面创建一个类


import javax.persistence.Entity;
import javax.persistence.Id;

//7.引入@Entity注解
@Entity

public class OrderList {
//    2.实体类建好以后和数据库一一映射
//    8.声明主键   这个操作完说明OrderList这个表跟java已经映射好了
    @Id
    private Integer id;
//    private  String name ;
    private String discription;
    private String expected ;
    private String quality;
    private  String category;
    private  String enterTime;
    private  Integer tid;
//    3.基本结构写完了接下来是写构建方法   按alt+insert


//    6.把set  get方法导进来   到这里实体类就写完了    实体类写完要做一些jpa的操作和数据库构成映射关系


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(String enterTime) {
        this.enterTime = enterTime;
    }

    //4.导入无参数的构造参数    直接选择select  none

    public OrderList() {
    }

//5.导入有参数的构造参数   全选  按ok
//    public OrderList(Integer id, String name, Integer tid) {
//        this.id = id;
//        this.name = name;
//        this.tid = tid;
//    }

    public OrderList(Integer id, String name, String discription, String expected, String quality, String category, String enterTime, Integer tid) {
        this.id = id;
//        this.name = name;
        this.discription = discription;
        this.expected = expected;
        this.quality = quality;
        this.category = category;
        this.enterTime = enterTime;
        this.tid = tid;
    }
}
