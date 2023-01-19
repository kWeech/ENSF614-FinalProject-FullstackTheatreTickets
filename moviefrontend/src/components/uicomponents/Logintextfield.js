import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import {useState} from "react";
import {Button} from '@mui/material';
import { useNavigate } from 'react-router-dom';




export default function BasicTextFields() {
  let navigate = useNavigate();
  const[user, setUser] = useState()
  const[username,setUsername] = useState("");
  const[password,setPassword] = useState("");

  const handleSubmit=(evt)=>{
    evt.preventDefault();
    console.log(requestOptions)

    fetch('http://localhost:8080/registereduser/login',requestOptions)
    .then((response)=> {
      if (response.ok) {
        //console.log(response.json())
        response.json().then((data) => {
          setUser(data);
          sessionStorage.setItem("user", JSON.stringify(data))
          console.log(data)
        })
        navigate("/movies", {state: response.json()})
      } else {
        response.json().then(data => alert(data.message))
      }
    })
  }


  const requestOptions = {
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
      {/* <TextField id="username" label="Username" variant="outlined" />
      <TextField id="password" label="Password" variant="outlined" /> */}

      <TextField
          required
          id="outlined-required"
          label="Username"
          value = {username}
          onChange={(e) => setUsername(e.target.value)}
        />

      <TextField
          required
          id="outlined-required"
          label="Password"
          value = {password}
          onChange={(e) => setPassword(e.target.value)}
        />
      
      <br></br>
      <Button style= {{width: '25px'}} type='submit' version='primary' variant = 'contained' value = "submit"> LOGIN </Button>

    </Box>
  );
}
