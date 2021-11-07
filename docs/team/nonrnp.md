# Pichanon Rattanadilok Na Phuket's Project Portfolio Page

## Project Overview

Kolinux is an all-in-one application for NUS freshmen to manage their work and learn more about NUS through its
integrated features such as the Module Manager, Timetable, Event Planner, CAP Calculator, and Bus Route Finder.
These features are powered by the NUSMods API. Written in Java, Kolinux and provides a CLI for users to interact with the app.

## Summary of Contributions

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=nonrnp&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)
* **Main feature implemented**: CAP Calculator
  * Ability to calculate overall CAP from module credit and grade letter.
  * Ability to calculate overall CAP from module code and grade letter.
  * Ability to calculate CAP in presence of grades with no assigned point.
  * Ability to store and show all the modules with invalid description format to the user.
* **Enhancement added**: Integration with Module Manager feature
  * What it does: Calculate the overall CAP directly from the stored modules and their respective grade within module manager without having to key in all the modules and grades in a separate command.
  * Justification: This enhancement allows the user to conveniently calculate their overall CAP using a short, simple command without having to refer to another feature.
  * Highlights: This feature has a different approach in retrieving the modules and grades from the user, thus making it necessary to introduce an additional class dedicated for extracting and calculating the overall CAP from module description with different data type than the basic calculator.

<div style="page-break-after: always;"></div>
  
* **Enhancement added**: Grade suggestion based on stored modules in Module Manager feature
  * What it does: Calculate the overall CAP of the user's stored module and compare the value with the user's desired CAP. Then, based on the user's other modules with no assigned grade, calculate and suggest the minimum overall grade needed to achieve the desired CAP to the user.
  * Justification: This allows the user to estimate their effort needed to achieve their desired result, which would allow the user to manage their study plan and get prepared for their upcoming modules.
  * Highlights: Despite similar functionality with the cap calculator from module list, this functionality needs to be implemented using a different class in order to compare the grade point value back into a letter grade form to show to the user.
* **Enhancement added**: Check whether if a module accept S/U grade
  * What it does: Initially, the users was able to put in S/U grade for any modules, which should not be the case as most of the modules offered in NUS do not accept these grade. This enhancement checks for the validity of S/U grade with the input module from the user.
  * Justification: If the S/U grade is allowed for every module, it can cause confusion to the users who do not understand the S/U system of NUS. Also, it can make the cap calculator become inaccurate.
  * Highlights: This enhancement requires some adjustments to the way each module is stored as `ModuleDetails`, as the S/U-compatibility has to be stored as an additional attribute of the object.
* **Contributions to UG**:
  * Added documentation for the feature `cap` which includes `cap mc` and `cap code`
  * Added documentation for the subcommand of `module` feature, which are `module cap` and `module cap DESIRED_CAP`
  * Ensured the documentation format is consistent with the rest of the user guide.
* **Contributions to DG**:
  * Added documentation for the design architecture of cap calculator.
  * Added documentation for implementation of `cap code` feature.
  * Added UML diagrams: `CapCalculatorByCodeSequenceDiagram1.puml`, `CapCalculatorByCodeSequenceDiagram2.puml`, `CapCalculatorByCodeSequenceDiagram3.puml`, `CapCalculatorClassDiagram.puml`
* **Contributions to team-based tasks**:
  * PR reviews and merging to the main branch.