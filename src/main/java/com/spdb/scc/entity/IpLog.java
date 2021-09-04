package com.spdb.scc.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Table;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "ip_log")
public class IpLog {
    int id = 0;
    String ip = "";
    String login_tm = "";
}
