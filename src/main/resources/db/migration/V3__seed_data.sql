INSERT INTO patrons (username, password, role) VALUES 
('admin', '$2a$12$QVO0yZGVEagedZUR0ikDfeBQ1PxkDg0iO72vIv6e4wDq0k8hXk5Re', 'ADMIN'),
('user', '$2a$12$X6w7eRQUBGbpWc5nGehLPuGDoDHpnporQnZsdZAmEziB31eVctrsq', 'USER');

INSERT INTO booths (number, capacity, zone) VALUES 
(1, 4, 'VIP_LOUNGE'),  -- The Gecko Booth
(2, 6, 'STAGE'),       -- The Vampire Table
(3, 8, 'MAIN_HALL'),   -- The Titty Twister
(4, 2, 'VIP_LOUNGE'),  -- The Salma Hayek
(5, 4, 'MAIN_HALL'),   -- The Snake
(6, 6, 'PATIO'),       -- The Jackal
(7, 2, 'BACK_ROOM'),   -- The Crucifix
(8, 4, 'PATIO');       -- The Sunrise

INSERT INTO shift_templates (name, start_time, end_time) VALUES 
('Sunset', '18:00', '20:00'),
('Dusk', '20:00', '22:00'),
('Midnight', '22:00', '00:00'),
('Dawn', '00:00', '04:00');