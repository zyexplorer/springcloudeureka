package com.pab.springcloudeurekaclientprovider1;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Type：
 * @Description:
 * @Author: ZY
 * @Date: 2017-12-26 10:21:04
 */
@EnableEurekaClient
@Controller
@RequestMapping("/user")
public class ServiceController {

    @ResponseBody
    @RequestMapping("/checkUsername")
    public String checkUsername(@RequestParam("username") String username){
        System.out.println(username + "进入provider1后台");
        return username + "可用";
    }

    @ResponseBody
    @RequestMapping("/checkMobile")
    public Map<String,Object> checkMobile(String mobile){
        System.out.println("调用service provider1开始检查手机号可用性");
        System.out.println("需要检查可用性的手机号：" + mobile);
        Map<String,Object>map = new HashMap<>();
        map.put("result",true);
        map.put("msg", mobile + "可用");
        return map;
    }

}
