package com.amazingname.api.repository;

import com.amazingname.api.model.ServiceAn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<ServiceAn, Long> {
    List<ServiceAn> findAllByNameContaining(String name);
}
