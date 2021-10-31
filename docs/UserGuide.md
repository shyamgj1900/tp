# Welcome to Kolinux User Guide! 😃

## Introduction

_Kolinux_ helps NUS freshmen to better integrate into university life by allowing them to 
**manage their modules and schedule**, **familiarise with the NUS internal bus system**, and **receive grade
suggestions based on their CAP** all in a **single integrated platform**. It is optimized for CLI 
users so that they can access the information that they require faster by typing in commands easily.

Hence, _Kolinux_ offers a wide range of features for freshmen. These features include a **module manager**
for freshmen to manage their modules and view information about them, a **timetable** to view their classes, an 
**event planner** for freshmen to organise their schedule for the day, a **CAP calculator**, and a **route finder** 
for the NUS internal shuttle bus.

This user guide brings you on a tour around _Kolinux_ and gives you step-by-step instructions on using its features.
Alternatively, you may visit the [Table of Contents](#table-of-contents) for quick access to any of the features. You
may also navigate to the [List of Commands](#2-list-of-commands) to view a summary of all our available commands and
their usages.

The following explains the use of different icons in this user guide:
* ⚠️ - Notes about the feature.
* ❗ - Important rules that you should follow when using the program.
* 🔗 - Links to related sections in this user guide.

### Latest Releases

* 💥 `v2.1` _Coming soon_
* 💥 [`v2.0`](https://github.com/AY2122S1-CS2113T-W11-1/tp/releases) Released on Oct 26, 2021.
* 💥 `v1.0` Released on Oct 12, 2021.

## Table of Contents

* 1 [Quick Start](#1-quick-start)
* 2 [List of Commands](#2-list-of-commands)
* 3 [Features](#3-features)
  * 3.1 [`module`](#31-module-manager-module)
    * 3.1.1 [`module store`](#311-add-modules-to-module-list-by-code-module-store)
    * 3.1.2 [`module delete`](#312-delete-modules-from-module-list-by-code-module-delete)
    * 3.1.3 [`module list`](#313-list-modules-from-module-list-module-list)
    * 3.1.4 [`module view`](#314-view-module-details-module-view)
    * 3.1.5 [`module grade`](#315-set-a-modules-grade-in-module-list-module-grade)
    * 3.1.6 [`module cap`](#316-calculate-overall-cap-from-modules-in-module-list-module-cap)
  * 3.2 [`timetable`](#32-timetable-timetable)
    * 3.2.1 [`timetable add`](#321-add-lessons-to-timetable--timetable-add)
    * 3.2.2 [`timetable delete`](#322-delete-lessons-from-timetable--timetable-delete)
    * 3.2.3 [`timetable view`](#323-view-timetable-on-cli--timetable-view)
    * 3.2.4 [`timetable update`](#324-update-a-lesson-to-another-timing-your-timetable--timetable-update)
  * 3.3 [`planner`](#33-event-planner-planner)
    * 3.3.1 [`planner add`](#331-add-an-event-to-planner-planner-add)
    * 3.3.2 [`planner list`](#332-list-events-on-a-specific-date-planner-list)
    * 3.3.3 [`planner delete`](#333-delete-an-event-from-planner-planner-delete)
  * 3.4 [`bus`](#34-nus-bus-route-finder-bus)
    * 3.4.1 [`bus`](#341-bus-route-search-bus)
    * 3.4.2 [`bus stop list`](#342-list-of-all-bus-stop-names-bus-stop-list)
  * 3.5 [`cap`](#35-cap-calculator-cap)
    * 3.5.1 [`cap mc`](#351-calculate-cap-using-modular-credit-cap-mc)
    * 3.5.2 [`cap code`](#352-calculate-cap-using-module-code-cap-code)
  * 3.6 [`help`](#36-view-menu-help)
  * 3.7 [`bye`](#37-exit-bye)
* 4 [FAQ](#4-faq)
* 5 [Data Storage](#5-data-storage)
* 6 [Contact Us](#6-contact-us)

## 1 Quick Start

### Installation and start-up

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `Kolinux.jar` from [here](https://github.com/AY2122S1-CS2113T-W11-1/tp/releases).
3. Copy `Kolinux.jar` to an empty folder.
4. Start the command prompt.
5. Navigate to the folder `Kolinux.jar` is stored on the command prompt.
6. Launch `Kolinux.jar` using `java -jar Kolinux.jar`.
7. Enter commands to use _Kolinux_.

The following is what you should expect to observe on the terminal upon start-up:
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

## 2 List of Commands

|    Command    	|                     Action                     	              |               Command Format               	       |
|:-------------:	|:----------------------------------------------:	              |:------------------------------------------:	       |
| [`module store`](#311-add-modules-to-module-list-by-code-module-store)	| Store a module in the module list			                      | `module store MODULE_CODE`			               |
| [`module delete`](#312-delete-modules-from-module-list-by-code-module-delete)	| Delete a module from the module list			                  | `module delete MODULE_CODE`			               |
| [`module list`](#313-list-modules-from-module-list-module-list)     | List all stored modules from the module list                    | `module list`                                      |
| [`module view`](#314-view-module-details-module-view)     | View module information                                         | `module view MODULE_CODE`                          |
| [`module grade`](#315-set-a-modules-grade-in-module-list-module-grade)    | Set the grade of a module in the module list by its module code | `module grade MODULE_CODE/GRADE`                          |
| [`module cap`](#316-calculate-overall-cap-from-modules-in-module-list-module-cap)      | Calculate CAP given modules and grades in the module list       | `module cap`                                       |
| [`module cap`](#316-calculate-overall-cap-from-modules-in-module-list-module-cap)      | Get average grade needed to get desired CAP from module list    | `module cap DESIRED_CAP`                           |
| [`timetable add`](#321-add-lessons-to-timetable--timetable-add)   | Add a lesson to your timetable                                  | `timetable add MODULE_CODE/LESSON_TYPE/DAY/START_TIME/END_TIME`|
| [`timetable delete`](#322-delete-lessons-from-timetable--timetable-delete)| Delete a lesson from your timetable                             | `timetable delete MODULE_CODE/LESSON_TYPE/DAY`     |
| [`timetable view`](#323-view-timetable-on-cli--timetable-view)  | View timetable                                                  | `timetable view`                                   |
| [`timetable update`](#324-update-a-lesson-to-another-timing-your-timetable--timetable-update)| Update a lesson to another timing on your timetable             | `timetable update MODULE_CODE/LESSON_TYPE/OLD_DAY/NEW_DAY/NEW_START_TIME`|
| [`planner add`](#331-add-an-event-to-planner-planner-add) 	| Add a new event to your planner on a particular date           | `planner add DESCRIPTION/DATE/START_TIME/END_TIME` |
| [`planner list`](#332-list-events-on-a-specific-date-planner-list)	| List the events in your planner on a particular date                            | `planner list DATE`                                |
| [`planner delete`](#333-delete-an-event-from-planner-planner-delete) 	| Delete an event from your planner on a particular date                            | `planner delete DATE`                              |
| [`bus`](#341-bus-route-search-bus)        	| Check for an NUS bus route from one stop to another 	          | `bus /start_location /end_location`                |
| [`bus stop list`](#342-list-of-all-bus-stop-names-bus-stop-list)   | Lists all the bus stops in the NUS shuttle bus routes           | `bus stop list`                                    |
| [`cap mc`](#351-calculate-cap-using-modular-credit-cap-mc)         	| Calculate CAP using a set of modular credits and grades         | `cap mc MC/GRADE`                            	   |
| [`cap code`](#352-calculate-cap-using-module-code-cap-code)        | Calculate CAP using a set of module codes and grades            | `cap code MODULE_CODE/GRADE`                              |
| [`help`](#36-view-menu-help)       	| View the different commands available          	              | `help`                                     	       |
| [`bye`](#37-exit-bye)      	| Exit _Kolinux_                                   	              | `bye`                                      	       |


## 3 Features 

_Kolinux_ provides a **single integrated platform** consisting of **5 core features**:

1. `module` - Module Manager
2. `timetable` - Timetable
3. `planner` - Event Planner
4. `bus` - Route Finder
5. `cap` - CAP Calculator

### 3.1 Module Manager: `module`

The module manager allows users to [`store`](#311-add-modules-to-module-list-by-code-module-store), 
[`delete`](#312-delete-modules-from-module-list-by-code-module-delete), and 
[`list`](#313-list-modules-from-module-list-module-list) the modules they are taking for the semester. Users 
are also able to [`view`](#314-view-module-details-module-view) important information regarding a module, such as the 
description and workload requirements. In addition, users are able to set a 
[`grade`](#315-set-a-modules-grade-in-module-list-module-grade) on the modules in their list for the purpose of 
[`cap`](#316-calculate-overall-cap-from-modules-in-module-list-module-cap) calculation and grades suggestion.

#### 3.1.1 Add modules to module list by code: `module store`

**Format:** `module store MODULE_CODE `

- `MODULE_CODE` is not case-sensitive, hence `cs2113t` and `CS2113T` will give the same output.

**Example of usage:**

- `module store CS2113T`

Demo:

```
Successfully stored module: CS2113T
....................................................................
```

#### 3.1.2 Delete modules from module list by code: `module delete`

**Format:** `module delete MODULE_CODE `

- `MODULE_CODE` is not case-sensitive, hence `cs2113t` and `CS2113T` will give the same output.

**Example of usage:**

- `module delete CS2113T`

Demo:

```
Successfully deleted module: CS2113T
....................................................................
```

#### 3.1.3 List modules from module list: `module list`

**Format:** `module list `

**Example of usage:**

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

#### 3.1.4 View module details: `module view`

**Format:** `module view MODULE_CODE`

- `MODULE_CODE` is not case-sensitive, hence `cs2113t` and `CS2113T` will give the same output.

**Example of usage:**

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

#### 3.1.5 Set a module's grade in module list: `module grade`

**Format:** `module grade CODE/GRADE `

**Example of usage:**

- `module grade CS2113T/A` when CS2113T is already stored in the list

Demo:

```
module grade CS2113T/A
CS2113T grade set to A
....................................................................
```

>⚠️ Note: The grades set using this command will be used to calculate CAP based on your module list. 
You may choose to enter real grades to calculate your current CAP and/or enter target grades to calculate a CAP goal.

### 3.1.6 Calculate overall CAP from modules in module list: `module cap`

**Format:** `module cap`

**Example of usage:**

- `module cap` when at least one module is already stored in the list with the module's grade

Demo:

```
module cap
Based on your available grade, your cap for this semester is 5.00
....................................................................
```

This feature also provides user with the minimum grade to get for the other modules in order to achieve desired CAP 
by including the CAP at the end of the command.

**Format:** `module cap DESIRED_CAP`

**Example of usage:**
- `module cap 4.0` when at least one module doesn't have the grade stored

Demo:

```
module cap 4.0
Based on your modules, you have to get an average grade of B+ or higher 
in order to achieve your desired CAP
....................................................................
```

### 3.2 Timetable: `timetable`

This feature allows users to [`add`](#321-add-lessons-to-timetable--timetable-add) and 
[`delete`](#322-delete-lessons-from-timetable--timetable-delete) lessons from their timetable based on the modules 
added in the module manager. 
Users are also able to [`update`](#324-update-a-lesson-to-another-timing-your-timetable--timetable-update)
their existing lessons to another time slot.
It also provides users an aesthetic visual representation of their timetable for users to 
[`view`](#323-view-timetable-on-cli--timetable-view) on CLI.

#### 3.2.1 Add lessons to timetable : `timetable add`

**Format:** `timetable add MODULE_CODE/LESSON_TYPE/DAY/START_TIME/END_TIME`

* Ensure `MODULE_CODE` is stored in the module list using 
[`module store`](#311-add-modules-to-module-list-by-code-module-store) first before adding to timetable
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


**Example of usage:**

* `timetable add CS1010/TUT/Monday/1200/1400`
* `timetable add CS2113T/LEC/friday/1600/1800`

Demo:
```
timetable add CS1010/TUT/Monday/1200/1400
Lesson has been added to timetable
....................................................................
```
>⚠️ Note: Ensure `MODULE_CODE` is stored in the module list using
[`module store`](#311-add-modules-to-module-list-by-code-module-store) first before adding to timetable as only
the modules added to module list can be added to the timetable

>⚠️ ️Note: Please also note that the timetable has been built to only take lesson of durations in multiples of 1 hour.
> This has been done so to ensure readability of the timetable on CLI when you input 
> [`timetable view`](#323-view-timetable-on-cli--timetable-view). So if you will not be allowed to enter e.g. 30 min 
> slots of lessons onto the timetable.

>🔗 Visit [`module`](#31-module-manager-module) for more information on the command formats.

#### 3.2.2 Delete lessons from timetable : `timetable delete`

**Format:** `timetable delete MODULE_CODE/LESSON_TYPE/DAY`

* Ensure `MODULE_CODE` is stored in the module list using
  [`module store`](#311-add-modules-to-module-list-by-code-module-store) first before adding to timetable
* `LESSON_TYPE` needs to be one of the following:
  * `TUT` refers to tutorial
  * `LEC` refers to lecture
  * `LAB` refers to lab
* `DAY` must be from between `Monday` and `Friday`
* `MODULE_CODE`,`LESSON_TYPE` and `DAY`  are not case-sensitive
  * i.e. `CS1010` is the same as `cs1010`
  * i.e. `TUT` is the same as `tut` or `Tut`
  * i.e. `monday` is the same as `MONDAY`

**Example of usage:**

* `timetable delete cs1010/lec/tuesday`
* `timetable delete CS2113T/LEC/Friday`

Demo:
```
timetable delete cs1010/lec/tuesday
CS1010 LEC tuesday has been deleted from timetable
....................................................................
```
#### 3.2.3 View timetable on CLI : `timetable view`

**Format:** `timetable view`

**Example of usage:**

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
>⚠️ Note: Please expand your CLI to view the timetable clearly if the timetable appears skewed.

#### 3.2.4 Update a lesson to another timing your timetable : `timetable update`

**Format:** `timetable update MODULE_CODE/LESSON_TYPE/OLD_DAY/NEW_DAY/NEW_START_TIME`

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

**Example of usage:**

* `timetable update cs1010/lec/tuesday/monday/1200`
* `timetable update CS2113T/LEC/Friday/Monday/1300`

Demo:
```
timetable update cs1010/lec/tuesday/monday/1200
CS1010 LEC has been updated
....................................................................
```

### 3.3 Event Planner: `planner`

The event planner works by allowing you to [`add`](#331-add-an-event-to-planner-planner-add), 
[`delete`](#333-delete-an-event-from-planner-planner-delete), and 
[`list`](#332-list-events-on-a-specific-date-planner-list) any events happening on a specific date. 
This is for you to manage your schedules daily. This feature is also integrated with the timetable and the module 
manager so that you will also be able to view your lessons and exams on specific dates.

#### 3.3.1 Add an event to Planner: `planner add`

**Format:** `planner add DESCRIPTION/DATE/START_TIME/END_TIME`

* The `DATE` needs to follow the following format: `yyyy-mm-dd`
* `START_TIME` and `END_TIME` needs to follow the following format: `hhMM`

**Example of usage:**

* `planner add MA1508E quiz/2021-10-10/1700/1800`

This is what you should observe on your terminal when adding an event to your planner:

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

>⚠️ Note: If you attempt to add an event that has a time conflict with another event/lesson/exam, you will need to 
give additional confirmation if you wish to proceed.

#### 3.3.2 List events on a specific date: `planner list`

**Format:** `planner list DATE`

* The `DATE` needs to follow the following format: `yyyy-mm-dd`

**Example of usage:**

* `planner list 2021-10-10`

This is what you should observe on the terminal when you list the events in your planner on a specific date:

```
planner list 2021-10-10
2021-10-10
15:30 - 17:30 Watch movie
17:00 - 18:00 MA1508E quiz
....................................................................
```

>⚠️ Note: The `list` will include all the events, lessons, and exams occurring on the `DATE` specified.

>🔗 Visit the section on [`timetable`](#32-timetable-timetable) for commands to add lessons.

>🔗 Exams are added to the list automatically as modules are stored in the [`module`](#31-module-manager-module) manager.

#### 3.3.3 Delete an event from Planner: `planner delete`

**Format:** `planner delete DATE`

This command has two steps:
1. Input the command as shown in the format above.
2. Find the ID of the event you wish to delete from the schedule of events on `DATE` displayed.
3. Input the ID to delete the event.

* The `DATE` needs to follow the following format: `yyyy-mm-dd`

This is what you should observe on the terminal when you delete an event on a specific date:
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

>⚠️ Note: Only events added via the `planner` will be displayed on the list in step 2.
Hence, you are not allowed to delete lessons added via the `timetable` and exams from the `planner`.

>🔗 Visit the section on [`timetable`](#32-timetable-timetable) for commands to delete lessons.

>🔗 Exams will be automatically deleted as modules are deleted from the [`module`](#31-module-manager-module) manager.

### 3.4 NUS Bus Route Finder: `bus`

The route finder helps users to find if there are [`bus`](#341-bus-route-search-bus) routes between any two NUS shuttle 
service bus stops. Users are recommended buses they can take to reach their destination. 
Users also have the ability to view the [`bus stop list`](#342-list-of-all-bus-stop-names-bus-stop-list) which contains 
all the bus stop names which are covered by the NUS shuttle bus service. 
This feature allows users to get familiarised with the NUS internal shuttle bus route.

#### 3.4.1 Bus route search: `bus`

**Format:** `bus /start_location /end_location`

* Bus stop names are not case-sensitive

**Example of usage:**

* `bus /pgp /museum`

Demo:

```
Bus [D2] goes from PGP to MUSEUM
....................................................................
```

#### 3.4.2 List of all bus stop names: `bus stop list`

**Format:** `bus stop list`

**Example of usage:**

* `bus stop list`

Demo:

```
._______________________________________________________________________________________________________.
|_____BUS_A1_____|_____BUS_A2_____|_____BUS_D1_____|_____BUS_D2_____|_____BUS_E_____|_______BUS_K_______|
|KR BUS TERMINAL |KR BUS TERMINAL |OPP HSSML       |PGP             |KENT VALE      |PGP                |
|LT13            |IT              |OPP NUSS        |KR MRT          |EA             |KR MRT             |
|AS 5            |OPP YIH         |COM 2           |LT27            |SDE 3          |LT27               |
|COM 2           |MUSEUM          |VENTUS          |UHALL           |IT             |UHALL              |
|BIZ 2           |UHC             |IT              |OPP UHC         |OPP YIH        |OPP UHC            |
|OPP TCOMS       |OPP UHALL       |OPP YIH         |MUSEUM          |UTOWN          |YIH                |
|PGP             |S 17            |MUSEUM          |UTOWN           |RAFFLES HALL   |CLB                |
|KR MRT          |OPP KR MRT      |UTOWN           |UHC             |               |OPP SDE 3          |
|LT27            |PGPR            |YIH             |OPP UHALL       |               |JAPANESE PRI SCHOOL|
|UHALL           |TCOMS           |CLB             |S 17            |               |KENT VALE          |
|OPP UHC         |OPP HSSML       |LT13            |OPP KR MRT      |               |MUSEUM             |
|YIH             |OPP NUSS        |AS 5            |PGPR            |               |UHC                |
|CLB             |COM 2           |BIZ 2           |                |               |OPP UHALL          |
|                |VENTUS          |                |                |               |S 17               |
|                |                |                |                |               |OPP KR MRT         |
|________________|________________|________________|________________|_______________|PGPR_______________|
....................................................................
```

>⚠️ Note: Routes implemented cover Buses A1, A2, D1, D2, E and K. Also routes are recommended if there is direct route or an indirect route
where the user needs to change and board another bus (only single change routes are recommended).

### 3.5 CAP Calculator: `cap`

The CAP calculator helps users to keep track on their CAP and set desired grades for the
current semester. Users can choose between different formats of module 
([`cap mc`](#351-calculate-cap-using-modular-credit-cap-mc) or [`cap code`](#352-calculate-cap-using-module-code-cap-code)) 
and the respective grade to allow more command flexibility.

>🔗 Visit [`module cap`](#316-calculate-overall-cap-from-modules-in-module-list-module-cap) to see how this feature can
be used with the module manager.

#### 3.5.1 Calculate CAP using modular credit: `cap mc`

**Format:** `cap mc MC/GRADE`

**Example of usage:**

* `cap mc 4/A 6/B+ 4/B 4/B- 4/A+`

Demo:

```
cap 4A 6B+ 4B 4B- 4A+
Your CAP for this semester will be 4.09 if you get your desired grades!
....................................................................
```

#### 3.5.2 Calculate CAP using module code: `cap code`

**Format:** `cap code CODE/GRADE`

**Example of usage:**

* `cap code CS2113T/A CS2101/C CG2027/B-`

Demo:

```
cap code CS2113T/A CS2101/C CG2027/B-
Your CAP for this semester will be 3.40 if you get your desired grades!
....................................................................
```

### 3.6 View menu: `help`

Views the different commands available.

This is what you should observe on the terminal when you send a help command:

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

### 3.7 Exit: `bye`

Terminates _Kolinux_.

This is what you should observe on the terminal upon termination:

```
bye
Bye! Thank you for using Kolinux
....................................................................
```

## 4 FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Locate the `/data` directory stored in the same folder as `Kolinux.jar`. Copy the directory to the other 
computer, in the same folder as `Kolinux.jar`. The data will be automatically read upon starting up `Kolinux`.

## 5 Data Storage

_Kolinux_ automatically creates a directory `/data` upon the first start up. The directory consists of the files:

`logger.log` - User activity log of the most recent session.

`moduleList.txt` - Your module manager data.

`planner.txt` - Your planner data.

`timetable.txt` - Your timetable data.

>❗ You are strongly advised not to modify these files as it can corrupt important user data and cause data loss.

## 6 Contact Us

Our team welcomes you to contact us when faced with issues that this user guide fails to cover. We are
also open to any suggestions that you may have to improve _Kolinux_. 😃

You may find our contact information [here](AboutUs.md).
