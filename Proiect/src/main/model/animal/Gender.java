package main.model.animal;

import main.exception.GenderDoesNotExistException;

public enum Gender {
    MALE, FEMALE;

    public static Gender getGender(String gender) {
        if (gender.equalsIgnoreCase("male")) {
            return MALE;
        }
        if (gender.equalsIgnoreCase("female")) {
            return FEMALE;
        }
        throw new GenderDoesNotExistException(gender);
    }

    public static String getNameFromGender(Gender gender) {
        if (gender.equals(MALE)) {
            return "male";
        }
        return "female";
    }
}
