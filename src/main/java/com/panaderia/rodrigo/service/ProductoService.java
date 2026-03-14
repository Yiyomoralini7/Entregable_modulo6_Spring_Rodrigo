package com.panaderia.rodrigo.service;

import com.panaderia.rodrigo.model.Producto;
import com.panaderia.rodrigo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    // ❌ Retornaba void → el RestController necesita el objeto guardado con su ID
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto findById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public void delete(Long id) {
        productoRepository.deleteById(id);
    }

    // Métodos que faltaban y son usados por controladores y RestControllers
    public Producto update(Long id, Producto productoActualizado) {
        Producto existente = findById(id);
        existente.setNombre(productoActualizado.getNombre());
        existente.setCategoria(productoActualizado.getCategoria());
        existente.setPrecio(productoActualizado.getPrecio());
        existente.setStock(productoActualizado.getStock());
        existente.setDescripcion(productoActualizado.getDescripcion());
        existente.setDisponible(productoActualizado.getDisponible());
        return productoRepository.save(existente);
    }

    public List<Producto> findByCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    public List<Producto> findDisponibles() {
        return productoRepository.findByDisponibleTrue();
    }

    public List<Producto> findStockBajo() {
        return productoRepository.findByStockBajo();
    }

    public List<String> obtenerCategorias() {
        return productoRepository.findAllCategorias();
    }

    public long count() {
        return productoRepository.count();
    }
}