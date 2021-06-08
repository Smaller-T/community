package com.tianye.community.community.model;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;  //对应githubUser的id
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
}
