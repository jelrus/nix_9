INSERT INTO users
values ('ADMIN', 1,  CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
        true, 'Communicator. Passionate web fanatic. Coffee expert. Organizer. Avid student. Total entrepreneur. Internet practitioner. Friendly creator.',
        true, 'Alex', 'Church', '$2a$10$//54UM2W6fIGN4E9clcatetVbQ0OyaplGy8oSD.J/DiRKU712BY4C',
        'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png', 'ROLE_ADMIN', 'alex_church');

INSERT INTO users
values ('STANDARD_USER', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
        true, 'Infuriatingly humble pop culture maven. Hardcore bacon practitioner. Food fanatic. Unapologetic analyst. Avid reader.',
        true, 'Mysha', 'Mueller', '$2a$10$//54UM2W6fIGN4E9clcatetVbQ0OyaplGy8oSD.J/DiRKU712BY4C',
        'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png', 'ROLE_STANDARD_USER', 'muller');

INSERT INTO posts
values (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
        true, 'fixing a worker shortage problem',
        20, 'https://preview.redd.it/8hc2w9cjxef81.jpg?width=960&crop=smart&auto=webp&s=6748fe3b9e7aa5b33cec049840c0970469e34983' , 30, 10, '', 1);

INSERT INTO posts
values (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
        true, '',  30, 'https://i.redd.it/cz9hzadh7ff81.jpg' , 30, 0, 'Imagine.', 2);

INSERT INTO posts
values (3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
        true, '',  26, '' , 19, -7, 'My wife put her two weeks notice in today because we only have one car so she''s
                                     going to look for a place closer to home so she can use the city bus.', 1);

INSERT INTO posts
values (4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
        true, 'Robbery',  3, 'https://nypost.com/wp-content/uploads/sites/2/2018/06/180622-bank-robber.jpg?quality=80&strip=all' , 30, 27, 'Vegas bartender robbed at gunpoint was forced by his employer to sign a contract
                                    agreeing to pay back the $3,937.35 stolen.', 2);

INSERT INTO posts
values (5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
        true, 'Check out what Home Depot does to its employees.',  2,
        'https://preview.redd.it/qtwsags5sbf81.jpg?width=640&crop=smart&auto=webp&s=9ad05b0e3dfc76027d940f75ff9c09b1c7d881bd' ,
        5, 3, '', 1);

INSERT INTO posts
values (6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
        true, 'Real talk and actual facts.',  21, 'https://i.redd.it/n9r9k3iilbf81.png' , 15, -6, '', 2);

INSERT INTO comments
values (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
        true, '“I can do no wrong, for I am without sin” - Probably some 7 year old kid with a bible strapped to
                his chest yelling multiple racial slurs in a COD lobby', 1, 2);

INSERT INTO comments
values (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
        true, 'Its no coincidence that all the Q-idiots are religious.
                Another thing that will hopefully die out faster when boomers are gone.', 2, 1);

INSERT INTO comments
values (3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
        true, 'When I worked in a grocery store, we dreaded the few hours right around 11:00 am on Sunday.
               The level of entitlement was obscene.', 3, 2);

INSERT INTO comments
values (4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
        true, 'No tip, no service. If I recognized that person, they''d never get a warm
               uncontaminated meal from me ever again.', 4, 1);

INSERT INTO comments
values (5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
        true, 'Once you get 20 years in……they’re also the cheapest sons of *******.
              “Oh sorry! I gave all my money to Jesus!”', 5, 2);

INSERT INTO comments
values (6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
        true, 'I used to get 10% tips on Sundays religiously(pun intended). Went on to Amazon and bought a 50 pack of
               pens that said.', 6, 1);

INSERT INTO comments
values (7, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
        true, 'Or when they leave you the tiny jesus brochure in place of a tip…. Total ******', 1, 2);