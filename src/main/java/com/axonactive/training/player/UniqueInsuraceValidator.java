package com.axonactive.training.player;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class UniqueInsuraceValidator implements ConstraintValidator<InsuraneUniqued, String> {

    @Inject
    PlayerService playerService;

    public void initialize(InsuraneUniqued constraint) {
    }

    @Override
    public boolean isValid(String insuranceNumber, ConstraintValidatorContext arg1) {
        return StringUtils.isNotBlank(insuranceNumber)
                && playerService.findByInsuranceNumber(insuranceNumber).isEmpty();
    }

}
