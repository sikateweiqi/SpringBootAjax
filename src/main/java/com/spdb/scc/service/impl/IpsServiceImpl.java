package com.spdb.scc.service.impl;

import com.spdb.scc.dao.IpsDao;
import com.spdb.scc.service.IpsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class IpsServiceImpl implements IpsService {

    @Autowired
    private IpsDao ipsDao;

    @Override
    public List<String> whiteList() {
        return ipsDao.whiteList();
    }
}
