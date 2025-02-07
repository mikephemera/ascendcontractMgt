package com.ascendcargo.contractmgt.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ascendcargo.contractmgt.model.Route;

public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findByLane_Id(Long laneId);
}