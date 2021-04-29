# Yahtzee
Implementation of the board game Yahtzee in Java and JavaFX

## How to build:

If you would like to compile and run Yahtzee, the following command
```
gradlew run
```
can be executed in the project folder to build and launch the application.

## How to test:
Automatic test cases can be run with the following command
```
gradlew test
```

The command `gradlew` can also be used on its own to build the program without running it.

## How to build a standalone application:
- Note: This expects Java 16 to be installed at default location on Windows, edit the path in `build.gradle` line 75 if otherwise.

A standalone binary or installer can be compiled with the use of the following command
```
gradlew jpackage
```
which will generate an executable with its own runtime and an installer for your host operating system. This package can then be installed or ran on a machine that does not have the JRE installed.
