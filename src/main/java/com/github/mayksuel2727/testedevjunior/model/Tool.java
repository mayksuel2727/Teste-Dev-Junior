package com.github.mayksuel2727.testedevjunior.model;

import com.github.mayksuel2727.testedevjunior.repository.ToolRepository;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tool")
@Data
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150) @NotEmpty @NotNull
    private String title;

    @Column(nullable = false) @NotEmpty @NotNull
    private String link;

    @Column
    private String description;

    @OneToMany(mappedBy = "tool", cascade = CascadeType.ALL)
    private List<Tag> tags = new ArrayList<>();

    public Tool update(Integer id, ToolRepository toolRepository) {
        Tool tool = toolRepository.getById(id);
        tool.setDescription(this.description);
        tool.setTitle(this.title);
        tool.setLink(this.link);
        tool.setTags(this.tags);

        return tool;
    }
}
