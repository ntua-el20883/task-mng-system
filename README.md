# Task Management System - Setup Guide

## Prerequisites

Follow these steps to set up your environment successfully.

---

### **Java**  
*Mandatory programming language for this project.*

1. **Download Java JDK 23 for Windows**  
   [Download JDK-23](https://download.oracle.com/java/23/latest/jdk-23_windows-x64_bin.exe)

2. **Install Java**  
   - Set the installation folder to:  
     ```
     C:\Program Files\Java\jdk-23
     ```

3. **Update PATH Variables** (if not auto-configured):  
   - Press the **Windows key**, search for **"Edit the system environment variables"**, and open it.  
   - Click **Environment Variables** and locate the `Path` variable under both **User Variables** and **System Variables**.  
   - Select the `Path` variable, click **Edit**, then **New**, and add:  
     ```
     C:\Program Files\Java\jdk-23\bin
     ```

4. **Restart Your Terminals**  
   - Restart all open terminals (e.g., Command Prompt, VS Code) to apply the changes.

---

### **JavaFX**  
*Frontend-related components.*

> **Important:**  
> JavaFX and Jackson encounter issues when stored in paths with white spaces or non-English characters.  
> To avoid this, these dependencies are placed in the project directory:  
> `C:\Users\juant\Documents\coding\task-mng-system\lib`  
> Ensure the path to `lib` has no spaces and uses only English characters.

1. **Download JavaFX SDK**  
   - Visit the [Gluon JavaFX Website](https://gluonhq.com/products/javafx/) and configure the following:  
     - **JavaFX version:** `23.0.1`  
     - **Operating System:** `Windows`  
     - **Architecture:** `x64`  
     - **Type:** `SDK`  
   - Click **Download** to get the `.zip` file:  
     ```
     openjfx-23.0.1_windows-x64_bin-sdk
     ```

2. **Extract and Place Files**  
   - Unzip the downloaded file. Inside, locate the folder:  
     ```
     javafx-sdk-23.0.1
     ```
   - Move `javafx-sdk-23.0.1` to the project directory:  
     ```
     C:\Users\juant\Documents\coding\task-mng-system\lib
     ```

---

### **Jackson**  
*Used for reading `.json` files. Alternatively, you can use Gson.*

1. **Download Jackson Packages**  
   Download the following `.jar` files by navigating to the provided links. Choose the package that exactly matches the titles below (usually the 9th option in the list).

   - [**jackson-annotations-2.18.1.jar**](https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/2.18.1/)
   - [**jackson-core-2.18.1.jar**](https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/2.18.1/)
   - [**jackson-databind-2.18.1.jar**](https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/2.18.1/)

2. **Place Files in the `lib` Directory**  
   After downloading, move the `.jar` files to:  
     ```
     C:\Users\juant\Documents\coding\task-mng-system\lib
     ```


---

# Project Description

## **Section 1: Design and Implementation of Logic**

### **Task**
- **Attributes**:
- **Title**
- **Description**
- **Category**: `["Default", ...]`
- **Priority**: `["Default", ...]`
- **Deadline**: `["dd/mm/yyyy"]`
- **Status**: `["Open", "In Progress", "Postponed", "Completed", "Delayed"]`

#### **Points to Consider**
- A new task defaults to status `"Open"`.
- Tasks with deadlines in the past automatically update to `"Delayed"`.
- Users can:
- Create a new Task.
- Modify existing tasks (excluding Status).
- Delete tasks (also removes associated Reminders, if any).

---

### **Category**
- **Attributes**:
- **Name**

#### **Points to Consider**
- Tasks belong to exactly one Category.
- Unspecified Categories default to `"Default"`.
- Users can:
- Create new Categories.
- Modify existing Categories.
- Delete Categories (also removes associated Tasks, if any).

---

### **Reminder**
- **Attributes**:
- **Date**

#### **Points to Consider**
- Valid Reminder dates:
- `"1 day before Deadline"`, `"1 week before Deadline"`, `"1 month before Deadline"`, or custom dates before the Deadline.
- Rules:
- Reminders belong to exactly one Task.
- Tasks may have multiple Reminders.
- Completed Tasks cannot have new Reminders.
- Tasks marked as `"Completed"` automatically delete their Reminders.
- Users can:
- Create new Reminders.
- Modify existing ones.
- Delete Reminders.

---

### **Priority**
- **Attributes**:
- **Name**: `["Default", ...]`
- **Value**: `["0", ...]`

#### **Points to Consider**
- Default Priority is `"Default"` with a Value of `0`.
- Larger Values indicate higher Priority.
- Priority Values must be unique and integers > 0.
- Users cannot modify or delete the `"Default"` Priority.
- Users can:
- Create new Priorities.
- Modify existing ones.
- Delete Priorities (affected Tasks default to `"Default"` Priority).

---

### **Search Filters for Tasks**
Users can search Tasks using any combination of these criteria:
- **Title**
- **Category**
- **Priority**

---

## **Section 2: Storage and Retrieval of App Information**
- Data is stored in JSON files in the `medialab` directory.
- Implement classes to:
- Retrieve data from JSON files during initialization.
- Manage all Task and Reminder operations in memory.
- Save updated system state to JSON files before termination.

### **Logic**
- **Initialization**: Load all data into memory from JSON files.
- **Execution**: Operate in-memory during the session.
- **Termination**: Write updated data to JSON files.

---

## **Section 3: Graphic User Interface (GUI)**

- Ensure system data updates on App termination.
- **Header**: Top-left of the central window displays `"MediaLab Assistant"`.
- **Layout**: Central window divided into two halves (2x4 grid).

### **Cells 1-4 (Top Half)**:
1. **Cell 1**: Total number of Tasks (all statuses).  
2. **Cell 2**: Tasks with status `"Completed"`.  
3. **Cell 3**: Tasks with status `"Delayed"`.  
4. **Cell 4**: Tasks with Deadlines within 7 days.  

### **Cells 5-8 (Bottom Half)**:
Each cell has a button that redirects to a page for managing the respective feature.

- **Cell 5**: Task Management (create, modify, delete, and search Tasks).  
- **Cell 6**: Category Management (create, modify, and delete Categories).  
- **Cell 7**: Priority Management (create, modify, and delete Priorities).  
- **Cell 8**: Reminder Management (create, modify, and delete Reminders).  
