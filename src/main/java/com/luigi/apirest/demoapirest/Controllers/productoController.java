package com.luigi.apirest.demoapirest.Controllers;
//Acá se colocan todas las direcciones y urls para consumir la informacion que
//les damos

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luigi.apirest.demoapirest.Repositories.ProductoRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.luigi.apirest.demoapirest.Entities.Producto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/productos")
public class productoController {

    @Autowired
    private ProductoRepository ProductoRepository;


    //METODO GET
    @GetMapping
    public List<Producto>getAllProductos(){
        return ProductoRepository.findAll();
    }
    
    //METODO POST
    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {        
        return ProductoRepository.save(producto);
    }

    //BUSCAMOS EL PRODUCTO POR ID
    @GetMapping("/{id}")
    public Producto getProductById(@RequestParam Long id) {
        return ProductoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el producto con ID:" + id));
    }
    


    //METODO PUT
    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto productoDetalles) {
        Producto producto = ProductoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con ID:" + id));
        //Obtenemos el producto con el id
        //modificamos el nombre y el precio
        //Lo devolvemos y lo guardamos en la base de datos

        producto.setNombre(productoDetalles.getNombre());
        producto.setPrecio(productoDetalles.getPrecio());

        return ProductoRepository.save(producto);
    }
    
    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Long id){
        Producto producto = ProductoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con ID:" + id));
        
        ProductoRepository.delete(producto);
        return "El producto con ID:" + id + " fue eliminado satisfactoriamente de la base de datos.";
    }


}
