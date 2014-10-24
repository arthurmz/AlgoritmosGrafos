/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Arthur
 */
public class Aresta {
    public Vertice v1;
    public Vertice v2;
    
    public Integer peso;
    
    public Aresta(Vertice v1, Vertice v2, Integer peso){
        this.v1 = v1;
        this.v2 = v2;
        this.peso = peso;
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.drawLine(v1.b.x + (v1.b.width/2), 
                   v1.b.y + (v1.b.height/2),
                   v2.b.x + (v1.b.width/2),
                   v2.b.y + (v2.b.height/2));
    }
    
}
