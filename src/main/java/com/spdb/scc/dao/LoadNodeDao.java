package com.spdb.scc.dao;

import com.spdb.scc.entity.ClusterNodeMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoadNodeDao {
    List<ClusterNodeMap> loadAllNode() throws Exception;
}
