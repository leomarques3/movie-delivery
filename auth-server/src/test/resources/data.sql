insert into user_account(id, first_Name, last_Name, username, email, password, account_Non_Expired, account_Non_Locked, credentials_Non_Expired, enabled) values (1, 'Leonardo', 'Marques', 'leochassot3', 'leonardo.marques@test.com', '$2a$10$eLM/8ETTN8H.w7V/JfZfZe/nFgr9R7k3NetFErSrA5tpobvKNPyde', true, true, true, true);
insert into user_account(id, first_Name, last_Name, username, email, password, account_Non_Expired, account_Non_Locked, credentials_Non_Expired, enabled) values (2, 'John', 'Doe', 'johndoe', 'john.doe@test.com', '$2a$10$eLM/8ETTN8H.w7V/JfZfZe/nFgr9R7k3NetFErSrA5tpobvKNPyde', true, true, true, true);
insert into user_account(id, first_Name, last_Name, username, email, password, account_Non_Expired, account_Non_Locked, credentials_Non_Expired, enabled) values (3, 'Fulano', 'Silva', 'fulanosilva', 'fulano.silva@test.com', '$2a$10$eLM/8ETTN8H.w7V/JfZfZe/nFgr9R7k3NetFErSrA5tpobvKNPyde', true, true, true, true);
insert into user_authority(id, authority) values(1, 'admin');
insert into user_authority(id, authority) values(2, 'user');
insert into user_authority(id, authority) values(3, 'guest');
insert into user_role(user_id, role_id) values(1, 1);
insert into user_role(user_id, role_id) values(2, 2);
insert into user_role(user_id, role_id) values(3, 2);