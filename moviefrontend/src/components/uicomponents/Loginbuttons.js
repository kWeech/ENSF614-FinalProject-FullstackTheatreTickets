import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import {useNavigate} from "react-router-dom";



const useStyles = makeStyles((theme) => ({
  root: {
    '& > *': {
      margin: theme.spacing(1),
    },
  },
}));

export default function GuestButton() {
  const classes = useStyles();

  let navigate = useNavigate();

  return (
    <div className={classes.root}>
      <Button variant="contained" onClick= {() => navigate("/movies")}>Login</Button>
      <Button variant="contained" onClick= {() => navigate("/movies")}>Guest</Button>
    </div>
  );
}
