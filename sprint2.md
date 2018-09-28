# Sprint 2 - *18* - *Rigatoni*

## Goal

### A mobile, responsive map and itinerary!
### Sprint Leader: Daniel Chavez

## Definition of Done

* Sprint Review and Restrospectives completed (sprint2.md).
* Version in pom.xml should be `<version>2.0.0</version>`.
* Increment deployed for demo and testing.
* Increment release `v2.0` created on GitHub with appropriate version number and name.


## Policies

#### Test Driven Development
* Write method headers, javadoc, unit tests, and code in that order.
* Unit tests are fully automated.
#### Configuration Management
* Code adheres to Google style guides for Java and JavaScript.
* Code Climate maintainability of A or B.
* Always check for new changes in master to resolve merge conflicts locally before committing them.
* All changes are built and tested before they are committed.
* All commits with more than 1 line of change include a task/issue number.
* All pull requests include tests for the added or modified code.
* Master is never broken.  If broken, it is fixed immediately.
#### Continuous Integration
* Continuous integration successfully builds and tests all pull requests for master branch.
* All Java dependencies in pom.xml.  Do not load external libraries in your repo. 


## Plan

In this sprint we plan to build onto our existing distance calculator by adding a mobile and responsive map itinerary. We will do this by completing the following epics:


* *TripCo: The solution must be responsive for mobile devices.*
* *All code shall be clean!*
* *User: I want to supply my own units for distances.*
* *User: I want a map and itenerary for my trip.*
* *All servers and clients must interoperate!*


*Each of us will start the project with two tasks, and if we find that more tasks need to be created during the project they will be divided up equally. Even though we all have seperate tasks we will collaborate and help each other out with the tasks. We will also split up any tasks that are bigger than orginally thought in order to keep the time estimates between 1 to 2 hours. Most importantly, communication will be constant and consistent in order to adress impediments as quickly and effieciently as possible.*


## Metrics

| Statistic | # Planned | # Completed |
| --- | ---: | ---: |
| Epics | *5* | *value* |
| Tasks |  *8*   | *value* | 
| Story Points |  *16*  | *value* | 

*Enter the `# Planned` at the beginning of the sprint, `# Completed` at the end of the sprint.*


## Scrums

| Date | Tasks closed  | Tasks in progress | Impediments |
| :--- | :--- | :--- | :--- |
| *9/14/18* | *none* | *none* | *none* | 
| *9/17/18* | *none* | *#87* | *none*|
| *9/19/18* | *none* | *#129 # 88 #124 # 118 #122 #114 # 112 # 116 #105 #111 #87* | *Learning the new stuff* |
| *9/21/18* | *#129 #88 #124 #118 #122 #114 #112 #116 #105 #111 #87 #109* | *None* | *Maven stuff* |
| *9/24/18* | *none* | *none* | *none*|
| *9/26/18* | *none* | *#Draw lines #96 #93* | *Time*|

*Add a new row for each scrum session.*

## Review

*An introductory paragraph describing the overall results of the sprint.*

#### Completed epics in Sprint Backlog 

*Describe the solution based on the completed epics and list the epics below.*

* *I Want to Supply my own units for distance: user should be able to provide ones own units and the server will return the calculated distances*

#### Incomplete epics in Sprint Backlog 

*Describe capabilities not included in the release and list the epics below with an explanation.*

* *## epic title: explanation*
* *User:I want a map and itinerary for my trip: We did not get the picture to populate, instead it is just a string. Then also we didn't get the itinerary to populate from the json.*
* *TripCo: The solution must be responsive for mobile devices.: We did not get to this epic because we couldn't get the map to load, so this means that we couldn't test the mobile responsivenes.*
* *All clients and servers must interoperate!: We cleaned up our tffi but never wrote the code to make it work on other ports.
* *All code shall be clean: This is an ongoing epic that must be addressed every sprint so it is left open. We did however clean up the Distance which was pretty messy.

#### What went well

*Describe what went well during the sprint in general terms followed by a more detailed list.*

* *Our communication throughout the sprint was very good, and everyone knew what was going on, even if it wasn't ideal exactly what was going on.
* *We did talk through most of the isses that needed to get done and fixed in the sprint2 implementation as well as keeping uptodate github.

#### Problems encountered and resolutions

*Describe what problems occurred during the sprint in general terms followed by a more detailed list.*

* *Some group members started late into the sprint, which caused some stress and things to be potentially left unimplemented
* *We could have worked togeather more in the lab allowing for everyone to have the same amount of work time as well as everyone being on the same page, but this didnt occur till the last day.

## Retrospective

*We should have split up the tasks better so everyone was working on parts of an epic instead of everyone just taking one. This would have aloud for everyone to be less overwhelmed as well as other people not waiting for certain things to be done. Overall we need to just meet more and seek help at an earlier time through other teams, instructors or piazza. *

#### What we changed this sprint

*This sprint we tried to meet more together as a group in order to help all of us understand what was going on and to work on bits a pieces with each other if someone was confused. We also changed our work habits, but not for the better, started later than was probably a good time to start.*

#### What we did well

*Our communication throughout the sprint was actually very good, which benefited our efficency. Also, our gitHub proficiency increased considerably as a team this sprint and there was no issues with gitHub at all, unlike last sprint. While our planning was not on par with what it should have been, when we actually got together, we got quite a bit done, almost finishing the whole project, but almost only counts in horseshoes and handgrenades.*

#### What we need to work on

*We absolutely need to work on our time management for tasks and epics. Its too volitile to continue working towards the end of the due date.*

#### What we will change next sprint 

*Articulate at the end of the sprint.  Focus on one of things you need to work on.*
