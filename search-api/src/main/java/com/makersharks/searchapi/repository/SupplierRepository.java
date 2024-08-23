package com.makersharks.searchapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.makersharks.searchapi.model.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Page<Supplier> findBySupplierId(Long supplierId, Pageable pageable);

    Page<Supplier> findByCompanyNameContaining(String companyName, Pageable pageable);

    Page<Supplier> findByWebsiteContaining(String website, Pageable pageable);

    Page<Supplier> findByLocationAndNatureOfBusinessAndManufacturingProcess(
        String location,
        Supplier.NatureOfBusiness natureOfBusiness,
        Supplier.ManufacturingProcess manufacturingProcess,
        Pageable pageable
    );

    Page<Supplier> findByLocationAndNatureOfBusiness(
        String location,
        Supplier.NatureOfBusiness natureOfBusiness,
        Pageable pageable
    );

    Page<Supplier> findByLocationAndManufacturingProcess(
        String location,
        Supplier.ManufacturingProcess manufacturingProcess,
        Pageable pageable
    );

    Page<Supplier> findByNatureOfBusinessAndManufacturingProcess(
        Supplier.NatureOfBusiness natureOfBusiness,
        Supplier.ManufacturingProcess manufacturingProcess,
        Pageable pageable
    );

    Page<Supplier> findByLocation(String location, Pageable pageable);

    Page<Supplier> findByNatureOfBusiness(Supplier.NatureOfBusiness natureOfBusiness, Pageable pageable);

    Page<Supplier> findByManufacturingProcess(Supplier.ManufacturingProcess manufacturingProcess, Pageable pageable);

    Page<Supplier> findAll(Pageable pageable);
    
}
