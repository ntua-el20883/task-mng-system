# Section 1: Design and Implementation of Logic
Description of the Entities and Attributes. Inside the [] is the format or mandatory values the Attributes will take.

## Task
- Title
- Description
- Category ["Default", ...]
- Priority ["Default", ...]
- Deadline ["dd/mm/yyyy"]
- Status ["Open", "In Progress", "Postponed", "Completed", "Delayed"]

### Points to consider
- A new task has default status "Open".
- If the current date is past the deadline, the status should automatically be updated to "Delayed".
- The user can create a new Task, modify an existing one (all attributes except Status) and delete one (in which case its corresponding Reminders (if they exist) should also be deleted).

## Category
- Name

### Points to consider
- A Task belongs to exactly one Category.
- If the user does not specify the Category of a new Task, the Category is "Default".
- The user can create new Categories, modify the name of existing ones and delete (in which case its corresponding Tasks (if they exist) should also be deleted).

## Reminder
- Date

### Points to consider
- A Reminder can have one of the following values: "1 day before the Deadline", "1 week before the Deadline", "1 month before the Deadline", or any date before the Deadline, specified by the user.
- One Reminder belongs to exactly one Task.
- One Task can have multiple Reminders.
- If the Task has Status: Completed, it can't have new Reminders. If a Task pass to Completed, its corresponding Reminders (if they exist) should also be deleted.
- The user can create new Reminders, modify the date of existing ones and delete.

## Priority
- Name ["Default", ...]
- Value ["0", ...]

### Points to consider
- If the user doesn't assign a Priority to a new Task, its Priority will be set as Default.
- A Priority with a bigger Value is considered more important than a Priority with a smaller Value.
- A Value of a Priority should be an integer number greater than 0.
- A user can't create a Priority with the same Value as an existing one. 
- The user can't modify or delete the Priority with Name: Default and Value: 0.
- The user can create new Priorities (assigning a Name and Value), modify existing ones and delete (in which case its corresponding Tasks (if they exist) should have their Priority set to Default). 

## Search Filters for Tasks
- A user should be able to search based on any combination of the following criteria/filters: title, category, and priority.




# Section 2: Storage and Retrieval of App Information
- For Storage and Retrieval of App Information JSON files will be used.
- The data files should be stored in a folder named "medialab".
- Decide  and  define  your  own  organization  (data  schema)  for  the JSON  data  and  the  set  of  files  that  will  be  used  to  store  the  App  data.  
- Implement  through  the  appropriate  classes  the  methods  that  will  allow you to retrieve the information that the files have and initialize the appropriate objects in your App and refresh the files so that the overall state can be maintained between intermittent runs of the App.

## Logic to retrieve and update the App data
- Initialization: You should be retrieving all the information in the JSON files and initializing the corresponding objects in your App at the same time.
- Execution:  The  App  will  use  the  state  information  retrieved in  program  memory  during  initialization.  All  operations  related  to  Tasks  and Reminders managed by the app will be executed based on the information in program memory.
- Termination:  JSON  files  with  system  state  information  will  be refreshed  exclusively  before  App  termination.  The  implementation shall store in the corresponding JSON files the total state of the App at the time of termination.




# Section 3: Graphic User Interface (GUI)
- The GUI implementation should ensure that when the App is terminated, the system information in the relevant files is updated
- On the top left of the central window, there should be the title/header "MediaLab Assistant".
- The frontend will have one central window, divided in half. 
- Each half (top and buttom) will have 4 "cells" or areas (kind like a 2x4 matrix). The cells of the top half will be named for convinience 1, 2, 3 and 4 (from left to right), while the cells of the buttom half will be named for convinience 5, 6, 7 and 8 (from left to right).

## Cells 1-4
- Cell 1: total  number  of  Tasks  regardless  of  Status.
- Cell 2: number  of  Tasks  with  Status "Completed".
- Cell 3: number  of  Tasks  with  Status  "Delayed".
- Cell 4: number  of Tasks with Deadline within 7 days.

## Cells 5-8
These cells should display a button that redirects the user to another page, in which the user will be able to utilize the according function.
- Cell 5: Task Management. The App must present the available Tasks by Category. The user can create, modify and delete Tasks (according to previous instrucions in Sections 1 and 2). The user can search with filters (according to previous instrucions in Section 1).
- Cell 6: Category Management. The App must present the available Categories. The user can create, modify and delete Categories (according to previous instrucions in Sections 1 and 2).
- Cell 7: Priority Management. The App must present the available Priorities. The user can create, modify and delete Priorities (according to previous instrucions in Sections 1 and 2).
- Cell 8: Reminder Management. The App must present the available Reminders. The user can create, modify and delete Reminders (according to previous instrucions in Sections 1 and 2).

