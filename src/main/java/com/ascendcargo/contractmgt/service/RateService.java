package com.ascendcargo.contractmgt.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ascendcargo.contractmgt.model.Rate;
import com.ascendcargo.contractmgt.repository.RateRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RateService {

    private final RateRepository rateRepository;

    public Rate createRate(Rate rate) {
        validateRateDates(rate);
        return rateRepository.save(rate);
    }

    private void validateRateDates(Rate rate) {
        if (rate.getEndDate() != null && rate.getStartDate().isAfter(rate.getEndDate())) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
    }

    public Rate updateRate(Long id, Rate rateDetails) {
        Rate existing = rateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rate not found"));
        
        if (rateDetails.getContractedOrKniff() != null) {
            existing.setContractedOrKniff(rateDetails.getContractedOrKniff());
        }
        // 更新其他字段...
        return rateRepository.save(existing);
    }

    public List<Rate> getAllRates() {
        return rateRepository.findAll();
    }

    public Rate getRateById(Long id) {
        return rateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rate not found with id: " + id));
    }

    public void deleteRate(Long id) {
        Rate rate = rateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rate not found with id: " + id));
        rateRepository.delete(rate);
    }
}