package com.panaderia.rodrigo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

// @Builder   ← FALTABA ESTO → causa del "cannot find symbol method builder()"
// @Table     ← buena práctica nombrar la tabla explícitamente
// Los campos descripcion y disponible faltaban pero el DataLoader los usa
@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "La categoría es obligatoria")
    private String categoria;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    private Double precio;

    @NotNull(message = "El stock es obligatorio")
    @PositiveOrZero(message = "El stock no puede ser negativo")
    private Integer stock;

    // Estos dos campos FALTABAN en la versión original
    // pero el DataLoader los usa con .descripcion() y .disponible()
    private String descripcion;

    @Builder.Default  // necesario para que Lombok respete el valor true al usar builder()
    private Boolean disponible = true;
}