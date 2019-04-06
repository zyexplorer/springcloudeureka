package com.pab.springcloudeurekaclientconsumer.pagecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Type：
 * @Description:
 * @Author: ZY
 * @Date: 2017-12-27 09:22:37
 */
@Controller
public class IndexController {
    
    @RequestMapping("/")
    public String toIndex(){
        return "querypage/queryPage";
    }
}
