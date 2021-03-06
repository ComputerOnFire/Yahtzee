# Yahtzee
Implementation of the board game Yahtzee in Java and JavaFX

* To run the program with gradle, make sure your IntelliJ configuration looks like this. The important thing being the 'run' inside the tasks field.
![](https://i.imgur.com/M1SPfKj.png) 

#Or
If you do not wish to compile with gradle and want to run the program directly, you will need to obtain the JavaFX sdk on your own from this page. https://gluonhq.com/products/javafx/ Please scroll down to version 15, download, and extract the archive to a permanent location, "C:\Program Files\Java\javafx-sdk-15.0.1\" For example.

Then you can modify the VM settings with 
~~~~
"--module-path "C:\Program Files\Java\javafx-sdk-15.0.1\lib" --add-modules javafx.controls,javafx.fxml"
~~~~

which will be placed here.

![](https://i.imgur.com/DsDESCC.png)

The previous command arguments 
~~~~
"--module-path "C:\Program Files\Java\javafx-sdk-15.0.1\lib" --add-modules javafx.controls,javafx.fxml" 
~~~~
can also be used to compile from command line.
