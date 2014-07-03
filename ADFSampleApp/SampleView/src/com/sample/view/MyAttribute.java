package com.sample.view;

public class MyAttribute {
   private String code;

    public MyAttribute(String code) {
        super();
        this.code = code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof MyAttribute)) {
            return false;
        }
        final MyAttribute other = (MyAttribute)object;
        if (!(code == null ? other.code == null : code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 37;
        int result = 1;
        result = PRIME * result + ((code == null) ? 0 : code.hashCode());
        return result;
    }
}
