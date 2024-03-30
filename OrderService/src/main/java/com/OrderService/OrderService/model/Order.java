package com.OrderService.OrderService.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Orders")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String bookid;
    private String customername;
}
