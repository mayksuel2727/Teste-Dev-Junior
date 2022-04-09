package com.github.mayksuel2727.testedevjunior.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tag")
@Data
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String Description;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Tool tool;
}
