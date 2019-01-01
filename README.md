# Car-Parking-Game-Solver
Solves various levels for the game Car Parking 2 automatically. This game is pretty much a [Sokoban](https://en.wikipedia.org/wiki/Sokoban) reskin. The game can be found at https://www.mindgames.com/game/Car+Parking+2.


## How it works
A level in Car Parking 2 looks like so.

![alt test](https://github.com/MichaelMuinos/Car-Parking-Game-Solver/blob/master/ExampleLevel.png)

This level is equivalent to the following matrix.
```
110011
132311
410411
```
Where

----> **0** = Out of Bounds

----> **1** = Empty Space

----> **2** = Car

----> **3** = Box

----> **4** = Parking Spot


So, our encoding of the level is pretty much this image. We only care about the
content inside of the red square. The goal is to encode the level to be the smallest
matrix possible without exluding useful content.

![alt test](https://github.com/MichaelMuinos/Car-Parking-Game-Solver/blob/master/ExampleLevelModified.png)


Now that we have the encoding for our level, we can create our initial state and pass it to our algorithm to process
the moves. If we were to enter the level above to be solved, the result would be the following moves.

```<	^	<	v	>	>	>	v	>	>	^	^	<	v	>	v	<```

## Interactive Mode
In interactive mode, you don't have to touch anything! To start interactive mode you must...
1) Open the browser to https://www.mindgames.com/game/Car+Parking+2 and click 'Play'.
2) Run `Driver.java`.
3) Once the command line is running, you can choose whether you want to solve all levels,
solve a single level, or solve a range of levels.
4) Make sure scope is on the game's browser. If you click anywhere else after starting interactive mode, the level can't be solved.

**Note:** If you want to solve a level in interactive mode, you have to make sure you are on that specific level in the game.
For example, if I want to solve a range of levels between 50 and 60, I would need to make sure I am starting on level 50 in the browser.
