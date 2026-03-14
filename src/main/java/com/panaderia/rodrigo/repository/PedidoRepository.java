package com.panaderia.rodrigo.repository;

import com.panaderia.rodrigo.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // ❌ findByClienteID → Spring Data necesita que coincida exactamente
    //    con el nombre del campo en la entidad: cliente.id → findByClienteId
    List<Pedido> findByClienteId(Long clienteId);

    // ❌ findEstado → faltaba el "By" → Spring Data no reconocía el método
    List<Pedido> findByEstado(Pedido.EstadoPedido estado);

    // ❌ "ORDER BYE" → typo de JPQL, debe ser "ORDER BY"
    @Query("SELECT p FROM Pedido p ORDER BY p.fechaPedido DESC")
    List<Pedido> findAllOrderByFechaDesc();

    @Query("SELECT COUNT(p) FROM Pedido p WHERE p.estado = :estado")
    Long countByEstado(Pedido.EstadoPedido estado);
}