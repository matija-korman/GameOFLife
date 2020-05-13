
The Game of Life

“The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970. The “game” is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input. One interacts with the Game of Life by creating an initial configuration and observing how it evolves, or, for advanced “players”, by creating patterns with particular properties.” 

The rules

“The universe of the Game of Life is an infinite two-dimensional orthogonal grid of square cells, each of which is in one of two possible states, alive or dead. Every cell interacts with its eight neighbors, which are the cells that are horizontally, vertically, or diagonally adjacent. At each step in time, the following transitions occur:

    Any live cell with fewer than two live neighbors dies, as if caused by under-population.
    Any live cell with two or three live neighbors lives on to the next generation.
    Any live cell with more than three live neighbors dies, as if by over-population.
    Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

The initial pattern constitutes the seed of the system. The first generation is created by applying the above rules simultaneously to every cell in the seed—births and deaths occur simultaneously, and the discrete moment at which this happens is sometimes called a tick (in other words, each generation is a pure function of the preceding one). The rules continue to be applied repeatedly to create further generations.” Wikipedia
About this program

The program is an implementation of Conways Game of Life and it fallows its rules. When the program starts the interface displays a grid of 14 400 dead cells. Dead cells are marked as white, when they come alive they are marked as green. The user manually creates the initial state of the grid by clicking on individual cells to give them life, or, a computer creates a random grid configuration. When the game is started the cells evolve according to the aforementioned rules. The game can be paused and restarted at will.

The life status of individual cells can be changed even after the game has started. There are two ways to do this. The user can pause the game and then click on individual cells to change their life status (kill a cell if it’s alive or give it life if it’s dead). Pausing the game allows the user to change the life status of as many cells as he/she likes. Alternatively, the user can change the life status of a cell while the game is executing. Changing the life status of cells after the initial configuration has started evolving adds flexibility to the game and gives the players more control over creating the desired patterns.

If the user doesn’t want to manually create the initial configuration of cells the program enables the user to start a random game and the computer randomly gives life to certain cells creating an initial configuration that the game can evolve from.

The program is connected to a database so the user can even save and load configurations he or she likes the most.

Motivation and inspiration

This is a personal project that began as a way to learn how to develop Java applications. My interest for the game sparks from its use in the study of emergent properties central to the theories of complex systems and the general philosophical study of emergence.
