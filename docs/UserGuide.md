# User Guide

## Introduction

Kolinux is built to help NUS Computer Engineering freshmen to better integrate into university life 
by allowing them to manage their work and learn more about NUS, all in 1 platform. It is optimized 
for CLI users so that they can access the information that they require faster by typing in commands.

* [Quick Start](#quick-start)
* [List of Commands](#list-of-commands)
* [Features](#features)
  * [`planner`](##adding-to-planner-planner-add)
    * [`planner add`](##adding-to-planner-planner-add)
  * [`bus`](#finding-nus-bus-route-between-2-points-bus)
  * [`cap`](#calculating-total-cap-cap)
  * [`view`](#viewing-module-details-view)
  * [`help`](#viewing-menu-help)
  * [`bye`](##exiting-kolinux-bye)

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `Kolinux` from [here]().

## List of Commands

|    Command    	|                     Action                     	|               Command Format               	|
|:-------------:	|:----------------------------------------------:	|:------------------------------------------:	|
| `planner add` 	| Adds a new event to your schedule              	| `add DESCRIPTION/DATE/START_TIME/END_TIME` 	|
| `bus`         	| Check for a NUS bus route from stop to another 	| `bus`                                      	|
| `cap`         	| Calculates the total cap for the semester      	| `cap MC_GRADE`                             	|
| `view`        	| Viewing module details                         	| `view MODULE_CODE`                         	|
| `help`        	| View the different commands available          	| `help`                                     	|
| `bye`         	| Exit Kolinux                                   	| `bye`                                      	|



## Features 

### Adding to Planner: `planner add`

Adds a new event to your schedule

Format: `add DESCRIPTION/DATE/START_TIME/END_TIME`

* The `DATE` needs to follow the following format: `yyyy-mm-dd`

### Finding NUS bus route between 2 points: `bus`
### Calculating total CAP: `cap`

Format: `cap MC_GRADE`

Example of usage:

* `cap 4A 6B+ 4B 4B- 4A+`

Expected Outcome:

```
cap 4A 6B+ 4B 4B- 4A+
Your CAP for this semester will be 4.09 if you get your desired grades!
```

### Viewing module details: `view`

Format: `view MODULE_CODE`

* `MODULE_CODE` needs to be in uppercase letters

Example of usage:

* `view CS1010`

Expected Outcome:

```
view CS1010
CS1010: Programming Methodology
Department: Computer Science
Faculty: Computing
Credits: 4
This module introduces the fundamental concepts of
problem solving by computing and programming using
an imperative programming language. It is the first
and foremost introductory course to computing.  Topics
covered include computational thinking and computational
problem solving, designing and specifying an algorithm,
basic problem formulation and problem solving approaches,
program development, coding, testing and debugging,
fundamental programming constructs (variables, types,
expressions, assignments, functions, control structures,
etc.), fundamental data structures (arrays, strings,
composite data types), basic sorting, and recursion.
```

### Viewing menu: `help`
View the different commands available

Expected outcome:

```
help
Here are the list of commands:
1. cap MC_AND_GRADES  - Calculates the total cap for the semester
2. view MODULE_CODE - View the module details
3. bus routes - Check for a NUS bus route from stop to another
4. planner add DESCRIPTION/DATE/START_TIME/END_TIME - Add an event to your schedule
5. help - View this menu again
6. bye - Exit Kolinux
```

### Exiting Kolinux: `bye`

Exits Kolinux

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}


