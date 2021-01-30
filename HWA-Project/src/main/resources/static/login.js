'use strict';

const USERNAME = document.querySelector("#username1");
const PASSWORD = document.querySelector("#password1");
let loginButton = document.querySelector('#loginSubmit');



const validateLogin = () => {
    fetch("http://localhost:8081/passenger/readAll", {
        method: "GET"
    }).then(response => {
        if (response.status !== 200) {
            throw new Error("something went wrong");
        } else {
            response.json().then(retrievedinfo => {

                console.log(retrievedinfo);
                if (!compare(retrievedinfo)) {

                    alert('Error - Incorrect Username or Password. Please check and try again');

                    return;
                } else {

                    sessionStorage.setItem("currentLogin", USERNAME.value);
                    location.replace('Index.html');
                }
            })
        }
    }).catch(err => console.error(err));
}


const compare = (retrievedData) => {
    console.log(retrievedData);
    console.log(USERNAME.value)
    for (let data of retrievedData) {

        let passengerId = document.createTextNode(data.passengerID);
        let testEmail = document.createTextNode(data.email);
        let testPass = document.createTextNode(data.password);
        let firstname = document.createTextNode(data.first_name);


        console.log(('"' + USERNAME.value + '"').localeCompare(testEmail));
        if ((testEmail.nodeValue === USERNAME.value) && (PASSWORD.value === testPass.nodeValue)) {
            alert('login successful, Welcome ' + firstname.nodeValue);
            sessionStorage.setItem("loginID", passengerId.nodeValue);
            return true;

        }
    }
    return false;
}

const noReg = () => {

    location.replace("Signup.html");

}