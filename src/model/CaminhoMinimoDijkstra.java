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
    

    public Grafo processarGrafo(Grafo grafoOriginal, Vertice first, Vertice end) {
        /** Setup */        
        janela.println("Configurando as distancias para todos os vértices");
        for (Vertice v: grafoOriginal.getVertices()){
            v.setDistancia(Integer.MAX_VALUE);
            v.setVisitado(false);
        }
        
        
        
        return new Grafo();
    }
}
