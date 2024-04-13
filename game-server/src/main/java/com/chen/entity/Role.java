package com.chen.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
public class Role implements Serializable {

    private String name;

    private int roleId;

    private int sex;

}
