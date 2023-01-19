import React from "react";
import Button from '@material-ui/core/Button';
import {useNavigate} from "react-router-dom";
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { useState, useEffect} from "react";



function Updatepayments() {
    let navigate = useNavigate();

    var user1;
    const [count, setCount] = useState(0);

    const [payment, setPayment] = useState();
    const [user, setUser] = useState()

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
        if(count){
        var user1 = JSON.parse(sessionStorage.getItem("user"));
        console.log("calling payment");
        addPayment(user1.id);
        }
        else{
          setCount(count+1);
        }
    }, [payment]);

    useEffect(() => {
        try{
        console.log("List is updated", user);
        //sessionStorage.setItem("user", JSON.stringify(user))
        }
        catch (error) {
          console.log(error)
        }
        
    }, [user]);


    if(sessionStorage.getItem("user") !== "undefined" && sessionStorage.getItem("user") !== null) {
        var user1 = JSON.parse(sessionStorage.getItem("user"));
        console.log(user1);
    } else {
        var user1 = {"username": "Guest", "id": 0};
        console.log(user1)
    }

    const handleSubmit = (evt) => {
        evt.preventDefault();
        console.log("Hello")
        if(user1.id) {
            user1 = JSON.parse(sessionStorage.getItem("user"));
            addPayment(user1.id);
        } else {
          alert("Error fetching user error!")
        }
    }

    function addPayment(userid) { 
        fetch('http://localhost:8080/payment/'+userid, requestPaymentOptions)
                .then((response3)=> {
                console.log(requestPaymentOptions);
                if (response3.ok) {
                  console.log("updated payment info")
                  loginSystem();
                  navigate("/account")
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
          response4.json().then(data => alert(data.message))
        }
        })
    }

    const requestPaymentOptions = {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({cardHolderName, cardNumber, expiryDate, cvc, emailAddress, address, city, province, postalCode})
    };

    const requestLoginOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({"username": user1.username, "password": user1.password})
    };
    
    return (
    <div>
    <Box
      component="form"
      sx={{
        '.MuiTextField-root': { m: 1, width: '25ch' },
        '.MuiButton-root' : {m:1 , fullwidth: 'true', disabled:'true'}
      }}
      noValidate
      onSubmit={handleSubmit}
      display = {"false"}
    >
      <div>
        <TextField
          required
          id="outlined-required"
          value = {cardHolderName}
          onChange={(e) => setName(e.target.value)}
          label = "Name"
        />

        <TextField
          required
          id="outlined-required"
          value = {address}
          onChange={(e) => setAddress(e.target.value)}
          label = "Address"

        />
        <TextField
          required
          id="outlined-required"
          value = {city}
          onChange={(e) => setCity(e.target.value)}
          label = "City"
        />
        <TextField
          required
          id="outlined-required"
          value = {province}
          onChange={(e) => setProvince(e.target.value)}
          label = "Province"

        />
        <TextField
          required
          id="outlined-required"
          value = {postalCode}
          onChange={(e) => setPostalCode(e.target.value)}
          label = "Postal Code"

        />

        <TextField
          required
          id="outlined-required"
          value = {cardNumber}
          onChange={(e) => setCardNumber(e.target.value)}
          label = "Credit Card Number"

        />

        <TextField
          required
          id="outlined-required"
          value = {expiryDate}
          onChange={(e) => setExpirtyDate(e.target.value)}
          label = "Expiry Date"

        />

        <TextField
          required
          id="outlined-required"
          value = {cvc}
          onChange={(e) => setCVC(e.target.value)}
          label = "CVC"

        />

        <TextField
          required
          id="outlined-required"
          value = {emailAddress}
          onChange={(e) => setEmailAddress(e.target.value)}
          label = "Email Address"

        />

        <br></br>
        <br></br>
        <Button type='submit' version='primary' variant = 'contained' value = "submit"> UPDATE PAYMENT </Button>
      </div>
      
    </Box>
    </div>
    );
}
export default Updatepayments;