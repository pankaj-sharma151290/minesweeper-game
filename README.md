# Minesweeper Game
Minesweeper is a single-player puzzle video game. The objective of the game is to clear a rectangular board containing hidden "mines" or bombs without detonating any of them, with help from clues about the number of neighboring mines in each field.If the Player opens all the squares without bomb then he/she wins the game.

## Implementation Overview

This project is JAVA implementation for Minsweeper game. The game starts upon running class MinesweeperGame, jar or using docker.

### Features covered
1. Single Player Game.
2. Computer will randomly choose squares to place the bombs.
3. If the grid is a safe one, it should show the number of bombs in its neighbouring squares.
4. If the player loses the game by pressing on a bomb, then the new game will start with the same grid. (same bombs)
5. Until the player wants to quit, player will be able to continue with a new game.
6. Flagging a square as a bomb is also available. Player can flag or un-flag the tile. Details for same is also available in game console.

####Note: Player will never hit the bomb on first select, so relax for the first time :)

### Improvements

1. No UI available for the game.
2. Rest APIs can be exposed by adding controller, And same code can be used from the service layer.

## Getting Started

The project is dockerized.
There is no UI in the application therefore the application interact with user through console.

### The application is built on below stack.

* Java
* Spring Boot Console Application
* Docker


### Prerequisites

Things required to run the application.

```
Docker
OR
JAVA 8 or above
```

### How to run
```
Checkout the code from github : <https://github.com/pankaj-sharma151290/minesweeper-game.git>

=> Follow steps to run using executable jar
1. cd minesweeper-game
2. Open CMD/Terminal
3. mvn clean package
4. cd target
5. java -jar minesweeper-game-0.0.1-SNAPSHOT.jar

=> Follow steps to run using Docker
1. cd minesweeper-game
2. Open CMD/Terminal
3. mvn clean package
4. docker build --no-cache -t minesweeper-game:1.0.0 .
5. docker run --name minesweeper minesweeper-game:1.0.0

=> Follow below steps to run from IDE
1. Open checked out project <minesweeper-game>
2. Run Main class <MinesweeperGame.java>, to start the game.
3. You will see in console game is started and wating for your input.

```
## Access application 

```
There is no web UI for this application, It can be accessed through console only.
```

## Test cases

The application has Unit Test written.

### Unit Test (coverage ~ 100%)
 The Unit test cases are available for service layer under <test> package 
```
GameServiceImplTest.java
```
### How to Play

1. On start, it waits for user's input for Board size. board size can not be more than 50*50 for users convenience.
```
Welcome to Minesweeper Game.

Enter the grid height (Should not be >50) :

Enter the grid width (Should not be >50) : 

Enter the number of mines (should be < height*width): 
```

####Note: The Game will not proceed further unil user enters valid input for board size.

2.Once is board's height, width and number of mines selected. The Game starts and displays the list of valid commands that game expects, board details and board. For example board's height=10, width=10 and number of mines=10 then console will display below. 

```
Valid Commands:
           1. "help": opens the help menu
           2. "select": specify which tile you want to check
           3. "flag": specify which tile you want to flag
           4. "restart": start a new game
           5. "quit": to quit the game
           6. "showBoard": to display the current status

Game Status :RUNNING
No of Covered Tiles :0
No of Flagged Tiles :0
No of Mines left :10

	 -	 -	 -	 -	 -	 -	 -	 -	 -	 -
	 -	 -	 -	 -	 -	 -	 -	 -	 -	 -
	 -	 -	 -	 -	 -	 -	 -	 -	 -	 -
	 -	 -	 -	 -	 -	 -	 -	 -	 -	 -
	 -	 -	 -	 -	 -	 -	 -	 -	 -	 -
	 -	 -	 -	 -	 -	 -	 -	 -	 -	 -
	 -	 -	 -	 -	 -	 -	 -	 -	 -	 -
	 -	 -	 -	 -	 -	 -	 -	 -	 -	 -
	 -	 -	 -	 -	 -	 -	 -	 -	 -	 -
	 -	 -	 -	 -	 -	 -	 -	 -	 -	 -
```
3.Then game will wait for user's command. That should be valid one else it will display error message as below.
```
Unknown command, Please enter valid command. enter help for list of acceptable commands.
```
4. To select the tile, enter command "select" then after enter tile's row and column no.

````
select 
Enter row no from 1 to 10: 
1
Enter column no from 1 to 10: 
1
````

5. To flag the tile, enter command "flag" then after enter tile's row and column no.

````
flag 
Enter row no from 1 to 10: 
1
Enter column no from 1 to 10: 
1
````

6. To exit, restart, display board and get the list of valid commands' player has to enter exit, restart, showboard and help respectively.  
#### Note: On every successful command user will get the status of the game with board and its details.
