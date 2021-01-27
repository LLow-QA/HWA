'use strict';


const firstName2 = document.querySelector("#firstName1");
const lastName2 = document.querySelector("#lastName1");
const email2 = document.querySelector("#email1");
const address2 = document.querySelector("#address1");
const postcode2 = document.querySelector("#postcode1");
const password2 = document.querySelector("#password1");
const numberOfTickets = 0;
const totalCost = 0.0;
let submitButton = document.querySelector('#signupSubmit');


const checkPassword = (inputtxt) => {

    const passwordReqs = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$/;
    if (inputtxt.value.match(passwordReqs)) {
        return true;
    }
    else {
        alert('Please follow the instructions to enter a valid password');
        return false;
    }
}

const checkNames = (inputStuffs) => {

    const nameReqs =  /^[a-zA-Z].{2,30}$/;
    if (inputStuffs.value.match(nameReqs)) {
        return true;
    }else {
        alert('Error - Please enter a valid name.');
        return false;
    }
}

const checkPostcode = (inputPostcode) => {

    const UKpostcode = /^(([a-zA-Z][0-9])|([a-zA-Z][0-9][0-9])|([a-zA-Z][a-zA-Z][0-9])|([a-zA-Z][a-zA-Z][0-9][0-9])) [0-9][a-zA-Z][a-zA-Z]$/;
    
    if (inputPostcode.value.match(UKpostcode)) {
        return true;
    }else{
        alert('Error - please input a valid UK postcode.');
        return false;
    }

}

const checkEmail = (inputE) => {

    const mails = /(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])$/;

    if (inputE.value.match(mails)){
        return true;
    }else {
        alert('Error - Please check that your email matches the required format.');
        return false;
    }

}

const createEntry = () => {

    if (!checkPassword(password2) || !checkNames(firstName2) || !checkNames(lastName2) || !checkPostcode(postcode2) || !checkEmail(email2)) {
        location.reload();
        return;
    }
    else {

        fetch("http://localhost:8081/passenger/create", {
            method: "POST",
            body: JSON.stringify({

                "address": address2.value,
                "email": email2.value,
                "first_name": firstName2.value,
                "last_name": lastName2.value,
                "numberOfTickets": numberOfTickets.value,
                "password": password2.value,
                "postcode": postcode2.value,
                "totalCost": totalCost.value

            }),
            headers: {
                'Content-type': "application/json",
            },
        }).then((response) => response.json())
            .then((json) => console.log(json))
            .catch(err => console.error("Stop"));

        alert("Congratulations!!\n You have officially registered with QA Coaches.")
        window.location.replace("login.html");
    }
}

submitButton.addEventListener('click', createEntry);