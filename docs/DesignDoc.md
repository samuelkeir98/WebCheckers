---
geometry: margin=1in
---
# WebCheckers Design Documentation

# Team Information
* Team name: D
* Team members
  * Andy Gin
  * Anthony Amassicci
  * Adrian Postolache
  * Samuel Keir
  * Zachary Eberhardt

## Executive Summary

This is the design documentation for the WebCheckers application. The documentation first covers purpose and requirements of the application, describing the vision of the project. Following is the Application Domain, illustrating the problem space of the application and the entities involved in playing a checkers game. The Web Architecture is next described, illustrating a high level view of the tiers and models and how the web technologies fit in the application. An overview of the User Interface is then shown, giving a user an idea of what he/she might expect to see throughout the application's pages. Finally, a summary of how the application works at each tier in the architecture is given to revel more of the design's inner workings.

### Purpose

This project is about creating a checkers game online so that players can play against their friends or random opponents to have fun.

### Glossary and Acronyms

| Term | Definition |
|------|------------|
| VO | Value Object |


## Requirements

Major requirements include sign in/sign out, starting a game, movement in game, and ending a game. Further enhancements required are a help menu which tells a player the moves available and spectating a game, in which a user may spectate an ongoing game.



### Definition of MVP

The Minimum Viable Product of this product is a checkers game that allows a user to choose their opponent from a list of other users in a lobby. Once an opponent is chosen the two players will be able to play a simple game of checkers in which they can move their pieces. Each move can go forward by stepping or jumping over opponents pieces. A game will be won once either all your or your opponents pieces are taken or either player has no more moves.

Upon reaching the opponent end of the board, a piece can be promoted to a king in which that piece may move forward or backwards. A king cannot move again upon promotion. If a piece can jump an opponent piece, that piece must make a jump move that turn. If the jump results in a position where the piece may make another jump, it must do so. The only exception to this is if a piece jumps another piece and is promoted to a king, in which case it cannot move further.

### MVP Features

Player Sign-in
Start a Game
Movement Epic
   	Step move
    Simple Capture
    Multi Jump
    Become a King
    King Movement
    Backup Move
Winning/Losing Game
Resign

### Roadmap of Enhancements

Spectator
Player Help button and window

## Application Domain

This section describes the application domain.

![The WebCheckers Domain Model](DomainModel.png)

The domain model above models the problem space of a Checkers game and covers the different parts of a game. Starting at the top is the WebCheckers Game, which is the overarching entity that the rest of the model considers to illustrate the different parts of the game.

The game is played on an 8x8 Checker Board which is made of 64 alternating black and white squares. There may be pieces on the squares, which can be normal pieces or a king pieces. Furthermore, 2 Players play the game, taking turns to make Moves that transition the pieces and move the game along.

A Move entity is broken down into two categories: a Jump, which is further broken down into a Multi Jump and a Single Jump, and a Simple Move, which is further broken down into a Normal Move and a King Move. All Moves adhere to the rules of Checkers.



## Architecture

This section describes the application architecture.

### Summary

The following Tiers/Layers model shows a high-level view of the webapp's architecture.

![The Tiers & Layers of the Architecture](architecture-tiers-and-layers.png)

As a web application, the user interacts with the system using a browser.  The client-side
of the UI is composed of HTML pages with some minimal CSS for styling the page.  There is also
some JavaScript that has been provided to the team by the architect.

The server-side tiers include the UI Tier that is composed of UI Controllers and Views.
Controllers are built using the Spark framework and View are built using the FreeMarker framework.  The Application and Model tiers are built using plain-old Java objects (POJOs).

Details of the components within these tiers are supplied below


### Overview of User Interface

This section describes the web interface flow; this is how the user views and interacts
with the WebCheckers application.

![The WebCheckers Web Interface Statechart](Statechart.png)

Users initially have no connection to the page. A user is directed to the Home page upon making a connection to the server. At the Home page, the number of users is displayed along with a link to signin.

The sign in link directs the user to the Sign In page with one text field to input a name and a button to sign in. If the name is taken, it will display a error message on the same page. If the name is available, the user is redirected to the home page, now with a list of online players displayed and a signout link instead of a signin one.

The Home page refreshes every 5 seconds, updating the player list and number of players. If the user signs out, the signin button is displayed again and the player list is no longer displayed.

Otherwise, the user may select an online player and issue a challenge, in which case the user is directed to the Game page. The game components are rendered with the user playing as red and the opponent playing as black.

If the user is challenged by another player in the home page, the Game page is also rendered with the user playing as black and the opponent as red.

When the game is over, the user may click on the home link to return to the Home page.


### UI Tier

The WebSever class sets up the routes for the UI tier. The GetHomeRoute class initializes the webcheckers server. Our GET routes and post routes for sign-in cooperate to populate the player lobby with signed-in players. Furthermore, our GET and POST routes for the game work in unison to pull players into a game once a challenge has been issued. The POST game route is used to handle the challenger clicking the button to challenge the opponent and the GET game route is used to enter a game if the player has been challenged by another player. While the game is running, our post routes for validate move, check turn, and submit turn operate in order to keep the game loop functional. At any time, if a player attempts to sign out, the get route for signing out handles the calls and completes the request.

#### Static models

#### Dynamic models
> For example, in WebCheckers you might create a sequence diagram of the `POST /validateMove` HTTP request processing or you might use a state diagram if the Game component uses a state machine to manage the game.
Start Game Sequence Diagram
![The Start Game Sequence Diagram](startGameSequence.png)

### Application Tier
The Application tier contains the GameLobby and PlayerLobby classes and keeps track of all of the players in the lobby and the ongoing games. The PlayerLobby class also signs in players by adding them to its map of players. The GameLobby can be used to find the players in a particular game and notify a user if he/she has been challenged. It also puts both players into a game when a user challenges another player.

#### Static models


#### Dynamic models


### Model Tier

The game and board classes are the main classes in the Model Tier. The game class implements the functionality of the game loop and keeps it running while players are participating in a webcheckers match. Player is a representation of the users participating in the game. The board keeps track of the pieces and the state of the turns for the players. Color, piece, row, and space all represent their corresponding features inside the board, and are interacted with in order to keep the game loop functional and accurate to the rules for checkers. The moves package includes all of the implementation for the different types of moves that occur in checkers, ranging from jumps to kinging
and more.

#### Static models
Model UML
![The Model UML Diagram](modelUML.png)

#### Dynamic models

