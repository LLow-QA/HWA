'use strict';

const updatePageId = sessionStorage.getItem("loginID");
const totCost = sessionStorage.getItem("totalCost");
const tickPrice = sessionStorage.getItem("coachCost");
const coachid = Number(sessionStorage.getItem("coach"));
let floatTot = Number.parseFloat(totCost);
let tickNum = Number.parseInt(String(floatTot / (Number.parseFloat(tickPrice))));



const addToTable = () => {

    const table = document.getElementById("fromStorage");

    let row = table.insertRow();

    let numberrow = row.insertCell(0);
    let number = "£" + floatTot;
    numberrow.innerHTML = number;

    let vatrow = row.insertCell(1);
    let vat = "£" + ((floatTot * 1.2).toFixed(2));
    vatrow.innerHTML = vat;
}

const addToCustomer = () => {
    const taxExempt = document.getElementById('vatCheck').checked;
    console.log(taxExempt);
    if (taxExempt) {

        fetch(`http://localhost:8081/passenger/update/${updatePageId}`, {

            method: "PATCH",
            body: JSON.stringify({

                'numberOfTickets': tickNum,
                'totalCost': floatTot,
                'coach': {
                    'coachID': coachid
                },
            }),
            headers: {
                "Content-Type": "application/json"
            }
        }).then(response => response.json())
            .then(json => console.log(json))
            .catch(err => console.log("Error with PATCH."));

        alert(`Thank you for your purchase.\nYou ticket(s) have been added to your account.`);
        location.replace("Index.html");

    } else {

        fetch(`http://localhost:8081/passenger/update/${updatePageId}`, {

            method: "PATCH",
            body: JSON.stringify({

                'numberOfTickets': tickNum,
                'totalCost': (floatTot * 1.2).toFixed(2),
                'coach': {
                    'coachID': coachid
                },
            }),
            headers: {
                "Content-Type": "application/json"
            }
        }).then(response => response.json())
            .then(json => console.log(json))
            .catch(err => console.log("Error with PATCH+VAT."));

        alert(`Thank you for your purchase.\nYou ticket(s) have been added to your account.`);
        location.replace("Index.html");

    }
}

