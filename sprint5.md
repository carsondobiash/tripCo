# Sprint 5 - *18* - *Rigatoni*

## Goal

### Wrap It Up!
### Sprint Leader: *Brandon Jungen*

## Definition of Done

* Sprint Review and Restrospectives completed (sprint5.md).
* Version in pom.xml should be `<version>5.0.0</version>`.
* Increment deployed for demo and testing as server-5.0.jar on the production server.
* Increment release `v5.0` created on GitHub with appropriate version number and name.
* Epics and Tasks updated in Zenhub.


## Policies

#### Test Driven Development
* Write method headers, javadoc, unit tests, and code in that order for all methods/functions.
* Unit tests are fully automated.
* Code coverage is at least 50%, 70% preferred.
#### Clean Code
* Code Climate maintainability of A or B.
* Code adheres to Google style guides for Java and JavaScript.
#### Configuration Management
* Always check for new changes in master to resolve merge conflicts locally before committing them.
* All changes are built and tested before they are committed.
* All commits with more than 1 line of change include a task/issue number.
* All pull requests include tests for the added or modified code.
* All tests pass.
* Master is never broken.  If broken, it is fixed immediately.
#### Continuous Integration / Delivery
* Travis successfully builds and tests on all pull requests for master branch.
* All Java dependencies in pom.xml.  Do not load external libraries in your repo. 
* All pull requests are deployed on the development server.
* The development server is never broken.  If broken, it is fixed immediately.


## Plan

*This is the final sprint. Our team plans on completely finishing the website and making it look nicer too! We are going to add an interactive map, further optimization (3opt), cleaner UI, and faster trip planning. And lastly we are going to add an authors section to our page.*

*Include any design diagrams prepared during sprint planning (user interface, component diagram, component/state/hierarchy, etc.) with a short paragraph for each.

UI:
![UI](/images/unnamed.jpg)

API Server Requests:
![UI](/Diagrams/Sprint4/ServerClassDiagram.jpg)
* This diagram shows the server interoperation when a request is made to the server. 

Client Component Hierarchy:
![UI](/Diagrams/Sprint5/Sprint5ClientComp.png)
* This diagram contains all the components in our website and all the props being passed between components.


Epics planned for this sprint.

* *User: I want an interactive map: This epic will involve using Leaflet to display the map as well as moving the SVG/KML to have a save only function.*
* *User: I want to know who to thank for this application*
* *User: I want trip planning to be fast*
* *User: I want the shortest trips possible*
* *User: Make the system easier to use: This epic mostly involves rearranging the front end to be more visually appealing and easy to understand for the user.*
* *User: I want to view my trip in other tools: This epic is mostly done from last sprint, we just need to add a button for svg/kml.*

## Metrics

| Statistic | Planned | Completed |
| --- | ---: | ---: |
| Epics | *6* | *total* |
| Tasks |  *12*   | *total* | 
| Story Points |  *26*  | *total* | 

*Enter the `# Planned` at the beginning of the sprint.  Include a discussion of planning decisions based on the planned number of story points versus how many were completed in previous sprints.*

The number planned for this sprint is a total of 26 story points. This shouldn't be that big of an issue as the number of story points that we completed last sprint was 38. We will probably still add more to this as we find more and more stuff that needs to be added.

*Enter the `# Completed` at the end of the sprint.  Include a discussion about any difference in the number planned versus completed tasks and story points.*


## Scrums

| Date | Tasks closed  | Tasks in progress | Impediments |
| :--- | :--- | :--- | :--- |
| *date* | *#task, ...* | *#task, ...* | *none* | 

*Add a new row for the scrum session after each lecture. *

## Review

*An introductory paragraph describing the overall results of the sprint.*

#### Completed Epics in Sprint Backlog 

*Describe the solution based on the completed epics and list the epics below.*

* *## epic title: comments*
* 

#### Incomplete Epics in Sprint Backlog 

*Describe capabilities not included in the release and list the epics below with an explanation.*

* *## epic title: explanation*
*

#### What Went Well

*Describe what went well during the sprint in general terms followed by a more detailed list.*

* *something*
*

#### Problems Encountered and Resolutions

*Describe what problems occurred during the sprint in general terms followed by a more detailed list.*

* *something*
*

## Retrospective

*An introductory paragraph for your retrospective.*

#### What we changed this sprint

*Articulate specifically what you will do differently based on the retrospective from the previous sprint before the sprint starts.*

#### What we did well

*Articulate what went well at the end of the sprint.*

#### What we need to work on

*Articulate things you could improve at the end of the sprint.*

#### What we will change next sprint 

*Articulate the one thing you will change for the next sprint and how you will accomplish that.*
