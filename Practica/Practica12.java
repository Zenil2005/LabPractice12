package mx.unam.fi.poo.g1.p12.Practica12;

import mx.unam.fi.poo.g1.p12.CuentaBanco.CuentaBanco;

/**
 * Clase principal de la práctica 12
 * 
 * @author Grupo 1 de POO
 * @version Noviembre de 2024
 */

public class Practica12 {

    /**
     * Metodo main
     * Se ejecuta todo el funcionamiento de la aplicación
     * 
     * @param args -> Arreglo por defecto del metodo main
     */
    public static void main(String[] args) {
        CuentaBanco cuenta = new CuentaBanco(1000);

        cuenta.consultarCuenta();

        Thread h1 = new Thread(() -> {
            cuenta.depositar(500);
            cuenta.retirar(300);
        });

        Thread h2 = new Thread(() -> {
            cuenta.depositar(600);
            cuenta.retirar(1000);
        });

        Thread h3 = new Thread(() -> {
            cuenta.depositar(10);
            cuenta.retirar(3000);
        });

        h1.start();
        h2.start();
        h3.start();
    }
}
