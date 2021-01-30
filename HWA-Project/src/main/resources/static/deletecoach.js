'use strict';

const ID = document.querySelector("#delete");

const deleteRoute = () => {


    fetch(`http://localhost:8081/coach/delete/${ID.value}`, {
        method: "DELETE"
    }).then(response => {
        if (response.status !== 204) {
            alert("Error - Something went wrong, check the Route exists.")
            throw new Error("something went wrong");
        } else {
                alert("Route Deleted");
                location.replace("adminsettings.html");
        }

    }).catch(err => console.error(err));
}