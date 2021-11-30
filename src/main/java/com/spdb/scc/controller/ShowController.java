package com.spdb.scc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/scc")
public class ShowController {


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        modelMap.put("msg", "SCC 集群台账信息");

        return "index";
    }


    //更具请求集群名称查询
}
