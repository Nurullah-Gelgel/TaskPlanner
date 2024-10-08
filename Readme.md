# TaskPlanner

**TaskPlanner** is a comprehensive task management application designed for simplicity and efficiency. It enables users to create, update, and track tasks through a user-friendly interface. This project is built using Java and leverages several Java libraries for managing tasks, data persistence, and user interaction.

## Features

- **Task Management**: Create, edit, delete, and organize tasks easily.
- **Task Status**: Mark tasks as "To Do", "In Progress", or "Completed".
- **Prioritization**: Assign priorities (e.g., High, Medium, Low) to each task.
- **Due Dates and Reminders**: Set due dates and receive notifications.
- **Search and Filter**: Quickly find tasks based on status, priority, or keywords.
- **Data Persistence**: Saves task data locally to ensure no loss of information.

## Project Structure

The project is structured as follows:

- **src/main/java**: Contains the source code for the application.
  - **models**: Data models representing the tasks and their attributes.
  - **controllers**: Logic for task manipulation and user interaction.
  - **views**: UI components for displaying tasks and receiving input.
- **src/test/java**: Contains unit and integration tests.
- **resources**: Includes configuration files and resource bundles.
- **target**: Contains compiled classes and JAR files after building the project.

## Prerequisites

To build and run TaskPlanner, ensure you have the following installed:

- **Java Development Kit (JDK)**: Version 11 or higher.
- **Maven**: Version 3.6.3 or higher.

## Getting Started

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Nurullah-Gelgel/TaskPlanner.git
# Project Directory Navigation

**Navigate to the Project Directory:

```bash
  cd TaskPlanner

**Build the Project--
Use Maven to compile and package the project:

```bash
mvn clean install

Run the Application
After building, execute the JAR file:

```bash
java -jar target/taskplanner-1.0.jar

Usage
Creating a Task
Click the "Add Task" button and fill in the necessary fields.

Editing a Task
Double-click on a task to modify its details.

Deleting a Task
Select a task and click the "Delete" button.

Filtering Tasks
Use the filter menu to view tasks based on status or priority.

Contribution
Contributions are welcome! Please follow these steps:

Fork the repository.
Create a new branch for your feature or bug fix.
Submit a pull request with a detailed description of your changes.
License
This project is licensed under the MIT License. Feel free to use, modify, and distribute it as per the license terms.
