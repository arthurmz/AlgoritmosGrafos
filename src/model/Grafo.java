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
 * Grafo implementado como uma lista de incidências 
 * (não confundir com lista de adjacências)
 * @author Arthur
 */
public class Grafo {
    List<Vertice> listaVertices;
    
    public Grafo(){
        listaVertices = new ArrayList<Vertice>();
    }
    
    /**
     * Adiciona um vértice ao grafo.
     * o nome do vértice é atribuido automaticamente.
     * @return 
     */
    public String addVertice(){
        int idVertice = 1+listaVertices.size();
        String nomeVertice = "V"+Integer.toString(idVertice);
        listaVertices.add(
            new Vertice(nomeVertice,
            100+idVertice, 
            100+idVertice, 20, 20));
        return nomeVertice;
    }
    
    /**
     * Adiciona uma aresta entre dois vértices
     * @param v1
     * @param v2
     * @param peso 
     */
    public void addAresta(Vertice v1, Vertice v2, int peso){
        Aresta a = new Aresta(v1, v2, peso);
        if (!existeAresta(a)){
            v1.addAresta(a);
            v2.addAresta(a);
        }
    }
    
    /**
     * Verifica se a aresta existe no grafo
     * não compara pelas instancias dos objetos
     * @param a
     * @return 
     */
    public boolean existeAresta(Aresta a) {
        List<Aresta> listaArestas = getArestas();
        return listaArestas.contains(a);
    }
    
    /**
     * Adiciona várias arestas ao primeiro vértice
     * @param first
     * @param others 
     */
    public void addArestas(Vertice first, List<Vertice> others, int peso){
        for (Vertice outro : others){
            addAresta(first, outro, peso);
        }
    }
    
    /**
     * Retorna a lista de vértices do grafo
     * @return 
     */
    public List<Vertice> getVertices(){
        return listaVertices;
    }
    
    /**
     * Retorna a lista de arestas do grafo
     * Pode ser otimizado caso leve em conta o momento em que uma
     * aresta é adicionada ou não.
     * @return 
     */
    public List<Aresta> getArestas(){
        List<Aresta> alist = new ArrayList<Aresta>();
        for (Vertice v : listaVertices){
            for (Aresta a : v.getArestas()){
                if (!alist.contains(a))
                    alist.add(a);
            }
        }
        return alist;
    }
    
    /**
     * Recupera um vértice à partir do nome
     * @param nomeVertice
     * @return 
     */
    public Vertice getVertice(String nomeVertice){
        for (Vertice v : listaVertices){
            if (v.nome.equals(nomeVertice))
                return v;
        }
        return null;
    }
    
    /**
     * Adiciona um vértice, caso ele não exista
     * @param v 
     */
    void addVertice(Vertice v) {
        if (getVertice(v.nome) == null);
        listaVertices.add(v);
    }
    
        /**
     * Remove o vértice v do grafo, junto com suas arestas adjacentes
     * @param v 
     */
    private void removeVertice(Vertice v) {
        List<Aresta> arestasVerticeRemover = v.getArestas();
        
        for (Vertice verticeVizinho : listaVertices){//para todos os vertices
            if (verticeVizinho == v) continue;//pula o próprio vertice
            
            Iterator arestasVerticeVizinho = verticeVizinho.getArestas().iterator();
            //Para cada aresta desse vertice
            while(arestasVerticeVizinho.hasNext()){
                Aresta arestaVerticeVizinho = (Aresta)arestasVerticeVizinho.next();
                //se for igual a uma das a restas para remover, remove!
                for (Aresta arestaRemover : arestasVerticeRemover){
                    if (arestaRemover.equals(arestaVerticeVizinho))
                       arestasVerticeVizinho.remove();
                }
            }
        }
        listaVertices.remove(v);
    }
    
    
    //==========================Parte gráfica================================
    
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
    
     public String removerVerticesSelecionados() {
        StringBuilder s = new StringBuilder();
        Queue<Vertice> vertices = getVerticesSelecionados();
        for (Vertice v : vertices){
            removeVertice(v);
            s.append(v.nome + ", ");
        }
        return s.toString();
    }

    /**
     * Informa se uma das pontas da aresta A
     * pode ser conectada a algum dos vértices deste grafo.
     * @param a
     * @return 
     */
    public boolean admiteAresta(Aresta a) {
        if (a.v1.nome != null && a.v2.nome != null){
            for (Vertice v : getVertices()) {
                if (v.nome == null) continue;
                else if (a.v1.nome == v.nome || a.v2.nome == v.nome)
                    return true;
            }
        }
        return false;
        
    }

    boolean isEmpty() {
        if (getVertices().size() == 0)
            return true;
        return false;
    }
    
    /**
     * Imprime o grafo
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Aresta a : getArestas()){
            sb.append(a.toString() + " ");
        }
        return sb.toString();
    }
    
}
