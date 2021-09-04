package com.spdb.scc.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Table;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "ips")
public class Ips {
    int id;
    String ips = "";
    int pwd_readable = 0;
}
