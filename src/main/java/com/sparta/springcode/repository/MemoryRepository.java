package com.sparta.springcode.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sparta.springcode.table.Memory;

@Repository
public interface MemoryRepository extends JpaRepository<Memory, Long> {
   List<Memory> findAllByUserIdOrderByModifiedAtDesc(Long userId);
   List<Memory> findAllByOrderByModifiedAtDesc();
   Optional<Memory> findByUserId(Long userId);
   Optional<Memory> findById(Long id);
}