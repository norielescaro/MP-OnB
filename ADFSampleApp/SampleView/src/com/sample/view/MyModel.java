package com.sample.view;

public class MyModel {
   private String code;
   private String description;
   private Integer id  = 1;
   private MyAttribute attribute;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setAttribute(MyAttribute attribute) {
        this.attribute = attribute;
    }

    public MyAttribute getAttribute() {
        return attribute;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
