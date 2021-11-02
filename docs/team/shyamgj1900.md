# Shyam Ganesh - Project Portfolio Page

## Project Overview

Kolinux is an all-in-one application for NUS freshmen to manage their work and learn more about NUS through its
integrated features such as the Module Manager, Timetable, Event Planner, CAP Calculator, and Bus Route Finder.
It is written in Java, and uses the NUSMods API. The user interacts with it using a CLI.

## Summary of Contributions
* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=shyamgj1900&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=shyamgj1900&tabRepo=AY2122S1-CS2113T-W11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
* **Main feature implemented**: Bus Route Finder
    * Ability to find a direct bus route from any 2 NUS bus stops.
    * Use BFS to check if 2 points in a graph are connected.
    * All graph vertices are read in from a text file when this feature is used.
    * There are total of 6 bus routes which can be used to find if 2 bus stops are connected.
    * Each bus route has its own seperate graph and corresponding vertices.
* **Enhancement added**: Find indirect routes
    * Functionality: With indirect routes, the program recommends users a bus route where they might need to change buses at a single intermediate location and board another bus to reach their final destination. Indirect routes are recommended whenever direct routes are not found.
    * Justification: This feature allows the user to have more flexibility when it comes to finding routes as they are no longer bounded to looking for routes which require a single bus.
* **Enhancement added**: Find direct alternate routes
    * Functionality: With direct alternate routes, the program recommends users a bus route where they might need to change their starting location to reach their final destination. The new starting location is a bus stop which is opposite to their current starting location. The bus route recommended from the new bus stop to the final destination is always a direct route.
    * Justification: This feature allows the user to find routes by changing their start location to a near by bus stop. Direct alternate routes are recommended as a last case scenario when neither direct nor indirect routes were to be found.
* **Enhancement added**: List all bus stop names
    * Functionality: Users will be able to view all the bus stops covered by the NUS bus services by using this feature. All corresponding bus stop names for each bus service are shown in a table format for easy view.
    * Justification: This feature gives the user the ability to check for all bus stop names within any bus route and allows them to get familiarised with NUS internal bus stops.
* **Contributions to UG**: 
    * Added documentation for feature `bus` which includes `bus /start_location /end_location` and `bus stop list`.
    * Ensured the documentation was consistent with the rest of the user guide.
* **Contributions to DG**:
    * Added documentation for the design architecture of bus route finder command.
    * Added UML diagrams: `BusRouteClassDiagram.puml`, `BusRouteSequenceDiagram.puml`
    * Added documentation for how various objects interact with each other.
* **Contributions to team-based tasks**:
    * Reviewed multiple PR's and merged them with the main branch.
    * Contributed to user stories and value propositions.
