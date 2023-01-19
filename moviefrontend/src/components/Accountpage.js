import React from "react";
import Button from '@material-ui/core/Button';
import {useNavigate} from "react-router-dom";
import { useState, useEffect} from "react";
import Box from '@mui/material/Box';


function Accountpage() {
    let navigate = useNavigate();
    
    //var sessionUser = JSON.parse(sessionStorage.getItem("user"));
    const[user, setUser] = useState(JSON.parse(sessionStorage.getItem("user")))


    useEffect(() => {
      try{
      console.log("List is updated", user);
      sessionStorage.setItem("user", JSON.stringify(user))
      }
      catch (error) {
        console.log(error)
      }
      
    }, [user]);

    function paySubscription() { 
      fetch('http://localhost:8080/registereduser/paySubscription/'+user.id, {method:'PUT'})
        .then((response)=> {
        console.log(response);
        if (response.ok) {
          console.log("subscription fees payed")
          fetchLogin();
        } else {
          response.json().then(data => alert(data.message))
        }
        })
    }

    function fetchLogin() { 
      fetch('http://localhost:8080/registereduser/login',requestLoginOptions)
        .then((response2)=> {
        console.log(requestLoginOptions);
        if (response2.ok) {
          console.log("have logged in")
          response2.json()
          .then((data1) => {
            setUser(data1);
            console.log(data1)
          })
        } else {
          response2.json().then(data => alert(data))
        }
        })
    }

    const requestLoginOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({"username": user.username, "password":user.password})
    };

    return (
      <Box
      component="form"
      sx={{
        '& > :not(style)': { m: 2, width: '50ch' },
      }}

      noValidate
      autoComplete="off"
      >
      <div>
        <h1> Welcome to Accounts Page</h1>

        <p>Subcription Status:</p>
        {(user.subscriptionPayed) === true? <p style= {{padding: "10px", border: "2px solid green"}}>ACTIVE</p> :<p style= {{padding: "10px", border: "2px solid red"}}>EXPIRED</p>}
        <p>Subscription Expiry Date:</p>
        {user.subscriptionEndDate}
        <br></br>
        <br></br>

        <Button color="inherit" version='primary' variant = 'contained' onClick={() => paySubscription()} >Renew Subcription</Button>
        <br></br>
        <br></br>
        <Button color="inherit" version='primary' variant = 'contained' onClick= {() => navigate("/updatepayment")} >Update Payment INFO</Button>

      </div>
      </Box>
    );
}

export default Accountpage;

