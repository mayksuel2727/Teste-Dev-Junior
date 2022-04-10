package com.github.mayksuel2727.testedevjunior.controller;

import com.github.mayksuel2727.testedevjunior.controller.dto.ToolDTO;
import com.github.mayksuel2727.testedevjunior.model.Tool;
import com.github.mayksuel2727.testedevjunior.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/tools")
public class ToolController {

    @Autowired
    private ToolRepository toolRepository;

    @GetMapping
    public List<ToolDTO> listAll(){
        List<Tool> tools = toolRepository.findAll();
        return ToolDTO.converter(tools);
    }

    @PostMapping
    public ResponseEntity<ToolDTO> register(@RequestBody @Valid Tool tool, UriComponentsBuilder uriBuilder){
       for (int i=0; i< tool.getTags().size(); i++ ){
            tool.getTags().get(i).setTool(tool);
        }
        toolRepository.save(tool);
        URI uri =  uriBuilder.path("/tools/{id}").buildAndExpand(tool.getId()).toUri();
        return ResponseEntity.created(uri).body(new ToolDTO(tool));
    }
}
