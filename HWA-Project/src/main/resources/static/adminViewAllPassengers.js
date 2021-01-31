'use strict';



//get method
const getPassengers = () => {
    fetch("http://localhost:8081/passenger/readAll", {
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
        let fnamerow = row.insertCell(0);
        let fname = document.createTextNode(data.first_name);
        fnamerow.innerHTML = fname.nodeValue;

        let lnamerow = row.insertCell(1);
        let lname = document.createTextNode(data.last_name);
        lnamerow.innerHTML = lname.nodeValue;

        let emailrow = row.insertCell(2);
        let email = document.createTextNode(data.email);
        emailrow.innerHTML = email.nodeValue;

        let addressrow = row.insertCell(3);
        let address = document.createTextNode(data.address);
        addressrow.innerHTML = address.nodeValue;

        let postcoderow = row.insertCell(4);
        let postcode = document.createTextNode(data.postcode);
        postcoderow.innerHTML = postcode.nodeValue;

        let ticketrow = row.insertCell(5);
        let ticket = document.createTextNode(data.numberOfTickets);
        ticketrow.innerHTML = ticket.nodeValue;

        let costrow = row.insertCell(6);
        let cost = document.createTextNode(data.totalCost);
        costrow.innerHTML = cost.nodeValue;

        let coachrow = row.insertCell(7);
        let coach = document.createTextNode(data.coachID);
        coachrow.innerHTML = coach.nodeValue;



        
    }


}