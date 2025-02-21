package com.amazingname.api.repository;

import com.amazingname.api.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SiteRepository extends JpaRepository<Site, Long> {
    List<Site> findAllByCityContaining(String city);
}
