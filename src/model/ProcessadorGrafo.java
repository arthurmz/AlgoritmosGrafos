/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import gui.JanelaPrincipal;
import java.awt.Graphics;
import java.util.Queue;
import javax.swing.JPanel;

/**
 *
 * @author Arthur
 */
public class ProcessadorGrafo {
    Grafo grafoOriginal;
    Grafo resultado;
    boolean processado = false;
    JanelaPrincipal janela;
    

    public ProcessadorGrafo(Grafo grafo, JanelaPrincipal aThis) {
        this.grafoOriginal = grafo;
        janela = aThis;
    }

    public void processarArvoreGeradoraMinima() {
        ArvoreGeradoraMinimaKruskal gerador = new ArvoreGeradoraMinimaKruskal(janela);
        resultado = gerador.processarGrafo(grafoOriginal);
        configuraPosicoes();
        processado = true;
    }
    
    public void processarCaminhoMinimo(){
        janela.println("Processando o caminho mínimo entre dois vértices");
        if (grafoOriginal.isEmpty()){
            System.out.println();
            janela.println("Grafo vazio!");
        }
        else if (grafoOriginal.getVerticesSelecionados() == null
                || grafoOriginal.getVerticesSelecionados().size() != 2){
            janela.println("Selecione dois vértices do grafo!");
        }
        else {
            Queue<Vertice> verticesSelecionados = grafoOriginal.getVerticesSelecionados();
        
            Vertice first = verticesSelecionados.poll();
            Vertice end = verticesSelecionados.poll();
            janela.println("Vertice de origem "+first.nome);
            janela.println("Vertice de destino "+end.nome);
            
            CaminhoMinimoDijkstra gerador = new CaminhoMinimoDijkstra(janela);
            resultado = gerador.processarGrafo(grafoOriginal, first, end);
            configuraPosicoes();
            processado = true;
        }
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
