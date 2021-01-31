drop table if exists coach CASCADE; 
drop table if exists passenger CASCADE ;

create table coach (

coachid bigint generated by default as identity, 
start_point varchar(255), 
end_point varchar(255), 
departure_time varchar(255), 
arrival_time varchar(255), 
capacity integer check (capacity>=10), 
ticket_cost float check (ticket_cost<=20), 
primary key (coachid));


create table passenger (

passengerid bigint generated by default as identity,
first_name varchar(20) not null, 
last_name varchar(30) not null, 
email varchar(255) not null, 
password varchar(255) not null, 
address varchar(255) not null, 
postcode varchar(8) not null,
number_of_tickets integer not null check (number_of_tickets<=9), 
total_cost float not null, 
coach_coachid bigint, 
primary key (passengerid));



alter table passenger add constraint FKi20mfnrm9qht3700p84xyya43 foreign key (coach_coachid) references coach on delete cascade;
