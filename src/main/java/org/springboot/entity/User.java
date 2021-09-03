package org.springboot.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    Integer id;

    String name;

    String sex;

    Integer age;

    String role;
}
