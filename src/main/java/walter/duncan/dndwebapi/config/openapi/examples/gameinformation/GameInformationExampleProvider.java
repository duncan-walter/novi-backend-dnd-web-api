package walter.duncan.dndwebapi.config.openapi.examples.gameinformation;

public class GameInformationExampleProvider {
    public static final String CREATE_EQUIPMENT =
"""
{
    "name": "Crowbar",
    "description": "A sturdy iron bar used for prying open crates, doors, and other heavy objects.",
    "valueInCopperPieces": 200,
    "weightInLbs": 5.0
}
""";

    public static final String CREATE_WEAPON =
"""
{
    "name": "Light Crossbow",
    "description": "A mechanical ranged weapon that fires bolts with high accuracy and stopping power.",
    "valueInCopperPieces": 2500,
    "weightInLbs": 5.0,
    "damageDice": "1d8",
    "damageType": "piercing",
    "rangeNormal": 80,
    "rangeLong": 320,
    "isTwoHanded": true
}
""";

    public static final String UPDATE_EQUIPMENT =
"""
{
    "name": "Torch",
    "description": "A wooden torch that burns for about one hour, providing bright light and can be used to ignite flammable objects.",
    "valueInCopperPieces": 10
}
""";

    public static final String UPDATE_WEAPON =
"""
{
    "name": "Longsword +1",
    "description": "A finely crafted longsword imbued with minor enchantments that enhance its accuracy and lethality.",
    "valueInCopperPieces": 30000,
    "weightInLbs": 3.0,
    "damageDice": "1d8",
    "damageType": "slashing",
    "rangeNormal": null,
    "rangeLong": null,
    "isTwoHanded": false
}
""";
}