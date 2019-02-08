CREATE TABLE `oauth_access_token` (
`authentication_id` varchar(255) NOT NULL,
`token_id` varchar(255) NOT NULL,
`token` blob NOT NULL,
`user_name` varchar(255) NOT NULL,
`client_id` varchar(255) NOT NULL,
`authentication` blob NOT NULL,
`refresh_token` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `oauth_access_token`
ADD PRIMARY KEY (`authentication_id`);


CREATE TABLE `oauth_refresh_token` (
`token_id` varchar(255) NOT NULL,
`token` blob NOT NULL,
`authentication` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096)
);

create table oauth_client_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication_id VARCHAR(256),
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

create table oauth_code (
  code VARCHAR(256), authentication BLOB
);

create table ClientDetails (
  appId VARCHAR(256) PRIMARY KEY,
  resourceIds VARCHAR(256),
  appSecret VARCHAR(256),
  scope VARCHAR(256),
  grantTypes VARCHAR(256),
  redirectUrl VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096)
);

select * from oauth_access_token

select * from oauth_refresh_token


create table users(
username varchar(256) not null,
password varchar(256) not null,
enabled boolean
);

select * from users

insert into users(user_id,username,password,enabled) values(1,'user1','user1pwd',true)



create table user_entity_roles(
user_entity_username varchar(255) not null,
roles varchar(255)
);
insert into user_entity_roles(user_entity_username,roles) values ('user1','ADMIN')


insert into oauth_client_details(client_id,resource_ids,client_secret,scope,authorized_grant_types,authorities,access_token_validity) values ('client','oauth2-resource','client_secret','read,write,trust','client_credentials,password,refresh_token','ROLE_CLIENT',5600)

select * from oauth_client_details
