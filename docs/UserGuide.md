# User Guide

## Introduction

Kolinux is built to help NUS Computer Engineering freshmen to better integrate into university life 
by allowing them to **manage their work** and **learn more about NUS**, all in a **single integrated platform**. 
It is optimized for CLI users so that they can access the information that they require faster by typing in commands.

Kolinux offers a wide range of features for freshmen. These features include a **module manager**
for freshmen to manage their modules and view information about them, a **timetable** to view their classes, an 
**event planner** for freshmen to organise their schedule for the day, a **CAP calculator**, and a **route finder** 
for the NUS internal shuttle bus.

### Latest Releases

* 💥 `v2.0` _Coming soon_
* 💥 `v1.0` Released on Oct 12, 2021.

## Content

* [Quick Start](#quick-start)
* [List of Commands](#list-of-commands)
* [Features](#features)
  * [`module`](#module-manager-module)
    * [`module store`](#add-modules-to-module-list-by-code-module-store)
    * [`module delete`](#delete-modules-from-module-list-by-code-module-delete)
    * [`module list`](#list-modules-from-module-list-module-list)
    * [`module view`](#view-module-details-module-view)
    * [`module grade`](#set-a-modules-grade-in-module-list-module-grade)
    * [`module cap`](#calculate-overall-cap-from-modules-in-module-list-module-cap)
  * [`timetable`](#timetable-timetable)
    * [`timetable add`](#add-lessons-to-timetable--timetable-add)
    * [`timetable delete`](#delete-lessons-from-timetable--timetable-delete)
    * [`timetable view`](#view-timetable-on-cli--timetable-view)
    * [`timetable update`](#update-a-lesson-to-another-timing-your-timetable--timetable-update)
  * [`planner`](#event-planner-planner)
    * [`planner add`](#add-an-event-to-planner-planner-add)
    * [`planner list`](#list-events-planner-list)
    * [`planner delete`](#delete-an-event-from-planner-planner-delete)
  * [`bus`](#nus-bus-route-finder-bus)
  * [`cap`](#cap-calculator-cap)
    * [`cap mc`](#calculate-cap-using-modular-credit-cap-mc)
    * [`cap code`](#calculate-cap-using-module-code-cap-code)
  * [`help`](#view-menu-help)
  * [`bye`](#exit-bye)
* [FAQ](#faq)
* [Data Storage](#data-storage)

## Quick Start

### Installation and start-up

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

|    Command    	|                     Action                     	              |               Command Format               	       |
|:-------------:	|:----------------------------------------------:	              |:------------------------------------------:	       |
| `module store`	| Store a module in the module list			                      | `module store MODULE_CODE`			               |
| `module delete`	| Delete a module from the module list			                  | `module delete MODULE_CODE`			               |
| `module list`     | List all stored modules from the module list                    | `module list`                                      |
| `module view`     | View module details                                             | `module view MODULE_CODE`                          |
| `module grade`    | Set the grade of a module in the module list by its module code | `module grade CODE/GRADE`                          |
| `module cap`      | Calculate overall CAP of modules in the module list             | `module cap`                                       |
| `timetable add`   | Add a lesson to your timetable                                  | `timetable add MODULE_CODE/LESSON_TYPE/DAY/START_TIME/END_TIME`|
| `timetable delete`| Delete a lesson from your timetable                             | `timetable delete MODULE_CODE/LESSON_TYPE/DAY`     |
| `timetable view`  | View timetable on CLI                                           | `timetable view`                                   |
| `timetable update`| Update a lesson to another timing your timetable                | `timetable update MODULE_CODE/LESSON_TYPE/OLD_DAY/NEW_DAY/NEW_START_TIME`|
| `planner add` 	| Add a new event to your schedule on a particular date           | `planner add DESCRIPTION/DATE/START_TIME/END_TIME` |
| `planner list` 	| List the events on a particular date                            | `planner list DATE`                                |
| `planner delete` 	| Delete an event on a particular date                            | `planner delete DATE`                              |
| `bus`         	| Check for an NUS bus route from one stop to another 	          | `bus /start_location /end_location`                |
| `cap`         	| Calculate the total CAP given a set of grades                   | `cap MC_GRADE`                             	       |
| `help`        	| View the different commands available          	              | `help`                                     	       |
| `bye`         	| Exit Kolinux                                   	              | `bye`                                      	       |


## Features 

Kolinux provides a **single integrated platform** consisting of **5 core features**:

1. `module` - Module Manager
2. `timetable` - Timetable
3. `planner` - Event Planner
4. `bus` - Route Finder
5. `cap` - CAP Calculator

### Module Manager: `module`

The module manager allows users to `store`, `delete`, and `list` the modules they are taking for the semester. Users 
are also able to `view` important information regarding a module, such as the description and workload requirements. 
In addition, users are able to set a `grade` on the modules in their list for the purpose of CAP calculation using our 
in-built CAP calculator.

#### Add modules to module list by code: `module store`

Format: `module store MODULE_CODE `

- `MODULE_CODE` needs to be in uppercase letters

Example of usage:

- `module store CS2113T`

Demo:

```
Successfully stored module: CS2113T
....................................................................
```

#### Delete modules from module list by code: `module delete`

Format: `module delete MODULE_CODE `

- `MODULE_CODE` needs to be in uppercase letters

Example of usage:

- `module delete CS2113T`

Demo:

```
Successfully deleted module: CS2113T
....................................................................
```

#### List modules from module list: `module list`

Format: `module list `

Example of usage:

- `module list` when CS2113T is already stored in the list

Demo:

```
CS2113T Software Engineering & Object-Oriented Programming

Workload:
Lecture: 2.0 hours
Tutorial: 1.0 hours
Project Work: 3.0 hours
Preparation: 4.0 hours
....................................................................
Remember to add the module's lessons to the timetable based on the workload
....................................................................
```

#### View module details: `module view`

Format: `module view MODULE_CODE`

* `MODULE_CODE` needs to be in uppercase letters

Example of usage:

* `module view CS1010`

Demo:

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

#### Set a module's grade in module list: `module grade`

Format: `module grade CODE/GRADE `

Example of usage:

- `module grade CS2113T/A` when CS2113T is already stored in the list

Demo:

```
module grade CS2113T/A
CS2113T grade set to A
....................................................................
```

❕ Note: The grades set using this command will be used to calculate CAP based on your module list. 
You may choose to enter real grades to calculate your current CAP and/or enter target grades to calculate a CAP goal.

### Calculate overall CAP from modules in module list: `module cap`

Format: `module cap`

Example of usage:

- `module cap` when at least one module is already stored in the list with the module's grade

Demo:

```
module cap
Based on your available grade, your cap for this semester is 5.00
....................................................................
```

This feature also provides user with the minimum grade to get for the other modules in order to achieve desired CAP by including the CAP at the end of the command.

Format: `module cap DESIRED_CAP`

Example of usage:
- `module cap 4.0` when at least one module doesn't have the grade stored

Demo:

```
module cap 4.0
Based on your modules, you have to get an average grade of B+ or higher 
in order to achieve your desired CAP
....................................................................
```

### Timetable: `timetable`

This feature allows users to `add` and `clear` lessons from their timetable based on the modules added in the module
manager. It also provides users an aesthetic visual representation of their timetable on CLI interface.

#### Add lessons to timetable : `timetable add`

Format: `timetable add MODULE_CODE/LESSON_TYPE/DAY/START_TIME/END_TIME`

* Ensure `MODULE_CODE` is stored in the module list using 
[`module store`](#add-modules-to-module-list-by-code-module-store) first before adding to timetable
* `LESSON_TYPE` needs to be one of the following: 
  * `TUT` refers to tutorial
  * `LEC` refers to lecture
  * `LAB` refers to lab
* `START_TIME` and `END_TIME` needs to follow the following format: `hhMM` and must be between the school hours 
`0600` and `2100`
* `DAY` must be from between `Monday` and `Friday`
* `MODULE_CODE`,`LESSON_TYPE` and `DAY`  are not case-sensitive
  * i.e. `CS1010` is the same as `cs1010`
  * i.e. `TUT` is the same as `tut` or `Tut`
  * i.e. `monday` is the same as `MONDAY`


Example of usage:

* `timetable add CS1010/TUT/Monday/1200/1400`
* `timetable add CS2113T/LEC/friday/1600/1800`

Demo:
```
timetable add CS1010/TUT/Monday/1200/1400
Lesson has been added to timetable
....................................................................
```
❕ Note: Ensure `MODULE_CODE` is stored in the module list using
[`module store`](#add-modules-to-module-list-by-code-module-store) first before adding to timetable as only
the modules added to module list can be added to the timetable
#### Delete lessons from timetable : `timetable delete`

Format: `timetable delete MODULE_CODE/LESSON_TYPE/DAY`

* Ensure `MODULE_CODE` is stored in the module list using
  [`module store`](#add-modules-to-module-list-by-code-module-store) first before adding to timetable
* `LESSON_TYPE` needs to be one of the following:
  * `TUT` refers to tutorial
  * `LEC` refers to lecture
  * `LAB` refers to lab
* `DAY` must be from between `Monday` and `Friday`
* `MODULE_CODE`,`LESSON_TYPE` and `DAY`  are not case-sensitive
  * i.e. `CS1010` is the same as `cs1010`
  * i.e. `TUT` is the same as `tut` or `Tut`
  * i.e. `monday` is the same as `MONDAY`

Example of usage:

* `timetable delete cs1010/lec/tuesday`
* `timetable delete CS2113T/LEC/Friday`

Demo:
```
timetable delete cs1010/lec/tuesday
CS1010 LEC tuesday has been deleted from timetable
....................................................................
```
#### View timetable on CLI : `timetable view`

Format: `timetable view`

Example of usage:

* `timetable add CS1010/LEC/monday/1900/2000` followed by `timetable view`

Demo:
```
timetable add CS1010/LEC/monday/1900/2000
CS1010 LEC has been added to timetable
....................................................................
timetable view
+-------------+--------------------+--------------------+--------------------+--------------------+--------------------+
|             |       MONDAY       |       TUESDAY      |      WEDNESDAY     |      THURSDAY      |       FRIDAY       |
+-------------+--------------------+--------------------+--------------------+--------------------+--------------------+
|0600 - 0700  |                    |                    |                    |                    |                    |
+-------------+--------------------+--------------------+--------------------+--------------------+--------------------+
|0700 - 0800  |                    |                    |                    |                    |                    |
+-------------+--------------------+--------------------+--------------------+--------------------+--------------------+
|0800 - 0900  |                    |                    |                    |                    |                    |
+-------------+--------------------+--------------------+--------------------+--------------------+--------------------+
|0900 - 1000  |                    |                    |                    |                    |                    |
+-------------+--------------------+--------------------+--------------------+--------------------+--------------------+
|1000 - 1100  |                    |                    |                    |                    |                    |
+-------------+--------------------+--------------------+--------------------+--------------------+--------------------+
|1100 - 1200  |                    |                    |                    |                    |                    |
+-------------+--------------------+--------------------+--------------------+--------------------+--------------------+
|1200 - 1300  |                    |                    |                    |                    |                    |
+-------------+--------------------+--------------------+--------------------+--------------------+--------------------+
|1300 - 1400  |                    |                    |                    |                    |                    |
+-------------+--------------------+--------------------+--------------------+--------------------+--------------------+
|1400 - 1500  |                    |                    |                    |                    |                    |
+-------------+--------------------+--------------------+--------------------+--------------------+--------------------+
|1500 - 1600  |                    |                    |                    |                    |                    |
+-------------+--------------------+--------------------+--------------------+--------------------+--------------------+
|1600 - 1700  |                    |                    |                    |                    |                    |
+-------------+--------------------+--------------------+--------------------+--------------------+--------------------+
|1700 - 1800  |                    |                    |                    |                    |                    |
+-------------+--------------------+--------------------+--------------------+--------------------+--------------------+
|1800 - 1900  |                    |                    |                    |                    |                    |
+-------------+--------------------+--------------------+--------------------+--------------------+--------------------+
|1900 - 2000  |     CS1010 LEC     |                    |                    |                    |                    |
+-------------+--------------------+--------------------+--------------------+--------------------+--------------------+
|2000 - 2100  |                    |                    |                    |                    |                    |
+-------------+--------------------+--------------------+--------------------+--------------------+--------------------+
Timetable has been printed above
....................................................................
```
#### Update a lesson to another timing your timetable : `timetable update`

Format: `timetable update MODULE_CODE/LESSON_TYPE/OLD_DAY/NEW_DAY/NEW_START_TIME`

* `LESSON_TYPE` needs to be one of the following:
  * `TUT` refers to tutorial
  * `LEC` refers to lecture
  * `LAB` refers to lab
* `START_TIME` and `END_TIME` needs to follow the following format: `hhMM` and must be between the school hours
  `0600` and `2100`
* `DAY` must be from between `Monday` and `Friday`
* `MODULE_CODE`,`LESSON_TYPE` and `DAY`  are not case-sensitive
  * i.e. `CS1010` is the same as `cs1010`
  * i.e. `TUT` is the same as `tut` or `Tut`
  * i.e. `monday` is the same as `MONDAY`

Example of usage:

* `timetable update cs1010/lec/tuesday/monday/1200`
* `timetable update CS2113T/LEC/Friday/Monday/1300`

Demo:
```
timetable update cs1010/lec/tuesday/monday/1200
CS1010 LEC has been updated
....................................................................
```

### Event Planner: `planner`

The event planner works by allowing users to `add`, `delete`, and `list` any events happening on a specific date. 
This is for users to manage their schedules daily. This feature is also integrated with the timetable and the module 
manager so that users will also be able to view their lessons and exams on specific dates.

#### Add an event to Planner: `planner add`

Adds a new event to your schedule

Format: `planner add DESCRIPTION/DATE/START_TIME/END_TIME`

* The `DATE` needs to follow the following format: `yyyy-mm-dd`
* `START_TIME` and `END_TIME` needs to follow the following format: `hhMM`

Example of usage:

* `planner add MA1508E quiz/2021-10-10/1700/1800`

Demo:

```
planner add MA1508E quiz/2021-10-10/1700/1800
An event has been added to your schedule successfully!
....................................................................
planner add Watch movie/2021-10-10/1530/1730
You already have an event ongoing for that time period, do you still want to add? (y/n)
y
An event has been added to your schedule successfully!
....................................................................
```

❕ Note: Users who attempt to add an event that has a time conflict with another event will need to 
give additional confirmation if they wish to proceed.

#### List events: `planner list`

Lists events on a particular date

Format: `planner list DATE`

* The `DATE` needs to follow the following format: `yyyy-mm-dd`

Example of usage:

* `planner list 2021-10-10`

Demo:

```
planner list 2021-10-10
2021-10-10
15:30 - 17:30 Watch movie
17:00 - 18:00 MA1508E quiz
....................................................................
```

❕ Note: The `list` will include all the events, lessons, and exams occurring on the `DATE` specified.
_Visit the section on [`timetable`](#timetable-timetable) for commands to add lessons._
_Exams are added to the list automatically as modules are stored in the [`module`](#module-manager-module) manager._

#### Delete an event from Planner: `planner delete`

Deletes an event from your schedule

Format: `planner delete DATE`

This command has two steps:
1. Input the command as shown in the format above.
2. The schedule of events on `DATE` will be displayed, find the ID of the event you wish to delete.
3. Input the ID to delete the event.

* The `DATE` needs to follow the following format: `yyyy-mm-dd`

Demo:
```
planner delete 2021-11-05
Please enter the ID of the event you wish to delete (Enter 'n' to terminate this operation):
07:00 - 07:30 10km run (id: 7)
10:00 - 12:00 Attend career talk (id: 9)
15:00 - 17:00 Watch movie (id: 1)
9
An event has been deleted from your schedule successfully!
....................................................................
planner list 2021-11-05
2021-11-05
07:00 - 07:30 10km run
15:00 - 17:00 Watch movie
....................................................................
```

❕ Note: Only events added via the `planner` will be displayed on the list in step 2.
Hence, users are not allowed to delete lessons added via the `timetable` and exams from the `planner`.
_Visit the section on [`timetable`](#timetable-timetable) for commands to delete lessons._
_Exams will be automatically deleted as modules are deleted from the [`module`](#module-manager-module) manager._

### NUS Bus Route Finder: `bus`

The route finder helps users to find if there are bus routes between any two NUS bus stops. This feature allows users
to get familiarised with the NUS internal shuttle bus route.

Format: `bus /start_location /end_location`

* Bus stop names are not case sensitive

Example of usage:

* `bus /pgp /museum`

Demo:

```
Bus [D2] goes from PGP to MUSEUM
....................................................................
```

❕ Note: Only routes for buses A1, D1, D2 and E have been implemented so far.

### CAP Calculator: `cap`

The CAP calculator is an essential tool for many NUS students to keep track on their CAP and set desired grades for the
current semester. User can choose between different formats of module and the respective grade to allow more command 
flexibility.

#### Calculate CAP using modular credit: `cap mc`

Format: `cap mc MC/GRADE`

Example of usage:

* `cap mc 4/A 6/B+ 4/B 4/B- 4/A+`

Demo:

```
cap 4A 6B+ 4B 4B- 4A+
Your CAP for this semester will be 4.09 if you get your desired grades!
....................................................................
```

#### Calculate CAP using module code: `cap code`

Format: `cap code CODE/GRADE`

Example of usage:

* `cap code CS2113T/A CS2101/C CG2027/B-`

Demo:

```
cap code CS2113T/A CS2101/C CG2027/B-
Your CAP for this semester will be 3.40 if you get your desired grades!
....................................................................
```

### View menu: `help`

Views the different commands available.

Demo:

```
help
Here are the list of commands:
1. cap mc MC/GRADE - Calculates the total cap for the semester from MC
2. cap code MODULE_CODE/GRADE - Calculates the total cap for the semester from MODULE_CODE
3. bus /START_POINT /END_POINT - Check for a NUS bus route from stop to another
4. bus stop list - List all the bus stop names
5. module view MODULE_CODE - View the module details
6. module store MODULE_CODE - Add a module to your module list
7. module delete MODULE_CODE - Delete a module from your module list
8. module list - List all modules stored in your module list
9. module grade CODE/GRADE - Update the module CODE from your module list with a new grade GRADE
10. module cap - Calculate the overall CAP of modules stored in your module list
11. module cap DESIRED_CAP - Calculate the average minimum grade for the other modules needed
    to achieve DESIRED_CAP
12. planner add DESCRIPTION/DATE/START_TIME/END_TIME - Add an event to your schedule
13. planner list DATE - Lists events on a certain date
14. planner delete DATE - Delete an event on a certain date
15. timetable add MODULE_CODE/LESSON_TYPE/DAY/START_TIME/END_TIME - Add lesson to timetable
16. timetable update MODULE_CODE/LESSON_TYPE/OLD_DAY/NEW_DAY/NEW_START_TIME
    - shift a lesson to another timing
17. timetable delete MODULE_CODE/LESSON_TYPE/DAY - delete a specific lesson
18. timetable view - Print the timetable on CLI
19. help - View this menu again
20. bye - Exit Kolinux
....................................................................
```

### Exit: `bye`

Exits Kolinux.

Demo:

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

`moduleList.txt` - Module manager data of the user.

`planner.txt` - Planner data of the user.

`timetable.txt` - Timetable data of the user.

❗ Users are advised not to modify these files as it can corrupt important user data.
