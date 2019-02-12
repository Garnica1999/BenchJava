
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Carlos
 */
public class Bench implements Runnable{
    private Thread hilo;
    private final long numero;
    private float time;
    private String nameThread;

    public Bench(long numero, String nameThread) {
        this.numero = numero;
        this.nameThread = nameThread;
    }
    
    
    public BigInteger fibonacci(long n) {
        if(n == 0){
            return BigInteger.ZERO;
        } else if (n == 1){
            return BigInteger.ONE;
        }
        return fibonacci(n - 2).add(fibonacci(n - 1));
    }
    
    public ArrayList<BigInteger> fibonacciIter(long n){
        ArrayList<BigInteger> fb = new ArrayList<>();
        fb.add(new BigInteger("0"));
        fb.add(new BigInteger("1"));
        for(int i = 0 ; i < n ; i++){
            BigInteger ant1 = fb.get(fb.size()-2);
            BigInteger ant2 = fb.get(fb.size()-1);
            fb.add(ant1.add(ant2));
        }
        return fb;
    }

    @Override
    public void run() {
        float inicio = Float.parseFloat(System.currentTimeMillis() + "");
        System.out.println(new BigInteger(System.currentTimeMillis() + ""));
        //while(true){
            for(int i = 0 ; i < numero; i++){
                this.fibonacci(i);
            }
        //}
        System.out.println(new BigInteger(System.currentTimeMillis() + ""));
        float actual = Float.parseFloat(System.currentTimeMillis() + "");
        this.time = actual - inicio;
    }
    
    public void start(){
        hilo = new Thread(this, this.nameThread);
        System.out.println("Iniciando hilo " + nameThread);
        hilo.start();
        //hilo.join();
    }
    @Deprecated
    public void stop(){
        if(hilo != null){
            hilo.stop();
        }
    }
    
    public static int getCores(){
        return Runtime.getRuntime().availableProcessors();
    }

    public float getTime() {
        return time;
    }
    
    public void join(){
        try {
            this.hilo.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Bench.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
