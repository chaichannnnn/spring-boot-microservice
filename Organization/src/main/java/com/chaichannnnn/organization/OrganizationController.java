package com.chaichannnnn.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    @GetMapping
    public List<Organization> findAllOrganization() {
        return organizationService.findAllOrganization();
    }

//    @GetMapping("/{id}")
//    public Organization findOrganizationById(@PathVariable String id) {
//        return organizationService.findOrganizationById(id);
//    }

    @PostMapping
    public Organization addOrganization(@RequestBody Organization organization) {
        return organizationService.addNewOrganization(organization);
    }

    @PutMapping("/{id}")
    public Organization putOrganization(@PathVariable String id, @RequestBody Organization organization) {
        return organizationService.updateOrganization(id, organization);
    }

    @DeleteMapping("/{id}")
    public void deleteOrganization(@PathVariable String id) {
        organizationService.deleteOrganization(id);
    }

    @GetMapping("/test")
    public Organization test() {
        return Organization.builder().id("14").name("hello organization").build();
    }
}
