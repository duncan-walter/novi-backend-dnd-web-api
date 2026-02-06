package walter.duncan.dndwebapi.config.openapi.examples.charactermanagement;

public class CharacterExampleProvider {
    public static final String CREATE_CHARACTER =
"""
{
    "name": "Sauron",
    "charisma": 18,
    "constitution": 20,
    "dexterity": 12,
    "intelligence": 20,
    "strength": 25,
    "wisdom": 18,
    "maxHitPoints": 10000,
    "currentHitPoints": 10000,
    "experiencePoints": 1000000,
    "size": "Huge",
    "copperPieces": 0,
    "silverPieces": 1000,
    "electrumPieces": 500,
    "goldPieces": 10000,
    "platinumPieces": 5000,
    "notes": "The Dark Lord, master of Mordor and creator of the One Ring.",
    "alignmentCode": "LE",
    "typeId": 2,
    "raceId": 1,
    "classId": 3,
    "inventory": [
        {
            "referenceId": 1,
            "type": "weapon",
            "quantity": 1
        },
        {
            "referenceId": 2,
            "type": "weapon",
            "quantity": 10
        }
    ]
}
""";

    public static final String UPDATE_CHARACTER =
"""
{
    "name": "Gandalf the White",
    "charisma": 20,
    "constitution": 18,
    "dexterity": 14,
    "intelligence": 20,
    "strength": 14,
    "wisdom": 20,
    "maxHitPoints": 80,
    "currentHitPoints": 80,
    "experiencePoints": 12000,
    "size": "Medium",
    "copperPieces": 30,
    "silverPieces": 60,
    "electrumPieces": 40,
    "goldPieces": 250,
    "platinumPieces": 10,
    "notes": "Returned more powerful after defeating the Balrog, now leading the fight against Sauron.",
    "alignmentCode": "LG",
    "typeId": 1,
    "raceId": 1,
    "classId": 1,
    "inventory": [
        {
            "referenceId": 1,
            "type": "weapon",
            "quantity": 10
        },
        {
            "referenceId": 2,
            "type": "equipment",
            "quantity": 10
        },
        {
            "type": "custom",
            "name": "Glamdring, Sword of the West",
            "description": "The ancient sword of the High Elves, glowing with a blue flame whenever orcs are near. Once wielded by Turgon, reforged for Gandalf’s return.",
            "valueInCopperPieces": 500000,
            "weightInLbs": 4.5,
            "quantity": 1
        }
    ]
}
""";
}