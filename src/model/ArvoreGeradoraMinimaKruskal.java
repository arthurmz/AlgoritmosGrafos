/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Arthur
 */
public class ArvoreGeradoraMinimaKruskal {
    Grafo grafoOriginal;
    

    public Grafo processarGrafo(Grafo grafoOriginal) {
        /** Setup */
        this.grafoOriginal = grafoOriginal;
        LinkedList<Grafo> floresta = new LinkedList<Grafo>();
        for (Vertice v : grafoOriginal.getVertices()){
            Grafo g = new Grafo();
            g.addVertice(new Vertice(v.nome));
            floresta.add(g);
        }
        
        List<Aresta> listaArestas  = grafoOriginal.getArestas();
        Collections.sort(listaArestas);
        
        /**Inicio */
        for (Aresta a : listaArestas){
            List<Grafo> arvores = arvoresConectadasPelaAresta(a, floresta);
            if (arvores != null){
                floresta.removeAll(arvores);
                floresta.add(unirArvores(a, arvores));
            }
        }
        return floresta.getFirst();//espera que só tenha um grafo
    }
    
    /**
     * Retorna as arvores conectadas pela aresta
     * @param a
     * @param floresta
     * @return 
     */
    private List<Grafo> arvoresConectadasPelaAresta(Aresta a, List<Grafo>floresta){
        for (Grafo g1 : floresta){
            if (grafoOriginal.existeArestaExterna(a, g1.getVertices())){
                for (Grafo g2 : floresta){
                    if (g2 != g1 && grafoOriginal.existeArestaExterna(a, g2.getVertices())){
                        List<Grafo> arvores = new ArrayList<Grafo>();
                        arvores.add(g1);
                        arvores.add(g2);
                        return arvores;
                    }
                }
            }
        }
        return null;
    }

    private Grafo unirArvores(Aresta a, List<Grafo> arvores) {
        Grafo arvoreUniao = arvores.get(0);
        Grafo arvoreSecundaria = arvores.get(1);
        
        List<Vertice> vertices = new ArrayList<Vertice>(arvoreUniao.getVertices());
        for (Vertice v : vertices){
            if (a.v1.nome.equals(v.nome)){
                for (Vertice k : arvoreSecundaria.getVertices()){
                    arvoreUniao.addVertice(k);
                    if (a.v2.nome.equals(k.nome)){
                        arvoreUniao.addAresta(v, k, a.peso);
                    }
                }
            }
            else if (a.v2.nome.equals(v.nome)){
                for (Vertice k : arvoreSecundaria.getVertices()){
                    arvoreUniao.addVertice(k);
                    if (a.v1.nome.equals(k.nome)){
                        arvoreUniao.addAresta(k, v, a.peso);
                    } 
                }
            }
        }
        return arvoreUniao;
    }
    
}
