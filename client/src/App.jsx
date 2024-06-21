import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import Users from './components/Users';
import Teams from './components/Teams';
import Projects from './components/Projects';
import Sprints from './components/Sprints';
import Tasks from './components/Tasks';
import './App.css';

function App() {
  return (
    <Router>
      <div className="App">
        <nav className="navbar">
          <ul>
            <li><Link to="/users">Users</Link></li>
            <li><Link to="/teams">Teams</Link></li>
            <li><Link to="/projects">Projects</Link></li>
            <li><Link to="/sprints">Sprints</Link></li>
            <li><Link to="/tasks">Tasks</Link></li>
          </ul>
        </nav>
        <Routes>
          <Route path="/users" element={<Users />} />
          <Route path="/teams" element={<Teams />} />
          <Route path="/projects" element={<Projects />} />
          <Route path="/sprints" element={<Sprints />} />
          <Route path="/tasks" element={<Tasks />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
