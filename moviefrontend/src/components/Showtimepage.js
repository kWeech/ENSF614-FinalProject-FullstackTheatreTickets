import React from "react";
import {useLocation} from 'react-router-dom';
import {useNavigate} from "react-router-dom";


function Showtimepage() {
    
    const location = useLocation();
    let navigate = useNavigate();


    console.log(location.state.showtimes);
    return (
      <div>
        {location.state.showtimes?.map((showtime, index) => (
        <div key = {index}>
          <button value = {showtime.time} onClick= {() => navigate("/seats", {state: showtime})}> {showtime.time}</button>
        </div>
       ))}
      </div>
    );
}

export default Showtimepage;