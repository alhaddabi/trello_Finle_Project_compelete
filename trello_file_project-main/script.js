// Function to update the main title with the entered title
function updateTitle() {
    const newTitle = document.getElementById("site-title").value;
    if (newTitle) {
        document.getElementById("main-title").textContent = newTitle;
    }
}

// Function to add a task
function addTask() {
    // Get input values
    const title = document.getElementById("task-title").value;
    const description = document.getElementById("task-description").value;
    const priority = document.getElementById("task-priority").value;

    // Create a new task card with an ID
    const taskId = new Date().getTime(); // Unique ID using timestamp
    const card = createTaskCard(taskId, title, description);

    // Determine which column to add the task card to
    switch (priority) {
        case "high":
            document.querySelector(".todo-column").appendChild(card);
            break;
        case "medium":
            document.querySelector(".in-progress-column").appendChild(card);
            break;
        case "low":
            document.querySelector(".done-column").appendChild(card);
            break;
        default:
            break;
    }

    // Reset input fields after adding a task
    document.getElementById("task-title").value = "";
    document.getElementById("task-description").value = "";
    document.getElementById("task-priority").value = "high"; // Reset priority to default
}

// Function to create a new task card
function createTaskCard(id, title, description) {
    const card = document.createElement("div");
    card.classList.add("task-card");
    card.id = "task-" + id;

    const taskCardId = document.createElement("div");
    taskCardId.classList.add("task-card-id");
    taskCardId.textContent = "Task ID: " + id;
    card.appendChild(taskCardId);

    const taskTitle = document.createElement("h3");
    taskTitle.textContent = title;

    const taskDescription = document.createElement("p");
    taskDescription.textContent = description;

    card.appendChild(taskTitle);
    card.appendChild(taskDescription);

    return card;
}

// Function to delete a task
function deleteTask() {
    const taskIdToDelete = document.getElementById("delete-task-id").value;
    if (!taskIdToDelete) {
        alert("Please enter the Task ID to delete.");
        return;
    }

    const taskCardToDelete = document.getElementById("task-" + taskIdToDelete);
    if (taskCardToDelete) {
        taskCardToDelete.remove();
    } else {
        alert("Task with ID " + taskIdToDelete + " not found.");
    }
}

// Function to update a task
function updateTask() {
    const taskIdToUpdate = document.getElementById("update-task-id").value;
    if (!taskIdToUpdate) {
        alert("Please enter the Task ID to update.");
        return;
    }

    const taskCardToUpdate = document.getElementById("task-" + taskIdToUpdate);
    if (!taskCardToUpdate) {
        alert("Task with ID " + taskIdToUpdate + " not found.");
        return;
    }

    const newTitle = document.getElementById("update-task-title").value;
    const newDescription = document.getElementById("update-task-description").value;
    const newPriority = document.getElementById("update-task-priority").value;

    const taskTitleElement = taskCardToUpdate.querySelector("h3");
    const taskDescriptionElement = taskCardToUpdate.querySelector("p");

    if (newTitle) {
        taskTitleElement.textContent = newTitle;
    }
    if (newDescription) {
        taskDescriptionElement.textContent = newDescription;
    }
    if (newPriority) {
        // Remove the task from the current column
        const currentColumn = taskCardToUpdate.parentNode;
        currentColumn.removeChild(taskCardToUpdate);

        // Determine which column to add the task card to based on the updated priority
        switch (newPriority) {
            case "high":
                document.querySelector(".todo-column").appendChild(taskCardToUpdate);
                break;
            case "medium":
                document.querySelector(".in-progress-column").appendChild(taskCardToUpdate);
                break;
            case "low":
                document.querySelector(".done-column").appendChild(taskCardToUpdate);
                break;
            default:
                break;
        }
    }

    // Clear the update input fields
    document.getElementById("update-task-id").value = "";
    document.getElementById("update-task-title").value = "";
    document.getElementById("update-task-description").value = "";
    document.getElementById("update-task-priority").value = "high";
}
