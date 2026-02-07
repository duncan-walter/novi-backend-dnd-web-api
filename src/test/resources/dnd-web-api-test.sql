-- Weapon data
INSERT INTO weapons (name, description, value_in_copper_pieces, weight_in_lbs, damage_dice, damage_type, range_normal, range_long, is_two_handed, created_at, updated_at)
VALUES ('Longsword', 'A versatile steel blade favored by knights and soldiers.', NULL, NULL, '1d8', 'slashing', NULL, NULL, FALSE, NOW(), NOW()),
       ('Great Axe', 'A massive axe capable of devastating, cleaving blows.', 2000, 7.0, '1d12', 'slashing', NULL, NULL, TRUE, NOW(), NOW()),
       ('Shortbow', 'A compact bow designed for speed and mobility.', 1000, 2.0, '1d6', 'piercing', 80, 320, TRUE, NOW(), NOW()),
       ('Dagger', 'A small, easily concealed blade ideal for close combat.', 200, 1.0, '1d4', 'piercing', 20, 60, FALSE, NOW(), NOW()),
       ('Warhammer','A heavy hammer designed to crush armor and bone.', 1200, 2.5, '1d8', 'bludgeoning', NULL, NULL, FALSE, NOW(), NOW());

-- User data
INSERT INTO users (identity_provider_id, name, email, created_at, updated_at)
VALUES ('6c7abe11-6431-4c6b-b530-91f929c25e61', 'Henk the Tank', 'player1@dndapp.nl', NOW(), NOW()),
       ('6703dd9d-1872-4292-99a9-21bebbdb9b5c', 'Bert the Hurt', 'dungeon-master1@dndapp.nl', NOW(), NOW());

-- Character types data
INSERT INTO character_types (name, color, created_at, updated_at)
VALUES ('Placeholder', '#FFFFFF', NOW(), NOW());

-- Character races data
INSERT INTO character_races (name, description, speed, created_at, updated_at)
VALUES ('Placeholder', 'Placeholder', 0, NOW(), NOW());

-- Character races data
INSERT INTO character_classes (name, hit_die, created_at, updated_at)
VALUES ('Placeholder',6,NOW(),NOW());

-- Character data
INSERT INTO characters (name, charisma, constitution, dexterity, intelligence, strength, wisdom, max_hit_points, current_hit_points, experience_points, size, copper_pieces, silver_pieces, electrum_pieces, gold_pieces, platinum_pieces, notes, alignment, type_id, race_id, class_id, user_id, created_at, updated_at)
VALUES ('Gandalf the Grey', 18, 16, 14, 20, 12, 19, 60, 60, 9000, 'Medium', 20, 50, 25, 200, 5, 'A wandering wizard sent to guide the free peoples against the rising shadow.', 2, (SELECT id FROM character_types WHERE name = 'Placeholder'), (SELECT id FROM character_races WHERE name = 'Placeholder'), (SELECT id FROM character_classes WHERE name = 'Placeholder'), (SELECT id FROM users WHERE name = 'Henk the Tank'), NOW(), NOW()),
       ('Aragorn son of Arathorn', 17, 18, 16, 14, 18, 15, 75, 75, 8500, 'Medium', 100, 50, 0, 150, 2, 'The hidden king, a ranger of the north destined to reclaim an ancient throne.', 2, (SELECT id FROM character_types WHERE name = 'Placeholder'), (SELECT id FROM character_races WHERE name = 'Placeholder'), (SELECT id FROM character_classes WHERE name = 'Placeholder'), (SELECT id FROM users WHERE name = 'Henk the Tank'), NOW(), NOW()),
       ('Legolas Greenleaf', 16, 14, 20, 13, 14, 16, 65, 65, 8200, 'Medium', 40, 60, 0, 120, 1, 'An elven prince with unmatched eyesight and deadly precision with the bow.', 2, (SELECT id FROM character_types WHERE name = 'Placeholder'), (SELECT id FROM character_races WHERE name = 'Placeholder'), (SELECT id FROM character_classes WHERE name = 'Placeholder'), (SELECT id FROM users WHERE name = 'Henk the Tank'), NOW(), NOW()),
       ('Gimli son of Gloin', 14, 20, 12, 11, 19, 13, 80, 80, 7800, 'Medium', 200, 40, 0, 100, 1, 'A proud dwarf warrior whose axe is as unyielding as his loyalty.', 1, (SELECT id FROM character_types WHERE name = 'Placeholder'), (SELECT id FROM character_races WHERE name = 'Placeholder'), (SELECT id FROM character_classes WHERE name = 'Placeholder'), (SELECT id FROM users WHERE name = 'Henk the Tank'), NOW(), NOW()),
       ('Frodo of the Shire', 15, 14, 16, 12, 9, 17, 45, 45, 7000, 'Small', 30, 80, 0, 60, 0, 'A small hobbit burdened with a task that will decide the fate of the world.', 2, (SELECT id FROM character_types WHERE name = 'Placeholder'), (SELECT id FROM character_races WHERE name = 'Placeholder'), (SELECT id FROM character_classes WHERE name = 'Placeholder'), (SELECT id FROM users WHERE name = 'Henk the Tank'), NOW(), NOW());

-- Encounter data
INSERT INTO encounters(title, description, round_number, state, current_actor_id, user_id, created_at, updated_at)
VALUES ('Battle in the Mines of Moria', 'The fellowship delves deep into the dark halls of Moria. Shadows move in the corridors, goblins attack without warning, and a greater evil lurks beyond the bridge. Gandalf’s wisdom guides, Aragorn’s blade strikes true, and Legolas’s arrows find their marks as the trio fights to survive the ancient dwarven halls.', 0, 0, null, (SELECT id FROM users WHERE name = 'Bert the Hurt'), NOW(), NOW()),
       ('Ambush at Helm''s Deep', 'The defenders of Rohan brace themselves against waves of orcs and uruk-hai. Aragorn rallies the troops while Legolas and Gimli hold the walls. The night is long, and victory seems uncertain as the battle rages under the shadow of the fortress.', 0, 0, null, (SELECT id FROM users WHERE name = 'Bert the Hurt'), NOW(), NOW());

-- Encounter participant data
INSERT INTO encounter_participants(initiative, encounter_id, character_id, created_at, updated_at)
VALUES (15, (SELECT id FROM encounters WHERE title = 'Battle in the Mines of Moria'), (SELECT id FROM characters WHERE name = 'Legolas Greenleaf'), NOW(), NOW()),
       (20, (SELECT id FROM encounters WHERE title = 'Battle in the Mines of Moria'), (SELECT id FROM characters WHERE name = 'Gandalf the Grey'), NOW(), NOW()),
       (18, (SELECT id FROM encounters WHERE title = 'Battle in the Mines of Moria'), (SELECT id FROM characters WHERE name = 'Aragorn son of Arathorn'), NOW(), NOW());