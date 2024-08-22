package com.makersharks.searchapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.makersharks.searchapi.model.Supplier;
import com.makersharks.searchapi.service.SupplierService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping("/query")
    public ResponseEntity<Page<Supplier>> searchSuppliers(
        @Valid @RequestBody SearchCriteria searchCriteria
    ) {
        String location = searchCriteria.getLocation();
        Supplier.NatureOfBusiness natureOfBusiness = searchCriteria.getNatureOfBusiness();
        Supplier.ManufacturingProcess manufacturingProcess = searchCriteria.getManufacturingProcess();
        int page = Optional.ofNullable(searchCriteria.getPage()).orElse(0);
        int size = Optional.ofNullable(searchCriteria.getSize()).orElse(10);

        Pageable pageable = PageRequest.of(page, size);
        Page<Supplier> suppliers = supplierService.searchSuppliers(location, natureOfBusiness, manufacturingProcess, pageable);
        return ResponseEntity.ok(suppliers);
    }

    public static class SearchCriteria {
        @Size(max = 255)
        private String location;

        private Supplier.NatureOfBusiness natureOfBusiness;
        private Supplier.ManufacturingProcess manufacturingProcess;

        private Integer page;
        private Integer size;

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public Supplier.NatureOfBusiness getNatureOfBusiness() {
            return natureOfBusiness;
        }

        public void setNatureOfBusiness(Supplier.NatureOfBusiness natureOfBusiness) {
            this.natureOfBusiness = natureOfBusiness;
        }

        public Supplier.ManufacturingProcess getManufacturingProcess() {
            return manufacturingProcess;
        }

        public void setManufacturingProcess(Supplier.ManufacturingProcess manufacturingProcess) {
            this.manufacturingProcess = manufacturingProcess;
        }

        public Integer getPage() {
            return page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }
    }
}
