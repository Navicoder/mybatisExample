package com.chaoge.dao;


import com.chaoge.entity.RoleModel;
import com.chaoge.entity.User;

import java.util.List;

public interface RoleDao {


    public RoleModel getRoles(int roleId);

    public int insertRole(RoleModel role);
    public int batchInsert(List<RoleModel>  roleList);



}