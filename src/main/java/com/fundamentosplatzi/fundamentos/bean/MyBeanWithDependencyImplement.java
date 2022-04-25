package com.fundamentosplatzi.fundamentos.bean;

import com.fundamentosplatzi.fundamentos.FundamentosApplication;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{

    private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
    private MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        LOGGER.info("Hola hemos ingresado al metodo prinWithDependency");
        int numero = 1;
        LOGGER.debug("El numero enviado como parametro a la dependencia operacion es : " + numero);
        System.out.println(myOperation.sum(numero));
        System.out.println("Hola desde la implementacion de un bean con dependencia");
    }
}
