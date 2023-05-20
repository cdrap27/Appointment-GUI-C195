Title: Appointment Scheduling Application

Purpose:  The purpose of this application is to communicate with the SQL Database and make scheduling/viewing appointments easier through the use of Java FX

Author: Chad Draper   

Contact: Chad.Draper37@gmail.com  

Version 1.0  

1/8/2023  
  
(Everything was done on the virtual machine lab area)  
IntelliJ IDEA 2021.1.3 (Community Edition)  
Build #IC-211.7628.21, built on June 30, 2021  
Runtime version: 11.0.11+9-b1341.60 amd64  
VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.  

Java Version 17.0.1  
  
Java FX Version: javaFX-SDK-17.0  

What I learned:  
Thorough understanding of creating a GUI using Scene Builder and editing it using Java.    
How to communicate with an SQL database using Java as well as a GUI for easier entry/access.  
How to write code on multiple machines using GitHub/version control.  
Much greater confidence and understanding of programming using Java.  

Directions:   
Login using 'test' as username and password. Test data is automatically added and lists for all database information are created.  
Delete the test appointments and restart the application, this will add test data as 15 minutes from the current time, triggering appointment alerts.  
Appointment alerts are displayed in separate alerts for each appointment.   
Two table views on the dashboard, one for customers, one for appointments.  Both have buttons to add, modify, and delete entries.  Appointments has a radio button that can be used to get appointments by month(30 day period) and week(7 day period).  
Reports button shows 3 tableviews showing 3 different reports.  
Language is automatically translated for the login page only.  
Text file is created or edited to track login attempts.   

Additional Report:  
I created an additional report that displays customers based on their country.  Their are three customers by default in three different regions. 
Customer country can be modified or additional customer can be added to display multiple customers for each country.  
The report also counts the number of customers by country and displays the count at the top of the table.  
This would be more useful for a larger list of customers, but it will still work for a small or large list of customers.  
This report is useful when trying to contact all customers from a specific country or find out which country holds the most customers.   

MYSQL Connector driver version number: mysql-connector-java-8.0.25  

Once I understood how to use lambdas, I found them to be extremely useful.  I ended up using 10 Lambda Expressions.  Here are there locations.  
Lambda Expression 1: Located in Model/Appointment.java line 683  
function toLocalTime  
Lambda Expression 2: Located in Model/Appointment.java line 700  
function toUTC  
Lambda Expression 3: Located in Model/Appointment.java line 752  
function getMonths  
Lambda Expression 4: Located in Model/Appointment.java line 768  
function getContactSchedule  
Lambda Expression 5: Located in Model/Contacts.java line 83  
function contactNames  
Lambda Expression 6: Located in Model/Countries.java line 63  
function countryNames  
Lambda Expression 7: Located in Model/Customers.java line 136  
function customerNames  
Lambda Expression 8: Located in Model/Customers.java line 365  
function customersByCountry  
Lambda Expression 9: Located in Model/Division.java line 87  
function divisionNames  
Lambda Expression 10: Located in Model/Users.java line 81  
function userNames  
