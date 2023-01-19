import React from "react";
import {useLocation} from 'react-router-dom';
import {useNavigate} from "react-router-dom";
import {Button} from '@mui/material';
import Box from '@mui/material/Box';



function Seatspage() {
    
    const location = useLocation();
    let navigate = useNavigate();

    const myData = [].concat(location.state.seats)
        .sort((a, b) => a.id > b.id ? 1 : -1)
      
    const filteredA = myData.filter(data => {return data.rowLetter === 'A'})
    const filteredB = myData.filter(data => {return data.rowLetter=== 'B'})
    const filteredC = myData.filter(data => {return data.rowLetter === 'C'})
    const filteredD = myData.filter(data => {return data.rowLetter === 'D'})



    console.log(filteredA);
    return (
      <>
        <Box textAlign='center'>
          <br></br>
          <br></br>
          <Button style= {{width: '500px'}} variant="contained" disabled = "true" >Screen</Button>
          <br></br>
          <br></br>
        </Box>

        <table style = {{margin: "auto"}}>

        <tbody>
          <tr>
          </tr>
          <tr>
              {filteredA?.map((seat, index) => (
                <td key = {index}>
                  <div>
                    <button value = {seat} disabled = {seat.booked} onClick= {() => navigate("/payment", {state: seat})}> {seat.rowLetter} {seat.seatNum} </button>
                  </div>
                </td>

              ))}
          </tr>
          <tr>
              {filteredB?.map((seat, index) => (
                <td key = {index}>
                    <button value = {seat} disabled = {seat.booked} onClick= {() => navigate("/payment", {state: seat})}> {seat.rowLetter} {seat.seatNum} </button>
                </td>

              ))}
          </tr>
          <tr>
              {filteredC?.map((seat, index) => (
                <td key = {index}>
                    <button value = {seat} disabled = {seat.booked} onClick= {() => navigate("/payment", {state: seat})}> {seat.rowLetter} {seat.seatNum}</button>
                </td>

              ))}
          </tr>
          <tr>
              {filteredD?.map((seat, index) => (
                <td key = {index}>
                    <button value = {seat} disabled = {seat.booked} onClick= {() => navigate("/payment", {state: seat})}> {seat.rowLetter} {seat.seatNum}</button>
                </td>

              ))}
          </tr>


            

        {/* {myData.map((seat, index) => (
        <div key = {index}>
          <button value = {seat.id} disabled = {seat.booked} onClick= {() => navigate("/seats", {state: seat})}> {seat.seatNum} {seat.rowNum}</button>
        </div>
       ))} */}

        </tbody> 
        </table>
        </>  
    );
}

export default Seatspage;