import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class form1 extends JFrame {


    private JPanel mainPanel;
    private JButton LOGINButton;
    private JTextField contratxt;
    private JTextField usuariotxt;


    public form1() {

        setTitle("Login del sistema");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setPreferredSize(new Dimension(300,300));
        setMinimumSize(new Dimension(300,300));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String url="jdbc:mysql://localhost:3306/productos_cp";
                String user="root";
                String password="123456";

                try (Connection connection= DriverManager.getConnection(url,user,password)){

                    String query="select * from usuario where username= '"+ usuariotxt.getText()+"' and contrasena = '"+contratxt.getText()+"'";
                    Statement statement=connection.createStatement();
                    ResultSet resultSet=statement.executeQuery(query);

                    if (resultSet.next()){

                        if(resultSet.getString("username").equals(usuariotxt.getText()) && resultSet.getString("contrasena").equals(contratxt.getText())){
                            JOptionPane.showMessageDialog(null, "ACCESO CONCEDIDO");
                            new form2();
                            setVisible(false);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "ACCESO DENEGADO");

                    }

                } catch (SQLException e1) {
                    System.out.println(e1);
                }



            }
        });

    }
}
