package com.chaoge.dao;



import java.util.List;
import java.util.Map;

import com.chaoge.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {


    public User findUserById (int userId);
    public List<User> findUserByName ( String userId);
    //public List<User> findUserByName (@Param("name") String userId);



    public List<User> getByIdList(List<Integer> list);
    public List<User> getByNameList(List<String> list);
    public List<User> getByMap(Map map);


    public List<User> getByIdList2(List<Integer> list);
    public List<User> getByNameList2(List<String> list);
    public List<User> getByMap2(Map map);

    public List<User> getByIdList3(List<Integer> list);
    public List<User> getByNameList3(List<String> list);
    public List<User> getByMap3(Map map);


    public List<User> getByUser1(User user);
    public List<User> getByUser2(User user);
    public List<User> getByUser3(User user);



}