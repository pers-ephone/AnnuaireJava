package com.amazingname.api.controller;

import com.amazingname.api.model.Site;
import com.amazingname.api.service.SiteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sites")
public class SiteController {
    private final SiteService siteService;

    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @GetMapping
    public List<Site> getAllSites() {
        return siteService.getAllSites();
    }

    @GetMapping("{id}")
    public Site getSiteById(@PathVariable Long id) {
        return siteService.getSiteById(id);
    }

    @GetMapping("/search/{search}")
    public List<Site> searchSite(@PathVariable String search) {
        return siteService.searchSiteByCity(search);
    }

    @PostMapping
    public Site createSite(@RequestBody Site site) {
        return siteService.saveSite(site);
    }

    @PutMapping
    public Site updateSite(@RequestBody Site site) {
        return siteService.saveSite(site);
    }

    @DeleteMapping("{id}")
    public void deleteSiteById(@PathVariable Long id) {
        siteService.deleteSiteById(id);
    }
}
