# Pow Zhi Xiang's Project Portfolio Page

## Project Overview

Kolinux is an all-in-one application for NUS freshmen to manage their work and learn more about NUS through its
integrated features such as the Module Manager, Timetable, Event Planner, CAP Calculator, and Bus Route Finder.
It is written in Java, and uses the NUSMods API. The user interacts with it using a CLI.

## Summary of Contributions

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=powzx)
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
  * What it does: Conflicts will be detected when users try to add events to a timeslot already occupied by another event, lesson, or exam. Subsequently, a confirmation prompt will be displayed to give users the choice to continue adding the conflicted event or cancel the operation.
  * Justification: This enhancement improves the Event Planner significantly since users might forget what they have already planned for the day. This enhancement alerts users of an attempt to add conflicted events so that they can have a chance to rectify the mistake.
  * Highlights: This enhancement changes the way events are added to the Event Planner as additional checks have to be implemented. Different design alternatives have been considered as the adding process needs to be interrupted by a confirmation prompt on the user interface, wait for the user reply, and go back to where it was interrupted to continue the process.
* **Contributions to UG**:
  * Added documentation for the feature `planner` which includes `planner add`, `planner delete`, and `planner list`.
  * Added documentation for other sections such as the Introduction, Quick Start, FAQ, and Data Storage.
  * Ensured consistent use of words and icons throughout the UG.
* **Contributions to DG**:
  * Added documentation for the overall design architecture, commands component, and planner component.
  * Added documentation for implementation of add to planner feature.
  * Added instructions for manual testing for planner.
  * Added UML diagrams: `ArchitectureDiagram.puml`, `CommandsClassDiagram.puml`, `OverviewSequenceDiagram.puml`, `PlannerAddSequenceDiagram1.puml`, `PlannerAddSequenceDiagram2.puml`, `PlannerClassDiagram.puml`, `PlannerObjectDiagramAfter.puml`, `PlannerObjectDiagramBefore.puml`
  * Ensured consistent use of colors throughout the DG through `Style.puml`.
* **Contributions to team-based tasks**:
  * Managed releases `v1.0` and `v2.0` on GitHub.
* **Review/mentoring contributions**:
* **Contributions beyond the project team**: