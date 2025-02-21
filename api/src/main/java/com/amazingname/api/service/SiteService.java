package com.amazingname.api.service;

import com.amazingname.api.model.Site;
import com.amazingname.api.repository.SiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteService {
    private final SiteRepository siteRepository;

    public SiteService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    public List<Site> getAllSites() {
        return siteRepository.findAll();
    }

    public Site getSiteById(Long id) {
        return siteRepository.findById(id).orElse(null);
    }

    public Site saveSite(Site site) {
        return siteRepository.save(site);
    }

    public void deleteSiteById(Long id) {
        siteRepository.deleteById(id);
    }

    public List<Site> searchSiteByCity(String search) {
        return siteRepository.findAllByCityContaining(search);
    }
}
