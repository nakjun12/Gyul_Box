package jeju.oneroom.validation;

import jeju.oneroom.common.entity.Rate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RateValidator implements ConstraintValidator<ValidRate, Rate> {

    @Override
    public boolean isValid(Rate rate, ConstraintValidatorContext context) {
        return rate != null
                && (rate.getInteriorRate() != null && rate.getInteriorRate() != 0.0)
                && (rate.getBuildingRate() != null && rate.getBuildingRate() != 0.0)
                && (rate.getTrafficRate() != null && rate.getTrafficRate() != 0.0)
                && (rate.getSecurityRate() != null && rate.getSecurityRate() != 0.0)
                && (rate.getLocationRate() != null && rate.getLocationRate() != 0.0);
    }
}
