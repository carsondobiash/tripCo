# Sprint 4 - *18* - *Rigiatoni*

## Goal

### Interactive Maps and Shorter Trips!
### Sprint Leader: *Carson Dobiash*

## Definition of Done

* Sprint Review and Restrospectives completed (sprint4.md).
* Version in pom.xml should be `<version>4.0.0</version>`.
* Increment deployed for demo and testing as server-4.0.jar on the production server.
* Increment release `v4.0` created on GitHub with appropriate version number and name.
* Epics and Tasks updated in Zenhub.


## Policies

#### Test Driven Development
* Write method headers, javadoc, unit tests, and code in that order for all methods/functions.
* Unit tests are fully automated.
* Code coverage is at least 50%, 70% preferred.
#### Clean Code
* Code adheres to Google style guides for Java and JavaScript.
* Code Climate maintainability of A or B.
#### Configuration Management
* Always check for new changes in master to resolve merge conflicts locally before committing them.
* All changes are built and tested before they are committed.
* All commits with more than 1 line of change include a task/issue number.
* All pull requests include tests for the added or modified code.
* Master is never broken.  If broken, it is fixed immediately.
#### Continuous Integration / Delivery
* Travis successfully builds and tests all pull requests for master branch.
* All Java dependencies in pom.xml.  Do not load external libraries in your repo. 
* All pull requests are deployed on the development server.
* The development server is never broken.  If broken, it is fixed immediately.


## Plan
*This Sprint we should be able to compleete the four epics entailed in Tripco. We will also go back and fix some of our code layed out in Code Climate, as well as writting more test code for past code.*

User layout:
![UI](/images/unnamed.jpg)

*We want the user to have portability within their trips.*

API Server Requests:
![UI](/Diagrams/Sprint4/ServerClassDiagram.jpg)

Client Component Hierarchy
![UI](/Diagrams/Sprint4/Sprint4 Client Comp.png)


Epics planned for this sprint.

* *User: I want to view my trip in other tools.*
* *User: I want to choose what information is displayed in the itinerary and map.*
* *User: I'd like even shorter trips.*
* *User: I want to plan trips worldwide.*


## Metrics

| Statistic | Planned | Completed |
| --- | ---: | ---: |
| Epics | *4* | *total* |
| Tasks |  *9*   | *total* | 
| Story Points |  *19*  | *total* | 

*Enter the `# Planned` at the beginning of the sprint.  Include a discussion of planning decisions based on the planned number of story points versus how many were completed in previous sprints.*

*Enter the `# Completed` at the end of the sprint.  Include a discussion about any difference in the number planned versus completed tasks and story points.*


## Scrums

| Date | Tasks closed  | Tasks in progress | Impediments |
| :--- | :--- | :--- | :--- |
| *date* | *#task, ...* | *#task, ...* | *none* | 
| *10/26/18* | *None* | *None* | *None* |
| *10/29/18* | *None* | *#195, #260, #193, #289* | *None* |
| *10/31/18* | *None* | *#195, #260, #193, #289, #264* | *None* |
| *11/02/18* | *#303, #299, #198, #195, #297, #200, #298, #300, #264, #295* | *#260, #193, #190, #314* | *None* |
| *11/02/18* | *#303, #299, #198, #195, #297, #200, #298, #300, #264, #295, #307, #313, #315* | *#260, #193, #190, #314* | *None* |

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
