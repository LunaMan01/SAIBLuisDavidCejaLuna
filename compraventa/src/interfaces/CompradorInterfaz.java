package interfaces;

import comprador.Comprador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CompradorInterfaz extends JFrame {
    Comprador myComprador;
    private JTextField libroComprar;

    public CompradorInterfaz(Comprador comprador) {
        super(comprador.getLocalName());

        myComprador = comprador;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(new JLabel("Nombre del comprador: "));
        panel.add(new JLabel(myComprador.getAID().getName()));

        panel.add(new JLabel("Libro que quiere comprar: "));
        libroComprar = new JTextField();
        panel.add(libroComprar);


        getContentPane().add(panel, BorderLayout.CENTER);

        JButton comprarButton = new JButton("Comprar");
        comprarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    String libroC = libroComprar.getText().trim();

                    Object[] objects = new Object[1];
                    objects[0] = libroC;
                    myComprador.setLibroC(objects);
                    myComprador.isReady();

                    libroComprar.setText("");
                }catch(Exception e) {
                    JOptionPane.showMessageDialog(CompradorInterfaz.this, "Invalid values","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel = new JPanel();
        panel.add(comprarButton);
        getContentPane().add(panel, BorderLayout.SOUTH);

        System.out.println(myComprador.getTitulo() + "WWWWWWWWWW");

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                myComprador.doDelete();
            }
        });
    }

    public void mostrar() {
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int)screenSize.getWidth() / 2;
        int centerY = (int)screenSize.getHeight() / 2;

        setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
        super.setVisible(true);
    }
}
