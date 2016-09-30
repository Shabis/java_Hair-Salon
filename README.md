# Hair Salon
------

In this application the administrator has the ability to input new stylists with name and days that they work. When a stylist is selected they have the ability to add/edit/delete clients.

<br/>

### Setup/Installation Instructions
------
* In PSQL:
  * CREATE DATABASE hair_salon;
  * CREATE TABLE stylists (id serial PRIMARY KEY, name varchar, schedule varchar );
  * CREATE TABLE clients (id serial PRIMARY KEY, name varchar, appointment varchar, service varchar, stylist int );
  * CREATE DATABASE hair_salon_test WITH TEMPLATE hair_salon;
* In command prompt enter: git clone https://github.com/Shabis/java_Hair-Salon.git
* In command prompt enter: cd java_Hair-Salon
* In command prompt enter: gradle run
* In web browser navigate to: http://localhost:4567


<br/>

### Known Bugs
------

No known bugs.

<br/>

### Specifications
------

| BEHAVIOR                       | INPUT                  | OUTPUT                |
|--------------------------------|------------------------|-----------------------|
| Add Stylist to Database         | Name, Schedule       | Name, Schedule      |
| Add Client to Database        | Name, Appointment, Appt Reason, Stylist  | Name, Appointment, Appt Reason, Stylist |
| See All Stylists                | Navigate to index      | Stylist List           |
| See all Clients of one Stylist | Click on Stylist Name   | List of Clients      |
| Add Client to Stylist          | Name, Appointment, Appt Reason          | Name, Appointment, Appt Reason, Stylist |
| See Client details            | Click on Name          | Name, Appointment, Appt Reason, Stylist |
| Edit Client Details           | Name, Appointment, Appt Reason          | Name, Appointment, Appt Reason, Stylist |
| Delete Client                 | Click 'Delete Client' | Stylists Client List  |
<br/>

### Support and Contact Details
------

If you have any questions, please contact me at Shelby_Clayton@hotmail.com.

<br/>

### Technology Used
------

In this project I used java, gradle, GitHub, Atom, velocity, Spark, PostgreSQL, junit and Markdown.

<br/>


Copyright (c) 2016 **_Shelby Clayton_**
