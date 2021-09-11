package test;


import datos.Conexion;
import datos.UsuarioDAO;
import datos.PersonaDAO;
import domain.Usuario;
import domain.Persona;
import java.sql.*;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class testUsuarioTrans {
    
    public static void main(String[] args) throws SQLException {
        Connection conexion = null;
        PersonaDAO personaDAO = new PersonaDAO(conexion);
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
            
        Scanner lector = new Scanner(System.in);

        int a = 0, b = 0, i = 0;
        int Ua = 0, Ue = 0, Ui = 0;
        int Pa = 0, Pe = 0, Pi = 0;
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
            System.out.println("* - Salir");
            opcionGen = lector.nextInt();
            a = opcionGen;
            System.out.println("\n");

            switch(opcionGen)
            {
                //Usuario
                case 1:
                    do
                    {
                        System.out.println("USUARIO");
                        System.out.println("Que desea realizar: ");
                        System.out.println("1 - Agregar");
                        System.out.println("2 - Actualizar");
                        System.out.println("3 - Eliminar");
                        System.out.println("4 - Contador");
                        System.out.println("5 - Listado");
                        System.out.println("6 - Regresar");
                        opcionUs = lector.nextInt();
                        i = opcionUs;

                        switch(opcionUs)
                        {
                            //Agregar Usuario
                            case 1:
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
                                    Ui++;

                            break;

                            //Actualizar Usuario
                            case 2:
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
                                    usuarioDAO.actualizar(actualizarUsuario);
                                    System.out.println("Usuario actualizado correctamente..." + "\n");
                                    Ua++;

                            break;

                            //Eliminar Usuario
                            case 3:
                                    System.out.println("\nPara eliminar un Usuario, introduzca el ID:");
                                    System.out.println("ID:");
                                    UserID = lector.nextInt();
                                    Usuario usuarioEliminar = new Usuario(UserID);
                                    usuarioDAO.eliminar(usuarioEliminar);
                                    System.out.println("Usuario eliminando..." + "\n");
                                    Ue++;
                            break;

                            //Regresar
                            case 4:
                                    System.out.println("\nUsuarios insertados: " + Ui);
                                    System.out.println("\nUsuarios eliminados: " + Ue);
                                    System.out.println("\nUsuarios actualizados: " + Ua);
                                    System.out.println("\n");
                            break;
                            
                            case 5:
                                List<Usuario> usuarios = usuarioDAO.seleccionar();
                                usuarios.forEach(usuario -> {
                                System.out.println(usuario);
                                });
                                System.out.println("\n");
                            break;
                            
                            case 6:
                                    i = 6;
                                    System.out.println("\nSaliendo...\n");
                            break;

                            default:
                                System.out.println("\nSeleccione un numero valido...\n");
                            break;
                        }
                    }while(i != 6);
                break;

                //Persona
                case 2:
                    do
                    {
                        System.out.println("PERSONA");
                        System.out.println("Que desea realizar: ");
                        System.out.println("1 - Agregar");
                        System.out.println("2 - Actualizar");
                        System.out.println("3 - Eliminar");
                        System.out.println("4 - Contador");
                        System.out.println("5 - Listado");
                        System.out.println("6 - Regresar");
                        opcionPer = lector.nextInt();
                        b = opcionPer;

                        switch(opcionPer)
                        {
                            //Agregar Persona
                            case 1:
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
                                    Pi++;

                            break;

                            //Actualizar Persona
                            case 2:
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
                                    personaDAO.actualizar(actualizarPersona);
                                    System.out.println("Persona actualizada con exito..." + "\n");
                                    Pa++;
                            break;

                            //Eliminar Persona
                            case 3:
                                    System.out.println("\nPara eliminar una Persona, introduzca el ID:");
                                    System.out.println("ID: ");
                                    PerID = lector.nextInt();
                                    Persona PersonaEliminar = new Persona(PerID);
                                    personaDAO.eliminar(PersonaEliminar);
                                    System.out.println("Persona eliminada con exito..." + "\n");
                                    Pe++;

                            break;
                            //Contadores
                            case 4:
                                    System.out.println("\nPersonas insertados: " + Pi);
                                    System.out.println("\nPersonas eliminados: " + Pe);
                                    System.out.println("\nPersonas actualizados: " + Pa);
                                    System.out.println("\n");
                            break;
                            
                            case 5:
                                    List<Persona> personas = personaDAO.seleccionar();
                                    personas.forEach(persona -> {
                                    System.out.println(persona);
                                    });
                                    System.out.println("\n");
                            break;
                            
                            case 6:
                                    b = 6;
                                    System.out.println("\nSaliendo...\n");
                            break;

                            default:
                                System.out.println("\nSeleccione un numero valido...\n");
                            break;
                        }
                    }while(b != 6);
                break;
                    
                default:
                    System.out.println("Saliendo....");
                break;
            }  
        }while(a != 4);
    }   
}
