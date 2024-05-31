package com.luigi.apirest.demoapirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luigi.apirest.demoapirest.Entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
