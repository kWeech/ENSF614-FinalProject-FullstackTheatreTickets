
import React ,{useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";


function Moviespage() {

  const current = new Date();
  const todaysDate = `${current.getFullYear()}-${current.getMonth()+1}-${current.getDate()}`;

  if(sessionStorage.getItem("user") !== null) {
    var user = JSON.parse(sessionStorage.getItem("user"));
    console.log(user);
  } else {
    var user = {"username": "Guest", "id": 0};
    console.log(user)
  }

  let navigate = useNavigate();

  const[searchTerm,setSearchTerm]=useState('')
  const[movies, setMovies] = useState([]);

  var filteredMovies = movies


  if (!user.id) {
    filteredMovies = movies.filter(data => {return todaysDate > data.releaseDate})
  } else {
    filteredMovies = movies.filter(data => {return todaysDate > data.preReleaseDate})
  }


  const fetchData = () => {

    fetch('http://localhost:8080/movie')
    .then((response) => response.json())
    .then((actualdata) => {
      console.log(actualdata);
      setMovies(actualdata);
    })
  }
  
  useEffect(() =>{
    fetchData();
  }, [])

  
  return (
      <div className = "App">

      <input type="text" placeholder="Search: "onChange={event=>{setSearchTerm(event.target.value)}}/>


      {filteredMovies.filter((movie)=>{
        if(searchTerm===""){
          return movie
        }
        else if(movie.title.toLowerCase().includes(searchTerm.toLowerCase())){
          return movie
      }})
      .map((movie, index) => (
        <div key = {index}>
          <button value = {movie.title} onClick= {() => navigate("/theatre", {state: movie})}> {movie.title}</button> 
        </div>
       ))}

      </div>
  );
}

export default Moviespage;

