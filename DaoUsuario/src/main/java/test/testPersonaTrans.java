package test;

import datos.Conexion;
import datos.PersonaDAO;
import domain.Persona;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class testPersonaTrans {
    public static void main(String[] args) {
        Connection conexion = null;
        
        Scanner lector = new Scanner(System.in);
            
            int i = 0;
            int opcionPer = 0; 
            int PerID = 0;
            String PerName = "", PerApellido = "", PerEmail = "", PerTel = "";
            
        do
        {
            System.out.println("Que desea realizar: ");
            System.out.println("1 - Agregar");
            System.out.println("2 - Actualizar");
            System.out.println("3 - Eliminar");
            System.out.println("4 - Regresar");
            opcionPer = lector.nextInt();
            i = opcionPer;

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
                    i = 4;
                    System.out.println("Saliendo...");
                break;

                default:
                    System.out.println("Ingrese un número valido");
                break;
            }
        }while(i != 4);
    }
}
