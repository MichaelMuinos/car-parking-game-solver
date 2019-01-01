# Car-Parking-Game-Solver
Solves various levels for the game Car Parking 2 automatically. This game is pretty much a [Sokoban](https://en.wikipedia.org/wiki/Sokoban) reskin.

## How it works
A level in Car Parking 2 looks like so.

![alt test](https://github.com/MichaelMuinos/Car-Parking-Game-Solver/blob/master/ExampleLevel.png "Example Level")

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

