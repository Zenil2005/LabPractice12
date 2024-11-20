package mx.unam.fi.poo.g1.p12.CuentaBanco;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * La clase CuentaBanco representa una cuenta de banco que permite
 * depositar, retirar y consultar fondos de manera segura en un entorno
 * multihilo utilizando un bloqueo.
 */
public class CuentaBanco {
    private double fondos;
    private final Lock lock = new ReentrantLock();

    /**
     * Método constructor
     * 
     * Crea una nueva cuenta de banco con el saldo inicial especificado.
     *
     * @param fondos -> El saldo inicial de la cuenta
     */
    public CuentaBanco(double fondos) {
        setFondos(fondos);
    }

    /**
     * Método set
     * 
     * Establece los fondos de la cuenta.
     *
     * @param fondos -> El nuevo saldo de la cuenta
     */
    public void setFondos(double fondos) {
        this.fondos = fondos;
    }

    /**
     * Método get
     * 
     * Obtiene los fondos actuales de la cuenta.
     *
     * @return -> El saldo actual de la cuenta
     */
    public double getFondos() {
        return fondos;
    }

    /**
     * Imprime el saldo actual de la cuenta en la consola.
     */
    public void consultarCuenta() {
        System.out.println("Los fondos disponibles en su cuenta son: " + this.getFondos() + "$.\n");
    }

    /**
     * Deposita una cantidad especificada en la cuenta de manera segura.
     *
     * @param cantidad -> La cantidad a depositar
     */
    public void depositar(double cantidad) {
        lock.lock();
        try {
            double sum = this.getFondos() + cantidad;
            setFondos(sum);
            System.out.println("Se han depositado " + cantidad + "$ exitosamente");
            System.out.println("El saldo actual después del depósito es: " + this.getFondos() + "$.\n");
        } finally {
            lock.unlock();
        }
    }

    /**
     * Retira una cantidad especificada de la cuenta de manera segura.
     * Si los fondos son insuficientes, se imprime un mensaje de error.
     *
     * @param cantidad -> La cantidad a retirar
     */
    public void retirar(double cantidad) {
        lock.lock();
        try {
            if (cantidad > this.getFondos()) {
                System.out.println(
                        "La cuenta no tiene los fondos suficientes para realizar el retiro de " + cantidad + "$.");
                System.out.println("Usted dispone de: " + this.getFondos() + "$ en su cuenta.\n");
            } else {
                double res = this.getFondos() - cantidad;
                setFondos(res);
                System.out.println("Se han retirado exitosamente los fondos.");
                System.out.println("Fondos retirados: " + cantidad + "$.");
                System.out.println("El saldo en la cuenta posterior al retiro es: " + this.getFondos() + "$.\n");
            }
        } finally {
            lock.unlock();
        }
    }
}
