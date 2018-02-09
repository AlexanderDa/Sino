/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.dao.pruebas;

import ec.edu.sino.dao.metodos.MAdministrador;
import ec.edu.sino.negocios.entidades.Administrador;
import java.util.List;

/**
 *
 * @author paul
 */
public class TAdministrador {
    
    public static void main(String[] args) throws Exception {
        
        Administrador admin = new Administrador(38,"alexgualli38", "alex", "Alex", "Gualli");
        
        MAdministrador dao = new MAdministrador();
        
        try {            
            if(dao.insertar(admin)>0){
                System.out.println("INSERTADO CORRECTAMENTE");                
            }
            else{
                System.out.println("ERROR AL INSERTAR");
            }            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
        
        admin= new Administrador(38, "paul","paul", "paul");
        
        try {         
            
            if(dao.modificar(admin)>0){
                System.out.println("MODIFICADO CORRECTAMENTE");                
            }
            else{
                System.out.println("ERROR AL MODIFICADO");
            }            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        admin = new Administrador(38, "pancho");
        
        try {            
            if(dao.modificarClave(admin)>0){
                System.out.println("CLAVE MODIFICADA CORRECTAMENTE");                
            }
            else{
                System.out.println("ERROR AL MODIFICAR CLAVE");
            }            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    /* NO OBTIENE LOS DATOS
        admin = dao.obtener("paul", "pancho");
        if(admin != null){
            System.out.println("Nombre: " + admin.getNombre());
            System.out.println("Apellido: " + admin.getApellido());
            System.out.println("Usuario: " + admin.getUsuario());
        }
    */
    
        List<Administrador> lista = dao.obtener();
        for(Administrador doc : lista){
            System.out.println("Nombre: " + doc.getNombre());
            System.out.println("Apellidos: " + doc.getApellido());
        }
        
        try {            
            if(dao.eliminar(admin)>0){
                System.out.println("ELIMINADO CORRECTAMENTE");                
            }
            else{
                System.out.println("ERROR AL ELIMINAR");
            }            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
        
        
          
            
            
    }
    
}
