/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import java.util.Queue;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import model.Aresta;
import model.Grafo;
import model.ProcessadorGrafo;
import model.Vertice;

/**
 *
 * @author Arthur
 */
public class JanelaPrincipal extends javax.swing.JFrame {
    
    private Grafo grafo;
    private boolean dragging = false;
    private boolean selecionando = false;
    private Point pontoAnterior = null;
    private ProcessadorGrafo processadorGrafo;

    /**
     * Creates new form JanelaPrincipal
     */
    public JanelaPrincipal() {
        clear();
        initComponents();
    }

    private void clear() {
        grafo = new Grafo();
        processadorGrafo = new ProcessadorGrafo(grafo, this);
    }
    
    private class MouseHandler extends MouseAdapter {
        
         @Override
        public void mouseReleased(MouseEvent e) {
            if (dragging){
                dragging = false;
                pontoAnterior = null;
            }
            
            e.getComponent().repaint();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.isShiftDown()) {
                grafo.selecionaMaisUmVertice(e.getPoint());
            }
            else{
                grafo.selecionaVertice(e.getPoint());
            }
        }
        
    }
    
    private class MouseMotionHandler extends MouseMotionAdapter {  
        @Override
        public void mouseDragged(MouseEvent e) {
            Point mousePt = e.getPoint();
           
            if (pontoAnterior == null)
                pontoAnterior = mousePt;
            
            Point delta = new Point();
            delta.x = mousePt.x - pontoAnterior.x;
            delta.y = mousePt.y - pontoAnterior.y;
            
            for (Vertice v : grafo.getVertices()){
                if (v.isSelecionado()) {
                    v.moveTo(delta);
                    e.getComponent().repaint();
                }
            }
            
            pontoAnterior = mousePt;
            dragging = true;
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDesenharGrafo = new javax.swing.JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Vertice v : grafo.getVertices()){
                    for (Aresta a : v.getArestas()){
                        a.draw(g);
                    }
                }
                for (Vertice v : grafo.getVertices()){
                    v.draw(g);
                }
            }
            {
                addMouseListener(new MouseHandler());
                addMouseMotionListener(new MouseMotionHandler());
            }
        };
        panelOutputGrafo = new javax.swing.JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                processadorGrafo.desenharGrafoResultante(g);
            }
        };
        jToolBar1 = new javax.swing.JToolBar();
        jButton3 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButton4 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButton1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        outputTextArea = new javax.swing.JTextArea();
        barraMenu = new javax.swing.JMenuBar();
        menuArquivo = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuAlgoritmos = new javax.swing.JMenu();
        itemMenuArvoreGeradoraMinima = new javax.swing.JMenuItem();
        itemMenuPlanaridadeGrafo = new javax.swing.JMenuItem();
        itemMenuCaminhoMinimo = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelDesenharGrafo.setBackground(new java.awt.Color(255, 255, 255));
        panelDesenharGrafo.setPreferredSize(new java.awt.Dimension(0, 260));

        javax.swing.GroupLayout panelDesenharGrafoLayout = new javax.swing.GroupLayout(panelDesenharGrafo);
        panelDesenharGrafo.setLayout(panelDesenharGrafoLayout);
        panelDesenharGrafoLayout.setHorizontalGroup(
            panelDesenharGrafoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDesenharGrafoLayout.setVerticalGroup(
            panelDesenharGrafoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );

        panelOutputGrafo.setBackground(new java.awt.Color(255, 255, 255));
        panelOutputGrafo.setPreferredSize(new java.awt.Dimension(0, 260));

        javax.swing.GroupLayout panelOutputGrafoLayout = new javax.swing.GroupLayout(panelOutputGrafo);
        panelOutputGrafo.setLayout(panelOutputGrafoLayout);
        panelOutputGrafoLayout.setHorizontalGroup(
            panelOutputGrafoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelOutputGrafoLayout.setVerticalGroup(
            panelOutputGrafoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );

        jToolBar1.setRollover(true);

        jButton3.setText("Add vertice");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);
        jToolBar1.add(jSeparator1);

        jButton4.setText("Remover vertice");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);
        jToolBar1.add(jSeparator3);

        jButton1.setText("Conectar");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);
        jToolBar1.add(jSeparator2);

        jButton2.setText("Limpar Tudo");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        outputTextArea.setBackground(new java.awt.Color(0, 0, 0));
        outputTextArea.setColumns(20);
        outputTextArea.setForeground(new java.awt.Color(255, 255, 255));
        outputTextArea.setLineWrap(true);
        outputTextArea.setRows(5);
        jScrollPane1.setViewportView(outputTextArea);

        menuArquivo.setText("Arquivo");

        jMenuItem1.setText("Sair");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuArquivo.add(jMenuItem1);

        barraMenu.add(menuArquivo);

        menuAlgoritmos.setText("Algoritmos");
        menuAlgoritmos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAlgoritmosActionPerformed(evt);
            }
        });

        itemMenuArvoreGeradoraMinima.setText("Arvore geradora mínima com Kruskal");
        itemMenuArvoreGeradoraMinima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuArvoreGeradoraMinimaActionPerformed(evt);
            }
        });
        menuAlgoritmos.add(itemMenuArvoreGeradoraMinima);

        itemMenuPlanaridadeGrafo.setText("Planaridade do grafo");
        menuAlgoritmos.add(itemMenuPlanaridadeGrafo);

        itemMenuCaminhoMinimo.setText("Caminho mínimo");
        itemMenuCaminhoMinimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuCaminhoMinimoActionPerformed(evt);
            }
        });
        menuAlgoritmos.add(itemMenuCaminhoMinimo);

        barraMenu.add(menuAlgoritmos);

        setJMenuBar(barraMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelDesenharGrafo, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
                            .addComponent(panelOutputGrafo, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDesenharGrafo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelOutputGrafo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void itemMenuArvoreGeradoraMinimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuArvoreGeradoraMinimaActionPerformed
        processadorGrafo.processarArvoreGeradoraMinima();
        this.panelOutputGrafo.repaint();
    }//GEN-LAST:event_itemMenuArvoreGeradoraMinimaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String nomeVertice = grafo.addVertice();
        repaint();
        println("Vertice " + nomeVertice + " adicionado.");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Integer peso = Integer.parseInt ((String)JOptionPane.showInputDialog(
                    this,
                    "Peso",
                    JOptionPane.PLAIN_MESSAGE));
        Queue<Vertice> l = grafo.getVerticesSelecionados();
        
        Vertice first = l.poll();
        StringBuilder s = new StringBuilder();
        s.append("Vertice(s) ");
        if (first != null){
            s.append(first.nome);
            for (Vertice v : l){
                grafo.addAresta(first, v, peso);
                s.append(", "+ v.nome);
            }
        }
        s.append(" Conectados(s)\n");
        println(s.toString());
        this.panelDesenharGrafo.repaint();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        clear();
        this.panelDesenharGrafo.repaint();
        this.panelOutputGrafo.repaint();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String vertices = grafo.removerVerticesSelecionados();
        println("Vertice(s) " + vertices + "Removido(s)");
        this.panelDesenharGrafo.repaint();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void menuAlgoritmosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAlgoritmosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuAlgoritmosActionPerformed

    private void itemMenuCaminhoMinimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuCaminhoMinimoActionPerformed
        processadorGrafo.processarCaminhoMinimo();
        this.panelOutputGrafo.repaint();
    }//GEN-LAST:event_itemMenuCaminhoMinimoActionPerformed

    public void println(String s){
        if (this.outputTextArea != null){
            this.outputTextArea.append(s+"\n");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JMenuItem itemMenuArvoreGeradoraMinima;
    private javax.swing.JMenuItem itemMenuCaminhoMinimo;
    private javax.swing.JMenuItem itemMenuPlanaridadeGrafo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenu menuAlgoritmos;
    private javax.swing.JMenu menuArquivo;
    private javax.swing.JTextArea outputTextArea;
    private javax.swing.JPanel panelDesenharGrafo;
    private javax.swing.JPanel panelOutputGrafo;
    // End of variables declaration//GEN-END:variables
}
