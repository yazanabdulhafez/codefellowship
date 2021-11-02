# codefellowship

## Overview

CodeFellowship that allows people learning to code to connect with each other and support each other on their coding journeys.

## Feature Tasks

1. Build an app that allows users to create their profile on CodeFellowship.

2. The site should have a splash page at the root route (/) that contains basic information about the site, as well as a link to the “sign up” page.

3. An ApplicationUser should have a username, password (will be hashed using BCrypt), firstName, lastName, dateOfBirth, bio, and any other fields you think are useful.
The site should allow users to create an ApplicationUser on the “sign up” page.

4. Your Controller should have an @Autowired private PasswordEncoder passwordEncoder; and use that to run passwordEncoder.encode(password) before saving the password into the new user.

5. The site should have a page which allows viewing the data about a single ApplicationUser, at a route like /users/{id}.
This should include a default profile picture, which is the same for every user, and their basic information.

6. When a user is logged in, the app should display the user’s username on every page (probably in the heading).

7. Ensure that your homepage, login, and registration routes are accessible to non-logged in users. All other routes should be limited to logged-in users.
The site should be well-styled and attractive.

8. The site should use reusable templates for its information. (At a minimum, it should have one Thymeleaf fragment that is used on multiple pages.)

9. The site should have a non-whitelabel error handling page that lets the user know, at minimum, the error code and a brief message about what went wrong.

10. Ensure that user registration also logs users into your app automatically.
Add a Post entity to your app.
A Post has a body and a createdAt timestamp.
A logged-in user should be able to create a Post, and a post should belong to the user that created it.
hint: this is a relationship between two pieces of data

11. A user’s posts should be visible on their profile page.

### How to Run

1. clone codefellowship repository
2. In the terminal cd into the codefellowship directory
3. Enter: ./gradlew bootRun
4. Open web browser to: localhost:8080 and go through the app

## CodeFellowship Profiles with Login

### Overview(17)

In this lab you’ll add login to the CodeFellowship app, and allow users to create posts.

### Feature Tasks(17)

* Allow users to log in to CodeFellowship and create posts.

* Using the above cheat sheet, add the ability for users to log in to your app.
Upon logging in, users should be taken to a /myprofile route that displays their information.

* Ensure that your homepage, login, and registration routes are accessible to non-logged in users. All other routes should be limited to logged-in users.

* Ensure that user registration also logs users into your app automatically.
Add a Post entity to your app.
A Post has a body and a createdAt timestamp.
A logged-in user should be able to create a Post, and a post should belong to the user that created it.
hint: this is a relationship between two pieces of data

* A user’s posts should be visible on their profile page.

* When a user is logged in, the app should display the user’s username on every page (probably in the heading).

### How to Run(lab 17)

1. clone codefellowship repository
2. create adata base as the same in the proprites.
3. In the terminal cd into the codefellowship directory
4. Enter: ./gradlew bootRun
5. Open web browser to: localhost:8080 and go through the app

## Spring Security against User

### Overview(18)

In this lab we need to add the follow featuer , feed page to show the posts of other users that you follow.

### Feature Tasks(18)

* Ensure that users can’t perform SQL injection or HTML injection with their posts.

* Allow users to follow other users. Following a user means that their posts show up in the logged-in user’s feed, where they can see what all of their followed users have posted recently.

* Ensure there is some way (like a users index page) that a user can discover other users on the service.

* On a user profile page that does NOT belong to the currently logged-in user, display a “Follow” button. When a user clicks that follow button, the logged-in user is now following the viewed-profile-page user.
note: this will require a self-join on ApplicationUsers.

* A user can visit a url (like /feed) to view all of the posts from the users that they follow.

* Each post should have a link to the user profile of the user who wrote the post.

### How to Run(lab 18)

1. clone codefellowship repository
2. create adata base as the same name in the propreites.
3. In the terminal cd into the codefellowship directory
4. Enter: ./gradlew bootRun
5. Open web browser to: localhost:8080 and go through the app.
