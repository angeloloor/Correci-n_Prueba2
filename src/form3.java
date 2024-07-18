import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class form3 extends JFrame {

    private JPanel panel3;
    private JButton BUSCARButton;
    private JTextField buscar;
    private JButton REGRESOAINGRESARButton;
    private JButton LogBack;
    private JLabel codigo;
    private JLabel nombre;
    private JLabel descripcion;
    private JLabel precio;
    private JLabel cantidad;
    private JLabel categoria;

    public form3(){
        setTitle("Login del sistema");
        setContentPane(panel3);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setMinimumSize(new Dimension(500,500));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


        REGRESOAINGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new form2();
                setVisible(false);
            }
        });

        LogBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new form1();
                setVisible(false);
            }
        });

        BUSCARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String url="jdbc:mysql://localhost:3306/productos_cp";
                String user="root";
                String password="123456";

                try (Connection connection= DriverManager.getConnection(url,user,password)){

                    String query="select * from producto where codigo_producto= '"+ buscar.getText()+"'";
                    Statement statement=connection.createStatement();
                    ResultSet resultSet=statement.executeQuery(query);

                    if (resultSet.next()){

                        System.out.println(resultSet.getString("codigo_producto"));
                        codigo.setText("Codigo: "+resultSet.getString("codigo_producto"));
                        nombre.setText("Nombre: "+resultSet.getString("nombre"));
                        descripcion.setText("Descripcion: "+resultSet.getString("descripcion"));
                        precio.setText("Precio: "+resultSet.getString("precio"));
                        cantidad.setText("Cantidad: "+resultSet.getString("cantidad"));
                        categoria.setText("Categoria: "+resultSet.getString("categoria"));


                    }else{
                        JOptionPane.showMessageDialog(null, "NO EXISTEN REGISTROS DEL PRODUCTO");

                    }

                } catch (SQLException e1) {
                    System.out.println(e1);
                }

            }
        });
    }
}
