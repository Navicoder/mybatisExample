CREATE TABLE role (
  id               INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name             VARCHAR(50) NOT NULL UNIQUE,
  description      VARCHAR(100),
  is_active        BOOLEAN     NOT NULL DEFAULT TRUE,
  last_update_time TIMESTAMP            DEFAULT current_timestamp ON UPDATE current_timestamp
);

INSERT INTO role (name, description, is_active, last_update_time)
VALUES ('ROLE_ADMIN', 'Administrator', TRUE, current_timestamp);
INSERT INTO role (name, description, is_active, last_update_time)
VALUES ('ROLE_USER', 'User', TRUE, current_timestamp);
CREATE TABLE menu (
  id               INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  value            VARCHAR(100) NOT NULL,
  display_value    VARCHAR(100) NOT NULL,
  url              VARCHAR(100) NOT NULL,
  category         INT,
  description      VARCHAR(100),
  is_active        BOOLEAN      NOT NULL DEFAULT TRUE,
  last_update_time TIMESTAMP             DEFAULT current_timestamp ON UPDATE current_timestamp
);

INSERT INTO menu (value, display_value, url, description, is_active, last_update_time)
VALUES ('/admin/dashboard', 'Admin Dashboard', '/admin/dashboard', 'Admin Dashboard', TRUE, current_timestamp);

INSERT INTO menu (value, display_value, url, description, is_active, last_update_time)
VALUES ('/admin/profile', 'Admin Profile', '/admin/profile', 'Admin Profile', TRUE, current_timestamp);


INSERT INTO menu (value, display_value, url, description, is_active, last_update_time)
VALUES ('/user/dashboard', 'User Dashboard', '/user/dashboard', 'User Dashboard', TRUE, current_timestamp);

INSERT INTO menu (value, display_value, url, description, is_active, last_update_time)
VALUES ('/user/profile', 'User Profile', '/user/profile', 'User Profile', TRUE, current_timestamp);

CREATE TABLE role_menu_xref (
  id               INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  role_id          INT NOT NULL,
  menu_id          INT NOT NULL,
  last_update_time TIMESTAMP    DEFAULT current_timestamp ON UPDATE current_timestamp,
  CONSTRAINT FOREIGN KEY fk_role_menu_xref_role_id (role_id) REFERENCES role (id),
  CONSTRAINT FOREIGN KEY fk_role_menu_xref_menu_id(menu_id) REFERENCES menu (id),
  CONSTRAINT UNIQUE (role_id, menu_id)
);

INSERT role_menu_xref (role_id, menu_id, last_update_time)
VALUES (1, 1, current_timestamp);

INSERT role_menu_xref (role_id, menu_id, last_update_time)
VALUES (1, 2, current_timestamp);

INSERT role_menu_xref (role_id, menu_id, last_update_time)
VALUES (2, 3, current_timestamp);

INSERT role_menu_xref (role_id, menu_id, last_update_time)
VALUES (2, 4, current_timestamp);

