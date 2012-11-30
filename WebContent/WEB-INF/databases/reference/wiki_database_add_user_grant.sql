use wiki;

CREATE USER 'glider'@'localhost' IDENTIFIED BY 'glider';

GRANT ALL PRIVILEGES ON *.* TO 'glider'@'localhost'
WITH GRANT OPTION;


CREATE USER 'glider'@'%' IDENTIFIED BY 'glider';


GRANT ALL PRIVILEGES ON *.* TO 'glider'@'%'
WITH GRANT OPTION;
