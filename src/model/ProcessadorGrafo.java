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
        ArvoreGeradoraMinimaKruskal gerador = new ArvoreGeradoraMinimaKruskal();
        resultado = gerador.processarGrafo(grafoOriginal);
        configuraPosicoes();
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
            processado = false;
        } 
    }

    private void configuraPosicoes() {
        for (Vertice v : resultado.listaVertices){
            Vertice original = grafoOriginal.getVertice(v.nome);
            if (original != null)v.b = original.b;
            
            for (Aresta arestaInterna : v.getArestas()){
                Vertice original1 = grafoOriginal.getVertice(arestaInterna.v1.nome);
                if (original != null)arestaInterna.v1.b = original1.b;
                
                Vertice original2 = grafoOriginal.getVertice(arestaInterna.v2.nome);
                if (original2 != null) arestaInterna.v2.b = original2.b;
            }
            
        }
    }
}
