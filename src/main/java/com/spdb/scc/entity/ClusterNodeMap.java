package com.spdb.scc.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClusterNodeMap {

    String cluster = "";

    Integer order = 0;

    String node_name = "";

    String pip = "";

    String oip = "";

    String usr = "";

    String pwd = "";
}
