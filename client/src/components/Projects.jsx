import React, { useState, useEffect } from 'react';
import api from '../services/API';
import './Projects.css';

const Projects = () => {
  const [projects, setProjects] = useState([]);
  const [showAddProjectForm, setShowAddProjectForm] = useState(false);
  const [showAssignTeamForm, setShowAssignTeamForm] = useState(false);
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [projectId, setProjectId] = useState('');
  const [teamName, setTeamName] = useState('');
  const [editIndex, setEditIndex] = useState(-1);
  const [editId, setEditId] = useState(null);

  const [selectedProjectId, setSelectedProjectId] = useState(null);

  useEffect(() => {
    fetchProjects();
  }, []);

  const fetchProjects = async () => {
    try {
      const response = await api.get('/projects');
      setProjects(response.data);
    } catch (error) {
      console.error('Error fetching projects:', error);
    }
  };

  const addProject = async () => {
    try {
      const response = await api.post('/projects', { name, description });
      setProjects([...projects, response.data]);
      clearForm();
      window.location.reload();
    } catch (error) {
      console.error('Error adding project:', error);
    }
  };

  const deleteProject = async (id) => {
    try {
      await api.delete(`/projects/${id}`);
      setProjects(projects.filter(project => project.id !== id));
    } catch (error) {
      console.error('Error deleting project:', error);
    }
  };

  const editProject = (index) => {
    const project = projects[index];
    setName(project.name);
    setDescription(project.description);
    setEditIndex(index);
    setEditId(project.id);
    setShowAddProjectForm(true);
  };

  const updateProject = async () => {
    try {
      const response = await api.put(`/projects/${editId}`, { name, description });
      const updatedProjects = [...projects];
      updatedProjects[editIndex] = response.data;
      setProjects(updatedProjects);
      clearForm();
      window.location.reload();
    } catch (error) {
      console.error('Error updating project:', error);
    }
  };

  const assignTeamToProject = async () => {
    try {
      await api.put(`/projects/${selectedProjectId}/connect`, { teamName });
      fetchProjects(); // Обновляем список проектов после присоединения команды
      clearAssignForm();
    } catch (error) {
      console.error('Error assigning team to project:', error);
    }
  };

  const clearForm = () => {
    setName('');
    setDescription('');
    setEditIndex(-1);
    setEditId(null);
    setShowAddProjectForm(false);
  };

  const clearAssignForm = () => {
    setProjectId('');
    setTeamName('');
    setShowAssignTeamForm(false);
  };

  return (
    <div className="projects">
      <h2>Projects</h2>
      <div className="button-group">
        <button onClick={() => setShowAddProjectForm(!showAddProjectForm)}>
          {showAddProjectForm ? 'Cancel' : 'Create Project'}
        </button>
        <button onClick={() => setShowAssignTeamForm(!showAssignTeamForm)}>
          {showAssignTeamForm ? 'Cancel' : 'Assign Team to Project'}
        </button>
      </div>
      {showAddProjectForm && (
        <div className="add-project-form">
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            placeholder="Enter project name"
          />
          <textarea
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            placeholder="Enter project description"
          />
          {editIndex === -1 ? (
            <button onClick={addProject}>Add</button>
          ) : (
            <button onClick={updateProject}>Update</button>
          )}
        </div>
      )}
      {showAssignTeamForm && (
        <div className="assign-team-form">
          <select
            value={selectedProjectId || ''}
            onChange={(e) => setSelectedProjectId(e.target.value)}
          >
            <option value="" disabled>Select project</option>
            {projects.map((project) => (
              <option key={project.id} value={project.id}>
                {project.name}
              </option>
            ))}
          </select>
          <input
            type="text"
            value={teamName}
            onChange={(e) => setTeamName(e.target.value)}
            placeholder="Enter team name"
          />
          <button onClick={assignTeamToProject}>Assign</button>
        </div>
      )}
      <ul>
        {projects.map((project, index) => (
          <li key={project.id}>
            <div className="project-info"> {/* Изменено: добавлена обертка для информации о проекте */}
              <div>Name: {project.name}</div>
              <div>Description: {project.description}</div>
              {project.team && <div>Team: {project.team.name}</div>}
            </div>
            <div className="project-actions"> {/* Изменено: добавлена обертка для кнопок действий */}
              <button onClick={() => editProject(index)}>Edit</button>
              <button onClick={() => deleteProject(project.id)}>Delete</button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Projects;
