'use strict';


const origin_box = document.getElementById("origin");
const destination_box = document.getElementById("destination");
const departure_box = document.getElementById("depart");
const arrival_box = document.getElementById("arrive");
const capacity_box = document.getElementById("cap");
const price_box = document.getElementById("price");



const origins = document.querySelector("#origin");
const destination = document.querySelector("#destination");
const departure = document.querySelector("#depart");
const arrival = document.querySelector("#arrive");
const capacity = document.querySelector("#cap");
const price = document.querySelector("#price");
const coachID = document.querySelector("#coachID");




let submitButton = document.querySelector('#updateCoach');



const getCoachInfo = () => {


    fetch(`http://localhost:8081/coach/read/${coachID.value}`, {
        method: "GET"
    }).then(response => {
        if (response.status !== 200) {
            throw new Error("something went wrong");
        } else {
            response.json().then(retrievedinfo => {
                console.log(retrievedinfo);
                
                    populateFields(retrievedinfo);
            })
        }
    }).catch(err => console.error(err));
}

const populateFields = (data) => {

    let ori = document.createTextNode(data.startPoint);
    origin_box.value = ori.nodeValue;

    let dest = document.createTextNode(data.endPoint);
    destination_box.value = dest.nodeValue;

    let dep = document.createTextNode(data.departureTime);
    departure_box.value = dep.nodeValue;

    let arr = document.createTextNode(data.arrivalTime);
    arrival_box.value = arr.nodeValue;

    let cap = document.createTextNode(data.capacity);
    capacity_box.value = cap.nodeValue;

    let pr = document.createTextNode(data.ticketCost);
    price_box.value = pr.nodeValue;

}

const checkLocation = (inputStuffs) => {

    const nameReqs = /^[a-zA-Z].{2,255}$/;
    if (inputStuffs.value.match(nameReqs)) {
        return true;
    } else {
        alert('Error - Please enter a valid Location. (letters only)');
        return false;
    }
}

const checkTime = (inputTime) => {

    const hrtime = /^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/;

    if (inputTime.value.match(hrtime)) {
        return true;
    } else {
        alert('Error - please input a valid 24 hour time.');
        return false;
    }

}

const checkCapacity = (inputCap) => {

    const min = 10;

    if (Number(inputCap.value) < min) {

        alert('Error - Please add a coach capcity of 10 or more.');
        return false;

    } else {
        return true;
    }
}

const checkPrice = (inputPrice) => {

    const max = 20;

    if (Number(inputPrice.value) > max) {
        alert('Error - Please enter a price less than £20. We view ourselves as an affordable company.');
        return false;

    } else {
        return true;
    }
}




const updateEntry = () => {

    if (!checkLocation(origin_box) || !checkLocation(destination_box) || !checkTime(arrival_box) || !checkTime(departure_box) || !checkCapacity(capacity_box) || !checkPrice(price_box)) {
        location.reload();
        return;
    }
    else {
        fetch(`http://localhost:8081/coach/update/${coachID.value}`, {
            method: "PATCH",
            body: JSON.stringify({

                "arrivalTime": arrival.value,
                "capacity": capacity.value,
                "departureTime": departure.value,
                "endPoint": destination.value,
                "startPoint": origins.value,
                "ticketCost": price.value

                

            }),
            headers: {
                'Content-type': "application/json",
            },
        }).then((response) => response.json())
            .then((json) => console.log(json))
            .catch(err => console.error(err));


        alert("Coach successfully updated.")
        window.location.replace("adminsettings.html");
    }
}


submitButton.addEventListener('click', updateEntry);