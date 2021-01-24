package com.louis.springboot.demo.controller;

import com.louis.springboot.demo.dao.OrderListDao;
import com.louis.springboot.demo.entity.OrderList;
import com.louis.springboot.demo.entity.SysUser;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.Id;
import java.util.List;
@RestController
@RequestMapping("OrderList")
public class OrderListController {
    @Resource
    private OrderListDao OrderListDao;
@PostMapping(value="/findAll")
    public List<OrderList> findAll(){
//    截止这里一个面向前端的控制器就写好了
        return OrderListDao.findAll();
    }
//查询某一条信息
//@PostMapping(value="/findOne")
//public List<OrderList> findOne(@RequestParam("id") Integer id){
//    截止这里一个面向前端的控制器就写好了
//    return OrderListDao.findOne(int id);
//}
//    保存接口
@PostMapping(value="/save")
public Object save(@RequestBody OrderList orderList) {
    OrderListDao.save(orderList);
    return 1;
}
//修改接口    id相同会覆盖原来数据
@PostMapping(value="/modify")
public Object modify(@RequestBody OrderList orderList) {
    OrderListDao.save(orderList);
    return 1;
}

//    删除接口   传入参数数据库存在就会把这条数据删掉
@PostMapping(value="/delete")
    public Object delete(@RequestBody OrderList orderList) {
        OrderListDao.delete(orderList);
        return 1;
    }
}
