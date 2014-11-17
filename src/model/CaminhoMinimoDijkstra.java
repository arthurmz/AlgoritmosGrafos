/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import gui.JanelaPrincipal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Arthur
 */
public class CaminhoMinimoDijkstra {
    Grafo grafoOriginal;
    JanelaPrincipal janela;
    Vertice first;
    Vertice end;

    public CaminhoMinimoDijkstra(JanelaPrincipal janela) {
        this.janela = janela;
    }
    

    public Grafo processarGrafo(Grafo grafoOriginal) {
        /** Setup */
        janela.println("Processando o caminho mínimo entre dois vértices");
        if (grafoOriginal.isEmpty()){
            System.out.println();
            janela.println("Grafo vazio!");
            return grafoOriginal;
        }
        
        if (grafoOriginal.getVerticesSelecionados() == null
                || grafoOriginal.getVerticesSelecionados().size() != 2){
            janela.println("Selecione dois vértices do grafo!");
            return grafoOriginal;
        }
        
        Queue<Vertice> verticesSelecionados = grafoOriginal.getVerticesSelecionados();
        
        first = verticesSelecionados.poll();
        end = verticesSelecionados.poll();
        janela.println("Vertice de origem "+first.nome);
        janela.println("Vertice de destino "+end.nome);
            
        
        janela.println("Configurando as distancias para todos os vértices");
        for (Vertice v: grafoOriginal.getVertices()){
            v.setDistancia(Integer.MAX_VALUE);
        }
        
        return new Grafo();
    }
}
