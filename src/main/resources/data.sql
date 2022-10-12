
INSERT INTO customer (identification_number, name, creation_date, is_active) VALUES ('0103107066', 'HELBER GALINDO',  now(), true); 
INSERT INTO customer (identification_number, name, creation_date, is_active) VALUES ('US123456', 'JAMES HETFIELD',  now(), true);
INSERT INTO customer (identification_number, name, creation_date, is_active) VALUES ('AC12332', 'ROSA ESTHELA NUDOH',  now(), true); 

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

INSERT INTO device(system_name, device_type_id, creation_date, is_active) VALUES ('WINDOWS WORKSTATION HP-340', 1, now(), true); 
INSERT INTO device(system_name, device_type_id, creation_date, is_active) VALUES ('WINDOWS WORKSTATION MN 123', 1, now(), true); 
INSERT INTO device(system_name, device_type_id, creation_date, is_active) VALUES ('WINDOWS SERVER 2010 CF', 2, now(), true); 
INSERT INTO device(system_name, device_type_id, creation_date, is_active) VALUES ('WINDOWS DESKTOP PC 56', 3, now(), true); 
INSERT INTO device(system_name, device_type_id, creation_date, is_active) VALUES ('MAC DESKTOP 123', 4, now(), true); 
INSERT INTO device(system_name, device_type_id, creation_date, is_active) VALUES ('MAC LAPTOP PRO', 5, now(), true); 
INSERT INTO device(system_name, device_type_id, creation_date, is_active) VALUES ('MAC LAPTOP 789', 5, now(), true); 
INSERT INTO device(system_name, device_type_id, creation_date, is_active) VALUES ('MAC TABLET AA', 6, now(), true);
INSERT INTO device(system_name, device_type_id, creation_date, is_active) VALUES ('MAC TABLET XJ', 6, now(), true);
INSERT INTO device(system_name, device_type_id, creation_date, is_active) VALUES ('LINUX SERVER 123', 7, now(), true);
INSERT INTO device(system_name, device_type_id, creation_date, is_active) VALUES ('LINUX SERVER ABC', 7, now(), true);

INSERT INTO customer_device(customer_device_name, serial_number, description, customer_id, device_id, creation_date, is_active) VALUES ('WINDOWS WORKSTATION SALES DEPARTMENT', '123456', 'Workstation for sales department and human resources area', 1, 1, now(), true);
INSERT INTO customer_device(customer_device_name, serial_number, description, customer_id, device_id, creation_date, is_active) VALUES ('WINDOWS MAIN SERVER', 'ACV344', 'Enterprise main server', 1, 3, now(), true);
INSERT INTO customer_device(customer_device_name, serial_number, description, customer_id, device_id, creation_date, is_active) VALUES ('WINDOWS SECRETARY PC', 'GB677YYUU1', 'Enterprise main server', 1, 4, now(), true);
INSERT INTO customer_device(customer_device_name, serial_number, description, customer_id, device_id, creation_date, is_active) VALUES ('MANAGER LAPTOP', '3216544', '', 1, 6, now(), true);
INSERT INTO customer_device(customer_device_name, serial_number, description, customer_id, device_id, creation_date, is_active) VALUES ('SALES MANAGER LAPTOP', '12455', '', 1, 9, now(), true);
INSERT INTO customer_device(customer_device_name, serial_number, description, customer_id, device_id, creation_date, is_active) VALUES ('SALES MAN TABLET', '20122FG25', '', 1, 7, now(), true);
INSERT INTO customer_device(customer_device_name, serial_number, description, customer_id, device_id, creation_date, is_active) VALUES ('CUSTOMERS SERVER', 'LXNM122', '', 2, 10, now(), true);
INSERT INTO customer_device(customer_device_name, serial_number, description, customer_id, device_id, creation_date, is_active) VALUES ('COMPANY PRESIDENT LAPTOP', 'PM222334', 'Just to be used at home', 2, 7, now(), true);
INSERT INTO customer_device(customer_device_name, serial_number, description, customer_id, device_id, creation_date, is_active) VALUES ('COMPANY TI CONSULTANT PC', '9587455', '', 2, 5, now(), true);

INSERT INTO service (name, creation_date, is_active) VALUES ('ANTIVIRUS', now(), true); 
INSERT INTO service (name, creation_date, is_active) VALUES ('BACK UP', now(), true); 
INSERT INTO service (name, creation_date, is_active) VALUES ('PSA', now(), true); 
INSERT INTO service (name, creation_date, is_active) VALUES ('SCREEN SHARE', now(), true);

INSERT INTO customer_service(customer_id, service_id, creation_date, is_active) VALUES (1, 1, now(), true); 
INSERT INTO customer_service(customer_id, service_id, creation_date, is_active) VALUES (1, 2, now(), true); 
INSERT INTO customer_service(customer_id, service_id, creation_date, is_active) VALUES (1, 3, now(), true); 
INSERT INTO customer_service(customer_id, service_id, creation_date, is_active) VALUES (1, 4, now(), true);
INSERT INTO customer_service(customer_id, service_id, creation_date, is_active) VALUES (2, 1, now(), true);
INSERT INTO customer_service(customer_id, service_id, creation_date, is_active) VALUES (2, 2, now(), true);
INSERT INTO customer_service(customer_id, service_id, creation_date, is_active) VALUES (2, 3, now(), true);

INSERT INTO service_device_cost(device_type_id, cost, creation_date, is_active) VALUES (null, 4, now(), true);

INSERT INTO service_cost(service_cost_type, service_id, cost, creation_date, is_active) VALUES ('service_cost', 2, 3, now(), true);
INSERT INTO service_cost(service_cost_type, service_id, cost, creation_date, is_active) VALUES ('service_cost', 3, 2, now(), true);
INSERT INTO service_cost(service_cost_type, service_id, cost, creation_date, is_active) VALUES ('service_cost', 4, 1, now(), true);
INSERT INTO service_cost(service_cost_type, service_id, cost, creation_date, is_active) VALUES ('service_operating_system_group_cost', 1, 5, now(), true);
INSERT INTO service_cost(service_cost_type, service_id, cost, creation_date, is_active) VALUES ('service_operating_system_group_cost', 1, 7, now(), true);
INSERT INTO service_cost(service_cost_type, service_id, cost, creation_date, is_active) VALUES ('service_operating_system_group_cost', 1, 6, now(), true);

INSERT INTO service_operating_system_group_cost(id, operating_system_group_id) VALUES (4,1);
INSERT INTO service_operating_system_group_cost(id, operating_system_group_id) VALUES (5,2);
INSERT INTO service_operating_system_group_cost(id, operating_system_group_id) VALUES (6,3);








