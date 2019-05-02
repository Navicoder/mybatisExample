package com.chaoge;

import com.chaoge.dao.UserDao;
import com.chaoge.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Foreach {
    //Mybatis 通过SqlSessionFactory获取SqlSession, 然后才能通过SqlSession与数据库进行交互
    private static SqlSessionFactory getSessionFactory() {
        SqlSessionFactory sessionFactory = null;
        String resource = "configuration.xml";
        try {
            sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(resource));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sessionFactory;
    }
    public static void main(String[] args) throws Exception {
        SqlSessionFactory sessionFactory = getSessionFactory();
        SqlSession sqlSession = sessionFactory.openSession();
        sqlSession.getConnection().setAutoCommit(true);
        UserDao userDao = sqlSession.getMapper(UserDao.class);

//        There is no getter for property named 'name' in 'class java.lang.String'
//        List<User> w = userDao.findUserByName("W");
//        System.out.println(w);

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        List<String> list2 = new ArrayList<String>();
        list2.add("w");
        list2.add("z");
        System.out.println("===================================================================================");

        //1 List<Integer>  List<String> #{item}
        try {
            List<User> byIdList = userDao.getByIdList(list);
            System.out.println(byIdList);
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            List<User> byNameList = userDao.getByNameList(list2);
            System.out.println(byNameList);
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            Map map = new HashMap();
            map.put("ids",list);
            map.put("names",list2);
            List<User> byMapList =  userDao.getByMap(map);
            System.out.println(byMapList);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("===================================================================================");

        //2 List<Integer>  List<String> ${item}
        try {
            List<User> byIdList = userDao.getByIdList2(list);
            System.out.println(byIdList);
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            List<User> byNameList = userDao.getByNameList2(list2);
            System.out.println(byNameList);
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            Map map = new HashMap();
            map.put("ids",list);
            map.put("names",list2);
            List<User> byMapList =  userDao.getByMap2(map);
            System.out.println(byMapList);

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("===================================================================================");

        //3 map.put("ids",List<Integer>);
        //  map.put("names",List<String>);
        //  #{ids[${index}]}
        try {
            List<User> byIdList = userDao.getByIdList3(list);
            System.out.println(byIdList);
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            List<User> byNameList = userDao.getByNameList3(list2);
            System.out.println(byNameList);

        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            Map map = new HashMap();
            map.put("ids",list);
            map.put("names",list2);
            List<User> byMapList =  userDao.getByMap3(map);
            System.out.println(byMapList);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("===================================================================================");

        //4 根据实体中的map
        //  #{ids[${index}]}

        try {
            User user =  new User();
            user.setName("W");
            Map parammap = new HashMap();
            parammap.put("ids",list);
            parammap.put("names",list2);
            user.setParamMap(parammap);
            List<User> byMapList =  userDao.getByUser1(user);
            System.out.println(byMapList);

        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            User user =  new User();
            Map parammap = new HashMap();
            user.setName("W");
            parammap.put("ids",list);
            parammap.put("names",list2);
            user.setParamMap(parammap);
            List<User> byMapList =  userDao.getByUser2(user);
            System.out.println(byMapList);

        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            User user =  new User();
            user.setName("W");
            Map parammap = new HashMap();
            parammap.put("ids",list);
            parammap.put("names",list2);
            user.setParamMap(parammap);
            List<User> byMapList =  userDao.getByUser3(user);
            System.out.println(byMapList);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    }
