/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.controladores.activosfijos;

import datos.daoimpl.CategoriaDAOImpl;
import datos.modelos.Categoria;
import java.util.List;
import negocio.interfaces.CategoriaDAO;

/**
 *
 * @author KAKU
 */
public class CategoriaController {
    private CategoriaDAO dao;
    
    public CategoriaController(){
        dao = new CategoriaDAOImpl();
    }
    
    public int registrar(String nombre, int cat_sup){
        Categoria cat = new Categoria();
        cat.setNombre(nombre);
        cat.setCodigo(nombre.substring(0,3).toUpperCase());
        cat.setCategoria_sup(cat_sup);
        return dao.registrarCategoria(cat);
    }
    
    public boolean editar(int id, String nuevoNombre, int cat_sup){
        Categoria cat = dao.getCategoria(id);
        if(cat != null){
            if(!nuevoNombre.equals("_")){
                cat.setNombre(nuevoNombre);
                cat.setCodigo(nuevoNombre.substring(0, 3).toUpperCase());
            }
            if(cat_sup > 0){
                cat.setCategoria_sup(cat_sup);
            }
        }
        return dao.actualizarCategoria(cat);
    }
    
    public boolean eliminar(int id){
        return dao.eliminarCategoria(id);
    }
    
    public List<Categoria> listarCategorias(){
        return dao.listarCategorias();
    }
    
    public Categoria getCategoria(int id){
        return dao.getCategoria(id);
    }
}
