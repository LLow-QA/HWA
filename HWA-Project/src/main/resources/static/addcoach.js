'use strict';


const origin_box = document.querySelector("#origin");
const destination_box = document.querySelector("#destination");
const departure_box = document.querySelector("#depart");
const arrival_box = document.querySelector("#arrive");
const capacity_box = document.querySelector("#cap");
const price_box = document.querySelector("#price");

let submitButton = document.querySelector('#coachCreate');




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




const createEntry = () => {

    if (!checkLocation(origin_box) || !checkLocation(destination_box) || !checkTime(arrival_box) || !checkTime(departure_box) || !checkCapacity(capacity_box) || !checkPrice(price_box)) {
        location.reload();
        return;
    }
    else {

        fetch("http://localhost:8081/coach/create", {
            method: "POST",
            body: JSON.stringify({

                "arrivalTime": arrival_box.value,
                "capacity": capcity_box.value,
                "departureTime": departure_box.value,
                "endPoint": destination_box.value,
                "startPoint": origin_box.value,
                "ticketCost": price_box.value

            }),
            headers: {
                'Content-type': "application/json",
            },
        }).then(response => {
            if (response.status !== 201) {
                throw new Error("Create failed");
            } else {
                response.json().then((json) => console.log(json))


                alert("Route Created");
                window.location.replace("adminsettings.html");
            }
        }).catch(err => console.error(err));
    }
}

    submitButton.addEventListener('click', createEntry);