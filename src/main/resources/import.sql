INSERT INTO roles (name) VALUES ('USER');
INSERT INTO roles (name) VALUES ('ADMIN');

INSERT INTO users (fullname, username, email, password, validated) VALUES ('User User', '01234567H', 'user@user.com', '$2a$10$Vq7lBeLShnuk8VNbHA5FwesrbJH6loPkfbhBZWKfZCLKGua1w7nn.', FALSE);

INSERT INTO users (fullname, username, email, password, validated) VALUES ('Admin Admin', '12345678I','admin@admin.com', '$2a$10$LoXeexnhr7pJzLGhL6ZgM.IozhCW1Qg3ujsXygc3V2vfIcyeo9UDK', TRUE);

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);
