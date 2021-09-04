package com.spdb.scc.service.impl;

import com.spdb.scc.dao.IpLogDao;
import com.spdb.scc.dao.LoadNodeDao;
import com.spdb.scc.entity.ClusterNodeMap;
import com.spdb.scc.entity.Nodes;
import com.spdb.scc.service.IpsService;
import com.spdb.scc.service.LoadNodeDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class LoadNodeDataServiceImpl implements LoadNodeDataService {

    @Autowired
    private LoadNodeDao loadNodeDao;

    @Autowired
    private IpsService ipsService;

    @Autowired
    private IpLogDao ipLogDao;

    @Override
    public List<ClusterNodeMap> loadAllNode() {
        List<ClusterNodeMap> list = new ArrayList<>();
        try {
            list = loadNodeDao.loadAllNode();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Nodes> loadNodeByCluster(String cluster) {
        return null;
    }

    @Override
    public List<ClusterNodeMap> hidePWDByIp(List<ClusterNodeMap> node_list, String ip) {
        if (!isIpInWhiteList(ip)) {
            for (int i = 0; i < node_list.size(); i++) {
                node_list.get(i).setPwd("****");
                node_list.get(i).setUsr("****");
            }
        }

        return node_list;
    }

    private boolean isIpInWhiteList(String ip) {
        List<String> white_list = ipsService.whiteList();

        addIpLog(ip);

        if (white_list.isEmpty()) {
            return false;
        }

        if (white_list.contains(ip)) {
            return true;
        }

        return false;
    }

    private boolean addIpLog(String ip){
        return ipLogDao.insert(ip);
    }


}
