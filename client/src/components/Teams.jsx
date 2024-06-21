import React, { useState, useEffect } from 'react';
import api from '../services/API';
import './Teams.css';

const Teams = () => {
  const [teams, setTeams] = useState([]);
  const [showAddTeamForm, setShowAddTeamForm] = useState(false);
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [editIndex, setEditIndex] = useState(-1);
  const [editId, setEditId] = useState(null);

  useEffect(() => {
    fetchTeams();
  }, []);

  const fetchTeams = async () => {
    try {
      const response = await api.get('/teams');
      setTeams(response.data);
    } catch (error) {
      console.error('Error fetching teams:', error);
    }
  };

  const addTeam = async () => {
    try {
      const response = await api.post('/teams', { name, description });
      setTeams([...teams, response.data]);
      clearForm();
      window.location.reload();
    } catch (error) {
      console.error('Error adding team:', error);
    }
  };

  const deleteTeam = async (id) => {
    try {
      await api.delete(`/teams/${id}`);
      setTeams(teams.filter(team => team.id !== id));
    } catch (error) {
      console.error('Error deleting team:', error);
    }
  };

  const editTeam = (index) => {
    const team = teams[index];
    setName(team.name);
    setDescription(team.description);
    setEditIndex(index);
    setEditId(team.id);
    setShowAddTeamForm(true);
  };

  const updateTeam = async () => {
    try {
      const response = await api.put(`/teams/${editId}`, { name, description });
      const updatedTeams = [...teams];
      updatedTeams[editIndex] = response.data;
      console.log(updatedTeams[editIndex]);
      setTeams(updatedTeams);
      clearForm();
      window.location.reload();
    } catch (error) {
      console.error('Error updating team:', error);
    }
  };

  const clearForm = () => {
    setName('');
    setDescription('');
    setEditIndex(-1);
    setEditId(null);
    setShowAddTeamForm(false);
  };

  return (
    <div className="teams">
      <h2>Teams</h2>
      <button onClick={() => setShowAddTeamForm(!showAddTeamForm)}>
        {showAddTeamForm ? 'Cancel' : 'Create Team'}
      </button>
      {showAddTeamForm && (
        <div className="add-team-form">
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            placeholder="Enter team name"
          />
          <input
            type="text"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            placeholder="Enter team description"
          />
          {editIndex === -1 ? (
            <button onClick={addTeam}>Add</button>
          ) : (
            <button onClick={updateTeam}>Update</button>
          )}
        </div>
      )}
      <ul>
        {teams.map((team, index) => (
          <li key={team.id}>
            <div className="team-info"> {/* Изменено: добавлена обертка для информации о команде */}
              <div>Name: {team.name}</div>
              <div>Description: {team.description}</div>
            </div>
            <div className="team-actions"> {/* Изменено: добавлена обертка для кнопок действий */}
              <button onClick={() => editTeam(index)}>Edit</button>
              <button onClick={() => deleteTeam(team.id)}>Delete</button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Teams;
