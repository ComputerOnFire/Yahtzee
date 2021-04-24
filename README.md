# Yahtzee
Implementation of the board game Yahtzee in Java and JavaFX

### How to build :

If you would like to compile and run the program, the following command
```
gradlew run
```
can be executed in the project folder to build and launch the application.

The command `gradlew` can also be used on its own to build the program without running it.

### How to build a standalone application:
A standalone binary or installer can be compiled with the use of the following command
```
gradlew jpackage
```
which will generate an executable with its own runtime and an installer for your host operating system. This can be installed or ran on a machine that does not have the JRE installed.