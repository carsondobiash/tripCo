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
| Epics | *6* | *4* |
| Tasks |  *12*   | *39* | 
| Story Points |  *26*  | *44* | 

*Enter the `# Planned` at the beginning of the sprint.  Include a discussion of planning decisions based on the planned number of story points versus how many were completed in previous sprints.*

The number planned for this sprint is a total of 26 story points. This shouldn't be that big of an issue as the number of story points that we completed last sprint was 38. We will probably still add more to this as we find more and more stuff that needs to be added.

*Enter the `# Completed` at the end of the sprint.  Include a discussion about any difference in the number planned versus completed tasks and story points.*

* The number planned was far far less than the number we actually did. This means that we under planed this sprint and did not evaluate all that we had to do for the sprint. *


## Scrums

| Date | Tasks closed  | Tasks in progress | Impediments |
| :--- | :--- | :--- | :--- |
| *date* | *#task, ...* | *#task, ...* | *none* | 
| *11/14/18* | *none* | *#387* | *none* |
| *11/16/18* | *#387* | *none* | *none* |
| *11/26/18* | *#389* | *none* | *none* |
| *11/28/18* | *none* | *#390, #391* | *none* |

*Add a new row for the scrum session after each lecture. *

## Review

*This sprint was more productive than some of the past ones. We communicated consistently about tasks and bugs that needed to get fixed on or worked on. We also assisted eachother on our tasks when we were having trouble. The only really unproductive time was after thanksgiving break because we had lost some focus over break. Overall a good sprint!*

#### Completed Epics in Sprint Backlog 

*Describe the solution based on the completed epics and list the epics below.*

* *User: I want to know who to thank for my application.*
* *User: I want trip planning to be fast*
* *User: Make the system easier to use.*
* *User: I want to view my trip in other tools*

#### Incomplete Epics in Sprint Backlog 

*Describe capabilities not included in the release and list the epics below with an explanation.*

* *User: I want my options remembered so I don't have to fix them all the time: this epic was to enable cookies so that you didn't have to reclick each time, didn't get around to it*
* *All code shall be clean!: Still have lots of code sections that are not very clean*
* *TripCo: All code must be tested.: Test coverage is still low*
* *User: I want the shortest trips possible: 3-opt was not implemented*

#### What Went Well

*Overall this sprint went well. We had pretty consistent communication about the tasks that needed to get worked on as well as assisting each other with their tasks.*

* *One teammate was sort of unfamiliar with the inner workings of the search file and the teammate who had worked on it most helped shed some light.*
* *We worked well as a unit and communicated frequnently throughout the sprint*

#### Problems Encountered and Resolutions

*Describe what problems occurred during the sprint in general terms followed by a more detailed list.*

* *something*
*

## Retrospective

*An introductory paragraph for your retrospective.*

#### What we changed this sprint

*This sprint we didn't change a ton of things because our last sprint went pretty well with how we did it before. One thing that we did change was we had more people working on unfamiliar topics than in the past. Other than that we split up the work, planned, and then completed the tasks for the sprint.*

#### What we did well

*This sprint, we communicated well, as per the norm. Also, our UI looks amazing and were all super stoked on the improvements made this sprint. In additon to that, the response times for NN and other optimizations are a vast improvement from what we had last sprint.*

#### What we need to work on

*Once again, the common theme of our workflow is starting things way to late, resulting in long travis queues and possible misssing out on improvements made during this sprint. This is a pretty big issue that should've been fixed in the second sprint.*

#### What we will change next sprint 

*Articulate the one thing you will change for the next sprint and how you will accomplish that.*
