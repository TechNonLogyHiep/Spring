package org.example.baitap_apispringboot.repository;

import org.example.baitap_apispringboot.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
