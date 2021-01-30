'use strict';


const coaches = document.querySelector("#peeps");

//get method
const getCoaches = () => {
    fetch("http://localhost:8081/coach/readAll", {
        method: "GET"
    }).then(response => {
        if (response.status !== 200) {
            throw new Error("something went wrong");
        } else {
            response.json().then(retrievedinfo => {
                console.log(retrievedinfo);

                addToTable(retrievedinfo);
            })
        }
    }).catch(err => console.error(err));
}


const addToTable = (recieveddata) => {
    console.log(recieveddata);
    const table = document.getElementById("addhere");

    for (let data of recieveddata) {


        let row = table.insertRow();
        let originrow = row.insertCell(0);
        let origin = document.createTextNode(data.startPoint);
        originrow.innerHTML = origin.nodeValue;

        let destinationrow = row.insertCell(1);
        let destination = document.createTextNode(data.endPoint);
        destinationrow.innerHTML = destination.nodeValue;

        let departrow = row.insertCell(2);
        let depart = document.createTextNode(data.departureTime);
        departrow.innerHTML = depart.nodeValue.substring(0, 5);

        let arriverow = row.insertCell(3);
        let arrive = document.createTextNode(data.arrivalTime);
        arriverow.innerHTML = arrive.nodeValue.substring(0, 5);

        let costrow = row.insertCell(4);
        let cost = document.createTextNode(data.ticketCost);
        costrow.innerHTML = ('£' + cost.nodeValue);

        let buttonrow = row.insertCell(5);

        let coachid = document.createTextNode(data.coachID);
        let x = coachid.nodeValue;


        let span = document.createElement("span")
        span.innerHTML = `<button type = "button" id = "buyticket" name = '${x}' onclick = 'setInSessionStorage(this.name);'/>Buy Tickets</button>`;
  
        buttonrow.appendChild(span);

    }


}

const setInSessionStorage = (name) => {
    console.log(name);
    if (sessionStorage.getItem("currentLogin") == undefined) {
        alert('Please sign in to continue.');
        location.replace("login.html");
    } else {
        sessionStorage.setItem("coach", name);
        location.replace("PurchasePage.html");
    }
}
