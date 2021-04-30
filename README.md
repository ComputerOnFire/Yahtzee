# Yahtzee
Implementation of the board game Yahtzee in Java and JavaFX

<img align="top" width="60%" height="60%" src="https://user-images.githubusercontent.com/3631329/116506089-1830d380-a882-11eb-9b80-b16c9d44956a.png">

## How to build:

If you would like to compile and run Yahtzee, the command
```
gradlew run
```
can be executed in the project folder to build and launch the application.


## How to test:

Automatic test cases can be run with the command
```
gradlew test
```

The command `gradlew` can also be used on its own to build the program without running it.

## How to build a standalone application:
- Note: This expects Java 16 to be installed at default location on Windows, edit the path in `build.gradle` line 75 if otherwise.

A standalone binary or installer can be compiled with the use of the command
```
gradlew jpackage
```
which will generate an executable with its own runtime and an installer for your host operating system. This package can then be installed or ran on a machine that does not have the JRE installed.


## How to build in IntelliJ:

To run the program with gradle, make sure your IntelliJ configuration looks like this. The important thing being the 'run' inside the tasks field.
![](https://i.imgur.com/M1SPfKj.png) 

### How to build without gradle:
If you do not wish to compile with gradle and want to compile the program directly, you will need to obtain the JavaFX sdk on your own from this page. https://gluonhq.com/products/javafx/ Please scroll down to version 11.0.2, download, and extract the archive to a permanent location, `C:\Program Files\Java\javafx-sdk-11.0.2\` For example.

Then you can modify the VM settings of your IDE with 
~~~~
"--module-path "C:\Program Files\Java\javafx-sdk-11.0.2\lib" --add-modules javafx.controls,javafx.fxml"
~~~~

