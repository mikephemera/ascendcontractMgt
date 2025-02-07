package com.ascendcargo.contractmgt.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ascendcargo.contractmgt.model.ContractAccessorial;
import com.ascendcargo.contractmgt.model.Rate;
import com.ascendcargo.contractmgt.repository.ContractAccessorialRepository;
import com.ascendcargo.contractmgt.repository.RateRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RateService {
    private final RateRepository rateRepository;

    public Rate createRate(Rate rate) {
        validateRatePeriod(rate);
        return rateRepository.save(rate);
    }

    public BigDecimal calculateCharge(Rate rate, BigDecimal weight) {
        switch (rate.getBasisType()) {
            case FLAT:
                return rate.getCost().max(rate.getMinCharge()).min(rate.getMaxCharge());
            case PER_KG:
                return calculateWeightBased(rate, weight);
            default:
                throw new UnsupportedOperationException("Calculation method not implemented");
        }
    }

    private BigDecimal calculateWeightBased(Rate rate, BigDecimal weight) {
        BigDecimal base = rate.getCost().multiply(weight);
        return base.max(rate.getMinCharge()).min(rate.getMaxCharge());
    }

    private void validateRatePeriod(Rate rate) {
        if (rate.getEffectiveDate().isAfter(rate.getExpirationDate())) {
            throw new IllegalArgumentException("Rate period is invalid");
        }
    }

    public List<Rate> getRatesByRouteId(Long routeId) {
        return rateRepository.findByRouteId(routeId);
    }

    public Rate getRateById(Long rateId) {
        return rateRepository.findById(rateId).orElseThrow(
                () -> new EntityNotFoundException("Rate not found with id: " + rateId));
    }

    private final ContractAccessorialRepository accessorialRepo;

    public TotalCharge calculateTotalCharge(Rate rate, BigDecimal weight,
            Map<String, Object> context) {
        BigDecimal baseCharge = calculateCharge(rate, weight);
        List<ContractAccessorial> accessorials = getApplicableAccessorials(rate);

        TotalCharge total = new TotalCharge(baseCharge);

        accessorials.forEach(acc -> {
            total.addCharge(acc.getType().name(), acc.calculate(baseCharge, context));
        });

        return total;
    }

    private List<ContractAccessorial> getApplicableAccessorials(Rate rate) {
        return accessorialRepo.findByRouteIdOrLaneIdOrContractId(rate.getRoute().getId(),
                rate.getRoute().getLane().getId(), rate.getRoute().getLane().getContract().getId());
    }

    // 新增DTO
    @Data
    public static class TotalCharge {
        private BigDecimal baseAmount;
        private Map<String, BigDecimal> accessorials = new HashMap<>();
        private BigDecimal totalAmount;

        public TotalCharge(BigDecimal base) {
            this.baseAmount = base;
            this.totalAmount = base;
        }

        public void addCharge(String type, BigDecimal amount) {
            accessorials.put(type, amount);
            totalAmount = totalAmount.add(amount);
        }
    }
}
