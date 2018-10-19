# Sprint 3 - *t18* - *Team Rigatoni*

## Goal

### Build shorter trips!
### Sprint Leader: *Jacob Augustine*

## Definition of Done

* Sprint Review and Restrospectives completed (sprint3.md).
* Version in pom.xml should be `<version>3.0.0</version>`.
* Increment deployed for demo and testing as server-3.0.jar.
* Increment release `v3.0` created on GitHub with appropriate version number and name.
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
#### Continuous Integration
* Travis successfully builds and tests all pull requests for master branch.
* All Java dependencies in pom.xml.  Do not load external libraries in your repo. 


## Plan

At the beginning of this sprint, our team will focus on finishing up some lingering epics from previous sprints. Among finishing the lingering epics, it is also a good idea that we meet up earlier and work on tasks in order to have more time to finish everything.


*Include any design diagrams prepared during sprint planning (user interface, component diagram, component/state/hierarchy, etc.) with a short paragraph or each.

Epics planned for this sprint.

1. User: I want to determine the distance between a pair of destinations.
2. All clients and servers must interoperate!
3. User: I want to make and save changes to the trip.
4. User: I want to design a trip from scratch so I can stop using the other tool. 
5. User: I want to choose what information is displayed in the itinerary and map.
6. User: I want my trips to be shorter.
7. User: I'd like even shorter trips.
 


## Metrics

| Statistic | # Planned | # Completed |
| --- | ---: | ---: |
| Epics | *7* | *5* |
| Tasks |  *17*   | *25* | 
| Story Points |  *33*  | *40* | 

*Enter the `# Planned` at the beginning of the sprint.  Include a discussion of planning decisions based on the planned number of story points versus how many were completed in previous sprints.*

*Enter the `# Completed` at the end of the sprint.  Include a discussion about any difference in the number planned versus completed tasks and story points.*


## Scrums

| Date | Tasks closed  | Tasks in progress | Impediments |
| :--- | :--- | :--- | :--- |
| *10/3/18* | *none* | *#86* | *none* | 
| *10/5/18* | *none* | *#83 #179* | *none* |
| *10/8/18* | *none* | *#83 #179* | *none* |
| *10/10/18* | *none* | *#83 #179* *#203* | *none* |
| *10/12/18* | *#214 #176 #192 #179 #165* | *#209* | *none* |
| *10/15/18* | *#209 #181 #183 #180* | *none* | *none* |
| *10/17/18* | *#182 #217 #184* | *#177 #226 #197 #199* | *none* |

*Add a new row for the scrum session after each lecture. *

## Review

*This Sprint went a lot better than last. In the amount done overall is a huge improvement. We started earlier and becuase of that got more work done even though something were still done last minute. Because of that some tasks were caught in Travis and never got added to the final deploy.*


#### Completed Epics in Sprint Backlog 

*Describe the solution based on the completed epics and list the epics below.*

* *## User: I want to make and save changes to the trip: implemented a new UI for the user to add, delete, reverse, make first, and save the current trip they are viewing*
* *## User: I want to determine the distance between a pair of destinations: Created a UI for the user to input two sets of latitude and longitude and calculate the distance between the two.*
* *## User: I want to design a trip from scratch so I can stop using the other tool.*
* *## User: I want my trips to be shorter.*
* *## All clients and servers must interoperate!*

#### Incomplete Epics in Sprint Backlog 

*Describe capabilities not included in the release and list the epics below with an explanation.*

* *## User: I want to choose what information is displayed in the itinerary and map.*
* *## User: I'd like even shorter trips.*

#### What Went Well

*Describe what went well during the sprint in general terms followed by a more detailed list.*

* *This sprint proficincies in javascript shot through the rough. It is a much more comfortable environment to be developing in now.*
* *We had good communication through slack, any issues we had we could help eachother work through them.*
* *Github pull requests were merged in a timely manner so we could continue our work.*
* *Starting on the code earlier allowed for more trouble shooting through out the sprint.*


#### Problems Encountered and Resolutions

*Describe what problems occurred during the sprint in general terms followed by a more detailed list.*

* *Once again, time seems to be a relative weakness of the teams, as a majority of the work was left till the last minute.*
* *Also, one group member was doing the same work as another, it might be useful to constantly check the pipelines, especially the in-progress one.*
* *Doing things last minute caused some things to not be added to the final deploy. This is becuase of the Travis wait queue but the blame is still on those that waited too long.*

## Retrospective

*An introductory paragraph for your retrospective.*
*We need to create a better plan at the start of the sprint. This should allow us to really know what is needed for each epic. Also the task that are being taken and worked on need to be assigned in Github to prevent someone from working on the same thing as another. Next time we need to do these things as well as meeting up in the lab at a designated time.*

#### What we changed this sprint

*Although groupmembers did start a little earlier, most of the work was left till the last minute, so not much changed. However more stuff still got done, but that may be because of luck. Some of our communication got better however and that caused a better environment.*

#### What we did well

*Communication was agian good this sprint. If there was something someone was struggling on then they would let everyone know that they were struggling there. Also the proficiency of our reactstrap greatly increased this sprint.*

#### What we need to work on

*We need to start working on the code a little earlier as well as communicate with one another regarding the purpose of each of our indivdual tasks to prevent mixups. We also need to clean up our UI so it is easier to navigate and manage.*

#### What we will change next sprint 

*Next Sprint we will meet up at least once to work on code as well as assign tasks to ourselves on Github to avoid mixups. This could have allowed more stuff to get done if two people werent working on the same thing.*
