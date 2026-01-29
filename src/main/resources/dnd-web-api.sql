-- Weapon data
INSERT INTO weapons (name, description, value_in_copper_pieces, weight_in_lbs, damage_dice, damage_type, range_normal, range_long, is_two_handed, created_at, updated_at)
VALUES ('Longsword', 'A versatile steel blade favored by knights and soldiers.', NULL, NULL, '1d8', 'slashing', NULL, NULL, FALSE, NOW(), NOW()),
       ('Great Axe', 'A massive axe capable of devastating, cleaving blows.', 2000, 7.0, '1d12', 'slashing', NULL, NULL, TRUE, NOW(), NOW()),
       ('Shortbow', 'A compact bow designed for speed and mobility.', 1000, 2.0, '1d6', 'piercing', 80, 320, TRUE, NOW(), NOW()),
       ('Dagger', 'A small, easily concealed blade ideal for close combat.', 200, 1.0, '1d4', 'piercing', 20, 60, FALSE, NOW(), NOW()),
       ('Warhammer','A heavy hammer designed to crush armor and bone.', 1200, 2.5, '1d8', 'bludgeoning', NULL, NULL, FALSE, NOW(), NOW());

-- Equipment data
INSERT INTO equipment (name, description, value_in_copper_pieces, weight_in_lbs, created_at, updated_at)
VALUES ('Torch', 'A wooden torch that burns for about one hour, providing light.', 10, 1.0, NOW(), NOW()),
       ('Bucket', 'A sturdy wooden bucket useful for carrying liquids or materials.', 50, 2.0, NOW(), NOW()),
       ('Rope (50 ft)', 'A coil of hempen rope, strong and reliable.', 100, 10.0, NOW(), NOW()),
       ('Bedroll', 'A simple roll of cloth and padding for sleeping outdoors.', 100, 7.0, NOW(), NOW()),
       ('Flint and Steel', 'A small kit used to create sparks for starting fires.', 50, 0.5, NOW(), NOW());

-- Character types data
INSERT INTO character_types (name, color, created_at, updated_at)
VALUES ('Player', '#4CAF50', NOW(), NOW()),
       ('NPC', '#2196F3', NOW(), NOW()),
       ('Monster', '#F44336', NOW(), NOW()),
       ('Summon', '#9C27B0', NOW(), NOW());

-- Character races data
INSERT INTO character_races (name, description, speed, created_at, updated_at)
VALUES ('Human', 'Versatile and ambitious, humans adapt quickly and thrive in all lands.', 30, NOW(), NOW()),
       ('Elf', 'Graceful and perceptive beings with keen senses and a long lifespan.', 30, NOW(), NOW()),
       ('Dwarf', 'Stout and resilient folk known for endurance and martial tradition.', 25, NOW(), NOW()),
       ('Halfling', 'Small, nimble people with quiet courage and surprising resilience.', 25, NOW(), NOW()),
       ('Half-Elf', 'Born of both human ambition and elven grace, half-elves move easily between worlds.', 30, NOW(), NOW());

-- Character races data
INSERT INTO character_classes (name, hit_die, created_at, updated_at)
VALUES ('Wizard',6,NOW(),NOW()),
       ('Ranger',10,NOW(),NOW()),
       ('Fighter',10,NOW(),NOW()),
       ('Rogue',8,NOW(),NOW()),
       ('Paladin', 10, NOW(), NOW());

-- Character data
INSERT INTO characters (name, charisma, constitution, dexterity, intelligence, strength, wisdom, max_hit_points, current_hit_points, experience_points, size, copper_pieces, silver_pieces, electrum_pieces, gold_pieces, platinum_pieces, notes, alignment, type_id, race_id, class_id, created_at, updated_at)
VALUES ('Gandalf the Grey', 18, 16, 14, 20, 12, 19, 60, 60, 9000, 'Medium', 20, 50, 25, 200, 5, 'A wandering wizard sent to guide the free peoples against the rising shadow.', 2, (SELECT id FROM character_types WHERE name = 'Player'), (SELECT id FROM character_races WHERE name = 'Human'), (SELECT id FROM character_classes WHERE name = 'Wizard'), NOW(), NOW()),
       ('Aragorn son of Arathorn', 17, 18, 16, 14, 18, 15, 75, 75, 8500, 'Medium', 100, 50, 0, 150, 2, 'The hidden king, a ranger of the north destined to reclaim an ancient throne.', 2, (SELECT id FROM character_types WHERE name = 'Player'), (SELECT id FROM character_races WHERE name = 'Human'), (SELECT id FROM character_classes WHERE name = 'Ranger'), NOW(), NOW()),
       ('Legolas Greenleaf', 16, 14, 20, 13, 14, 16, 65, 65, 8200, 'Medium', 40, 60, 0, 120, 1, 'An elven prince with unmatched eyesight and deadly precision with the bow.', 2, (SELECT id FROM character_types WHERE name = 'Player'), (SELECT id FROM character_races WHERE name = 'Elf'), (SELECT id FROM character_classes WHERE name = 'Fighter'), NOW(), NOW()),
       ('Gimli son of Gloin', 14, 20, 12, 11, 19, 13, 80, 80, 7800, 'Medium', 200, 40, 0, 100, 1, 'A proud dwarf warrior whose axe is as unyielding as his loyalty.', 1, (SELECT id FROM character_types WHERE name = 'Player'), (SELECT id FROM character_races WHERE name = 'Dwarf'), (SELECT id FROM character_classes WHERE name = 'Fighter'), NOW(), NOW()),
       ('Frodo of the Shire', 15, 14, 16, 12, 9, 17, 45, 45, 7000, 'Small', 30, 80, 0, 60, 0, 'A small hobbit burdened with a task that will decide the fate of the world.', 2, (SELECT id FROM character_types WHERE name = 'Player'), (SELECT id FROM character_races WHERE name = 'Halfling'), (SELECT id FROM character_classes WHERE name = 'Rogue'), NOW(), NOW());

-- Character inventory items
INSERT INTO character_inventory_items(reference_id, type, quantity, character_id, created_at, updated_at)
VALUES ((SELECT id FROM weapons WHERE name = 'Dagger'), 0, 1, (SELECT id FROM characters WHERE name = 'Gandalf the Grey'), NOW(), NOW()),
       ((SELECT id FROM weapons WHERE name = 'Longsword'), 0, 1, (SELECT id FROM characters WHERE name = 'Gandalf the Grey'), NOW(), NOW()),
       ((SELECT id FROM weapons WHERE name = 'Great Axe'), 0, 1, (SELECT id FROM characters WHERE name = 'Gandalf the Grey'), NOW(), NOW()),
       ((SELECT id FROM equipment WHERE name = 'Torch'), 1, 5, (SELECT id FROM characters WHERE name = 'Gandalf the Grey'), NOW(), NOW()),
       ((SELECT id FROM equipment WHERE name = 'Bedroll'), 1, 2, (SELECT id FROM characters WHERE name = 'Gandalf the Grey'), NOW(), NOW());

-- Custom character inventory items
INSERT INTO character_inventory_items(type, quantity, character_id, created_at, updated_at)
VALUES (2, 1, (SELECT id FROM characters WHERE name = 'Gandalf the Grey'), NOW(), NOW());

INSERT INTO character_inventory_item_custom_infos(character_inventory_item_id, name, description, value_in_copper_pieces, weight_in_lbs, created_at, updated_at)
VALUES ((SELECT id FROM character_inventory_items WHERE type = 2), 'Lucky stone', 'A smooth river stone that (seriously) brings luck.', 1000, 0.1, NOW(), NOW())