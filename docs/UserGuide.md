# User Guide

## Introduction

Kolinux is built to help NUS Computer Engineering freshmen to better integrate into university life 
by allowing them to manage their work and learn more about NUS, all in 1 platform. It is optimized 
for CLI users so that they can access the information that they require faster by typing in commands.

Kolinux v1.0 offers a range of minimum features for freshmen. These features include a module viewer
for freshmen to view information of a module, a CAP calculator, a planner for freshmen to organise 
their activities for the day and a route finder for the NUS internal shuttle bus.

* [Quick Start](#quick-start)
* [List of Commands](#list-of-commands)
* [Features](#features)
  * [`planner`](#add-an-event-to-planner-planner-add)
    * [`planner add`](#add-an-event-to-planner-planner-add)
    * [`planner list`](#list-events-planner-list)
  * [`bus`](#check-nus-bus-route-between-2-stops-bus)
  * [`cap`](#calculate-total-cap-cap)
  * [`view`](#view-module-details-view)
  * [`module list`](#add-modules-to-module-list-by-code-store_module)
    * [`store_module`](#add-modules-to-module-list-by-code-store_module)
    * [`delete_module`](#delete-modules-from-module-list-by-code-delete_module)
  * [`timetable`](#add-lessons-to-timetable--timetable-add)
    * [`timetable add`](#add-lessons-to-timetable--timetable-add)
    * [`timetable clear`](#clear-timetable-timetable-clear)
  * [`help`](#view-menu-help)
  * [`bye`](#exit-kolinux-bye)

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `Kolinux.jar` from [here](https://github.com/AY2122S1-CS2113T-W11-1/tp/releases).
3. Copy `Kolinux.jar` to an empty folder.
4. On the command prompt, navigate to the folder `Kolinux.jar` is stored.
5. Launch `Kolinux.jar` using `java -jar Kolinux.jar`.
6. Enter commands to use Kolinux.

Demo:
```
java -jar Kolinux.jar


(_)   | |    | |(_)
 _____| |___ | | _ ____  _   _ _   _
|  _   _) _ \| || |  _ \| | | ( \ / )
| |  \ \ |_| | || | | | | |_| |) X (
|_|   \_)___/ \_)_|_| |_|____/(_/ \_)
Welcome to Kolinux! Enter "help" to view the list of commands
....................................................................
```

## List of Commands

|    Command    	|                     Action                     	|               Command Format               	     |
|:-------------:	|:----------------------------------------------:	|:------------------------------------------:	     |
| `planner add` 	| Add a new event to your schedule              	| `planner add DESCRIPTION/DATE/START_TIME/END_TIME` |
| `planner list` 	| List the events on a particular date              | `planner list DATE`                                |
| `store_module`	| Store a module in the module list			| `store_module MODULE_CODE`			     |
| `delete_module`	| Delete a module from the module list			| `delete_module MODULE_CODE`			     |
| `bus`         	| Check for a NUS bus route from stop to another 	| `bus /start_location /end_location`                |
| `cap`         	| Calculate the total cap given a set of grades     | `cap MC_GRADE`                             	     |
| `view`        	| View module details                         	    | `view MODULE_CODE`                         	     |
| `help`        	| View the different commands available          	| `help`                                     	     |
| `bye`         	| Exit Kolinux                                   	| `bye`                                      	     |


## Features 

### Add an event to Planner: `planner add`

Adds a new event to your schedule

Format: `planner add DESCRIPTION/DATE/START_TIME/END_TIME`

* The `DATE` needs to follow the following format: `yyyy-mm-dd`
* `START_TIME` and `END_TIME` needs to follow the following format: `hh:MM`

Example of usage:

* `planner add MA1508E quiz/2021-10-10/17:00/18:00`

Expected Outcome:

```
planner add MA1508E quiz/2021-10-10/17:00/18:00
An event has been added to your schedule successfully!
....................................................................
```

### List events: `planner list`

Lists events on a particular date

Format: `planner list DATE`

* The `DATE` needs to follow the following format: `yyyy-mm-dd`

Example of usage:

* `planner list 2021-10-10`

Expected Outcome:

```
planner list 2021-10-10
2021-10-10
17:00 - 18:00 MA1508E quiz
....................................................................
```

### Check NUS bus route between 2 stops: `bus`

Helps to find if there are bus routes between any 2 NUS bus stops. 

Format: `bus /start_location /end_location`

* Bus stop names are not case sensitive

Example of usage:

* `bus /pgp /museum`

Expected Outcome:

```
Bus [D2] goes from PGP to MUSEUM
....................................................................
```

Note: ***Routes for buses A1, D1, D2 and E have been implemented so far***

### Calculate total CAP: `cap`

Format: `cap MC_GRADE`

Example of usage:

* `cap 4A 6B+ 4B 4B- 4A+`

Expected Outcome:

```
cap 4A 6B+ 4B 4B- 4A+
Your CAP for this semester will be 4.09 if you get your desired grades!
....................................................................
```

### View module details: `view`

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
....................................................................
```
### Add modules to module list by code: `store_module`

Format: `store_module MODULE_CODE `

- `MODULE_CODE` needs to be in uppercase letters

Example of usage:

- `store_module CS2113T`

Expected Outcome:

```
Successfully stored module: CS2113T
....................................................................
```

### Delete modules from module list by code: `delete_module`

Format: `delete_module MODULE_CODE `

- `MODULE_CODE` needs to be in uppercase letters

Example of usage:

- `delete_module CS2113T`

Expected Outcome:

```
Successfully deleted module: CS2113T
....................................................................
```

### Add lessons to timetable : `timetable add`

Format: `timetable add DESCRIPTION/DAY/START_TIME/END_TIME`

* `START_TIME` and `END_TIME` needs to follow the following format: `hhMM`
* `DAY` must be from between Monday to Friday where `DAY` is not case-sensitive
  * i.e. `monday` is the same as `MONDAY`

Example of usage:

* `timetable add CS1010 TUT/Monday/1200/1400`
* `timetable add CS2113T LEC/friday/1600/1800`

Expected Outcome:
```
timetable add CS1010 TUT/Monday/1200/1400
Lesson has been added to timetable
....................................................................
```

### Clear timetable: `timetable clear`

Expected outcome:
```
timetable clear
Timetable has been cleared completely
....................................................................
```

### View menu: `help`

Views the different commands available

Expected outcome:

```
help
Here are the list of commands:
1. cap MC_AND_GRADES  - Calculates the total cap for the semester
2. view MODULE_CODE - View the module details
3. bus /START_POINT /END_POINT - Check for a NUS bus route from stop to another
4. store_module MODULE_CODE - Add a module to your module list
5. delete_module MODULE_CODE - Delete a module from your module list
6. planner add DESCRIPTION/DATE/START_TIME/END_TIME - Add an event to your schedule
7. planner list DATE - Lists events on a certain date
8. timetable add DESCRIPTION/DAY/START_TIME/END_TIME - Add lesson to timetable
9. timetable clear - Remove all lessons from timetable
10. help - View this menu again
11. bye - Exit Kolinux
....................................................................
```

### Exit Kolinux: `bye`

Exits Kolinux

Expected Outcome:

```
bye
Bye! Thank you for using Kolinux
....................................................................
```

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Locate the `/data` directory stored in the same folder as `Kolinux.jar`. Copy the directory to the other 
computer, in the same folder as `Kolinux.jar`. The data will be automatically read upon starting up `Kolinux`.

## Data Storage

Kolinux automatically creates a directory `/data` upon the first start up. The directory consists of the files:

`logger.log` - User activity log of the most recent session.

`planner.txt` - Planner data of the user.

`timetable.txt` - Timetable data of the user.

Users are advised not to modify these files as it can corrupt important user data.
