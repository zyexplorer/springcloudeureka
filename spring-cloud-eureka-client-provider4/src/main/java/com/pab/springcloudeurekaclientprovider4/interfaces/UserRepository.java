package com.pab.springcloudeurekaclientprovider4.interfaces;


import com.pab.springcloudeurekaclientprovider4.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Type：
 * @Description:
 * @Author: ZY
 * @Date: 2017-12-26 19:09:27
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    /*
    * @Description: 根据年龄来查询用户列表
    * @Author: ZY
    * @Date：2017-7-6 23:39:11
    * @Params:
    */
    public List<User> getUserByAge(int age);

    /*
     * @Description: 根据姓名和年龄来查询用户列表
     * @Author: ZY
     * @Date：2017-7-6 23:40:40
     * @Params:
     */
    public List<User> getUserByNameAndAge(String name, int age);

    public List<User> getUserByMobile(String mobile);

    public List<User> getUserByName(String name);

    public List<User> getUserByNameAndMobile(String name, String mobile);
}
