package com.bhc.startstop.cis.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhc.startstop.cis.model.ServicePoint;

public interface ServicePointRepository extends JpaRepository<ServicePoint, Long> {

    List<ServicePoint> findAllByCoyAndPremiseIdIn(Integer coy, Collection<Long> premiseIds);
    ServicePoint findByServicePointId(Long servicePointId);

}