package com.makersharks.searchapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.makersharks.searchapi.exception.InvalidEnumValueException;
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
        
        validateSearchCriteria(searchCriteria);

        Long supplierId = searchCriteria.getSupplierId();
        String companyName = searchCriteria.getCompanyName();
        String website = searchCriteria.getWebsite();
        String location = searchCriteria.getLocation();
        Supplier.NatureOfBusiness natureOfBusiness = searchCriteria.getNatureOfBusiness();
        Supplier.ManufacturingProcess manufacturingProcess = searchCriteria.getManufacturingProcess();
        int page = Optional.ofNullable(searchCriteria.getPage()).orElse(0);
        int size = Optional.ofNullable(searchCriteria.getSize()).orElse(20);
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Supplier> suppliers = supplierService.searchSuppliers(supplierId, companyName, website, location, natureOfBusiness, manufacturingProcess, pageable);
        return ResponseEntity.ok(suppliers);
    }

    private void validateSearchCriteria(SearchCriteria searchCriteria) {
        if (searchCriteria.getPage() != null && searchCriteria.getPage() < 0) {
            throw new IllegalArgumentException("Page number must be zero or positive");
        }
        if (searchCriteria.getSize() != null && searchCriteria.getSize() <= 0) {
            throw new IllegalArgumentException("Size must be greater than zero");
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEnumValueException.class)
    public ResponseEntity<String> handleInvalidEnumValueException(InvalidEnumValueException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<String> handleJsonMappingException(JsonMappingException ex) {
        return new ResponseEntity<>("Invalid value for enum: " + ex.getOriginalMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static class SearchCriteria {
        private Long supplierId;
        private String companyName;
        private String website;
        @Size(max = 255)
        private String location;

        private Supplier.NatureOfBusiness natureOfBusiness;
        private Supplier.ManufacturingProcess manufacturingProcess;

        private Integer page;
        private Integer size;

        public Long getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(Long supplierId) {
            this.supplierId = supplierId;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

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
