package walter.duncan.dndwebapi.config.openapi.examples.encountermanagement;

public class EncounterExampleProvider {
    public static final String CREATE_ENCOUNTER =
"""
{
    "title": "Ambush at Amon Hen",
    "description": "As the Fellowship camps near the ancient ruins of Amon Hen, the air grows tense. Orcs under the command of Saruman emerge from the forest, arrows flying and blades clashing. Boromir fights valiantly to protect the Hobbits, Aragorn and Legolas race through the trees, and chaos erupts as the Fellowship is torn apart."
}
""";

    public static final String ADD_PARTICIPANT =
"""
{
    "characterId": 5,
    "initiative": 25
}
""";

    public static final String CREATE_ENCOUNTER_JOIN_REQUEST =
"""
{
    "characterId": 5,
    "initiative": 10
}
""";
}