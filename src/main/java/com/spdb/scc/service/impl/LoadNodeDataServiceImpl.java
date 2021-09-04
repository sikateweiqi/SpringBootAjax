package com.spdb.scc.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spdb.scc.dao.IpLogDao;
import com.spdb.scc.dao.LoadNodeDao;
import com.spdb.scc.entity.ClusterNodeMap;
import com.spdb.scc.entity.Nodes;
import com.spdb.scc.service.IpsService;
import com.spdb.scc.service.LoadNodeDataService;
import com.spdb.scc.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class LoadNodeDataServiceImpl implements LoadNodeDataService {

    @Autowired
    private LoadNodeDao loadNodeDao;

    @Autowired
    private IpsService ipsService;

    @Autowired
    private IpLogDao ipLogDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<ClusterNodeMap> loadAllNode() {
        List<ClusterNodeMap> list = new ArrayList<>();

        Object obj = redisTemplate.opsForValue().get(Constants.REDIS_CLUSTER_NODE_KEY);

//        list = (List<ClusterNodeMap>) redisTemplate.opsForValue().get(Constants.REDIS_CLUSTER_NODE_KEY);

        if (obj != null){
            //从redis获取;
            String redis_value = obj.toString();
            list = new Gson().fromJson(redis_value,new TypeToken<List<ClusterNodeMap>>(){}.getType());
            return list;
        }

        try {
            //从数据库获取并写入缓存
            list = loadNodeDao.loadAllNode();
            redisTemplate.opsForValue().set(Constants.REDIS_CLUSTER_NODE_KEY,new Gson().toJson(list), 5,TimeUnit.MINUTES);
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
        List<String> white_list = new ArrayList<>();

        addIpLog(ip);

        Object obj =  redisTemplate.opsForValue().get(Constants.REDIS_PWD_WHITE_LIST_KEY);
        if (obj == null){
            //从数据库获取;
            white_list = ipsService.whiteList();
            redisTemplate.opsForValue().set(Constants.REDIS_PWD_WHITE_LIST_KEY,new Gson().toJson(white_list), 5, TimeUnit.MINUTES);

        }else {
            //从redis获取
            String redis_value = obj.toString();
            white_list = new Gson().fromJson(redis_value,new TypeToken<List<String>>(){}.getType());
        }

        if (white_list == null || white_list.isEmpty()) {
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

    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }


}
