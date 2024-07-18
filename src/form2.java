import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class form2 extends JFrame{

    private JPanel panel2;
    private JButton INGRESARButton;
    private JButton buscarProductoButton;
    private JTextField nombretxt;
    private JTextField codtxt;
    private JTextField descriptxt;
    private JTextField preciotxt;
    private JTextField cantidadtxt;
    private JTextField categoriatxt;

    public form2(){
        setTitle("Login del sistema");
        setContentPane(panel2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setPreferredSize(new Dimension(400,400));
        setMinimumSize(new Dimension(400,400));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


        INGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String url = "jdbc:mysql://localhost:3306/productos_cp";
                String user = "root";
                String password = "123456";

                if (codtxt.getText().equals("") || nombretxt.getText().equals("") || descriptxt.getText().equals("") || preciotxt.getText().equals("") || cantidadtxt.getText().equals("") || categoriatxt.getText().equals("")) {

                    JOptionPane.showMessageDialog(null, "LLENE TODOS LOS DATOS");
                    return;
                }

                productos nuevo = new productos(codtxt.getText(),categoriatxt.getText(),Integer.parseInt(cantidadtxt.getText()),Double.parseDouble(preciotxt.getText()),descriptxt.getText(),nombretxt.getText());

                String sql = "insert into producto(codigo_producto,nombre,descripcion,precio,cantidad,categoria) values(?,?,?,?,?,?)";


                try (Connection connection = DriverManager.getConnection(url,user,password)){
                    PreparedStatement cadenaPreparada = connection.prepareStatement(sql);

                    cadenaPreparada.setString(1,nuevo.getCodigo());
                    cadenaPreparada.setString(2,nuevo.getNombre());
                    cadenaPreparada.setString(3,nuevo.getDescripcion());
                    cadenaPreparada.setDouble(4,nuevo.getPrecio());
                    cadenaPreparada.setInt(5,nuevo.getCantidad());
                    cadenaPreparada.setString(6,nuevo.getCategoria());

                    cadenaPreparada.executeUpdate();

                    System.out.println("Datos insertados correctamente");

                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });

        buscarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new form3();
                setVisible(false);

            }
        });
    }

}