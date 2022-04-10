package com.github.mayksuel2727.testedevjunior.repository;

import com.github.mayksuel2727.testedevjunior.model.Tool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToolRepository extends JpaRepository<Tool, Integer> {
    Page<Tool> findByTitle(String title, Pageable pageable);
}
