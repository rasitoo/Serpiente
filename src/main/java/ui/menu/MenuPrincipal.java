package ui.menu;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Thread.sleep;

public class MenuPrincipal extends javax.swing.JFrame{

    public MenuPrincipal() {
        initComponents();



        NewJFrame1 y = new NewJFrame1();
        y.setVisible(true);
        y.setBounds(0, 0, 800, 800);

        this.add(y);

        NewJFrame x = new NewJFrame();
        x.setVisible(true);
        x.setBounds(0, 0, 800, 800);

        this.add(x);

    }

    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(474, 263));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 399, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }


    public static void main(String[] args) {

        {
            /* Set the Nimbus look and feel */
            //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
            /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
             * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
             */
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>

            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new Main().setVisible(true);
                }
            });
        }

        JFrame ventana= new JFrame ("Menú");
        ventana.setPreferredSize(new Dimension(500,320));
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*Estilo del cursor*/
        Toolkit tk = Toolkit.getDefaultToolkit();
        ImageIcon icon = new ImageIcon("cursor1.png");
        Cursor cursor = tk.createCustomCursor(icon.getImage(), new Point(0, 0), "Cursor personalizado");
        ventana.setCursor(cursor);

        ventana.setResizable(false); //para no poder modificar el tamaño de la pantalla
        ventana.pack();
        System.out.println("Se muestra la ventana ");
    }
}
