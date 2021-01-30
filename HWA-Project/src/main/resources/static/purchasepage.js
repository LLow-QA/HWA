'use strict';


const coachID = sessionStorage.getItem("coach");
const countEl = document.getElementById("ticketnum");
const ticket = document.getElementById("totalcost");

console.log(coachID.value);



const readChosenCoach = () => {
    fetch("http://localhost:8081/coach/read/" + coachID, {
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

const addToTable = (data) => {

    console.log(data);
    const table = document.getElementById("singlecoach");

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
    sessionStorage.setItem("coachCost",cost.nodeValue);
    displayTotalCost(); 



}

let count = 1;


const plus = () => {
    count++;
    countEl.value = count;
    displayTotalCost();
}

const minus = () => {
  if (count > 1) {
    count--;
    countEl.value = count;
    displayTotalCost();
  }  
}

const displayTotalCost = () => {
    let coachTicketPrice = sessionStorage.getItem("coachCost");
    let tot = Number(coachTicketPrice)*count;
    ticket.value = "£" + tot;
    sessionStorage.setItem("totalCost",tot);
}

const toPay = () => {

    location.replace("checkout.html");
    
}