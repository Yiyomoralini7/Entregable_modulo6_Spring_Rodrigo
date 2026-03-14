package com.panaderia.rodrigo.service;

import com.panaderia.rodrigo.model.Pedido;
import com.panaderia.rodrigo.model.Pedido.EstadoPedido;
import com.panaderia.rodrigo.repository.ClienteRepository;
import com.panaderia.rodrigo.repository.PedidoRepository;
import com.panaderia.rodrigo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

// Estaba vacía → todos los controladores que inyectan PedidoService fallaban
@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Pedido findById(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + id));
    }

    public List<Pedido> findByEstado(EstadoPedido estado) {
        return pedidoRepository.findByEstado(estado);
    }

    public Pedido save(Pedido pedido) {
        // Resuelve el cliente completo desde el ID que llega del formulario
        if (pedido.getCliente() != null && pedido.getCliente().getId() != null) {
            pedido.setCliente(
                    clienteRepository.findById(pedido.getCliente().getId())
                            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"))
            );
        }
        // Resuelve los productos completos desde los IDs que llegan del formulario
        if (pedido.getProductos() != null && !pedido.getProductos().isEmpty()) {
            List<Long> ids = pedido.getProductos()
                    .stream().map(p -> p.getId()).toList();
            pedido.setProductos(productoRepository.findAllById(ids));
        }
        // Calcula el total sumando los precios de los productos
        if (pedido.getProductos() != null) {
            double total = pedido.getProductos()
                    .stream().mapToDouble(p -> p.getPrecio()).sum();
            pedido.setTotal(total);
        }
        return pedidoRepository.save(pedido);
    }

    public Pedido cambiarEstado(Long id, EstadoPedido estado) {
        Pedido pedido = findById(id);
        pedido.setEstado(estado);
        return pedidoRepository.save(pedido);
    }

    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }

    public long count() {
        return pedidoRepository.count();
    }

    public Long countByEstado(EstadoPedido estado) {
        return pedidoRepository.countByEstado(estado);
    }
}