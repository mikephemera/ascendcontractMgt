package com.ascendcargo.contractmgt.service;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ascendcargo.contractmgt.model.Rate;
import com.ascendcargo.contractmgt.repository.RateRepository;
import jakarta.persistence.EntityNotFoundException;
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
        return rateRepository.findById(rateId)
            .orElseThrow(() -> new EntityNotFoundException("Rate not found with id: " + rateId));
    }
}