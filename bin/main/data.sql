INSERT INTO SAMPLE VALUES('1', 'VALUE1');
INSERT INTO SAMPLE VALUES('2', 'VALUE2');

INSERT INTO operating_system_group (name, creation_date, is_active) VALUES ('WINDOWS', now(), true); 
INSERT INTO operating_system_group (name, creation_date, is_active) VALUES ('MAC OS', now(), true); 
INSERT INTO operating_system_group (name, creation_date, is_active) VALUES ('LINUX', now(), true); 

INSERT INTO device_type(name, operating_system_group_id, creation_date, is_active) VALUES ('WINDOWS WORKSTATION', 1, now(), true); 
INSERT INTO device_type(name, operating_system_group_id, creation_date, is_active) VALUES ('WINDOWS SERVER', 1, now(), true); 
INSERT INTO device_type(name, operating_system_group_id, creation_date, is_active) VALUES ('WINDOWS DESKTOP', 1, now(), true); 
INSERT INTO device_type(name, operating_system_group_id, creation_date, is_active) VALUES ('MAC DESKTOP', 2, now(), true); 
INSERT INTO device_type(name, operating_system_group_id, creation_date, is_active) VALUES ('MAC LAPTOP', 2, now(), true);
INSERT INTO device_type(name, operating_system_group_id, creation_date, is_active) VALUES ('MAC TABLET', 2, now(), true);
INSERT INTO device_type(name, operating_system_group_id, creation_date, is_active) VALUES ('LINUX SERVER', 3, now(), true);

INSERT INTO device(name, operating_system_group_id, creation_date, is_active) VALUES ('WINDOWS WORKSTATION', 1, now(), true); 
