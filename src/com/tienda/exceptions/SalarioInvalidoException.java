package com.tienda.exceptions;

public class SalarioInvalidoException extends Exception {
    public SalarioInvalidoException(double salario) {
        super("Salario invalido: " + salario + ". Debe ser mayor que 0.");
    }
}
