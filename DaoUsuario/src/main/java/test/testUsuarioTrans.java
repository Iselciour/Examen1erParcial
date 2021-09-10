package test;


import datos.Conexion;
import datos.UsuarioDAO;
import domain.Usuario;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class testUsuarioTrans {
    
    public static void main(String[] args) {
        Connection conexion = null;
            
            Scanner lector = new Scanner(System.in);
            
            int i = 0;
            int opcionUs = 0;
            int UserID = 0; 
            String UserName = "" ,UserContra = "";
            
            do
            {
                System.out.println("Que desea realizar: ");
                System.out.println("1 - Agregar");
                System.out.println("2 - Actualizar");
                System.out.println("3 - Eliminar");
                System.out.println("4 - Regresar");
                opcionUs = lector.nextInt();
                i = opcionUs;

                switch(opcionUs)
                {
                    //Agregar Usuario
                    case 1:
                        UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
                        try {
                            conexion = Conexion.getConnection();
                            if(conexion.getAutoCommit()){
                                conexion.setAutoCommit(false);// Para no hacer automaticamente el commit
                            }
                            System.out.println("\nVa a agregar un nuevo Usuario, proporcione lo siguiente:");
                            System.out.println("Usuario: ");
                            UserName = lector.next();
                            System.out.println("Contraseña: ");
                            UserContra = lector.next();
                            Usuario nuevaUsuario = new Usuario();
                            nuevaUsuario.setUsername(UserName);
                            nuevaUsuario.setPassw(UserContra);
                            usuarioDAO.insertar(nuevaUsuario);
                            System.out.println("Usuario agregado con exito..." + "\n");
                        } catch (SQLException ex) {
                            ex.printStackTrace(System.out);
                            System.out.println("Entramos al rollback - no fue posible realizar cambios");
                        try {
                            conexion.rollback();
                            } catch (SQLException ex1) {
                                ex1.printStackTrace(System.out);
                            }
                        }
                    break;

                    //Actualizar Usuario
                    case 2:
                        UsuarioDAO usuarioDAOO = new UsuarioDAO(conexion);
                        try {
                            conexion = Conexion.getConnection();
                            if(conexion.getAutoCommit()){
                                conexion.setAutoCommit(false);// Para no hacer automaticamente el commit
                            }
                            System.out.println("\nPara actualizar un Usuario, proporcione lo siguiente:");
                            System.out.println("ID:");
                            UserID = lector.nextInt();
                            System.out.println("Usuario:");
                            UserName = lector.next();
                            System.out.println("Contraseña:");
                            UserContra = lector.next();
                            Usuario actualizarUsuario = new Usuario();
                            actualizarUsuario.setIdUsuario(UserID);
                            actualizarUsuario.setUsername(UserName);
                            actualizarUsuario.setPassw(UserContra);
                            usuarioDAOO.actualizar(actualizarUsuario);
                            System.out.println("Usuario actualizado correctamente..." + "\n");
                        } catch (SQLException ex) {
                            ex.printStackTrace(System.out);
                            System.out.println("Entramos al rollback - no fue posible realizar cambios");
                        try {
                            conexion.rollback();
                            } catch (SQLException ex1) {
                                ex1.printStackTrace(System.out);
                            }
                        }
                    break;

                    //Eliminar Usuario
                    case 3:
                        UsuarioDAO usuarioDAOOO = new UsuarioDAO(conexion);
                        try {
                            conexion = Conexion.getConnection();
                            if(conexion.getAutoCommit()){
                                conexion.setAutoCommit(false);// Para no hacer automaticamente el commit
                            }
                            System.out.println("\nPara eliminar un Usuario, introduzca el ID:");
                            System.out.println("ID:");
                            UserID = lector.nextInt();
                            Usuario usuarioEliminar = new Usuario(UserID);
                            usuarioDAOOO.eliminar(usuarioEliminar);
                            System.out.println("Usuario eliminando..." + "\n");
                        } catch (SQLException ex) {
                            ex.printStackTrace(System.out);
                            System.out.println("Entramos al rollback - no fue posible realizar cambios");
                            try {
                                conexion.rollback();
                            } catch (SQLException ex1) {
                                ex1.printStackTrace(System.out);
                            }
                        } 
                    break;

                    //Regresar
                    case 4:
                        i = 4;
                        System.out.println("Saliendo...");
                    break;

                    default:
                        System.out.println("Ingrese un número valido");
                    break;
                }
            }while(i != 4);

            /*Usuario actualizarUsuario = new Usuario();
            actualizarUsuario.setIdUsuario(1);
            actualizarUsuario.setUsername("hello");
            actualizarUsuario.setPassw("35435345345");
            usuarioDAO.actualizar(actualizarUsuario);
            

            Usuario nuevaUsuario = new Usuario();
            nuevaUsuario.setUsername("Shakira");
            nuevaUsuario.setPassw("Piqué");
            usuarioDAO.insertar(nuevaUsuario);
           
            conexion.commit();
            System.out.println("Se realizaron correctamente las modificaciones en la BD");*/
            
    }
    
}
