const base_url = "http://localhost:8080";
const createButton = document.getElementById('create-button');

createButton.addEventListener('click', createCard);

async function createCard() {
    const cardTitle = document.getElementById('task-title').value;
    const cardDescription = document.getElementById('task-description').value;
    const taskPriority = document.getElementById('task-priority').value;

    let sectionNumber;

    switch (taskPriority) {
        case "high": // TODO
            sectionNumber = 1;
            break;
        case "medium": // IN PROGRESS
            sectionNumber = 2;
            break;
        case "low": // DONE
            sectionNumber = 3;
            break;
        default:
            sectionNumber = 1; // Default to TODO
            break;
    }

    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    const raw = JSON.stringify({
      "title": cardTitle,
      "description": cardDescription,
      "section": sectionNumber // Use the determined section number
    });

    const requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    try {
        const response = await fetch(`${base_url}/api/cards/${sectionNumber}`, requestOptions); // Use base_url
        const result = await response.text();
        if (response.ok) {
            console.log("Card created:", result);
            getAllBoards();
        } else {
            console.error('Error:', response.status);
        }
    } catch (error) {
        console.error('Error:', error.message);
    }
}









function deleteTask() {
    const taskIdToDelete = document.getElementById("delete-task-id").value;
    if (!taskIdToDelete) {
        alert("Please enter the Task ID to delete.");
        return;
    }

    const taskCardToDelete = document.getElementById("task-" + taskIdToDelete);
    if (taskCardToDelete) {
        taskCardToDelete.remove(); // Remove from UI
        console.log("Card with ID " + taskIdToDelete + " deleted from UI.");

        // Additional code to delete the card from the server/database
        // ...

    } else {
        alert("Task with ID " + taskIdToDelete + " not found.");
    }


    try {
            const response = await fetch(`${base_url}/api/cards/${cardId}`, requestOptions); // Use base_url
            const result = await response.text();
            if (response.ok) {
                console.log("Card created:", result);
                getAllBoards();
            } else {
                console.error('Error:', response.status);
            }
        } catch (error) {
            console.error('Error:', error.message);
        }
}





// Function to display cards by section
async function displayCardsBySection() {
    const base_url = "http://localhost:8080";
    try {
        const response = await fetch(`${base_url}/api/cards`);
        if (!response.ok) {
            throw new Error(`Failed to fetch cards. Status: ${response.status} ${response.statusText}`);
        }
        const cards = await response.json();

        // Get the containers for each section
        const todoContainer = document.querySelector(".todo-column");
        todoContainer.innerHTML = '<h2>TODO</h2>';

        const inProgressContainer = document.querySelector(".in-progress-column");
        inProgressContainer.innerHTML = '<h2>IN PROGRESS</h2>';

        const doneContainer = document.querySelector(".done-column");
        doneContainer.innerHTML = '<h2>DONE</h2>';

        // Loop through the cards and create HTML elements to display them
        cards.forEach((card) => {
            const cardElement = document.createElement('div');
            cardElement.classList.add('task-card'); // Add the 'task-card' class for styling
            cardElement.innerHTML = `
                <h3>Card ID: ${card.id}</h3>
                <p>Title: ${card.title}</p>
                <p>Description: ${card.description}</p>
            `;

            // Add the card to the corresponding container based on the section
            if (card.section === 1) {
                todoContainer.appendChild(cardElement);
            } else if (card.section === 2) {
                inProgressContainer.appendChild(cardElement);
            } else if (card.section === 3) {
                doneContainer.appendChild(cardElement);
            }
        });
    } catch (error) {
        console.error('Error fetching cards:', error);
        alert('An error occurred while fetching cards.');
    }
}

// Initial call to fetch and display cards when the page loads
displayCardsBySection();










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
