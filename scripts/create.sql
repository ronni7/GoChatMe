create table channel (channelid bigint not null auto_increment, active bit not null, adults_only bit not null, description varchar(255), name varchar(255), primary key (channelid)) engine=MyISAM
create table message_output (id bigint not null auto_increment, sender varchar(255), text varchar(255), time varchar(255), channelid bigint, primary key (id)) engine=MyISAM
create table private_channel (channelid bigint not null auto_increment, accepted bit, token varchar(255), primary key (channelid)) engine=MyISAM
create table private_message_output (id bigint not null auto_increment, sender varchar(255), text varchar(255), time varchar(255), channelid bigint, primary key (id)) engine=MyISAM
create table user (id bigint not null auto_increment, email varchar(255), login varchar(255), name varchar(255), nickname varchar(255), password varchar(255), surname varchar(255), primary key (id)) engine=MyISAM
alter table message_output add constraint FKqdifdarkta82d7kxl24w4wtas foreign key (channelid) references channel (channelid)
alter table private_message_output add constraint FK1bubrcwhghh0ofud7so03gpvl foreign key (channelid) references private_channel (channelid)
create table channel (channelid bigint not null auto_increment, active bit not null, adults_only bit not null, description varchar(255), name varchar(255), primary key (channelid)) engine=MyISAM
create table message_output (id bigint not null auto_increment, sender varchar(255), text varchar(255), time varchar(255), channelid bigint, primary key (id)) engine=MyISAM
create table private_channel (channelid bigint not null auto_increment, accepted bit, token varchar(255), primary key (channelid)) engine=MyISAM
create table private_message_output (id bigint not null auto_increment, sender varchar(255), text varchar(255), time varchar(255), channelid bigint, primary key (id)) engine=MyISAM
create table user (id bigint not null auto_increment, email varchar(255), login varchar(255), name varchar(255), nickname varchar(255), password varchar(255), surname varchar(255), primary key (id)) engine=MyISAM
alter table message_output add constraint FKqdifdarkta82d7kxl24w4wtas foreign key (channelid) references channel (channelid)
alter table private_message_output add constraint FK1bubrcwhghh0ofud7so03gpvl foreign key (channelid) references private_channel (channelid)
