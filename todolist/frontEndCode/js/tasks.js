var ListID;
const getTasks = (id) => {
    ListID = id;
    fetch("http://localhost:8080/task/" + id, {
        methd: "GET"
    }).then((response) => {
        if (response.status === 200) {
            //there was tasks
            return response.json();
        } else if (response.status === 204) {
            console.log(response)
            //there was no tasks but i should continue anyways
        } else {
            //something was wrong
            throw "Tasks couldn't be found for this list"
        }
    }).then((data) => {
        console.log("data is" + data)
        if (data != null) {

            data.forEach(element => {
                console.log(element)
                let Task = document.createElement('div');
                Task.innerHTML = `<div class="Task p-3">
                    <div class="text-center mt-2">
                        <p class='TaskText'></p>
                    </div>
                    <div class="row mx-auto text-center">
                        <button class="col-4 btn btn-primary mx-auto ModifyBtn">Modify</button>
                        <button class="col-4 btn btn-danger mx-auto DeleteBtn">Delete</button>
                    </div>
                </div>`
                Task.querySelector(".TaskText").innerHTML = element.task;
                let ModifyBtn = Task.querySelector(".ModifyBtn");
                ModifyBtn.addEventListener("click", function () {
                    ModifyTask(element.id)
                })
                let DeleteBtn = Task.querySelector(".DeleteBtn")
                DeleteBtn.addEventListener("click", function () {
                    DeleteTask(element.id)
                })
                document.querySelector("#InnerTasksBox").appendChild(Task)
            });
        }else{
        }
    }).catch((err) => {
        alert("There was a problem fetching the tasks for this list" + err)
    })
}

const ModifyTask = (id) => {

}

const DeleteTask = (id) => {

}


const AddTask = (event) => {
    event.preventDefault();
    let Task = document.querySelector("#Task").value
    /*
    "task" : "first task",
    "taskCompleted" : false,
    "list" : {
        "id" : 1
    }
    */
    fetch("http://localhost:8080/task", {
        method: "POST",
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            "task": Task,
            "taskCompleted": false,
            "list": {
                "id": ListID
            }
        })
    }).then((response) => {
        if (response.status == 200) {
            location.reload();
        } else {
            throw "An error occured when a task was being sent";
        }
    }).then((data) => {
        console.log(data)
    }).catch((err) => {
        alert("There was an error" + err)
    })
}


(function () { //should be at the end to stop it messing with arrow funcs
    const params = new URLSearchParams(window.location.search);
    getTasks(params.get("TaskID"))

})();