package com.spdb.scc.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Nodes {
    Integer id = 0;

    Integer cluster_id = 0;

    String node_name = "";

    String pip = "";

    String oip = "";

    String usr = "";

    String pwd = "";
}
