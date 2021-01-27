'use strict';


const coaches = document.querySelector("#peeps");

//get method
const getCoaches = () => {
    fetch("localhost:8081/coach/readAll", {
        method : "GET"
    }).then(response => {
        if (response.status !== 200) {
            throw new Error("something went wrong");
        } else {
            response.json().then(retrievedinfo => {
                console.log(retrievedinfo);
                for(let i=0; i<json.length; i++) {
            let h3 = document.createElement("h3");
            let title = document.createTextNode(json[i].name);
            let p = document.createElement("p");
            let body = document.createTextNode(json[i].role);
            
            h3.appendChild(title);
            p.appendChild(body);

            coaches.appendChild(h3);
            coaches.appendChild(p)
}
            })
        }
    }).catch(err => console.error(err));
}

 document.getElementById('getcoaches').addEventListener('click', getCoaches);