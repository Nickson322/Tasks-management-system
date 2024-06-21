import React, { useState, useEffect } from 'react';
import api from '../services/API';
import './Sprints.css';

const Sprints = () => {
//   const [sprints, setSprints] = useState([]);
//   const [showAddSprintForm, setShowAddSprintForm] = useState(false);
//   const [showAssignTeamForm, setShowAssignTeamForm] = useState(false);
//   const [name, setName] = useState('');
//   const [startDate, setStartDate] = useState('');
//   const [status, setStatus] = useState('');
//   const [editIndex, setEditIndex] = useState(-1);
//   const [editId, setEditId] = useState(null);
//   const [teamName, setTeamName] = useState('');

    const [sprints, setSprints] = useState([]);
    const [showAddSprintForm, setShowAddSprintForm] = useState(false);
    const [showAssignTeamForm, setShowAssignTeamForm] = useState(false);
    const [name, setName] = useState('');
    const [startDate, setStartDate] = useState('');
    const [status, setStatus] = useState('');
    const [teamName, setTeamName] = useState('');
    const [sprintId, setSprintId] = useState('');
    const [editIndex, setEditIndex] = useState(-1);
    const [editId, setEditId] = useState(null);

    const [selectedSprintId, setSelectedSprintId] = useState(null);

  useEffect(() => {
    fetchSprints();
  }, []);

  const fetchSprints = async () => {
    try {
      const response = await api.get('/sprints');
      setSprints(response.data);
    } catch (error) {
      console.error('Error fetching sprints:', error);
    }
  };

  const addSprint = async () => {
    try {
      const response = await api.post('/sprints', { name, startDate, status });
      setSprints([...sprints, response.data]);
      clearForm();
      window.location.reload();
    } catch (error) {
      console.error('Error adding sprint:', error);
    }
  };

  const deleteSprint = async (id) => {
    try {
      await api.delete(`/sprints/${id}`);
      setSprints(sprints.filter(sprint => sprint.id !== id));
    } catch (error) {
      console.error('Error deleting sprint:', error);
    }
  };

  const editSprint = (index) => {
    const sprint = sprints[index];
    setName(sprint.name);
    setStartDate(sprint.startDate);
    setStatus(sprint.status);
    setEditIndex(index);
    setEditId(sprint.id);
    setShowAddSprintForm(true);
  };

  const updateSprint = async () => {
    try {
      const response = await api.put(`/sprints/${editId}`, { name, startDate, status });
      const updatedSprints = [...sprints];
      updatedSprints[editIndex] = response.data;
      setSprints(updatedSprints);
      clearForm();
      window.location.reload();
    } catch (error) {
      console.error('Error updating sprint:', error);
    }
  };

  const assignTeam = async () => {
    try {
      await api.put(`/sprints/${selectedSprintId}/connect`, { teamName });
      fetchSprints();
      clearAssignForm();
    } catch (error) {
      console.error('Error assigning team to sprint:', error);
    }
  };

  const clearAssignForm = () => {
    setSprintId('');
    setTeamName('');
    setShowAssignTeamForm(false);
  };

  const clearForm = () => {
    setName('');
    setStartDate('');
    setStatus('');
    setEditIndex(-1);
    setEditId(null);
    setShowAddSprintForm(false);
  };

  return (
    <div className="sprints">
      <h2>Sprints</h2>
      <div className="button-group">
        <button onClick={() => setShowAddSprintForm(!showAddSprintForm)}>
          {showAddSprintForm ? 'Cancel' : 'Create Sprint'}
        </button>
        <button onClick={() => setShowAssignTeamForm(!showAssignTeamForm)}>
          {showAssignTeamForm ? 'Cancel' : 'Assign Team to Sprint'}
        </button>
      </div>
      {showAddSprintForm && (
        <div className="add-sprint-form">
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            placeholder="Enter sprint name"
          />
          <input
            type="date"
            value={startDate}
            onChange={(e) => setStartDate(e.target.value)}
            placeholder="Enter start date"
          />
          <input
            type="text"
            value={status}
            onChange={(e) => setStatus(e.target.value)}
            placeholder="Enter status"
          />
          {editIndex === -1 ? (
            <button onClick={addSprint}>Add</button>
          ) : (
            <button onClick={updateSprint}>Update</button>
          )}
        </div>
      )}
      {showAssignTeamForm && (
        <div className="assign-team-form"> {/* Измененный код */}
          <select
            value={selectedSprintId || ''}
            onChange={(e) => setSelectedSprintId(e.target.value)}
          >
            <option value="" disabled>Select sprint</option>
            {sprints.map((sprint) => (
              <option key={sprint.id} value={sprint.id}>
                {sprint.name}
              </option>
            ))}
          </select>
          <input
            type="text"
            value={teamName}
            onChange={(e) => setTeamName(e.target.value)}
            placeholder="Enter team name"
          />
          <button onClick={assignTeam}>Assign</button>
        </div>
      )}
      <ul>
        {sprints.map((sprint, index) => (
          <li key={sprint.id}>
            <div className="sprint-info">
              <div>Name: {sprint.name}</div>
              <div>Start Date: {sprint.startDate}</div>
              <div>Status: {sprint.status}</div>
              {sprint.team && <div>Team: {sprint.team.name}</div>} 
            </div>
            <div className="sprint-actions">
              <button onClick={() => editSprint(index)}>Edit</button>
              <button onClick={() => deleteSprint(sprint.id)}>Delete</button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Sprints;
