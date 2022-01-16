INSERT INTO clients
VALUES (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Kirk', 'Bubba', 'biba20cm@fakemail.com', '+380982281488');

INSERT INTO clients
VALUES (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Hairy', 'Trueman', 'lavash@fakemail.com', '+380992496661');

INSERT INTO accounts
values (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '1234-5678-9012-3456', 2500.00);

INSERT INTO accounts
values (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '098-7654-3210-9876', 2500.00);

INSERT INTO operations
values (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP(), 'Transfer1', 'OUTCOME', 'on something', 235.00);

INSERT INTO operations
values (2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP(), 'Transfer2', 'INCOME', 'on something good', 135.00);

INSERT INTO operations
values (3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP(), 'Transfer3', 'OUTCOME', 'on something very good', 435.00);

INSERT INTO operations
values (4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP(), 'Transfer4', 'INCOME', 'on something realy very good', 335.00);

INSERT INTO client_accounts
values (1,1);

INSERT INTO client_accounts
values (2,2);

INSERT INTO account_operations
values (1, 1);

INSERT INTO account_operations
values (1, 2);

INSERT INTO account_operations
values (2, 3);

INSERT INTO account_operations
values (2, 4);