import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { useState, useEffect} from "react";
import {Button} from '@mui/material';
import {useNavigate} from 'react-router-dom';



export default function Registrationtextfield() {
  let navigate = useNavigate();


  var today = new Date();
  var dd = String(today.getDate()).padStart(2, '0');
  var mm = String(today.getMonth() + 1).padStart(2, '0');
  var yyyy = today.getFullYear();
  var expyyyy = today.getFullYear()+1;

  var paymentdate = yyyy + '-' + mm + '-' + dd;
  var expiry = expyyyy + '-' + mm + '-' + dd;

  const [count, setCount] = useState(0);


  const[serverRespone1, setServerResponse1] = useState();
  const[serverRespone2, setServerResponse2] = useState();

  const[payment, setPayment] = useState();

  const[user, setUser] = useState()


  const[username,setUsername]=useState("");
  const[password,setPassword]=useState("");
  const[subscriptionPaymentDate,setSubscriptionPaymentDate]=useState(paymentdate)
  const[subscriptionEndDate,setSubscriptionEndDate]=useState(expiry);
  const[subscriptionPayed,setSubscriptionPayed]=useState(true);


  const [cardHolderName, setName] = useState("");
  const [cardNumber, setCardNumber] = useState("");
  const [expiryDate, setExpirtyDate] = useState("");
  const [cvc, setCVC] = useState("");
  const [emailAddress, setEmailAddress] = useState("");
  const [address, setAddress] = useState("");
  const [city, setCity] = useState("");
  const [province, setProvince] = useState("");
  const [postalCode, setPostalCode] = useState("");

  useEffect(() => {
      try{
      console.log("List is updated", user);
      //sessionStorage.setItem("user", JSON.stringify(user))
      }
      catch (error) {
        console.log(error)
      }
      
    }, [user]);

  
  useEffect(() => {
    if(count){
    var user1 = JSON.parse(sessionStorage.getItem("user"));
    console.log("calling payment");
    addPayment(user1.id);
    }
    else{
      setCount(1);
    }
  }, [payment]);
  
  
  function addPayment(userid) { 
    fetch('http://localhost:8080/payment/'+userid, requestPaymentOptions)
            .then((response3)=> {
            console.log(requestPaymentOptions);
            if (response3.ok) {
              console.log("added payment info")
              loginSystem();
              navigate("/movies")
            } else {
              response3.json().then(data => alert(data.message))
            }
            })
  }


  function loginSystem(){
    fetch('http://localhost:8080/registereduser/login',requestLoginOptions)
    .then((response4)=> {
    console.log(requestLoginOptions);
    if (response4.ok) {
      console.log("have logged in")
      response4.json()
      .then((data1) => {
        sessionStorage.setItem("user", JSON.stringify(data1))
        setUser(data1);
        console.log(data1);
      })
    } else {
      response4.json().then(data => setServerResponse2(data))
      console.log(serverRespone2.message);
      alert(serverRespone2.message);
    }
    })
  }

  
  const handleSubmit = (evt) => {
    evt.preventDefault();
    console.log("Hello")

    fetch('http://localhost:8080/registereduser/register', requestRegistrationOptions)
    .then((response1) => {
      console.log(requestRegistrationOptions);
      if (response1.ok){
        fetch('http://localhost:8080/registereduser/login',requestLoginOptions)
        .then((response2)=> {
        console.log(requestLoginOptions);
        if (response2.ok) {
          console.log("have logged in")
          response2.json()
          .then((data1) => {
            sessionStorage.setItem("user", JSON.stringify(data1));
            console.log("session storage has been set")
            setPayment(1);
          })
        } else {
          response2.json().then(data => setServerResponse2(data))
          console.log(serverRespone2.message);
          alert(serverRespone2.message);
        }
        })
      } else {
        response1.json().then(data => setServerResponse1(data))
        console.log(serverRespone1.message);
        alert(serverRespone1.message);

      }})
    //.then((data) => {
      //setServerResponse();
      //console.log(serverRespone);
      //alert(serverRespone);
    //data has status, timestamp, error, path
    //console.log(requestRegistrationOptions)
    //})
    
  }



  const requestRegistrationOptions = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({username, password, subscriptionPaymentDate, subscriptionEndDate, subscriptionPayed})
  };

  const requestPaymentOptions = {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({cardHolderName, cardNumber, expiryDate, cvc, emailAddress, address, city, province, postalCode})
  };

  const requestLoginOptions = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({username, password})
  };

      
  return (
    <Box
      component="form"
      sx={{
        '& > :not(style)': { m: 1, width: '25ch' },
      }}
      noValidate
      autoComplete="off"
      onSubmit={handleSubmit}
    >

      <TextField
        required id="outlined-required"
        label="Username"
        value = {username}
        onChangeCapture={(e)=>setUsername(e.target.value)}
        onChange={(e)=>setUsername(e.target.value)}
        />

      <TextField
        required id="outlined-required"
        label="Password"
        value = {password}
        onChangeCapture={(e)=>setPassword(e.target.value)}
        onChange={(e)=>setPassword(e.target.value)}
        />

        <TextField
          required
          id="outlined-required"
          label="Name"
          value = {cardHolderName}
          onChange={(e) => setName(e.target.value)}

        />

        <TextField
          required
          id="outlined-required"
          label="Address"
          value = {address}
          onChange={(e) => setAddress(e.target.value)}
        />
        <TextField
          required
          id="outlined-required"
          label="City"
          value = {city}
          onChange={(e) => setCity(e.target.value)}

        />
        <TextField
          required
          id="outlined-required"
          label="Province"
          value = {province}
          
          onChange={(e) => setProvince(e.target.value)}

        />
        <TextField
          required
          id="outlined-required"
          label="Postal Code"
          value = {postalCode}

          onChange={(e) => setPostalCode(e.target.value)}

        />

        <TextField
          required
          id="outlined-required"
          label="Credit Card Number"
          value = {cardNumber}
          onChange={(e) => setCardNumber(e.target.value)}

        />

        <TextField
          required
          id="outlined-required"
          label="Expiry"
          value = {expiryDate}
          onChange={(e) => setExpirtyDate(e.target.value)}

        />

        <TextField
          required
          id="outlined-required"
          label="CVC"
          value = {cvc}

          onChange={(e) => setCVC(e.target.value)}

        />

        <TextField
          required
          id="outlined-required"
          label="Email Address"
          value = {emailAddress}
          onChange={(e) => setEmailAddress(e.target.value)}

        />
      <br></br>
        <Button style= {{width: '500PX'}} type='submit' version='primary' variant = 'contained' value = "submit"> REGISTER AND PAY 1-YEAR SUBSCRIPTION </Button>      
    </Box>
  );
}
