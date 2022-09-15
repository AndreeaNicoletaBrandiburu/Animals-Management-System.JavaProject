package main.model.animal;

import main.exception.BehaviourDoesNotExistException;

public enum Behaviour {
    HAPPY, LYMPHATIC, APATHETIC, AGITATED;

    public static Behaviour getBehaviour(String behaviour) {
        switch (behaviour.toLowerCase()) {
            case "happy":
                return HAPPY;
            case "lymphatic":
                return LYMPHATIC;
            case "apathetic":
                return APATHETIC;
            case "agitated":
                return AGITATED;
            default:
                throw new BehaviourDoesNotExistException(behaviour);
        }
    }

    public static String getNameFromBehaviour(Behaviour behaviour) {
        if (behaviour.equals(HAPPY)) {
            return "happy";
        }
        if (behaviour.equals(LYMPHATIC)) {
            return "lymphatic";
        }
        if (behaviour.equals(APATHETIC)) {
            return "apathetic";
        }
        return "agitated";

    }
}
