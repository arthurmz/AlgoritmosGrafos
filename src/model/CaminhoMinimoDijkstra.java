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
        
        List<Vertice> listaVertices = new ArrayList(grafoOriginal.getVertices());
        
        while (listaVertices.size() > 0){
            Collections.sort(listaVertices);
            Vertice u = listaVertices.get(0);
            listaVertices.remove(u);
            
            if (u == destino){
                Grafo g = new Grafo();
                
                Vertice atual = u;
                
                Vertice newAtual = new Vertice(atual.nome, atual.getDistancia());
                g.addVertice(newAtual);
                
                while (atual.getAnterior() != null){
                    Aresta anterior = atual.getAnterior();
                    Vertice vAnterior = anterior.getOposto(atual);
                    
                    Vertice newAnterior = new Vertice(vAnterior.nome, vAnterior.getDistancia());
                    
                    g.addVertice(newAnterior);
                    g.addAresta(newAtual, newAnterior, anterior.peso);
                    
                    atual = vAnterior;
                    newAtual = new Vertice(atual.nome, atual.getDistancia());

                }
                return g;
            }
            
            for (Aresta a : u.getArestas()){
                Vertice vizinho  = a.getOposto(u);
                if (vizinho.isVisitado()) continue;
                int dist = u.getDistancia() + a.peso;
                
                if (dist < vizinho.getDistancia()){
                    vizinho.setDistancia(dist);
                    vizinho.setAnterior(a);
                }
            }
            u.setVisitado(true);
        }
        return grafoOriginal;
    }
}
