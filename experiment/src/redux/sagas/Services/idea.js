import axios from "axios";

//get request to display all ideas in the repository
export function RequestGetAllIdea() {
    let url = "http://localhost:8090/idea";
    let config = {
        params: {
            email: localStorage.getItem('email'),
        },
        headers: {
            Authorization: "Bearer " + localStorage.getItem('token')
        }
    }

    const final = axios.get(url, config)
    return final.then((response) => 
        response.data
       
    )
    .catch((error) => {
        console.log(error);
    })
    



};

//get request for an authenticated user
export function requestGetIdea() {
    let url = "http://localhost:8090/idea/user";
    let config = {
        params: {
            email: localStorage.getItem('email'),
        },
        headers: {
            Authorization: "Bearer " + localStorage.getItem('token')
        }
    }

    const final = axios.get(url, config)
    return final.then((response) => 
        response.data
       
    )
    .catch((error) => {
        console.log(error);
    })
    



};

//get request to display a single idea with id as a parameter
export function RequestSingleGetIdea(id) {
    const api = `http://localhost:8090/idea/${id}`
    let config = {
     
      headers: {
          Authorization: "Bearer " + localStorage.getItem('token'),
      }
  }
    const final = axios.get(api, config)
    return final.then((response) => 
    response.data
    )
    .catch((error) => {
        console.log(error);
    })
}

//post request to add an idea
export function RequestAddIdeas(obj) {

    const api = "http://localhost:8090/idea"
    let config = {
        params: {
            email: localStorage.getItem('email'),
        },
      headers: {
          Authorization: "Bearer " + localStorage.getItem('token'),
      }
  }

    const final = axios.post(api, obj, config)
    return final.then((response) => 
        response.data
       
    )
    .catch((error) => {
        console.log(error);
    })
    
}


//delete request to delete a particular idea by their id
export const RequestIdeaDeleted = (id) => {

let url = `http://localhost:8090/idea/${id}`;
let config = {
    
    headers: {
        Authorization: "Bearer " + localStorage.getItem('token')
    }
}

const final = axios.delete(url, config)
return final.then((response) => 
    response.data
   
)
.catch((error) => {
    console.log(error);
})

}

//update request to update a particular selected idea
export const RequestUpdateIdea = (id, obj) => {


const api = `http://localhost:8090/idea/${id}`
        let config = {
          headers: {
              Authorization: "Bearer " + localStorage.getItem('token'),
          }
      }


        const final = axios.put(api, obj, config)
        return final.then((response) => 
            response.data
           
        )
        .catch((error) => {
            console.log(error);
        })
        
    }