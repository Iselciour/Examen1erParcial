package datos;

import static datos.Conexion.*;
import domain.Persona;
import java.sql.*;
import java.util.*;

public class PersonaDAO {
    
    private Connection conTran;
    private static int countActualizarPersona = 0;
    private static int countEliminarPersona = 0;
    private static int countInsertarPersona = 0;
    
    //private static final int a = 3;
    private static final String SQL_SELECT = "SELECT idpersona, nombre, apellido, email, telefono FROM dabase.persona";
    private static final String SQL_INSERT = "INSERT INTO persona(nombre, apellido, email, telefono) VALUES(?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE persona SET nombre = ?, apellido = ?, email = ?, telefono = ? WHERE idpersona = ?";
    private static final String SQL_DELETE = "DELETE FROM persona WHERE idpersona=?";
    
    public PersonaDAO(){
        
    }
    
    public PersonaDAO(Connection conTran){
        this.conTran = conTran;
    }
    
    public List<Persona> seleccionar(){
        //Inicializar parametros de conexión
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Persona persona = null;
        
        List<Persona> personas = new ArrayList<>();
        
        try {
            conn = this.conTran != null ? this.conTran : getConnection();
            stmt  = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                int idPersona = rs.getInt("idpersona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                
                persona = new Persona(idPersona, nombre, apellido, email, telefono);
                
                personas.add(persona);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        finally{
            try {
                Conexion.close(rs);
                Conexion.close(stmt);
                if(this.conTran == null){
                    Conexion.close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
                
            }            
        }
        return personas;
    }
    
    public int insertar(Persona persona){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conTran != null ? this.conTran : getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, persona.getNombre());//?
            stmt.setString(2, persona.getApellido());//?
            stmt.setString(3, persona.getEmail());//...
            stmt.setString(4, persona.getTelefono());
            
            registros = stmt.executeUpdate();
            PersonaDAO.countInsertarPersona = countInsertarPersona++;
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                if(this.conTran == null){
                    Conexion.close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    public int actualizar(Persona persona){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0; 
        try {
            conn = this.conTran != null ? this.conTran : getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            stmt.setInt(5, persona.getIdPersona()); 
                        
            registros = stmt.executeUpdate(); 
            PersonaDAO.countActualizarPersona = countActualizarPersona++;
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                if(this.conTran == null){
                    Conexion.close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
        public int eliminar(Persona persona){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conTran != null ? this.conTran : getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);           
            stmt.setInt(1, persona.getIdPersona());
                        
            registros = stmt.executeUpdate();
            this.countEliminarPersona = countEliminarPersona++;
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                if(this.conTran == null){
                    Conexion.close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    
}