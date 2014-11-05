/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Comparator;

/**
 *
 * @author Arthur
 */
public class Aresta implements Comparable<Aresta>{
    public Vertice v1;
    public Vertice v2;
    
    public int peso;
    
    public Aresta(Vertice v1, Vertice v2, int peso){
        this.v1 = v1;
        this.v2 = v2;
        this.peso = peso;
    }

    Aresta() {
        
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.drawLine(v1.b.x + (v1.b.width/2), 
                   v1.b.y + (v1.b.height/2),
                   v2.b.x + (v1.b.width/2),
                   v2.b.y + (v2.b.height/2));
        
        int pesoPosX = v1.b.x + ((v2.b.x - v1.b.x)/2);
        int pesoPosY = v1.b.y + ((v2.b.y - v1.b.y)/2);
        
        g.drawString(Integer.toString(peso), pesoPosX, pesoPosY);
    }
    
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Aresta){
            Aresta a = (Aresta)obj;
            if (a.v1 == this.v1 && 
                a.v2 == this.v2)
                return true;
        }
        return false;
        
    }


    @Override
    public int compareTo(Aresta a) {
        return this.peso - a.peso;
    }
    
}
