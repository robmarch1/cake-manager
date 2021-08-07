import React from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
} from "react-router-dom";
import { Header } from './components/Header';
import { Home } from './pages/Home';
import { AllCakes } from './pages/AllCakes';
import { CakePage } from './pages/Cake';
import { NewCake } from './pages/NewCake';
import './App.css';

export default function App() {
  return (
    <Router>
      <Header />
      <Switch>
        <Route path='/cakes/:slug' component={() => (<CakePage key={Date.now()} />)} />
        <Route path="/new-cake" exact>
          <NewCake />
        </Route>
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

