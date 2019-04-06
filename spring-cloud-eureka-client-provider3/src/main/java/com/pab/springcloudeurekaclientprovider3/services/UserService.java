package com.pab.springcloudeurekaclientprovider3.services;

import com.alibaba.fastjson.JSONObject;
import com.pab.springcloudeurekaclientprovider3.interfaces.UserRepository;
import com.pab.springcloudeurekaclientprovider3.pojo.User;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Type：
 * @Description: 本service内的方法既可以供本应用内部调用，也可以作为webservice接口供外部应用调用
 * @Author: ZY
 * @Date: 2017-12-26 17:26:48
 */
@Service
@EnableEurekaClient
@RequestMapping("/user")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @ResponseBody
    @RequestMapping("/checkMobile")
    public Map<String, Object> checkMobile(@RequestParam("mobile") String mobile) {
        System.out.println("WebService要校验的手机号：" + mobile);
        Map<String, Object> map = new HashMap<>(2);
        List<User> userList = userRepository.getUserByMobile(mobile);
        System.out.println(userList);
        if (userList != null && userList.size() > 0) {
            map.put("result", false);
            map.put("msg", "手机号已被占用");
            return map;
        }
        map.put("result", true);
        map.put("msg", "手机号可用");
        return map;
    }

    @ResponseBody
    @RequestMapping("/checkName")
    public Map<String, Object> checkName(@RequestParam("name") String name) {
        System.out.println("WebService要校验的名字是：" + name);
        Map<String, Object> map = new HashMap<>(2);
        List<User> userList = userRepository.getUserByName(name);
        if (userList != null && userList.size() > 0) {
            map.put("result", false);
            map.put("msg", "名字已被占用");
            return map;
        }
        map.put("result", true);
        map.put("msg", "名字可用");
        return map;
    }

    @ResponseBody
    @RequestMapping("/checkNameAndMobile")
    public String checkNameAndMobile(@RequestParam("name") String name, @RequestParam("mobile") String mobile) {
        List<User> userList = userRepository.getUserByNameAndMobile(name, mobile);
        String jsonString = JSONArray.toJSONString(userList);
        System.out.println("8766：" + jsonString);
        System.out.println("8766：" + JSONObject.toJSON(userList));
        return jsonString;
    }

    @ResponseBody
    @RequestMapping("/getAllUsers")
    public String getAllUsers() {
        List<User> userList = userRepository.findAll();
        String json = JSONObject.toJSONString(userList);
        System.out.println(json);
        return json;
    }

    @ResponseBody
    @RequestMapping("/getAllUsersList")
    public List<User> getAllUsersList() {
        return userRepository.findAll();
    }

    @Test
    public void test() {
        List<Map> userList1 = new ArrayList<>();
        List<User> userList2 = new ArrayList<>();
        User user = new User();
        user.setId(5);
        user.setAge(28);
        user.setName("张三");
        user.setMobile("13297517768");

        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        userList1.add(map);
        userList2.add(user);
        String jsonStr1 = JSONArray.toJSONString(userList1);
        String jsonStr2 = JSONArray.toJSONString(userList2);

        Object userToJSON = JSONObject.toJSON(user);
        Object mapToJSON = JSONObject.toJSON(map);
        Object userList1ToJSON = JSONObject.toJSON(userList1);
        Object userList2ToJSON = JSONObject.toJSON(userList2);
        //{"mobile":"13297517768","name":"张三","id":5,"age":28}
        System.out.println("userToJSON:" + userToJSON);
        //{"user":{"mobile":"13297517768","name":"张三","id":5,"age":28}}
        System.out.println("mapToJSON:" + mapToJSON);
        //[{"user":{"mobile":"13297517768","name":"张三","id":5,"age":28}}]
        System.out.println("userList1ToJSON:" + userList1ToJSON);
        //[{"mobile":"13297517768","name":"张三","id":5,"age":28}]
        System.out.println("userList2ToJSON:" + userList2ToJSON);

        //list中装map  [{"user":{"name":"张三","mobile":"13297517768","id":5,"age":28}}]
        System.out.println(jsonStr1);
        //list中装对象 [{"name":"张三","mobile":"13297517768","id":5,"age":28}]
        System.out.println(jsonStr2);
    }
}
