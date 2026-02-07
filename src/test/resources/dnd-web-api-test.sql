-- Weapon data
INSERT INTO weapons (name, description, value_in_copper_pieces, weight_in_lbs, damage_dice, damage_type, range_normal, range_long, is_two_handed, created_at, updated_at)
VALUES ('Longsword', 'A versatile steel blade favored by knights and soldiers.', NULL, NULL, '1d8', 'slashing', NULL, NULL, FALSE, NOW(), NOW()),
       ('Great Axe', 'A massive axe capable of devastating, cleaving blows.', 2000, 7.0, '1d12', 'slashing', NULL, NULL, TRUE, NOW(), NOW()),
       ('Shortbow', 'A compact bow designed for speed and mobility.', 1000, 2.0, '1d6', 'piercing', 80, 320, TRUE, NOW(), NOW()),
       ('Dagger', 'A small, easily concealed blade ideal for close combat.', 200, 1.0, '1d4', 'piercing', 20, 60, FALSE, NOW(), NOW()),
       ('Warhammer','A heavy hammer designed to crush armor and bone.', 1200, 2.5, '1d8', 'bludgeoning', NULL, NULL, FALSE, NOW(), NOW());