'use strict';


const first_name_box = document.getElementById("box1");
const last_name_box = document.getElementById("box2");
const email_box = document.getElementById("box5");
const address_box = document.getElementById("box3");
const postcode_box = document.getElementById("box4");

const firstName = document.querySelector("#box1");
const lastName = document.querySelector("#box2");
const email1 = document.querySelector("#box3");
const address = document.querySelector("#box4");
const postcode = document.querySelector("#box5");
const password = document.querySelector("#box6");
const ID = sessionStorage.getItem("loginID");
let submitButton = document.querySelector('#updateSubmit');


const getUserInfo = () => {

    fetch(`http://localhost:8081/passenger/read/${ID}`, {
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

    let fname = document.createTextNode(data.first_name);
    first_name_box.value = fname.nodeValue;

    let lname = document.createTextNode(data.last_name);
    last_name_box.value = lname.nodeValue;

    let mail = document.createTextNode(data.email);
    email_box.value = mail.nodeValue;

    let addr = document.createTextNode(data.address);
    address_box.value = addr.nodeValue;

    let post = document.createTextNode(data.postcode);
    postcode_box.value = post.nodeValue;

}

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

const updateEntry = () => {

    if (!checkPassword(password2) || !checkNames(firstName2) || !checkNames(lastName2) || !checkPostcode(postcode2) || !checkEmail(email2)) {
        location.reload();
        return;
    }
    else {

        fetch(`http://localhost:8081/passenger/update/${ID}`, {
            method: "PATCH",
            body: JSON.stringify({

                "address": address.value,
                "email": email1.value,
                "first_name": firstName.value,
                "last_name": lastName.value,
                "password": password.value,
                "postcode": postcode.value,

            }),
            headers: {
                'Content-type': "application/json",
            },
        }).then((response) => response.json())
            .then((json) => console.log(json))
            .catch(err => console.error("Stop"));

        sessionStorage.setItem("currentLogin", email1.value);

        alert("Your details have been successfully updated.")
        window.location.replace("myaccount.html");
    }
}

submitButton.addEventListener('click', updateEntry);