package com.fundamentosplatzi.fundamentos.bean;

public class MyBeanWithPropertiesImplement implements MyBeanWithProperties{

    private String nombre;
    private String apellidos;

    public MyBeanWithPropertiesImplement(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    @Override
    public String function() {
        return nombre+"-"+apellidos;
    }
}
