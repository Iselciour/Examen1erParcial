package test;


import datos.Conexion;
import datos.UsuarioDAO;
import datos.PersonaDAO;
import domain.Usuario;
import domain.Persona;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class testUsuarioTrans {
    
    public static void main(String[] args) {
        Connection conexion = null;
            
            Scanner lector = new Scanner(System.in);
            
            int a = 0, b = 0, i = 0;
            int opcionGen = 0, opcionUs = 0, opcionPer = 0;
            int UserID = 0; 
            int PerID = 0;
            String UserName = "" ,UserContra = "";
            String PerName = "", PerApellido = "", PerEmail = "", PerTel = "";
            do
            {
                System.out.println("Acceder a: ");
                System.out.println("1 - Usuarios");
                System.out.println("2 - Personas");
                System.out.println("3 - Salir");
                opcionGen = lector.nextInt();
                a = opcionGen;
                
                switch(opcionGen)
                {
                    //Usuario
                    case 1:
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
                                    System.out.println("Saliendo al menu...");
                                break;
                    
                                default:
                                    System.out.println("Ingrese un número valido");
                                break;
                            }
                        }while(i != 4);
                    break;
                    
                    //Persona
                    case 2:
                        do
                        {
                            System.out.println("Que desea realizar: ");
                            System.out.println("1 - Agregar");
                            System.out.println("2 - Actualizar");
                            System.out.println("3 - Eliminar");
                            System.out.println("4 - Regresar");
                            opcionPer = lector.nextInt();
                            b = opcionPer;
            
                            switch(opcionPer)
                            {
                                //Agregar Persona
                                case 1:
                                    PersonaDAO personaDAO = new PersonaDAO(conexion);
                                    try {
                                        conexion = Conexion.getConnection();
                                        if(conexion.getAutoCommit()){
                                            conexion.setAutoCommit(false);// Para no hacer automaticamente el commit
                                        }
                                        System.out.println("\nVa a agregar una nueva Persona, proporcione lo siguiente:");
                                        System.out.println("Nombre: ");
                                        PerName = lector.next();
                                        System.out.println("Apellido: ");
                                        PerApellido = lector.next();
                                        System.out.println("Email: ");
                                        PerEmail = lector.next();
                                        System.out.println("Telefono: ");
                                        PerTel = lector.next();
                                        Persona nuevaPersona = new Persona();
                                        nuevaPersona.setNombre(PerName);
                                        nuevaPersona.setApellido(PerApellido);
                                        nuevaPersona.setEmail(PerEmail);
                                        nuevaPersona.setTelefono(PerTel);
                                        personaDAO.insertar(nuevaPersona);
                                        System.out.println("Persona agregadada con exito..." + "\n");
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
                    
                                //Actualizar Persona
                                case 2:
                                    PersonaDAO personaDAOO = new PersonaDAO(conexion);
                                    try {
                                        conexion = Conexion.getConnection();
                                        if(conexion.getAutoCommit()){
                                            conexion.setAutoCommit(false);// Para no hacer automaticamente el commit
                                        }
                                        System.out.println("\nPara actualizar una Persona, proporcione lo siguiente:");
                                        System.out.println("ID: ");
                                        PerID = lector.nextInt();
                                        System.out.println("Nombre: ");
                                        PerName = lector.next();
                                        System.out.println("Apellido: ");
                                        PerApellido = lector.next();
                                        System.out.println("Email: ");
                                        PerEmail = lector.next();
                                        System.out.println("Telefono: ");
                                        PerTel = lector.next();
                                        Persona actualizarPersona = new Persona();
                                        actualizarPersona.setIdPersona(PerID);
                                        actualizarPersona.setNombre(PerName);
                                        actualizarPersona.setApellido(PerApellido);
                                        actualizarPersona.setEmail(PerEmail);
                                        actualizarPersona.setTelefono(PerTel);
                                        personaDAOO.actualizar(actualizarPersona);
                                        System.out.println("Persona actualizada con exito..." + "\n");
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
                    
                                //Eliminar Persona
                                case 3:
                                    PersonaDAO personaDAOOO = new PersonaDAO(conexion);
                                    try {
                                        conexion = Conexion.getConnection();
                                        if(conexion.getAutoCommit()){
                                            conexion.setAutoCommit(false);// Para no hacer automaticamente el commit
                                        }
                                        System.out.println("\nPara eliminar una Persona, introduzca el ID:");
                                        System.out.println("ID: ");
                                        PerID = lector.nextInt();
                                        Persona PersonaEliminar = new Persona(PerID);
                                        personaDAOOO.eliminar(PersonaEliminar);
                                        System.out.println("Persona eliminada con exito..." + "\n");
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
                                    b = 4;
                                    System.out.println("Saliendo al menu...");
                                break;
                    
                                default:
                                    System.out.println("Ingrese un número valido");
                                break;
                            }
                        }while(b != 4);
                    break;
                    
                    //Salir
                    case 3:
                        a = 3;
                        System.out.println("Finalizando...");
                    break;
                }            
            }while(a != 3);

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
