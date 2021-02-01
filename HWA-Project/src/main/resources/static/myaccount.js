'use strict';

const ID = sessionStorage.getItem("loginID");

const deleteTicket = () => {

    fetch(`http://localhost:8081/passenger/update/${ID}`, {
        method: "PATCH",
        body: JSON.stringify({
            "coach": {
                "coachID": null,
            },
            "totalCost": 0,
            "numberOfTickets": 0,
        }),
        headers: {
            'Content-type': "application/json",
        },
    }).then((response) => response.json())
        .then((json) => console.log(json))
        .catch(err => console.error(err));

    alert("Refund request recieved.\n Please allow up to 7 days for the funds to be returned to your account.");
    sessionStorage.setItem(coach, undefined);
    sessionStorage.setItem(coachCost, undefined);
    sessionStorage.setItem(totalCost, undefined);
    window.location.replace("myaccount.html");
}

const deleteAccount = () => {


    fetch(`http://localhost:8081/passenger/delete/${ID}`, {
        method: "DELETE",
    }).then(response => {
        if (response.status !== 204) {
            throw new Error("something went wrong");
        } else {
            sessionStorage.setItem("loginID", undefined);
            sessionStorage.setItem("currentLogin", undefined);
            sessionStorage.setItem("coach", undefined);
            sessionStorage.setItem("coachCost", undefined);
            sessionStorage.setItem("totalCost", undefined);
            location.replace("Index.html")
        }
    }).catch(err => console.error(err));
}

const logout = () => {
    sessionStorage.clear();
    alert("You have been successfully logged out.");
    location.replace("Index.html");
}

const updateInfo = () => {

    location.replace("updateaccount.html");
}

const updateTicket = () => {

    location.replace("updateticket.html");

}