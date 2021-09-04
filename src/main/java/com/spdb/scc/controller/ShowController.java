package com.spdb.scc.controller;

import com.spdb.scc.entity.ClusterNodeMap;
import com.spdb.scc.service.IpsService;
import com.spdb.scc.service.LoadNodeDataService;
import com.spdb.scc.utils.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/scc")
public class ShowController {

    @Autowired
    private LoadNodeDataService loadNodeDataService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        modelMap.put("msg", "SCC 集群台账信息");

        return "index";
    }

    @RequestMapping(value = "/data", method = RequestMethod.POST)
    @ResponseBody
    public List<ClusterNodeMap> data(HttpServletRequest request) {
        String request_ip = IpUtil.getIpAddr(request);
        System.out.println(request_ip);
        List<ClusterNodeMap> node_list = loadNodeDataService.loadAllNode();
        return loadNodeDataService.hidePWDByIp(node_list,request_ip);
    }

    //更具请求集群名称查询
}
