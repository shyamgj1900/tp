# Pow Zhi Xiang's Project Portfolio Page

## Project Overview

Kolinux is an all-in-one application for NUS freshmen to manage their work and learn more about NUS through its
integrated features such as the Module Manager, Timetable, Event Planner, CAP Calculator, and Bus Route Finder.
It is written in Java, and uses the NUSMods API. The user interacts with it using a CLI.

## Summary of Contributions

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=powzx&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)
* **Main feature implemented**: Event Planner
  * Ability to add events to the planner on a specified date from a specified start time to end time.
  * Ability to list events from the planner on a specified date in a sorted order by time.
  * Ability to delete events on a specified date from the planner.
  * Ability to store events list as a text file locally.
* **Enhancement added**: Integration with the Timetable and Module Manager features
  * What it does: Fetches lessons and exams data from the Timetable and Module Manager features respectively and allows users to view their lessons and exams on a specified date via the Event Planner.
  * Justification: This enhancement allows users to view their lessons, exams and their own personal events in one integrated place, without the need to refer to multiple features or using other applications.
  * Highlights: This enhancement introduces the need for additional classes to perform the work of bridging the gap among Event Planner, Timetable, and Module Manager, so that there will be minimal disruption to the initial structure of Event Planner. This enhancement is challenging to implement as it requires the conversion from lessons to events, and creation of events based on the exam information.
* **Enhancement added**: Ability to detect conflicts when adding events to the Event Planner.
  * What it does: Conflicts will be detected when users try to add events to a timeslot already occupied by another event, lesson, or exam. A confirmation prompt will then be displayed to give users the choice to continue adding the conflicted event or cancel the operation.
  * Justification: This enhancement improves the Event Planner significantly since users might forget what they have already planned for the day. This enhancement alerts users of an attempt to add conflicted events so that they can have a chance to rectify the mistake.
  * Highlights: Different design alternatives have been considered as the adding process needs to be interrupted by a confirmation prompt on the user interface and return to where it was interrupted.
* **Enhancement added**: Redesigned the structure of `PlannerCommand` and `PlannerPromptHandler`
  * What it does: Initially, the handling of prompts (getting user replies) is a method in `Command`. However, this causes multiple levels of self invocation in `PlannerCommand` when a prompt is needed to resolve conflicts in event adding. Hence, the method to get user replies need to be separated out into `PlannerPromptHandler`, and it will be invoked only when a prompt is needed to resolve any process.
  * Justification: This enhancement improves the overall design of the planner and prompt features by the Single Responsibility Principle, which reduces the need for multiple self invocations. It also helped to make unit testing easier since `Planner` and `PlannerPromptHandler` may be tested independently of each other.
  * Highlights: This enhancement requires major revamp of the code design in `PlannerCommand` and `PlannerPromptHandler`.
* **Contributions to UG**:
  * Added documentation for the feature `planner` which includes `planner add`, `planner delete`, and `planner list`.
  * Added documentation for Introduction, Quick Start, FAQ, and Data Storage.
  * Ensured consistent formatting throughout the UG.
  * Designed the Kolinux logo.
* **Contributions to DG**:
  * Added documentation for the overall design architecture, commands component, planner component, and implementation of add to planner feature.
  * Added instructions for manual testing for planner.
  * Added UML diagrams: `ArchitectureDiagram.puml`, `CommandsClassDiagram.puml`, `OverviewSequenceDiagram.puml`, `PlannerAddSequenceDiagram1.puml`, `PlannerAddSequenceDiagram2.puml`, `PlannerClassDiagram.puml`, `PlannerObjectDiagramAfter.puml`, `PlannerObjectDiagramBefore.puml`
  * Ensured consistent use of colors throughout the DG using `Style.puml`.
* **Contributions to team-based tasks**:
  * Produced the skeletal code and high-level design to start Kolinux development.
  * Produced, maintained, and documented the code for general classes such as `Kolinux`, `KolinuxLogger`, `DirectoryCreator`, `PromptHandler`, `Parser`, and `Ui`.
* **Review/mentoring contributions**: 
  * Reviewed PRs and merged them to master branch.