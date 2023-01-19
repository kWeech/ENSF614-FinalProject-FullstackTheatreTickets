import React from "react";
import BasicTextFields from "./uicomponents/Logintextfield";
import Button from '@material-ui/core/Button';
import {useNavigate} from "react-router-dom";
import Box from '@mui/material/Box';


function Homepage() {
    let navigate = useNavigate();

    return (
      <div>
        <Box
          sx={{
            '.MuiTextField-root': { m: 1, width: '25ch' },
            '.MuiButton-root' : {m:1 , fullwidth: 'true', disabled:'true'}
          }}
        >
        <BasicTextFields/>
        <Button variant="contained" onClick= {() => navigate("/register")}>Register</Button>
        <br></br>
        <Button variant="contained" onClick= {() => navigate("/movies")}>Continue as Guest</Button>
        </Box>

      </div>
    );
}

export default Homepage;