import './App.css';

import {Routes, Route} from 'react-router-dom';

//import { NavLink, Switch} from 'react-router-dom';

import Appbar from './components/uicomponents/Appbar';

import Moviespage from './components/Moviespage';
import Homepage from './components/Homepage';
import Theatrepage from './components/Theatrepage';
import Showtimepage from './components/Showtimepage';
import Seatspage from './components/Seatspage';
import Paymentpage from './components/Paymentpage';
import Registrationpage from './components/Registrationpage';
import Accountpage from './components/Accountpage';
import Updatepayments from './components/Updatepayments';

function App() {
  return (
    //<div className="App">
      //<Router>
      <>
        <Appbar/>

        <Routes>

          <Route path = "/" element = {<Homepage />} />
          <Route path = "/movies" element = {<Moviespage />} />
          <Route path = "/theatre" element = {<Theatrepage/>}/>
          <Route path = "/showtime" element = {<Showtimepage/>}/>
          <Route path = "/seats" element = {<Seatspage/>}/>
          <Route path = "/payment" element = {<Paymentpage/>}/>
          <Route path = "/register" element = {<Registrationpage/>}/>
          <Route path = "/account" element = {<Accountpage/>}/>
          <Route path = "/updatepayment" element = {<Updatepayments/>}/>

        </Routes>
      {/* //</Router> */}
    {/* //</div> */}
    </>
  );
}


export default App;
