CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password varchar(255) NOT NULL
);

CREATE TABLE roles (
    role_id INT AUTO_INCREMENT PRIMARY KEY,
    authority VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE user_role (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(role_id) ON DELETE CASCADE
);