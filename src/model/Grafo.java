/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Arthur
 */
public class Grafo {
    List<Vertice> listaVertices;
    
    public Grafo(){
        listaVertices = new ArrayList<Vertice>();
    }
    
    public void addVertice(){
        int idVertice = listaVertices.size();
        listaVertices.add(
            new Vertice("V"+Integer.toString(idVertice),
            100+idVertice, 
            100+idVertice, 20, 20));
    }
    
    public void addAresta(Vertice v1, Vertice v2){
        Aresta a = new Aresta(v1, v2, null);
        v1.addAresta(a);
        v2.addAresta(a);
    }
    
    public List<Vertice> getVertices(){
        return listaVertices;
    }
    
    /**
     * Adiciona várias arestas ao primeiro vértice
     * @param first
     * @param others 
     */
    public void addArestas(Vertice first, List<Vertice> others){
        for (Vertice outro : others){
            addAresta(first, outro);
        }
    }

    /**
     * Seleciona um vertice, liberando os demais
     * @param point 
     */
    public void selecionaVertice(Point point) {
        boolean jaSelecionou = false;
        for (Vertice v: listaVertices){
            if (v.contem(point) && !jaSelecionou){
                v.setSelecionado(true);
                jaSelecionou = true;
            }
            else 
                v.setSelecionado(false);
        }
    }
    
    /**
     * Seleciona mais de um vertice
     * @param point 
     */
    public void selecionaMaisUmVertice(Point point){
        for (Vertice v: listaVertices){
            if (v.contem(point))
                v.setSelecionado(true);
        }
    }

    public Queue<Vertice> getVerticesSelecionados() {
        Queue<Vertice> verticesSelecionados = new ArrayDeque<Vertice>();
        for (Vertice v: listaVertices){
            if (v.isSelecionado())
                verticesSelecionados.add(v);         
        }
        
        return verticesSelecionados;
    }
    
}
