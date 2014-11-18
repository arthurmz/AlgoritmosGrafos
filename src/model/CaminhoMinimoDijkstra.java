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
    Vertice origem;

    public CaminhoMinimoDijkstra(JanelaPrincipal janela) {
        this.janela = janela;
    }
    

    public Grafo processarGrafo(Grafo grafoOriginal, Vertice inicial, Vertice destino) {
        /** Setup */  
        origem = inicial;
        janela.println("Configurando as distancias para todos os vértices");
        for (Vertice v: grafoOriginal.getVertices()){
            v.setDistancia(Integer.MAX_VALUE);
            v.setVisitado(false);
            v.setAnterior(null);
        }
        origem.setDistancia(0);
        
        List<Vertice> listaVertices = grafoOriginal.getVertices();
        
        while (listaVertices.size() > 0){
            Collections.sort(listaVertices);
            Vertice u = listaVertices.get(0);
            listaVertices.remove(u);
            
            if (u == destino){
                Grafo g = new Grafo();
                
                Vertice atual = u;
                g.addVertice(atual);
                while (atual.getAnterior() != null){
                    g.addVertice(atual.getAnterior());
                    atual = atual.getAnterior();
                }
                return g;
            }
            
            for (Aresta a : u.getArestas()){
                Vertice vizinho  = a.getOposto(u);
                int dist = u.getDistancia() + a.peso;
                
                if (dist < vizinho.getDistancia()){
                    vizinho.setDistancia(dist);
                    vizinho.setAnterior(u);
                }
            }
        }
        return new Grafo();
    }
}
