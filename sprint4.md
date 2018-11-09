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
| Epics | *4* | *3* |
| Tasks |  *9*   | *21* | 
| Story Points |  *19*  | *38* | 

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
| *11/5/18* | *#303, #299, #198, #195, #297, #200, #298, #300, #264, #295, #307, #313, #315* | *#260, #193, #190, #314* | *None* |
| *11/07/18* | *#303, #299, #198, #195, #297, #200, #298, #300, #264, #295, #307, #313, #315, #260* | *#193, #190, #314* | *None* |


*Add a new row for the scrum session after each lecture. *

## Review

*This sprint we almost got everything done that we set out to do. People started a lot eairlier on their stuff as well as talking more to the TAs. Also posting on piazza was a huge help in answering some questions we had. Some minor stuff was still saved to the last minute, causing us to have to wait for Travis to merge the Sprint4.md files.*

#### Completed Epics in Sprint Backlog 

*Describe the solution based on the completed epics and list the epics below.*

* *User: I want to choose what information is displayed in the itinerary and map: completed everything except for custom attribute input.*
* *User: I'd like even shorter trips: Implemented 2opt and with a button to select optimization options*
* *User: I want to plan trips worldwide: Trip planning now displays a world map with destinations.*

#### Incomplete Epics in Sprint Backlog 

*Describe capabilities not included in the release and list the epics below with an explanation.*

* *User: I want to view my trip in other tools: SVG files can be saved, but kml files cannot. this should be a quick fix before Sprint 5*

#### What Went Well

*Describe what went well during the sprint in general terms followed by a more detailed list.*

* *Communication was again good. Constant messaging though Slack and meetings in class allowed for a good communication between all group members.*
* *Starting on the work earlier. This sprint we all managed to work on tasks earlier and were able to get more done. This also allowed for less cruch time stuff near the deadline.*

#### Problems Encountered and Resolutions

*Describe what problems occurred during the sprint in general terms followed by a more detailed list.*

* *Pull requests not getting mereged in timely manner sometimes. The resolution to this was to just send a message in Slack tagging group members and letting them know that a pull request was open and waiting to be merged.*

## Retrospective

*Our plan at the start could still be better. We could add more tasks and really get into the meat of what needs to get done. Also we really need to add more test code as well as going through and cleaning smelly code. Still most stuff did get done even if some of it should have been done earlier. Also we should work on cleaning up how our webpage looks and the layout in the next sprint.*

#### What we changed this sprint

*This sprint we definately started working way earlier than previous sprints. There is still room to improve of course, as there was kinda of a mad dash to update the sprint.md at on the last day. We also went to TA's more for help instead of just being stuck for hours on end. Other than those two things, the sprint remained largely the same as those already passed.*

#### What we did well

*This sprint our time management was a LOT better than previous sprints. Some members actually finished their amount of the work well before the sprint due date, which relieved a lot of pressure towarads the end of the sprint. Would recommend doing that again! Part of the reason that people were able to get done so early is that we took advantage of office hours and actually asking questions in lecture. Who knew? Also, our communication was good as usual, which is extremely important.*

#### What we need to work on

*For next sprint, we should probably start working even earlier so to avoid rushing later on. We also should meet together more often so we can collaborate on tasks which involve multiple people working on them.*

#### What we will change next sprint 

*Next sprint, we will start working on the project earlier and more frequently throughout the week. We will also schedule meeting times so we can check up on how we are all doing.*
