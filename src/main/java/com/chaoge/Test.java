package com.chaoge;

public class Test {
    public void pp(){
        String alias = this.getClass().getSimpleName();
        String NAME = this.getClass().getName();
        String CanonicalName = this.getClass().getCanonicalName();
        System.out.println(alias);
        System.out.println(NAME);
        System.out.println(CanonicalName);
    }
    public static void main(String[] args) {
        new Test().pp();
    }
}
