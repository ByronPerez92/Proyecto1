/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author byron
 */
public class Grafo {
    
    int n;
    int m;
    public int grafo[][];
    private String output;
    
    Grafo(int n, int m)
    {
        this.n = n;
        this.m = m;
    }
    
    public void createGrafo(int n, int m)
    {
        this.grafo = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.grafo[i][j] = 0;
            }
        }
    }
    
    public int[][] getGrafo()
    {
        return this.grafo;
    }
    
    public int getNumeroAristas()
    {
        int contador = 0;
        for (int i = 0; i < grafo.length; i++) {
            for (int j = 0; j < grafo[0].length; j++) {
                if (grafo[i][j] == 1) {
                    contador++;
                }
            }
        }
        return contador;
    }
    
    public void addEdge(int n1, int n2)
    {
        this.grafo[n1][n2] = 1;
    }
    
    public void printGrafo()
    {
        for (int[] grafo1 : this.grafo) {
            for (int j = 0; j < this.grafo[0].length; j++) {
                System.out.print(grafo1[j] + " ");
            }
            System.out.println("");
        }
    }
    
    public void genErdosRenyi(int n, int m, boolean dirigido, boolean auto)
    {
        createGrafo(n, n);
        int n1, n2;
        while (getNumeroAristas() < m) {
            n1 = (int) Math.floor(Math.random()*n); //número aleatorio entre 1 y n,
            n2 = (int) Math.floor(Math.random()*n); //pero no agrego el 1 por que el nodo 1 sería la posición 0
            if (n1 != n2) {
                if (!existeConexionNodos(n1, n2)) {
                    addEdge(n1, n2);
                    if (dirigido == false) {
                        addEdge(n2, n1);
                    }
                }
            }
        }
        //printGrafo();
        generarOutputGV();
        try {
            escribirArchivoGV("genErdosRenyi" + n, ".gv");
        } catch (IOException ex) {
            Logger.getLogger(Grafo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void genGilbert(int n, double p, boolean dirigido, boolean auto)
    {
        createGrafo(n, n);
        int n1, n2;
        for (int i = 0; i < m; i++) {
            n1 = (int) Math.floor(Math.random()*n); //número aleatorio entre 1 y n,
            n2 = (int) Math.floor(Math.random()*n); //pero no agrego el 1 por que el nodo 1 sería la posición 0
            if (Math.random() <= p) {
                addEdge(n1, n2);
                if (dirigido == false) {
                    addEdge(n2, n1);
                }
            }            
        }
        //printGrafo();
        generarOutputGV();
        try {
            escribirArchivoGV("genGilbert" + n, ".gv");
        } catch (IOException ex) {
            Logger.getLogger(Grafo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void genGeografico(int n, double r, boolean dirigido, boolean auto)
    {
        createGrafo(n, n);
        int n1, n2;
        double distanciaEuclidiana;
        for (int i = 0; i < m; i++) {
            n1 = (int) Math.floor(Math.random()*n); //número aleatorio entre 1 y n,
            n2 = (int) Math.floor(Math.random()*n); //pero no agrego el 1 por que el nodo 1 sería la posición 0
            distanciaEuclidiana = calcularDistanciaEntreVertices(n1, n2);
            if (distanciaEuclidiana <= r) {
                addEdge(n1, n1);
                if (dirigido == false) {
                    addEdge(n2, n1);
                }
            }            
        }
        //printGrafo();
        generarOutputGV();
        try {
            escribirArchivoGV("genGeografico" + n, ".gv");
        } catch (IOException ex) {
            Logger.getLogger(Grafo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void genBarabasiAlbert(int n, double d, boolean dirigido, boolean auto)
    {        
        createGrafo(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double p = 1 - (degV(i)/d);
                if (Math.random() <= p) {
                    if (!existeConexionNodos(i, j) && (degV(i) < d)) {
                        addEdge(i, j);
                        if (dirigido == false) {
                            addEdge(i, j);
                        }
                    }
                }
            }
        }
        //printGrafo();
        generarOutputGV();
        try {
            escribirArchivoGV("genBarabasiAlbert" + n, ".gv");
        } catch (IOException ex) {
            Logger.getLogger(Grafo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public double calcularDistanciaEntreVertices(int n1, int n2) {
      return Math.sqrt(Math.pow((n1 - n2), 2) + Math.pow((n2 - n1), 2));//(2,3)(3,2)
    }
    
    public int calculard(int n)
    {
      return (int)(Math.floor(Math.random()*n));
    }
    
    public int degV(int i) {
        int contador = 0;
        for (int j = 0; j < grafo[0].length; j++) {
            if (grafo[i][j] == 1)
            {
                contador++;
            }
        }
        return contador;
    }
    
    private Boolean existeConexionNodos(int i, int j) {
        return grafo[i][j] == 1 || grafo[j][i] == 1; //si alguna de las condiciones se cumple entonces regresa true, si no, entonces regresa false
    }
    
    private void generarOutputGV()
    {
        this.output = "graph {\n";
        for (int i = 0; i < grafo.length; i++) {
            output += "n" + i + ";\n";
        }
        
        for (int i = 0; i < grafo.length; i++) {
            for (int j = 0; j < grafo[0].length; j++) {
                if (this.grafo[i][j] == 1) {
                    output += "n" + i + " -- n" + j + ";\n";
                }
            }
        }
        output += "}\n";
    }
    
    public void escribirArchivoGV(String nombre, String extension) throws IOException
    {
        File file;
        FileWriter archivo = null;
        File folder = null;
        try {
            String rutaAbsoluta = new File("").getAbsolutePath();
            //System.out.println(rutaAbsoluta);
            folder = new File(rutaAbsoluta + "\\Archivos\\");
            if (!folder.exists())
            {
                folder.mkdirs();
            }
            String ruta = rutaAbsoluta + "\\Archivos\\" + nombre + extension;
            file = new File(ruta);
            archivo = new FileWriter(file, true);
            PrintWriter pw = new PrintWriter(archivo);
            //BufferedWriter bw;
            if(file.exists()) {
                //bw = new BufferedWriter(archivo);
                //bw.write("El fichero de texto ya estaba creado.");
                pw.println(this.output);
            }
            else {
                //bw = new BufferedWriter(archivo);
                //bw.write("Acabo de crear el fichero de texto.");
                pw.println(this.output);
            }
        }
        catch (IOException ex) {
            Logger.getLogger(Grafo.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
            if (null != archivo)
            {
                archivo.close();
            } 
           } catch (IOException e2) {
               System.out.println("Error: " + e2);
           }
        }
    }
}