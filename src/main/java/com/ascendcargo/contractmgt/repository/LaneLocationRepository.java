package com.ascendcargo.contractmgt.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ascendcargo.contractmgt.model.LaneLocation;
import com.ascendcargo.contractmgt.model.LaneLocationId;

public interface LaneLocationRepository extends JpaRepository<LaneLocation, LaneLocationId> {
    @Query("SELECT ll FROM LaneLocation ll WHERE ll.id.laneId = :laneId AND ll.id.type = :type ORDER BY ll.sequence")
    List<LaneLocation> findByLaneIdAndType(@Param("laneId") Long laneId, @Param("type") LaneLocation.PointType type);
}