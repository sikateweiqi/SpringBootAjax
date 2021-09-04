package com.spdb.scc.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IpsDao {
    List<String> whiteList();
}
