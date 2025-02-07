package com.ascendcargo.contractmgt.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ascendcargo.contractmgt.model.ContractAccessorial;

public interface ContractAccessorialRepository extends JpaRepository<ContractAccessorial, Long> {
    @Query("SELECT ca FROM ContractAccessorial ca WHERE "
            + "(ca.scope = 'CONTRACT' AND ca.contract.id = :contractId) OR "
            + "(ca.scope = 'LANE' AND ca.lane.id = :laneId) OR "
            + "(ca.scope = 'ROUTE' AND ca.route.id = :routeId)")
    List<ContractAccessorial> findByHierarchy(@Param("contractId") Long contractId,
            @Param("laneId") Long laneId, @Param("routeId") Long routeId);


    List<ContractAccessorial> findByRouteIdOrLaneIdOrContractId(Long routeId, Long laneId,
            Long contractId);
}
