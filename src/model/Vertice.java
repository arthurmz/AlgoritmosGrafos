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
    
    public Vertice(String nome, int x, int y, int w, int h){
        this.nome = nome;
        b = new Rectangle(x,y,w,h);
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
}
