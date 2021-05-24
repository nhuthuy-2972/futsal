package com.axonactive.training.player;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class GenderPersistenceConverter implements AttributeConverter<Gender,Integer>{

    @Override
    public Integer convertToDatabaseColumn(Gender gender) {
        return (gender != null) ? gender.getValue() : null;
    }

    @Override
    public Gender convertToEntityAttribute(Integer dbGender) {
        Gender gender = Gender.UNKNOWN;
        
        for(Gender each : Gender.values())
        {
            if(each.getValue() == dbGender){
                gender = each;
                break;
            }
        }
        return gender;
    }

}
