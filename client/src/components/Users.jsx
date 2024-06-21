import React, { useEffect, useState } from 'react';
import api from '../services/API';
import './Users.css';

const Users = () => {
  const [users, setUsers] = useState([]);
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [userRole, setUserRole] = useState('');
  const [editIndex, setEditIndex] = useState(-1);
  const [editId, setEditId] = useState(null);

  const [teamName, setTeamName] = useState(''); 
  const [showAddUserForm, setShowAddUserForm] = useState(false);
  const [showAssignTeamForm, setShowAssignTeamForm] = useState(false); 
  const [selectedUserId, setSelectedUserId] = useState(null);

  useEffect(() => {
    fetchUsers();
  }, []);

  const fetchUsers = async () => {
    try {
        const response = await api.get('/users');
        setUsers(response.data);
    } catch(error) {
        console.error('Error fetching users:', error);
    }
  };

  const addUser = async () => {
    try {
        const response = await api.post('/users', { name, password, email, userRole });
        setUsers([...users, response.data]);
        clearForm();
        window.location.reload();
      } catch (error) {
        console.error('Error adding user:', error);
      }
  };

  const deleteUser = async (id) => {
    try {
        await api.delete(`/users/${id}`);
        setUsers(users.filter(user => user.id !== id));
      } catch (error) {
        console.error('Error deleting user:', error);
      }
  };

  const editUser = (index) => {
    const user = users[index];
    setName(user.name);
    setEmail(user.email);
    setPassword(user.password);
    setUserRole(user.userRole);
    setEditIndex(index);
    setEditId(user.id);
    setShowAddUserForm(true);
  };

  const updateUser = async () => {
    try {
        const response = await api.put(`/users/${editId}`, { name, email, userRole });
        const updatedUsers = [...users];
        updatedUsers[editIndex] = response.data;
        setUsers(updatedUsers);
        clearForm();
        window.location.reload();
      } catch (error) {
        console.error('Error updating user:', error);
      }
  };

  const assignUserToTeam = async () => {
    try {
      await api.put(`/users/${selectedUserId}/connect`, { teamName });
      fetchUsers(); 
      clearAssignForm();
    } catch (error) {
      console.error('Error assigning user to team:', error);
    }
  };


  const clearForm = () => {
    setName('');
    setEmail('');
    setPassword('');
    setUserRole('');
    setEditIndex(-1);
    setEditId(null);
    setShowAddUserForm(false);
  };

  const clearAssignForm = () => {
    setTeamName('');
    setSelectedUserId(null);
    setShowAssignTeamForm(false);
  };

  return (
    <div className="users">
      <h2>Users</h2>
      <div className="button-group">
        <button onClick={() => setShowAddUserForm(!showAddUserForm)}>
          {showAddUserForm ? 'Cancel' : 'Create User'}
        </button>
        <button onClick={() => setShowAssignTeamForm(!showAssignTeamForm)}>
          {showAssignTeamForm ? 'Cancel' : 'Assign to Team'}
        </button>
      </div>
      {showAddUserForm && (
        <div className="add-user-form">
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            placeholder="Enter name"
          />
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            placeholder="Enter email"
          />
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Enter password"
          />
          <input
            type="userRole"
            value={userRole}
            onChange={(e) => setUserRole(e.target.value)}
            placeholder="Enter user role"
          />
          {editIndex === -1 ? (
            <button onClick={addUser}>Add</button>
          ) : (
            <button onClick={updateUser}>Update</button>
          )}
        </div>
      )}
      {showAssignTeamForm && (
        <div className="assign-team-form"> 
          <select
            value={selectedUserId || ''}
            onChange={(e) => setSelectedUserId(e.target.value)}
          >
            <option value="" disabled>Select user</option>
            {users.map((user) => (
              <option key={user.id} value={user.id}>
                {user.name}
              </option>
            ))}
          </select>
          <input
            type="text"
            value={teamName}
            onChange={(e) => setTeamName(e.target.value)}
            placeholder="Enter team name"
          />
          <button onClick={assignUserToTeam}>Assign</button>
        </div>
      )}
      <ul>
        {users.map((user, index) => (
          <li key={user.id}>
            <div className="user-info">
              <div>Name: {user.name}</div>
              <div>Email: {user.email}</div>
              <div>Role: {user.userRole}</div>
              {user.team && <div>Team: {user.team.name}</div>} 
            </div>
            <div className="user-actions">
              <button onClick={() => editUser(index)}>Edit</button>
              <button onClick={() => deleteUser(user.id)}>Delete</button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Users;
