package com.spdb.scc.service;

import com.spdb.scc.entity.ClusterNodeMap;
import com.spdb.scc.entity.Nodes;

import java.util.List;

public interface LoadNodeDataService {
    List<ClusterNodeMap> loadAllNode();

    List<Nodes> loadNodeByCluster(String cluster);

    List<ClusterNodeMap> hidePWDByIp(List<ClusterNodeMap> node_list,String ip);
}
