package com.pab.springcloudeurekaclientconsumer.controller;

import com.alibaba.fastjson.JSONObject;
import com.pab.springcloudeurekaclientconsumer.services.ConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Type：
 * @Description:
 * @Author: ZY
 * @Date: 2017-12-26 11:30:47
 */
@Controller
public class TestConsumeServiceController {

    @Autowired
    private ConsumeService consumeService;

    @ResponseBody
    @RequestMapping("/hi")
    public String testHelloService(@RequestParam("name") String name){
        System.out.println("Hi RequestParam name:" + name);
        return  consumeService.hiService("祝印");
    }

    //@ResponseBody
    @RequestMapping("/checkMobile")
    public String testControllerService(@RequestParam("mobile") String mobile,Model model){
        System.out.println("要检查的手机号是：" + mobile);
        Map<String,Object> map = consumeService.checkMobile(mobile);
        System.out.println("result:" + map.get("result"));

        model.addAttribute("map",map);
        return "result";
    }

    @ResponseBody
    @RequestMapping("/checkMobileAjax")
    public Map<String,Object> testControllerService1(@RequestParam("mobile") String mobile,Model model){
        System.out.println("要检查的手机号是：" + mobile);
        Map<String,Object> map = consumeService.checkMobile(mobile);
        System.out.println("result:" + map.get("result"));
        return map;
    }

    @RequestMapping("/checkName")
    public String testControllerService2(@RequestParam("name") String name,Model model){
        System.out.println("要检查的名称是：" + name);
        Map<String, Object> map = consumeService.checkName(name);
        System.out.println("result:" + map.get("result"));
        model.addAttribute("map",map);
        return "result";
    }

    @ResponseBody
    @RequestMapping("/checkNameAjax")
    public Map<String,Object> testControllerService3(@RequestParam("name") String name){
        System.out.println("要检查的名字是：" + name);
        Map<String, Object> map = consumeService.checkName(name);
        System.out.println("result:" + map.get("result"));
        return map;
    }

    @ResponseBody
    @RequestMapping("/checkNameAndMobile")
    public String checkNameAndMobile(@RequestParam("name") String name, @RequestParam("mobile") String mobile){
        String jsonStr = consumeService.checkNameAndMobile(name, mobile);
        System.out.println("8764："+jsonStr);
        return jsonStr;
    }

    @ResponseBody
    @RequestMapping("/getAllUser")
    public String getAllUser(){
        Map<String, Object> map = consumeService.getAllUsers();
        System.out.println(map);
        return (String) map.get("retStr");
    }

    @ResponseBody
    @RequestMapping("/getAllUsersList")
    public String getAllUsersList(){
        String usersList = consumeService.getAllUsersList();
        return usersList;
    }
}
