/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
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
    
    
    public String addVertice(){
        int idVertice = listaVertices.size();
        String nomeVertice = "V"+Integer.toString(idVertice);
        listaVertices.add(
            new Vertice(nomeVertice,
            100+idVertice, 
            100+idVertice, 20, 20));
        return nomeVertice;
    }
    
    public void addAresta(Vertice v1, Vertice v2){
        Aresta a = new Aresta(v1, v2, null);
        if (!existeAresta(a)){
            v1.addAresta(a);
            v2.addAresta(a);
        }
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
    
    public List<Vertice> getVertices(){
        return listaVertices;
    }
    
    public List<Aresta> getArestas(){
        List<Aresta> alist = new ArrayList<Aresta>();
        for (Vertice v : listaVertices){
            for (Aresta a : v.getArestas()){
                alist.add(a);
            }
        }
        return alist;
    }
    
    private void removeVertice(Vertice v) {
        List<Aresta> arestasAdjacentes = v.getArestas();
        
        for (Vertice verticeLocal : listaVertices){//para todos os vertices
            if (verticeLocal == v) continue;//pula o próprio vertice
            
            Iterator it = verticeLocal.getArestas().iterator();
            //Para cada aresta desse vertice
            while(it.hasNext()){
                Aresta arestaLocal = (Aresta)it.next();
                //se for igual a uma das a restas para remover, remove!
                for (Aresta arestaRemover : arestasAdjacentes){
                    if (arestaRemover.equals(arestaLocal))
                       it.remove();
                }
            }
        }
        listaVertices.remove(v);
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

    private boolean existeAresta(Aresta a) {
        List<Aresta> listaArestas = getArestas();
        return listaArestas.contains(a);
    }

    public String removerVerticesSelecionados() {
        StringBuilder s = new StringBuilder();
        Queue<Vertice> vertices = getVerticesSelecionados();
        for (Vertice v : vertices){
            removeVertice(v);
            s.append(v.nome + ", ");
        }
        return s.toString();
    }
    
}
