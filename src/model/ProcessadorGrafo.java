/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Arthur
 */
public class ProcessadorGrafo {
    Grafo grafoOriginal;
    Grafo resultado;
    boolean processado = false;

    public ProcessadorGrafo(Grafo grafo) {
        this.grafoOriginal = grafo;
    }

    public void processarArvoreGeradoraMinima() {
        resultado = ArvoreGeradoraMinimaKruskal.processarGrafo(grafoOriginal);
        processado = true;
    }

    /**
     * Desenha o grafo resultante do processamento no panel passado
     * @param panelOutputGrafo 
     */
    public void desenharGrafoResultante(Graphics g) {
        if (processado){
            for (Vertice v : resultado.getVertices()){
                for (Aresta a : v.getArestas()){
                    a.draw(g);
                }
            }
            for (Vertice v : resultado.getVertices()){
                v.draw(g);
            }
        } 
    }
}
