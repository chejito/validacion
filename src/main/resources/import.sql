INSERT INTO roles (name) VALUES ('USER');
INSERT INTO roles (name) VALUES ('ADMIN');

INSERT INTO users (fullname, username, email, password, url_dni1, url_dni2, validated) VALUES ('User User', '01234567H', 'user@user.com', '$2a$10$Vq7lBeLShnuk8VNbHA5FwesrbJH6loPkfbhBZWKfZCLKGua1w7nn.', 'https://res.cloudinary.com/validacion-rest-api-env/image/upload/v1638116636/sxfn0bwoqevo44e1fj80.png', 'https://res.cloudinary.com/validacion-rest-api-env/image/upload/v1638116637/x48tgj7aicp7tm5q9x9m.png', TRUE);
INSERT INTO users (fullname, username, email, password, url_dni1, url_dni2, validated) VALUES ('User2 User2', '23456789J', 'user2@user.com', '$2a$10$rjkCs8JROMfbMpF4s4ue/es4ExOMD2iVHpvJ3n0vaBnVnVAAmu7py', 'https://res.cloudinary.com/validacion-rest-api-env/image/upload/v1638117190/ublzeu588dsdm6voenxb.png', 'https://res.cloudinary.com/validacion-rest-api-env/image/upload/v1638117191/vsrei6ig3vfkrn2euovp.png', FALSE);
INSERT INTO users (fullname, username, email, password, validated) VALUES ('User3 User3', '34567890K', 'user3@user.com', '$2a$10$RO8WXc5GEhQO7HVEK3q6le5fOz6uJD4U7c2o3pLVfTLwFF0Xasmru', FALSE);
INSERT INTO users (fullname, username, email, password, validated) VALUES ('User4 User4', '45678901L', 'user4@user.com', '$2a$10$yZEu9bmWgbcqV3kQMXjtreF7qqrfORPyMp9gtd8NnPkValG2IDO32', FALSE);
INSERT INTO users (fullname, username, email, password, validated) VALUES ('User5 User5', '56789012M', 'user5@user.com', '$2a$10$s857XrXd7fjVmlc6W2x4xOVt3MhOzYidRFYQxEsinIR/6Dznj.4aC', FALSE);

INSERT INTO users (fullname, username, email, password, validated) VALUES ('Admin Admin', '12345678I','admin@admin.com', '$2a$10$LoXeexnhr7pJzLGhL6ZgM.IozhCW1Qg3ujsXygc3V2vfIcyeo9UDK', TRUE);
INSERT INTO users (fullname, username, email, password, validated) VALUES ('Admin2 Admin', '67890123N','admin2@admin.com', '$2a$10$DPulmKxF8Xd84gM1mErbnuImaBwuveTCzylE94Zb8Yq2dpN6VpgfO', TRUE);

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (3, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (4, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (5, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (6, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (6, 2);

INSERT INTO user_roles (user_id, role_id) VALUES (7, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (7, 2);
