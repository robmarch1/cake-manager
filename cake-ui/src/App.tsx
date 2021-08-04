import React from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import { Header } from './components/Header';
import { Home } from './pages/Home';
import { AllCakes } from './pages/AllCakes';
import { Cake } from './pages/Cake';
import './App.css';

export default function App() {
  return (
    <Router>
      <Header />
      <Switch>
        <Route path='/cakes/:slug' component={Cake} />
        <Route path="/cakes" exact>
          <AllCakes />
        </Route>
        <Route path="/" exact>
          <Home />
        </Route>
      </Switch>
    </Router>
  );
}

