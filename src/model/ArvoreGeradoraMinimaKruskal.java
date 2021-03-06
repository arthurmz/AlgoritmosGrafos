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

/**
 *
 * @author Arthur
 */
public class ArvoreGeradoraMinimaKruskal {
    Grafo grafoOriginal;
    JanelaPrincipal janela;

    ArvoreGeradoraMinimaKruskal(JanelaPrincipal janela) {
        this.janela = janela;
    }
    

    public Grafo processarGrafo(Grafo grafoOriginal) {
        /** Setup */
        janela.println("Processando a arvore geradora mínima usando kruskal");
        if (grafoOriginal.isEmpty()){
            System.out.println();
            janela.println("Grafo vazio!");
            return grafoOriginal;
        }
        
        janela.println("Dividindo o grafo em " + grafoOriginal.getVertices().size() + " florestas");
        this.grafoOriginal = grafoOriginal;
        LinkedList<Grafo> floresta = new LinkedList<Grafo>();
        for (Vertice v : grafoOriginal.getVertices()){
            Grafo g = new Grafo();
            g.addVertice(new Vertice(v.nome));
            floresta.add(g);
        }
        
        List<Aresta> listaArestas  = grafoOriginal.getArestas();
        janela.println("Ordenando as arestas de forma crescente");
        Collections.sort(listaArestas);
        
        /**Inicio */
        for (Aresta a : listaArestas){
            List<Grafo> arvores = arvoresConectadasPelaAresta(a, floresta);
            if (arvores != null){
                janela.println("Aresta " + a.toString() + " conectando duas florestas diferentes, serão unidas");
                floresta.removeAll(arvores);
                floresta.add(unirArvores(a, arvores));
            }
            if (floresta.size() == 1) break;
        }
        janela.println("Fim da execução, árvore resultante:");
        janela.println(floresta.getFirst().toString());
        return floresta.getFirst();//espera que só tenha um grafo
    }
    
    /**
     * Verifica se a aresta conecta duas florestas diferentes, e caso positivo
     * retorna as duas.
     * @param a
     * @param floresta
     * @return 
     */
    private List<Grafo> arvoresConectadasPelaAresta(Aresta a, List<Grafo>floresta){
        for (Grafo g1 : floresta){
            if (g1.existeAresta(a)) continue;// se a aresta está dentro da floresta, então não olha mais pra ela
            for (Grafo g2 : floresta){
                if (g1 == g2) continue;
                else if (g1.admiteAresta(a) && g2.admiteAresta(a)){
                    List<Grafo> arvores = new ArrayList<Grafo>();
                        arvores.add(g1);
                        arvores.add(g2);
                        return arvores;
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
