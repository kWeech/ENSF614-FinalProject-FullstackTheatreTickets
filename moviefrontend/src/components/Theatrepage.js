import React from "react";
import {useLocation} from 'react-router-dom';
import {useNavigate} from "react-router-dom";


function Theatrepage() {
    
    const location = useLocation();
    let navigate = useNavigate();


    console.log(location.state);
    return (
      <div>
        {location.state.theatres?.map((theatre, index) => (
        <div key = {index}>
          <button value = {theatre.name} onClick= {() => navigate("/showtime", {state: theatre})}> {theatre.name}</button>
          </div>
       ))}
      </div>
    );
}

export default Theatrepage;