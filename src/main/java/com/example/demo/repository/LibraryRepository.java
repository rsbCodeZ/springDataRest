package com.example.demo.repository;

import com.example.demo.entity.Library;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Integer> {
}
