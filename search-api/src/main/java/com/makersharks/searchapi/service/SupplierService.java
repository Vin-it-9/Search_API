package com.makersharks.searchapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.makersharks.searchapi.model.Supplier;
import com.makersharks.searchapi.repository.SupplierRepository;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public Page<Supplier> searchSuppliers(String location, Supplier.NatureOfBusiness natureOfBusiness, 
                                          Supplier.ManufacturingProcess manufacturingProcess, Pageable pageable) {
                                            
        if (location != null && natureOfBusiness != null && manufacturingProcess != null) {
            return supplierRepository.findByLocationAndNatureOfBusinessAndManufacturingProcess(location, natureOfBusiness, manufacturingProcess, pageable);
        } else if (location != null && natureOfBusiness != null) {
            return supplierRepository.findByLocationAndNatureOfBusiness(location, natureOfBusiness, pageable);
        } else if (location != null && manufacturingProcess != null) {
            return supplierRepository.findByLocationAndManufacturingProcess(location, manufacturingProcess, pageable);
        } else if (natureOfBusiness != null && manufacturingProcess != null) {
            return supplierRepository.findByNatureOfBusinessAndManufacturingProcess(natureOfBusiness, manufacturingProcess, pageable);
        } else if (location != null) {
            return supplierRepository.findByLocation(location, pageable);
        } else if (natureOfBusiness != null) {
            return supplierRepository.findByNatureOfBusiness(natureOfBusiness, pageable);
        } else if (manufacturingProcess != null) {
            return supplierRepository.findByManufacturingProcess(manufacturingProcess, pageable);
        } else {
            return supplierRepository.findAll(pageable);
        }
    }
}
