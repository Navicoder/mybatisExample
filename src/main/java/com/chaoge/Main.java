package com.chaoge;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.JavaBeanSerializer;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.chaoge.dao.RoleDao;
import com.chaoge.dao.UserDao;
import com.chaoge.entity.RoleModel;
import com.chaoge.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {



    public static void main(String[] args) throws Exception{
        SqlSessionFactory sessionFactory = getSessionFactory();
        SqlSession sqlSession = sessionFactory.openSession();
        Configuration configuration = sessionFactory.getConfiguration();

        System.out.println(JSON.toJSONString(configuration,SerializerFeature.IgnoreNonFieldGetter));

//        不设置的话 不会提交；但是id 已经自增过了
//         Created connection 9461434.
//        Setting autocommit to false on JDBC Connection [com.mysql.jdbc.JDBC4Connection@905eba]
        sqlSession.getConnection().setAutoCommit(true);


/*          UserDao userMapper = sqlSession.getMapper(UserDao.class);
        //User user = userMapper.findUserById(2);
      List<User> userByName = userMapper.findUserByName("9uk");
        System.out.println(userByName);*/

        //https://blog.csdn.net/u013360850/article/details/78704701 Collection嵌套
        RoleDao roleDao = sqlSession.getMapper(RoleDao.class);
        //sqlSession.selectOne(String statement)
        RoleModel roles = roleDao.getRoles(1);
        System.out.println(roles.getLastUpdateTime());//Wed Nov 21 20:09:22 CST 2018
        System.out.println(JSONObject.toJSONString(roles));//"lastUpdateTime":1542802162000,
        System.out.println("===================================================================================");


        if (1 == 1) {
            RoleModel role =new RoleModel();
            List<RoleModel> list = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                role.setId(null);
                role.setActive(true);
                role.setDescription("insert_"+i);
                role.setName("testinsert_"+i);
                role.setLastUpdateTime(new Date());
                list.add(role);
            }
            System.out.println(roleDao.batchInsert(list));
            configuration = sessionFactory.getConfiguration();
            System.out.println(JSON.toJSONString(configuration,SerializerFeature.IgnoreNonFieldGetter));
           // Class<?> configurationFactory = sessionFactory.getConfiguration().getConfigurationFactory();

            return;
        }



        long time =System.currentTimeMillis();
        String timeStr = "2017-12-01 12:12:12";



        RoleModel role =new RoleModel();
        role.setActive(true);
        role.setDescription("insert");
        role.setName("testinsert");

        //role.setLastUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeStr));
        role.setLastUpdateTime(new Timestamp(time));
        System.out.println(roleDao.insertRole(role));
        System.out.println(roleDao.getRoles(1).getLastUpdateTime());// lastUpdateTime是Date类型 Wed Nov 21 20:09:22 CST 2018  lastUpdateTime是Timestamp 类型 2018-11-21 20:09:22.0
        System.out.println(JSONObject.toJSONString(roleDao.getRoles(1).getLastUpdateTime()));//无论是Date类型 还是TimeStamp类型 都是 1542802162000

        //https://blog.csdn.net/u010246789/article/details/SerializerFeature
        System.out.println(JSONObject.toJSONString(roleDao.getRoles(1).getLastUpdateTime(),SerializerFeature.UseISO8601DateFormat));// UseISO8601DateFormat:Date使用ISO8601格式输出，默认为false

        System.out.println(JSONObject.toJSONString(roleDao.getRoles(1).getLastUpdateTime(),SerializerFeature.WriteDateUseDateFormat));
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        JSON.toJSONString(roleDao.getRoles(1).getLastUpdateTime(), SerializerFeature.WriteDateUseDateFormat);//"2018-11-21 20:09:22"

        String jsonStringWithDateFormat =  JSON.toJSONStringWithDateFormat(null, "yyyy-MM-dd");
        String jsonStringWithDateFormat2 = JSONObject.toJSONStringWithDateFormat(null, "yyyy-MM-dd");

    }

    //Mybatis 通过SqlSessionFactory获取SqlSession, 然后才能通过SqlSession与数据库进行交互
    private static SqlSessionFactory getSessionFactory() {
        SqlSessionFactory sessionFactory = null;
        String resource = "configuration.xml";
        try {
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            sessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsReader(resource));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sessionFactory;
    }


    @Test
    public void test(){
        String s = JSONObject.toJSONString(new Date());
        System.out.println(s);
        System.out.println(new Date().toString());
    }
    /**
     * 3: https://blog.csdn.net/u010246789/article/details/52539576
     * UseISO8601DateFormat:Date使用ISO8601格式输出，默认为false
     */
    private static void useISO8601DateFormat(Object o) {
        System.out.println(JSONObject.toJSONString(o));
        System.out.println("设置UseISO8601DateFormat后：");
        System.out.println(JSONObject.toJSONString(o, SerializerFeature.UseISO8601DateFormat));
    }

}