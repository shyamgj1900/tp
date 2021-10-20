# Developer Guide
* [Acknowledgements](#acknowledgements)
* [Design](#design)
* [Implementation](#implementation)
  * [`timetable add`](#timetable-add-feature)
    * [Notes about method](#notes-about-the-methods)
  * [`planner add`](#planner-add-feature)
  * [`module`](#module-feature)
  * [`cap`](#cap-calculator-feature)
  * [`bus`](#bus-routes-feature)
* [Product Scope](#product-scope)
  * [Target user profile](#target-user-profile)
  * [Value proposition](#value-proposition)
* [User Stories](#user-stories)
* [Non-Functional Requirements](#non-functional-requirements)
* [Glossary](#glossary)
* [Instructions for manual testing](#instructions-for-manual-testing)
## Acknowledgements

* [NUSMods API](https://api.nusmods.com/v2/) 
* [GSON](https://github.com/google/gson)

## Design

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

## Implementation

### timetable add feature

The timetable add mechanism is facilitated by `Timetable`. The lessons added to `Timetable` 
via inputLesson(String[] lessonDetails) is stored in the `lessonStorage` within the program via 
the method `addLesson(Lesson lesson)` and locally in `TimetableStorage` which saves it 
to `timetable.txt` file to constantly save the lessons' data. It implements the following operations:

* Timetable#inputLesson(String[] lessonDetail) containing Timetable#addLesson(Lesson lesson) - Adds the lesson 
to `timetableStorage` based on the type of lesson it is, which is included in the lessonDetail.
* TimetableStorage#writeToFile() - Saves the lesson details to `timetable.txt` locally.

#### Notes about the methods:

* String[] lessonDetails consists of MODULE_CODE, LESSON_TYPE (`TUT` - tutorial, `LEC` - lecture or `LAB` - lab), 
DAY, START_TIME, END_TIME. 
* Lesson class is inherited by Tutorial, Lecture and Lab to add lessons based on the LESSON_TYPE as shown 
in the example below.

Given below are the examples of the usage of `timetable add` of lessons to the timetable.

Example 1: Adding a tutorial to the `lessonStorage` ( lesson of type `TUT` )

![Example 1](assets/images/timetableAdd1.png)

Example 2: Adding a lecture to the `lessonStorage` ( lesson of type `LEC` )

![Example 2](assets/images/timetableAdd2.png)

Example 3: Adding a lab to the `lessonStorage` ( lesson of type `LAB` )

![Example 3](assets/images/timetableAdd3.png)

The following sequence diagram shows the `timetable add` operation:

![Sequence Diagram](assets/images/addToTimetableSequence.png)


### Add to Planner feature

The Add to Planner mechanism is facilitated by `Planner`. Before adding an event, the `Event` is first checked for any
time conflicts with existing events/lessons/exams. Events are only added if there are no time conflicts or the 
user authorised the addition of a conflicted `Event`. Events added to the `Planner` are stored in a list 
`scheduleOfAllDates` which contains all added `Event` by the user. The events added are also written to the internal 
storage `data/timetable.txt` which saves the user data locally.

The feature is implemented by `Planner#addEvent(Event event, boolean allowConflict)` which invokes the following
methods:
* `Planner#hasTimeConflict(Event event)` which checks for any time conflicts between `event` and any existing events
in `scheduleOfAllDates`, lessons, and exams.
* `PlannerStorage#writeFile(String data)` which appends the data of the new `Event` to `data/planner.txt` for local
storage.

The figure below represents the sequence diagram when `planner add` is invoked:

### module feature

### cap calculator by code feature

This cap calculation is managed using `CapCalculatorByCode`. It extends `CapCalculator` which stores
the input modules and grades from user as a string array in `modules` when the object is constructed
once the command `cap code` is given from user, along with the other essential methods used for cap calculation.

When `CapCalculator#executeCapCalculator()` is executed, the following methods are invoked:

- `CapCalculator#checkModulesNotEmpty()` — which ensures that the `modules` attribute of the object is not empty.
- `CapCalculator#getCap()` — which is the methods used to do all the cap calculation.
- `CapCalculator#checkInvalidModules()` — which checks if there are any invalid modules after the cap calculation.

Below is a simplified sequence diagram showing important steps of how `cap code` works:


### bus routes feature
The bus routes feature is facilitated by the `BusRouteCommand` class. The `BusRouteCommand` class extends the `Command` class. 
When the user invokes and uses the bus routes feature the `BusRouteCommand` constructor creates a `Route` class object and passes
the `input` string to the `Route` class. The operation is implemented in the following way.

* The overriden function `executeCommand()` calls the `Route#checkRoutes()` method. 
* The `Route#checkRoutes()` contains the `Route#getLocations()`, `Route#checkDirectRoutes(ArrayList<String> busNumbers)` and `Route#checkIndirectRoutes(ArrayList<String> busOne, ArrayList<String> busTwo, ArrayList<String> midLoc)` - Checks whether there is a direct or an indirect route between the 2 user given bus stops and returns a string depending on the result.
* `Route#getLocations()` - Reads the connected graphs from the text file using the `Route#readNodesFromFile(ArrayList<String> vertices, String filePath)` and sets the route by adding the respective edges of the graph using the `Route#setRoute(ArrayList<String> vertices, Graph graph)` method.
* `Route#checkDirectRoutes(ArrayList<String> busNumbers)` - Check whether there is a direct bus route between the 2 user given bus stops by calling the `Graph#isConnected(int u, int v)` method which uses BFS to check if any 2 points in the directed unweighted graph are connected.
* `Route#checkIndirectRoutes(ArrayList<String> busOne, ArrayList<String> busTwo, ArrayList<String> midLoc)` - Checks whether there is an alternate route between the 2 user given bus stops which requires a single change of bus at an intermediate bus stop.

## Product scope
### Target user profile:

* needs help with adapting to university life by understanding the grading system, university bus routes etc.
* has a need to manage their schedule along with the modules they are taking in the semester
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

### Value proposition:
 
Users can manage all important university related tasks (Module Manager, Event Planner, Timetable, Bus Route Finder, 
and CAP calculator) in a single integrated platform.

## User Stories

| Version 	| As a ...                     	| I want to ...                                                                                                   	| So that ...                                                	|
|---------	|------------------------------	|-----------------------------------------------------------------------------------------------------------------	|------------------------------------------------------------	|
| v1.0    	| As a NUS freshman            	| be able to view all my modules in one local platform                                                            	| I can save time                                            	|
| v1.0    	| As a NUS freshman            	| view the schedule that I created for myself everyday                                                            	| I will not miss out on any important tasks for the day     	|
| v1.0    	| As a NUS freshman            	| add events to my schedule conveniently and ahead of time                                                        	| I will not forget about them when the day comes            	|
| v1.0    	| As a NUS freshman            	| find which buses I could take to go from one location to another in campus                                      	| I do not get lost within campus                            	|
| v1.0    	| As a NUS student             	| to view my timetable for the modules I'm taking in the current semester                                         	| I know what are my commitments of the week are like        	|
| v1.0    	| As a NUS student             	| add modules that I am interested in taking to my module list                                                    	| I can start preparing for modreg ahead of time             	|
| v1.0    	| As a NUS student             	| remove modules that I am no longer interested in taking from my list                                            	| I can focus on the modules which I'm interested in         	|
| v2.0    	| As a user                    	| see my plan of the day in a chronological order                                                                 	| I will not miss out any important events                   	|
| v2.0    	| As an overwhelmed freshman   	| be able to delete events from my Planner because of my unpredictable schedule                                   	| I can remove cancelled events out of my list.                 |
| v2.0    	| As a user                    	| see a list of my modules                                                                                        	| I can plan my academic journey                             	|
| v2.0    	| As a user                    	| be able to add events even when it conflicts with another event                                                 	| I can train my multitasking skills and be more productive. 	|
| v2.0    	| As a user                    	| view my classes when using the Planner for more effective planning of events                                    	| I can plan my schedule very precisely                      	|
| v2.0    	| As a user                    	| to modify my timetable                                                                                          	| I can swap classes after several rounds of modreg          	|
| v2.0    	| As a user                    	| to add the lessons to the timetable based on the modules, in the module list, I'm about to take in the semester 	| I only need to choose the day and start time of lesson     	|
| v2.0    	| As a user                    	| my module related data must be automatically saved                                                              	| I can be access it at a later time                         	|
| v2.0    	| As a user                    	| continue my module planning where I left off                                                                    	| I can refine my plan over time                             	|
| v2.0    	| As a user                    	| store grades for each of my module                                                                              	| I can check my CAP                                         	|

## Non-Functional Requirements

{Give non-functional requirements}
<ol>
<li> Should work on any mainstream OS as long as it has Java 11 or above installed. </li>
<li> A user with above average typing speed for regular English text (i.e. not code, not system admin commands) 
should be able to accomplish most of the tasks faster using commands than using the mouse.</li>
</ol>

## Glossary

* *Mainstream OS*: Windows, Linux, Unix, OS-X

## Instructions for manual testing

### Adding an event to Planner

1. Adding an event with no time conflicts with any existing events, lessons, or exams to the Planner.
   

   * Test case: `planner add watch movie/2021-10-20/1800/2100`
   
      Expected: Event is added to the list. Success message printed as output.


   * Test case: `planner add project meeting/20211020/0700/0800`
      
      Expected: Event is not added to the list. Error message regarding date and time format printed as output.


   * Test case: `planner add go back in time/2021-10-20/1400/1300`
   
      Expected: Event is not added to the list. Error message regarding wrong time order printed as output.


   * Other incorrect commands to try: `planner add something wrong//`, `planner add something amazing/ 3pm to 4pm`
      
      Expected: Similar to previous cases where an error message regarding the format of command is printed as output.

2. Adding an event with time conflicts with at least one existing event, lesson, or exam to the Planner.


  * Prerequisites: Add the event by `planner add conflict test/2022-05-05/0800/1100`. Add the module
  `module store cs2113t` and add a lesson `timetable add cs2113t/lec/thursday/1600/1800`.
  _Do note 2021-05-05 is a Thursday._


  * Test case: `planner list 2022-05-05`

     Expected: The event `conflict test`, lesson `CS2113T LEC`, exam `CS2113T Exam` are displayed as output.
   

  * Test case: `planner add love conflicts/2022-05-05/xxxx/yyyy` where `xxxx` and `yyyy` are start times and end
    times respectively which overlaps with any of the events listed.
   
       Expected: Event is not added to the list. A message will be shown seeking permission to proceed with the
       operation. Entering `y` will lead to a success message, while anything else will lead to the operation cancelled.

