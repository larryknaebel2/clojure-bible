# bible

This is an application written in Clojure that will display a specified Bible verse in one of ten English translations.

The application only runs on the non-graphical command line and will continue to
expect input commands until told to exit.

## Starting the Application

        lein run
        or
        java -jar bible-0.1.0-SNAPSHOT-standalone.jar

## The Opening Screen
---
    

    Ten Bible Versions



    AVAILABLE COMMANDS: book chapter:verse - get verse, q - quit, ss - show state, st - set active translation, sb - set active book
    |
---
The supported commands are:
-  ***book chapter:verse*** Display the desired verse Example: Genesis 1:1
-  ***q***  Quit
-  ***ss***  Show state. Displays the active translation and book.
-  ***st***  Set Translation. Set the desired translation from a list displayed after this command is entered. A number is expected. 
-  ***sb***  Set the active book to be used from a list displayed after this command is entered. (not currently functional)


The available translations are:

1 : King James Bible
2 : American Standard Version
3 : Douay-Rheims Bible
4 : Darby Bible Translation
5 : English Revised Version
6 : Webster Bible Translation
7 : World English Bible
8 : Young's Literal Translation
9 : American King James Version
10 : Weymouth New Testament

Please note that the Bible book name must be spelled properly and start with an upper case character (normal proper name syntax).
## License

Copyright © 2020 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
