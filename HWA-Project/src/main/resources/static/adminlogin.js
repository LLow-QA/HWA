'use strict';


let loginButton = document.querySelector('#adminSubmit');


const checkCreds = () => {

    const USERNAME = document.querySelector("#adminuser");
    const PASSWORD = document.querySelector("#adminpass");

    if ((USERNAME.value === "ll@qa.com") && (PASSWORD.value === "Admin12")) {

        alert("Admin login successful.")
        sessionStorage.clear();
        sessionStorage.setItem("currentLogin", "ll@qa.com");
        location.replace("adminsettings.html");
    }
    else {
        alert('Error - Incorrect Username or Password. Please check and try again.');
        location.reload();
    }

}




const toLogin = () => {

    location.replace("Login.html");
}