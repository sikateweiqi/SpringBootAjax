package com.spdb.scc.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IpLogDao {
    boolean insert(String ip);
}
