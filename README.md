# DiceRoller
A small java application for rolling different dice sets.

Features: By default it contains a RPG (D&D) standard set, a Star Wars RPG (FFG) standard set, a deck of cards (some RPGs use those) and a generic dice set to customize. All sets, and groups of sets, will let you customize their name, and it's possible to mix dice sets from different systems. The output is in html, so it features icons for easier readability. You can save all your specific dice settings in an external xml file (DiceRollerSaveFile.xml) that will be loaded automatically on launch if it's in the same folder as the jar. You can import a different xml file, if your GM set you up with one.

Specific features:

RPGSet: You can set modifiers to add/subtract to the total roll, to each rolled dice, or even set a particular throw as "with     advantage/disadvantage". 

  SWSet: There is a set that lets you input your skill checks, with a number of challenge dice and a number of ability dice. 

  Deck: You can choose to add/remove jokers from a deck, shuffle cards, or draw.

  Generic Set: You can input all possible values like +,+,-,-, ,  (which will result in a d6 similar to the fate system) or you can specify ranges of values like 1-4,6 will generate a "d5" with no number 5 side. 



Version 0.1 - First release. Probably riddled with bugs.

Version 0.2 - Fixed a couple of visual bugs.

Version 0.3 - Added "Enabled" checkbox for skill checks.
