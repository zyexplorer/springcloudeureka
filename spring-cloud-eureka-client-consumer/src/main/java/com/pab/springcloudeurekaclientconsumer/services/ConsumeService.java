package com.pab.springcloudeurekaclientconsumer.services;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Type：
 * @Description: 本service为调用其他服务器应用的service（如同调用WebService一样）
 *               调用格式，url:http://服务在Eureka注册中心注册的名称/服务发布的context-path/映射路径（？参数）  后面的*.class为远程方法的返回值类型
 * @Author: ZY
 * @Date: 2017-12-26 11:21:44
 */
@Service
public class ConsumeService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    public String hiService(String name){
        return restTemplate.getForObject("http://service-provider1/hi?name="+name,String.class);
    }

    public Map<String,Object> checkMobileService(String mobile){
        // 使用DiscoveryClient来获取服务
        String serviceName = "service-provider3";
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        if (CollectionUtils.isEmpty(instances)) {
            return null;
        }
        ServiceInstance serviceInstance = instances.get(0);
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        String url = "http://" + host + ":" + port;
        Map map = restTemplate.getForObject(url, Map.class);
        return map;
    }

    public Map<String,Object> checkMobile(String mobile){
        return restTemplate.getForObject("http://service-provider3/provider-client/user/checkMobile?mobile=" + mobile,Map.class);
    }

    public Map<String,Object> checkName(String name){
        Map map = restTemplate.getForObject("http://service-provider3/provider-client/user/checkName?name=" + name, Map.class);
        return map;
    }

    public String checkNameAndMobile(String name,String mobile){
        return restTemplate.getForObject("http://service-provider3/provider-client/user/checkNameAndMobile?name="+name+"&mobile="+mobile,String.class);
    }

    public Map<String,Object> getAllUsers(){
        String retStr = restTemplate.getForObject("http://service-provider4/provider-client/user/getAllUsers", String.class);
        System.out.println("ConsumeService:" + retStr);
        Map<String,Object>map = new HashMap<>(1);
        map.put("retStr",retStr);
        return map;
    }

    public String getAllUsersList(){
        List list = restTemplate.getForObject("http://service-provider3/provider-client/user/getAllUsersList", List.class);
        return JSONObject.toJSONString(list);
    }
}
