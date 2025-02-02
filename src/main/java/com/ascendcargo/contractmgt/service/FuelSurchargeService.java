package com.ascendcargo.contractmgt.service;

import com.ascendcargo.contractmgt.model.FuelSurcharge;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class FuelSurchargeService {
    private List<FuelSurcharge> surcharges = new ArrayList<>();

    public List<FuelSurcharge> getAll() {
        return surcharges;
    }

    public FuelSurcharge getCurrentSurcharge() {
        return surcharges.isEmpty() ? null : surcharges.get(surcharges.size() - 1);
    }

    public FuelSurcharge create(FuelSurcharge surcharge) {
        surcharges.add(surcharge);
        return surcharge;
    }

}
