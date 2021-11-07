# Ramalingam Saravanamani's Profile Portfolio Page

## Project Overview

Kolinux is an all-in-one application for NUS freshmen to manage their work and learn more about NUS through its
integrated features such as the Module Manager, Timetable, Event Planner, CAP Calculator, and Bus Route Finder.
Kolinux is written in Java and makes use of NusMods API. User can make use of Kolinux via CLI

## Summary of Contributions

* **Code contributed**: [ReposSense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=saravanamani1999&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)
* **Main feature implemented**: Timetable
  * Ability to add lessons to timetable of lesson types lecture, tutorial and lab for specified weekday, start time and end time.
  * Ability to view the lessons on an aesthetic timetable on CLI
  * Ability to delete specific lesson slots from timetable 
  * Ability update to update a specific lesson slot to different slot where the duration of the hour will be the same as the old lesson duration.

* **Enhancement added**: Integration with Module Manager Feature
  * What it does: It ensures that the user adds the modules they have to take during the semester to the module list in the module manager first before adding to the timetable. Also, when the user deletes a module from the module list, the lessons related to that specified module gets deleted from the timetable as well.
  * Justification: This enhancement enhances the user interaction between the Timetable and Module Manager where the program ensures that the users have added the modules they would like to take for the semester ensuring an integrated flow of actions within the program.
  * Highlights: This enhancement affects the user journey when interacting with the program with new introduced methods to check if the modules have been added to the Module Manager and automatically delete from the specific lesson data for the module deleted from the Module Manager.

* **Enhancement added**: Incorporated prompt feature for exceeding workload
  * What it does: It checks with the user if they want to continue adding a lesson to their timetable when their input duration for a lesson exceeds the total prescribed workload by incorporating `PromptHandler` to timetable
  * Justification: Due to some inaccuracies in NUSMods api for prescribed workload for each lessons, users can add a lesson regardless exceeding the workload

* **Contribution to other features**: Integration of NUSMods API to Module Manager Feature
  * What it does: The information for NUS modules were extracted from the NUSMods API for Module Manager which depends heavily on this data.
  * Justification: This ensures the information provided to the users are accurate by extracting relevant module data like module description, module credits, exam date and time, workload etc.
  * Highlights: Made use of GSON to extract the relevant data from the json file retrieved from NUSMods API. (Made use of local JSON file as program is expected to work without internet) The implementation was challenging as some of the data in the JSON file were of varying types thus had to add methods and exception handlers to handle these anomalies.
  * Credits: 
    * [NUSMods API](https://api.nusmods.com/v2/) 
    * [GSON](https://github.com/google/gson)
  
* **Contributions to UG**:
* Added documentation for the feature `timetable` which includes `timetable add`, `timetable delete`, `timetable update` and `timetable view`.
* Added documentation for other sections such as the Introduction, Quick Start and Data Storage.

* **Contributions to DG**:
  * Added documentation for other sections such as the Introduction, Quick Start, Product Scope, User stories and Glossary.
  * Added documentation for implementation of add to timetable feature.
  * Added UML diagrams: `TimetableAddSequence1.puml`, `TimetableAddSequence2.puml`, `TimetableAddSequence3.puml` and `TimetableClassDiagram.puml`

* **Contributions to team-based tasks**:
    * Set up the Github team org and rep
    * Incorporated GSON and NUSMods API
    * PR reviews and merging to master team repo
