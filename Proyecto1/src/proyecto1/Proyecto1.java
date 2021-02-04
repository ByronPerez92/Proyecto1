/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author byron
 */
public class Proyecto1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        Scanner scan = new Scanner(System.in);
        int n, m;
        double p = Math.random();
        String dir;
        boolean dirigido = true, auto = true;
        
        System.out.println("Ingresa el número n de nodos:");
        n = scan.nextInt();
        System.out.println("Ingresa el número m de pares de aristas");
        m = scan.nextInt();

        /*
        System.out.println("¿Desea que el grafo sea dirigido?(y/n)");
        dir = scan.nextLine();
        dir = dir.trim();
        dir = dir.toLowerCase();
        while(!dir.equals("y") && !dir.equals("n"))
        {
            System.out.println("Lo anterior no es una respuesta válida, intente nuevamente con y/n:");
            dir = scan.nextLine();
            dir = dir.trim();
            dir = dir.toLowerCase();
        }
        
        switch (dir)
        {
            case "y":
                dirigido = true;
                break;
            case "n":
                dirigido = false;
        }
        */
        //System.out.println("----------------------------------------------------");
        Grafo g = new Grafo(n, m);
        //g.createGrafo(n, n);
        g.genErdosRenyi(n, m, dirigido, auto);
        //g.printGrafo();
        //System.out.println("----------------------------------------------------");
        //g.createGrafo(n, n);
        g.genGilbert(n, p, dirigido, auto);
        //g.printGrafo();
        //System.out.println("----------------------------------------------------");
        int n1 = (int) Math.floor(Math.random()*n); //número aleatorio entre 1 y n,
        int n2 = (int) Math.floor(Math.random()*n); //pero no agrego el 1 por que el nodo 1 sería la posición 0
        double r = g.calcularDistanciaEntreVertices(n1, n2);
        g.genGeografico(n, r, dirigido, auto);
        //System.out.println("----------------------------------------------------");
        int d = g.calculard(n);
        g.genBarabasiAlbert(n, d, dirigido, auto);

        //Grafo g = new Grafo(n, n);
        //g.escribirArchivoGV("prueba", ".txt");
        //String rutaAbsoluta = new File("").getAbsolutePath();
        //System.out.println(rutaAbsoluta + "\\Archivos\\archivo.gv");
    }
    
}
