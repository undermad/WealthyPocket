package user_access.value_objects;

import user_access.exceptions.InvalidBornDate;

import java.time.LocalDate;

public record BornDate(LocalDate value) {
    
    public BornDate {
        if(value.isAfter(LocalDate.now())) throw new InvalidBornDate("Born date can not be after current date.");
    }
}
