package com.ascendcargo.contractmgt.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ascendcargo.contractmgt.model.Rate;

public interface RateRepository extends JpaRepository<Rate, Long> {
    List<Rate> findByRoute_Id(Long routeId);

    List<Rate> findByRouteId(Long routeId);
}