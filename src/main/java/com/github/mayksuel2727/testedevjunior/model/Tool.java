package com.github.mayksuel2727.testedevjunior.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tool")
@Data
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(nullable = false)
    private String link;

    @Column
    private String Description;

    @OneToMany(mappedBy = "tool", cascade = CascadeType.ALL)
    private List<Tag> tag = new ArrayList<>();
}
