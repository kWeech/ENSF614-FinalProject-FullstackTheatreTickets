import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import {Button} from '@mui/material';
import { useState } from "react";
import {useLocation, useNavigate} from 'react-router-dom';



export default function FormPropsTextFields() {
  let navigate = useNavigate();

  const location = useLocation();
  console.log(location.state.id);
  var namevar = "";
  var number = "";
  var exp = "";
  var code= "";
  var email = "";
  var add = "";
  var cityvar = "";
  var prov = "";
  var postal = "";
  
  if(sessionStorage.getItem("user") !== "undefined" && sessionStorage.getItem("user") !== null) {
    var user = JSON.parse(sessionStorage.getItem("user"));
    console.log(user);
    namevar = user.paymentInfo.cardHolderName;
    number = user.paymentInfo.cardNumber;
    exp = user.paymentInfo.expiryDate;
    code= user.paymentInfo.cvc;
    email = user.paymentInfo.emailAddress;
    add = user.paymentInfo.address;
    cityvar = user.paymentInfo.city;
    prov = user.paymentInfo.province;
    postal = user.paymentInfo.postalCode;
  } else {
    var user = {"username": "Guest", "id": 0};
    console.log(user)
  }

  const [cardHolderName, setName] = useState(namevar);
  const [cardNumber, setCardNumber] = useState(number);
  const [expiryDate, setExpirtyDate] = useState(exp);
  const [cvc, setCVC] = useState(code);
  const [emailAddress, setEmailAddress] = useState(email);
  const [address, setAddress] = useState(add);
  const [city, setCity] = useState(cityvar);
  const [province, setProvince] = useState(prov);
  const [postalCode, setPostalCode] = useState(postal);
  const [voucher, setVoucher] = useState();
  const [price, setPrice] = useState(location.state.price);

  

  const handleSubmit = (evt) => {
    evt.preventDefault();
    console.log("Hello")

    if(user.id) {
      console.log("fetching for registered user");
      fetch('http://localhost:8080/ticket/add/'+ user.id + "/" + location.state.id, requestOptions)
      .then((response) => {
        console.log(requestOptions);
        if (response.ok){
          alert("Payment has been made, you will not be redirected to movies page!")
          navigate('/movies')
        } else {
          response.json()
          .then((data) => alert(data.message))
        }})

    } else {
      console.log("fetching for guest user");
      fetch('http://localhost:8080/ticket/add/'+ location.state.id, requestOptions)
      .then((response) => {
        console.log(requestOptions);
        if (response.ok){
          alert("Payment has been made, you will not be redirected to home page!")
          navigate('/')
        } else {
          response.json()
          .then((data) => alert(data.message))
        }})
    }
  }

  function applyVoucher() {
    fetch('http://localhost:8080/voucher/'+ voucher)
    .then((response) => {
      console.log(response);
      if (response.ok){
        alert("Voucher has been applied")
        response.json()
        .then((data) => {
          console.log(data.voucherAmount);
          if (data.voucherAmount >= price) {
            setPrice(0);
          } else {
            setPrice((location.state.price - data.voucherAmount));
          }
        })
      } else {
        response.json()
        .then((data) => alert(data.message))
      }})
  }


  const requestOptions = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({cardHolderName, cardNumber, expiryDate, cvc, emailAddress, address, city, province, postalCode})
  };

  return (
    <div>
    <br></br>
    Your total price for ticket is: 
    <br></br>
    <br></br>
    ${price}
    <br></br>
    <br></br>
    Please input your information
    <br></br>
    <br></br>

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
          {...(user.id? {disabled: true}: {disabled:false})}
          {...(user.id? {label: (user.paymentInfo.cardHolderName)}: {label:"Name"})}

        />

        <TextField
          required
          id="outlined-required"
          value = {address}
          onChange={(e) => setAddress(e.target.value)}
          {...(user.id? {disabled: true}: {disabled:false})}
          {...(user.id? {label: (user.paymentInfo.address)}: {label:"Address"})}
        />
        <TextField
          required
          id="outlined-required"
          value = {city}
          onChange={(e) => setCity(e.target.value)}
          {...(user.id? {disabled: true}: {disabled:false})}
          {...(user.id? {label: (user.paymentInfo.city)}: {label:"City"})}

        />
        <TextField
          required
          id="outlined-required"
          value = {province}
          onChange={(e) => setProvince(e.target.value)}
          {...(user.id? {disabled: true}: {disabled:false})}
          {...(user.id? {label: (user.paymentInfo.province)}: {label:"Province"})}

        />
        <TextField
          required
          id="outlined-required"
          value = {postalCode}
          onChange={(e) => setPostalCode(e.target.value)}
          {...(user.id? {disabled: true}: {disabled:false})}
          {...(user.id? {label: (user.paymentInfo.postalCode)}: {label:"Postal Code"})}

        />

        <TextField
          required
          id="outlined-required"
          value = {cardNumber}
          onChange={(e) => setCardNumber(e.target.value)}
          {...(user.id? {disabled: true}: {disabled:false})}
          {...(user.id? {label: (user.paymentInfo.cardNumber)}: {label:"Credit Card Number"})}

        />

        <TextField
          required
          id="outlined-required"
          value = {expiryDate}
          onChange={(e) => setExpirtyDate(e.target.value)}
          {...(user.id? {disabled: true}: {disabled:false})}
          {...(user.id? {label: (user.paymentInfo.expiryDate)}: {label:"Expiry"})}

        />

        <TextField
          required
          id="outlined-required"
          value = {cvc}
          onChange={(e) => setCVC(e.target.value)}
          {...(user.id? {disabled: true}: {disabled:false})}
          {...(user.id? {label: (user.paymentInfo.cvc)}: {label:"CVC"})}

        />

        <TextField
          required
          id="outlined-required"
          value = {emailAddress}
          onChange={(e) => setEmailAddress(e.target.value)}
          {...(user.id? {disabled: true}: {disabled:false})}
          {...(user.id? {label: (user.paymentInfo.emailAddress)}: {label:"Email Address"})}

        />
        <TextField
          id="outlined-required"
          label="Voucher"
          value = {voucher}
          onChange={(e) => setVoucher(e.target.value)}
        />

        <br></br>
        <Button version='secondary' variant = 'contained' onClick={() => applyVoucher()}> APPLY VOUCHER </Button>
        <br></br>
        <Button style= {{width: '25px'}} type='submit' version='primary' variant = 'contained' value = "submit"> PAY </Button>
      </div>
      
    </Box>
    </div>
  );
}
