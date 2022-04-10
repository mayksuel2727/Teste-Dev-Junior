package com.github.mayksuel2727.testedevjunior.controller;

import com.github.mayksuel2727.testedevjunior.controller.dto.ToolDTO;
import com.github.mayksuel2727.testedevjunior.model.Tool;
import com.github.mayksuel2727.testedevjunior.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/tools")
public class ToolController {

    @Autowired
    private ToolRepository toolRepository;

    @GetMapping
    @Cacheable(value = "listOfTools")
    public List<ToolDTO> listAll() {
        List<Tool> tools = toolRepository.findAll();
        return ToolDTO.converter(tools);
    }

    @GetMapping("/pagination")
    @Cacheable(value = "listOfTools")
    public Page<ToolDTO> listPagination(@RequestParam(required = false) String title, @PageableDefault(sort = "title", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable page){

        if (title == null){
            Page<Tool> tools = toolRepository.findAll(page);
            return ToolDTO.converter(tools);
        }else {
            Page<Tool> tools = toolRepository.findByTitle(title, page);
            return ToolDTO.converter(tools);
        }
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listOfTools", allEntries = true )
    public ResponseEntity<ToolDTO> register(@RequestBody @Valid Tool tool, UriComponentsBuilder uriBuilder) {
        for (int i = 0; i < tool.getTags().size(); i++) {
            tool.getTags().get(i).setTool(tool);
        }
        toolRepository.save(tool);
        URI uri = uriBuilder.path("/tools/{id}").buildAndExpand(tool.getId()).toUri();
        return ResponseEntity.created(uri).body(new ToolDTO(tool));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToolDTO> uniqueTool(@PathVariable Integer id) {
        Optional<Tool> tool = toolRepository.findById(id);
        if (tool.isPresent()){
            return ResponseEntity.ok(new ToolDTO(tool.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listOfTools", allEntries = true )
    public ResponseEntity<ToolDTO> update(@PathVariable Integer id, @RequestBody @Valid Tool toolUpdate) {
        Optional<Tool> optionalTool = toolRepository.findById(id);
        if (optionalTool.isPresent()){
            Tool tool = toolUpdate.update(id, toolRepository);
            return ResponseEntity.ok(new ToolDTO(tool));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listOfTools", allEntries = true )
    public ResponseEntity<?> delete (@PathVariable Integer id){
        Optional<Tool> optionalTool = toolRepository.findById(id);
        if (optionalTool.isPresent()){
            toolRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
