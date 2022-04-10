package com.github.mayksuel2727.testedevjunior.controller.dto;

import com.github.mayksuel2727.testedevjunior.model.Tool;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ToolDTO {
    private Integer id;
    private String title;
    private String link;
    private String description;
    private List<String> tag = new ArrayList<>();

    public ToolDTO(Tool tool) {
        this.id = tool.getId();
        this.title = tool.getTitle();
        this.link = tool.getLink();
        this.description = tool.getDescription();
        for (int i=0; i<tool.getTags().size(); i++) {
            String descrip =null;
            descrip = tool.getTags().get(i).getDescription();
            this.tag.add(descrip);
        }
    }
    public static List<ToolDTO> converter(List<Tool> tools) {
        return tools.stream().map(ToolDTO::new).collect(Collectors.toList());
    }

    public static Page<ToolDTO> converter(Page<Tool> tools) {
        return tools.map(ToolDTO::new);
    }
}
