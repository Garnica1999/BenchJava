
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Carlos
 */
public class Main{

    public Main() {
        long numero = 45;
        
        ArrayList<Bench> threads = new ArrayList<>();
        for (int i = 0; i < Bench.getCores(); i++) {
            threads.add(new Bench(numero, "Hilo " + i));
            threads.get(i).start();
        }
        threads.forEach((hilo) -> {
            hilo.join();
        });
        
        this.calcularTiempos(threads);
        
    }
        
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main m = new Main();
    }

    private void calcularTiempos(ArrayList<Bench> threads) {
        float time = 0;
        for(int i = 0 ; i < threads.size(); i++){
            System.out.println("Tiempo del hilo " + i + ": " + threads.get(i).getTime());
            time += threads.get(i).getTime();
        }
        time = time/Bench.getCores();
        System.out.println("Tiempo promedio: " + time);
    }
    
}
