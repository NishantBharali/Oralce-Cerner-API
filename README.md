# Idea-Repository - API
An API for an idea portal (C.R.U.D. Application) application where a user can add, view, update and delete his/her own ideas as per their requirement with user authentication and authorization, made for a project component.

# About
Ideas Repository will act as a central hub for any user to quickly jot down any project ideas they 
have.

# React + Springboot + MySQL
This project is an integration of Springboot, MySQL database and React app. Semantics-UI has been implemented for the beautification.

# Functionalities
The project is aimed to develop an app where the user will be able to login and have the option to 
add/delete/edit project ideas.

# Requirements
1. Have maven installed and mvn added to path.
2. Have an instance of mysql server, prefereably running on localhost:3306

# Running the code
1. Import the project from github
2. Open the application.properties file and modify the database user, password and url as needed.
3. In the project directory, open terminal and run the command mvn clean package
4. goto the target directory which must now contain a JAR file.
5. Run the jar file using java -jar filename.jar
6. Open Java doc Documentation folder for a doc for all important classes and methods.
7. Open Jenkinsfile to use pipeline groovy scripts to build, test and deploy the project (both backend and frontend).

JWT for Authentication and Authorization  
===========================================
On successful login, clinet recieves a JWT token included 'Authorization' header from server and sets that token up in the local storage. On subsequent requests, this token has been sent back to server as an 'Authorization' header and validated for authorization.On logout, this token is removed from local storage. 

Registration Credentials Format
=========================
email: xyz@domain.com

password: PAssword@001 (Minimum 8 characters, One uppercase letter, One special character and one numeral are the minimum requirements)

confirm password: PAssword@001 (should match with the password above)

Login Credentials Format
=========================
username: "xyz@domain.com"

password: PAssword@001

Description
====================
* UI is a react app in Javascript making asynchronous ajax calls to REST APIs using React-Redux and Redux-Saga. 
* API is a spring boot REST API fetching data from a MySQL database table through JPA (Hibernate) repository.

Developed Using
===================
<pre>
* React - A JavaScript library for building user interfaces
* Semantic UI - Semantic is a development framework that helps create beautiful, responsive layouts using human-friendly HTML.
* Maven - Dependency Management
* ES6 – The next version of JavaScript. Allows us to write real JavaScript classes.
* JSX – Allows us to place HTML in JavaScript without concatenating strings.
* Webpack – Allows us to pack all of our JavaScript files into one bundle
* Springboot - RESTful Spring Boot Microservices with Spring Data JPA (Spring Data REST)
* Hibernate - JPA provider
* MySQL - Database
* JWT – For token based user authentication and authorization
</pre>

Prerequisites
=================
<pre>
* Install node 
* Install npm 
* Install Java (jdk 1.8 is preferred)
* Install Maven 3.x
* Install Eclipse or Spring Tool Suite
* Install Visual Studio Code
</pre>
Notes
============
 * I created this React app named **experiment** using <code>create-react-app</code>. In the case of create-react-app, you can either install it globally (i.e. <code> $ npm install -g create-react-app </code>) or install it locally (i.e. <code>$ npm install create-react-app </code>). If you choose the latter, you will have to specify its path whenever you invoke it (e.g. path/to/node_modules/.bin/create-react-app). 
 
 * Check Installed Versions
 
    Node: <code>$ node --version
    v16.14.2 </code>
              
    NPM: <code>$ npm  --version
    8.5.0</code>
              
    Create React App: <code>$ create-react-app  --version 
    5.0.0</code>
 

Quick Development Setup
=======================
<pre>
* git clone https://github.training.cerner.com/DevCenter/Ideas-Repository
* Import as maven project in eclipse or sts. Run springboot application on embedded Tomcat server localhost:8090. 
* Create database:
  Create **experiment** named database on MySQL on localhost:3306.
  Then set _**spring.jpa.hibernate.ddl-auto = create**_ in src/main/resources/application.properties in the springboot application.
  Once the database tables are created, set it to the value **udate**
* Run npm install and then npm start in VSCode command line
* Hit http://localhost:3000/
* You can now view experiment in the browser proxying request /login from http://localhost:3000 to          http://localhost:8080/ 
</pre> 

Versions 
======================
<pre>
* Node: 16.14.2
* NPM:  8.5.0
* Create React App: 5.0.0
* Spring Boot: 2.6.4 RELEASE
* React: 17.0.2
* Maven:
</pre>

Deployment
======================

* API

<pre>Run <code>mvn clean install</code> to generate a WAR file deployable in any servlet containers like Tomcat. </pre>

* UI

<pre>Run <code>npm run build</code></pre>
 
 It builds the app for production to the build folder.
 It correctly bundles React in production mode and optimizes the build for the best performance.
 The build is minified and the filenames include the hashes.
 Your app is ready to be deployed!
 
 <pre>Start <code>npm start</code></pre>
 It starts the development server in the browser.
 
 </pre>
 

Author 
===================
Nishant Kumar Bharali, NB097260

Note: Everything is tested on Windows environment
