'use strict';

const ticketNumBox = document.getElementById("ticketnum");
const costBox = document.getElementById("totalcost");
const coachID = sessionStorage.getItem("coach");
const originalTot = sessionStorage.getItem("totalCost");
const oneTickCost = sessionStorage.getItem("coachCost")
const passengerid = sessionStorage.getItem('loginID');
const countEl = document.getElementById("ticketnum");
const ticket = document.getElementById("totalcost");

let floatTot = Number.parseFloat(originalTot);
let tickNum = Number.parseInt(String(originalTot / (Number.parseFloat(oneTickCost))));
ticketNumBox.value = tickNum;
costBox.value = originalTot;


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

let count = tickNum;


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


const updateToProfile = () => {

    let updatePrice = sessionStorage.getItem("totalCost");
    let ticketNumber = Number.parseInt(String(updatePrice / (Number.parseFloat(oneTickCost))));

    fetch(`http://localhost:8081/passenger/update/${passengerid}`, {

            method: "PATCH",
            body: JSON.stringify({

                'numberOfTickets': ticketNumber,
                'totalCost': updatePrice,
            }),
            headers: {
                "Content-Type": "application/json"
            }
        }).then(response => response.json())
            .then(json => console.log(json))
            .catch(err => console.log(err));

        updateComplete();
}

const updateComplete = () => {

    let updatedCost = sessionStorage.getItem("totalCost");

    if (Number(updatedCost) > Number(originalTot)) {

        alert(`Update Successful \n\n Your order update generated an outstanding balance of £${((Number(updatedCost) - Number(originalTot)).toFixed(2))}. You will shortly recieve an email instructing you on how to pay this balance.`);
        location.replace("myaccount.html");

    }
    else if (Number(updatedCost) < Number(originalTot)) {

        alert(`Update Successful \n\n Your order update means we owe you £${((Number(originalTot) - Number(updatedCost)).toFixed(2))}. Please allow 7 working days for this money to be transferred back into your account.`);
        location.replace("myaccount.html");
    }
    else {
        alert('No update detected, redirecting you back to your account.');
        location.replace("myaccount.html");
    }

}