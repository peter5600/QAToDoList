const CreateForm = (event) => {
    event.preventDefault(); //dont submit
    let ListName = document.querySelector("input[name='ListName']").value;
    console.log(ListName)

    //need to send the name to localhopst:8080/list
    fetch("http://localhost:8080/list", {
        method: "POST",
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            "listName": ListName
        })
    }).then((response) => {
        if (response.status === 201) {
            //refresh the page to let the get command re run
            location.reload();
        } else {
            throw 'List not created';
        }
    }).catch((error) => {
        alert("There was a problem posting the data to the server. Please try again later. Thanks")
    })
}
var ListComponent; //This is the html from the list Component as a string global var


const GetLists = () => {
    fetch("http://localhost:8080/list", {
        method: "GET",
        headers: {
            "Content-type": "application/json"
        }
    }).then((response) => {
        console.log(response)
        if (response.status === 200) {
            return response.json(); //send the data to the next promise
        } else {
            throw "Lists couldn't be found"; //end the chain of promises jump to the catch 
        }
    }).then((data) => {
        if (data.length === 0) {
            //no lists this ok just add a notification
            document.querySelector("#Lists").innerHTML = "<h4 class='text-center'>No lists have been created yet. Please use the button below to create your first todo list.</h4>";
        }
        data.forEach(l => {
            console.log(JSON.stringify(l))
            let InnerListComponent = document.createElement('div');
            InnerListComponent.innerHTML = ListComponent.trim();
            InnerListComponent.querySelector(".ListName").innerHTML = l.listName;

            let TasksBtn = InnerListComponent.querySelector(".ViewTasks");
            TasksBtn.addEventListener("click", function () {
                DisplayTasks(l.id); //probably a better way to do this but it functions for now
            })
            let DeleteBtn = InnerListComponent.querySelector(".DeleteList");
            DeleteBtn.addEventListener("click", function () {
                DeleteList(l.id)
            })

            document.querySelector("#Lists").appendChild(InnerListComponent);

        })
    }).catch((err) => {
        alert("There was an error fetching the lists. Please try again later. Thanks" + err)
    })
}


const DisplayTasks = (id) => {

}

const DeleteList = (id) => {

}


(function () {

    //On page load
    /*fetch("../Components/list.html", {
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    }).then((response) => {
        console.log(response)
        if(response.status === 200){
            console.log("All good")
            return response.text();
        }else{
            throw 'List Component could not be found';
        }
    }).then((data) => {
        ListComponent = data;
        console.log("List component is " + ListComponent)
        GetLists()
    }).catch((err) => {

        alert("An error has occured when getting component the error is " + err);
    })*/
    ListComponent = `<div class="row List w-75 mx-auto" >
    <div class="col-2 text-center">
        <p class="ListName">List Name</p>
    </div>
    <div class="col-6"></div>
    <div class="col-4">
        <div class="row">
            <div class="col-6">
                <button class="btn btn-primary btn-lg ViewTasks">View tasks</button>
            </div>
            <div class="col-6">
                <button class="btn btn-danger btn-lg DeleteList">Delete List</button>
            </div>
        </div>
    </div>
</div>`; //i did have a fetch system that grabs the file but this only works with the live server extension so unfotunatley this has been replaced
    GetLists();



})();