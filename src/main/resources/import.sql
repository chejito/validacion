INSERT INTO roles (name) VALUES ('USER');
INSERT INTO roles (name) VALUES ('ADMIN');

INSERT INTO users (fullname, username, email, password, validated) VALUES ('User User', '01234567H', 'user@user.com', '$2a$10$Vq7lBeLShnuk8VNbHA5FwesrbJH6loPkfbhBZWKfZCLKGua1w7nn.',  FALSE);
INSERT INTO users (fullname, username, email, password, validated) VALUES ('Admin Admin', '12345678I','admin@admin.com', '$2a$10$LoXeexnhr7pJzLGhL6ZgM.IozhCW1Qg3ujsXygc3V2vfIcyeo9UDK', TRUE);

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);


INSERT INTO hashcodes (user_id, time_stamp, hash) VALUES (1, '2021-12-10 18:00:00000', 1234567890);
INSERT INTO hashcodes (user_id, time_stamp, hash) VALUES (2, '2021-12-01 00:00:00000', 0987654321);
