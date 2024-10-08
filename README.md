# **Make My Day API**

## **Overview**

Make My Day API is a RESTful backend application that allows users to search for events based on activity types and retrieve restaurant recommendations in London. It utilises Geoapify, Ticketmaster, and Skiddle APIs.

## **Table of Contents**

* Technologies
* Features
* Installation
* API Endpoint
* Internal API calls
* Error Handling

## **Technologies**

* Java 21
* Spring Boot
* Spring Security
* Spring Web
* PostgreSQL
* JWT (JSON Web Tokens)
* Jackson
* GSON
* OkHttp
* Validation

## **Features**

* User authentication - sign up and login.
* Retrieve restaurants and events by activity type - Sports, Music, Arts & Theatre, Wellness, Landmarks.
* Custom exception handling with meaningful error responses.

## **Installation**

1. Clone the repository: git clone https://github.com/jonquil-NC/make-my-day-api.git
2. cd make-my-day-api
3. Set up your database
4. Update src/main/resources/application-dev.properties with your database configuration and JWT secret.
5. Build the application
6. Run the application 

## **Main API Endpoint**

Main API Endpoint
The Make My Day API provides a single endpoint for retrieving activities from external services like Ticketmaster, Skiddle, as well as fetching relevant places and restaurants. This endpoint coordinates the internal API calls based on user search parameters.

###### POST Method

Base URL: /api/v1

* Description: This is the main endpoint the frontend calls to retrieve a list of activities based on user search parameters.
* Request Body: Accepts a SearchParams object to filter activities.
* Response: A list of filtered activities.

## **Internal API Calls**

### Activities

###### GET Method

Base URL: /api/v1/activities

* Description: Retrieves events filtered by activity types from external APIs (Ticketmaster and Skiddle).
* Query Parameters: type - A list of activity types to filter events.

### Places

Base URL: /api/v1/places

* Description: Retrieves places such as parks, landmarks, or wellness locations based on the type specified in the path variable.
* Fetches places by type(park, landmark, wellness)

###### GET Method

/api/v1/places/{type}

### Geoapify Restaurants

###### GET Method

* Base URL: /api/v1/geoapify/restaurants
* Description: Retrieves restaurants from Geoapify filtered by a specified type of cuisine or restaurant category.

###### GET Method

Fetches restaurants by type:
/api/v1/geoapify/restaurants/{type}


### Events from Skiddle

###### GET Method

* Base URL: /api/v1/skiddle
* Description: Integrates with Skiddle API to fetch events.

Fetch events filtered by activity type:
###### GET Method
/api/v1/skiddle/events

### Events from Ticketmaster
###### GET Method
* Base URL: /api/v1/ticketmaster
* Description: Integrates with Ticketmaster API to fetch events.

###### GET Method
* Fetch events filtered by activity type:/api/v1/ticketmaster/events

### User Management
* Base URL: /api/v1/makemyday
* Description: Handles user-related actions such as login and registration.


**User login:**
###### POST Method
/api/v1/makemyday/sign-in

**User registration:**
###### POST Method
/api/v1/makemyday/sign-up

## Error Handling
The application includes a global exception handler to provide user-friendly error messages. 
Errors are returned as JSON objects. 


