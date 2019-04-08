/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.controladores.CategoriaController;

import datos.daoimpl.CategoriaDAOImpl;
import datos.modelos.Categoria;
import java.util.List;
import negocio.interfaces.CategoriaDAO;

/**
 *
 * @author ASUS
 */
public class CategoriaController {

    private CategoriaDAO dao;

    public CategoriaController() {
        dao = new CategoriaDAOImpl();
    }

    public int registrar(String nombre) {
        Categoria cat = new Categoria();
        cat.setNombre(nombre);
        cat.setCodigo(nombre.substring(0, 3).toUpperCase());
        cat.setCategoria_sup(0);
        return dao.registrarCategoria(cat);
    }

    public boolean editar(int id, String nombre) {
        Categoria categoria = dao.getCategoria(id);
        if (categoria != null) {
            categoria.setNombre(nombre);
            categoria.setCodigo(nombre.substring(0, 3).toUpperCase());
            return dao.actualizarCategoria(categoria);
        }
        return false;
    }

    public boolean eliminar(int id) {
        return dao.eliminarCategoria(id);
    }

    public List<Categoria> listarCategorias() {
        return dao.listarCategorias();
    }

    public Categoria getCategoria(int id) {
        return dao.getCategoria(id);
    }

    public int crearSubcategoria(int idCatSup, String subcat) {
        if (dao.getCategoria(idCatSup) != null) {
            Categoria cat = new Categoria();
            cat.setNombre(subcat);
            cat.setCategoria_sup(idCatSup);
            cat.setCodigo(subcat.substring(0, 3));
            return dao.registrarCategoria(cat);
        }
        return -1;
    }

    public boolean agregarSubcategoria(int idCategoria, int idSub) {
        if (dao.getCategoria(idSub) != null && dao.getCategoria(idCategoria) != null) {
            Categoria cat = dao.getCategoria(idSub);
            cat.setCategoria_sup(idCategoria);
            return dao.actualizarCategoria(cat);
        }
        return false;
    }

    public boolean quitarSubcategoria(int idSup, int idSub) {
        if (dao.getCategoria(idSup) != null && dao.getCategoria(idSub) != null && dao.getCategoria(idSub).getCategoria_sup() == idSup) {
            Categoria cat = getCategoria(idSub);
            cat.setCategoria_sup(-1);
            return dao.actualizarCategoria(cat);
        }
        return false;
    }

}
