/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arthur
 */
public class Vertice {
    
    public String nome;
    public Rectangle b;
    private boolean selecionado;
    private List<Aresta> arestas;
    
    /**
     * Cria um vértice não inicializado
     */
    public Vertice(){
        
    }
    
    public Vertice(String nome, int x, int y, int w, int h){
        this.nome = nome;
        b = new Rectangle(x,y,w,h);
        arestas = new ArrayList<Aresta>();
    }

    public Vertice(String nomeVertice) {
        this.nome = nomeVertice;
        arestas = new ArrayList<Aresta>();
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillArc(b.x, b.y, b.width, b.height, 0, 360);
        g.setColor(Color.black);
        g.drawArc(b.x, b.y, b.width, b.height, 0, 360);
        g.drawString(nome, b.x+5, b.y+15);
        if (selecionado)
            g.drawRect(b.x, b.y, b.width, b.height);
    }
    
    public boolean contem(Point p){
        return b.contains(p); 
    }
    
    public void setSelecionado(boolean s){
        selecionado = s;
    }
    
    public boolean isSelecionado(){
        return selecionado;
    }

    /**
     * Move na mesma direção do ponto
     * trata o ponto como um vetor com origem na posição atual
     * @param mousePt 
     */
    public void moveTo(Point delta) {
        b.x+=delta.x;
        b.y+=delta.y;
    }

    void addAresta(Aresta a) {
        arestas.add(a);
    }
    
    public List<Aresta> getArestas(){
        return arestas;
    }
    
    public void addArestas(List<Aresta> a){
        this.arestas.addAll(a);
    }

    boolean contemAresta(Aresta a) {
        for (Aresta arestaInterna: arestas){
            if (arestaInterna.v1.nome.equals(a.v1.nome)
               || arestaInterna.v2.nome.equals(a.v2.nome))
                return true;
        }
        return false;
    }

    /**
     * Copia as arestas criando novas instancias
     * @param arestasNovas 
     */
    void setArestas(List<Aresta> arestasNovas) {
        for (Aresta a : arestasNovas){
            Aresta nova = new Aresta();
            
            nova.v1 = new Vertice(a.v1.nome);
            nova.v2 = new Vertice(a.v2.nome);
            
            arestas.add(a);
        }
            
    }
}
