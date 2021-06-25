package by.exposit.delivery.entities.metadata;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Gender {
    MALE('m'),
    FEMALE('f'),
    UNKNOWN('u');

    private final Character abbreviation;

    private static final Map<Character, Gender> genderMap;

    static {
        genderMap = Stream.of(Gender.values()).collect(Collectors.toMap(Gender::getAbbreviation, gender -> gender));
    }

    Gender(Character abbreviation) {
        this.abbreviation = abbreviation;
    }

    public static Gender getGender(Character abbreviation) {
        return genderMap.getOrDefault(abbreviation, Gender.UNKNOWN);
    }

    public Character getAbbreviation() {
        return abbreviation;
    }
}
