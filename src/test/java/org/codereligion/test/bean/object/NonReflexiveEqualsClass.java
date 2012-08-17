package org.codereligion.test.bean.object;

public class NonReflexiveEqualsClass {

    private String foo;

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    @Override
    public boolean equals(Object that) {
        return that != this && super.equals(that);
    }

}
