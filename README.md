# JavaRush-SecondProject-Island
***Island - simulation of wild life on the Island***

Short Description
-----------------

Console multithreading application for simulation of wild life on the Island. The default size of Island is 100 cells 
length and 20 width.The User may change the size of the Island. The Island is displaced by Entities: Animals and Plant. 
Animals are able to move, eat, reproduce, die form hunger or to be killed by the predator, grass grows every night. 
Animal may do only one action during the day. Random choice decides what action Animal will do today. At the end of each
day the statistic is printed in console. The statistic includes: number of predators, herbivores and grass.


Limitations in application
--------------------------

The game lasts 5 days.
Minimum width and length of the Island is 3.
Maximum number of each Entity on each position is defined in game.properties file.
If Animal didn't eat for 3 days, or it ate twice less kg than the maximum amount for saturation 3 days or during one or 
two of this three days, it dies.
Animal can't move to new location if there are maximum number of such Animals on target position, so it misses its action.
Animal can't reproduce if it is the only Animal of such type on Position, or there are the maximum number of such Animal
on this location, so the Animal misses its action.
Animal can't eat if the random possibility to eat is lower than ii property file for this Animal, so the Animal misses 
its action. 
Animal is considered dead if any Animal used it as food and is removed from its position. 
If there are no grass on position at the end of the day the grass grows at the night randomly, if there are any grass on
position left the grass grows for more 20%. 

Description of algorithm
------------------------

The User is suggested to change the size of the Island or not in welcome message. The User types in console the width 
and the length of the Island, if not the default size is used. On each location there random number of Animals and Grass. 
The statistic is printed on the start of simulation. On the day first Animals randomly choose action and do it, or do 
not do if the limitations applies. At the end of each day the statistic is printed. At the night grass grows, the animals
that suppose to be dead according to limitation above are removed from the Island. The life loop lasts 5 days.

Packages and Class description 
------------------------------

In root package there are tow packages: main and resource. Package resource contains file game.properties. There are all 
parameters of Animals, Grass and Island in this file. Package main contains packages: consoleui, enums, game, service,
species.
Consoleui package contains two classes: 
        - ConsoleDialogue, for communications with User,
        -ScannerSingleton, for typing and reading information from User. 
Enums contains 5 enums: 
        - Actions(enumeration of action that Animal are able to do), 
        - AnimalEnum (enumeration of type of Animals), 
        - AnimalParametersType (enumeration of Animals' parameters), 
        - DirectionOfMoving (enumeration of directions for moving), 
        - TextMassages (enumeration of text message for communication with User).  
Game contains the following classes: 
        - DisplacementOfPopulation (Animals and Grass are settled on its position), 
        - GameApplication (the game starts here, main method), 
        - God ("God" of the Island, it manages all actions of Animals and Grass), 
        - Island (class with parameters of Island), 
        - IslandInitialization (class which creates Island and settled the Entities on it). 
Services contain classes: 
        - EatingAction (here Animals eat), 
        - EntityProduction (it produces entities, and newborn Animals), 
        - EnumRandomChoice (supply random choice for each enumeration), 
        - GrassGrows (the grass grows here), 
        - IslandEntityIterationRunnable (here cells of Island are iterated, Animals in each cell are iterated, they choose their 
        actions, grass grows at the night and dead Animals are removed), 
        - MovingAction (here Animals move), 
        - PropertiesLoader (load properties from game.properties file at the start of the simulation), 
        - Reproduction (Animals reproduce here),
        - Statistic (statistic is counted and printed here), 
        - SupportingMethods (there are methods here which is used by several classes).
Species contains four packages: 
    - abstractclasses, it contains:
        - Animal (it extends from Entity),
        - Entity (parent class),
        - Plant (extends from Entity).
    - herbivores, it contains ten classes which extends from Animal: 
        - Boar, Buffalo Caterpillar, Deer, Duck, Goat, Horse, Mouse,
        Rabbit, Ship.
    - plant, contains one class, which extends from Plant:
        - Grass
    - predators contains five classes which extends from Animal:
        - Bear, Boa, Eagle, Fox, Wolf.
Resource package contains package settings in which there is:
        - game.properties file.

Description of the course of simulation
---------------------------------------

1. The simulation starts in class GameApplication, it contains main method in which the simulation is started.
2. The properties are loaded at the start.
3. The User see welcome message with the default size of the Island, length 100 cells, width 20 cells. The User is 
suggested to change the size of the Island. It is allowed to enter "yes" or "not" only. If "not" goes to para 3. If yes, 
the User is suggested to enter the new width and the new length. The User is allowed to enter numbers only starting 
from 3. 
4. The Island created with default size or with User inserted size. 
5. The Animals and Grass are settled on the Island. Entities created by means of reflection in the random amount from 
zero to maximum amount of such type of Entity per one cell. 
6. The statistic on the beginning of the simulation is printed.
7. For each cell the thread is created and paragraphs 8 - 14 are executed in its own thread.
8. For each Animal on each position (cell) random chooses action.
9. If action move: random chooses the direction for animal, check travel speed of animal. If it is zero, the 
actionDoneFlag of animal becomes true, animal stays on its position. If it is above zero: check the amount of the same 
animal on chosen position. If it is higher or equal to maximum allowed amount of such animal, the actionDoneFlag of 
animal becomes true, animal stays on its position. If it is lower than such amount: animal is removed from its current 
position and added to new position the actionDoneFlag of animal becomes true. The field saturationRation, which shows if
animal ate today decreased in 1. 
10. If action eat: random chooses the target entity to eat from HashMap eatingRation, it includes list of target food 
with possibility to eat ratio. Random chooses if hunting animal will eat the target food according to possibility to eat
ratio. If not: actionDoneFlag of animal becomes true, animal stays hungry, its saturation ratio decreased in 1. If yes:
check if such Entity (animal or grass) are on current position of hunting Animal. Count the weight of the total number 
of target entities, compare to number of kg for the full saturation of hunting animal, count the number of target 
Animal to eat, and number of Grass to each. Remove this number of target animal or grass from current position.
If the weight of total food that ate the animal is twice lower that its parameter kgForFullSaturation, it is considered 
that hunting animal didn't eat at all, its saturation ratio decreased in 1 anyway.
11. If action reproduce: check if the number of animal of the same type on current position higher or equal to 
maximum allowed amount of such animal, the actionDoneFlag of animal becomes true, animal doesn't do action. if not: 
one animal of such type is added to the current position, the actionDoneFlag of animal becomes true, its saturation ratio 
decreased in 1.
12. For each position for each animal the actionDoneFlag becomes false.
13. For each position if there is no grass on it, the grass grows in random number, if there is grass on position it is
increased in 20 percent. 
14. If the saturation ratio is 0 the animal is removed from its position. 
15. The statistic of the beginning of the day is printed. 
16. Paragraphs 7-15 start again.
 