import React from "react";
import {useLocation} from 'react-router-dom';
import Paymenttextfields from "./uicomponents/Paymenttextfields";

function Paymentpage() {
    
    const location = useLocation();

    console.log(location.state);
    return (
      <div style = {{margin: "10px"}}>

        <Paymenttextfields/>
        
      </div>
    );
}

export default Paymentpage;