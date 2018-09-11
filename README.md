# Bloodpool
Blood Donation Website

This web Application aims at amassing a group of blood donors and connecting them with the patients in need of blood, so that the required amount of blood can reach the patient in time. 

This application is designed to allow a user to submit a request for a patient and notify other users in vicinity of the hospital of the requirement of the patient.  The eligibility of the user to donate blood is also determined from various factors such as age, and the last time he/she had donated blood.

Google App Engine 4.5 Data Store: All the entities of BloodPool i.e. User Details Entity and Patient Details Entity are stored in the Google App Engine Data Store. These data items enter into the application from the Data Store whenever required. 
Google Maps API V3: The Google Maps API has been used for displaying markers and calculating distances in our application. The addresses from the Data Store are passed to the API for calculating distances and distances are retrieved from the API to our application. The distances between the hospital and addresses of the registered donors are calculated using Google Maps Distance Matrix API.

Website link: :  http://2-dot-bloodpool-181813.appspot.com 
