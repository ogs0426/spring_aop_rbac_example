
create table if not exists persistent_logins ( 
  username varchar(100) not null, 
  series varchar(64) primary key, 
  token varchar(64) not null, 
  last_used timestamp not null
);

delete from  role_right;
delete from  user_role;
delete from  rights;
delete from  roles;
delete from  users;

INSERT INTO roles (id, name) VALUES
                                 (1, 'ROLE_ADMIN'),
                                 (2, 'ROLE_USER'),
                                 (3, 'ROLE_LIMIT'),
                                 (4, 'ROLE_STUDENT');

INSERT INTO rights (id, name) VALUES
                                  (1, 'Edit System'),
                                  (2, 'Access Network'),
                                  (3, 'Edis User'),
                                  (4, 'Read /foo /bar files');

INSERT INTO users (id, password, name) VALUES
                                           (1, 'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855', 'Admin'),
                                           (3, 'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855', 'User');

insert into user_role(user_id, role_id) values
                                            (1,1),
                                            (1,2),
                                            (1,3),
                                            (3,3);

insert into role_right(role_id, right_id) values
                                              (1,1),
                                              (1,2),
                                              (1,3),
                                              (1,4),
                                              (2,2),
                                              (2,3),
                                              (3,3),
                                              (4,2),
                                              (4,4);
