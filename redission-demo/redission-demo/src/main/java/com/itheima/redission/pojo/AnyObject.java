package com.itheima.redission.pojo;


import lombok.*;

import java.io.Serializable;

/**
 * @ClassName AnyObject.java
 * @Description 如何对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AnyObject implements Serializable {

    private String id;

    private String name;

    private Integer age;

    private String address;
}
