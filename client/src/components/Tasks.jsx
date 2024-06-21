import React, { useState, useEffect } from 'react';
import api from '../services/API';
import './Tasks.css';

const Tasks = () => {
  const [tasks, setTasks] = useState([]);
  const [showAddTaskForm, setShowAddTaskForm] = useState(false);
  const [name, setName] = useState('');
  const [type, setType] = useState('');
  const [status, setStatus] = useState('');
  const [createdOn, setCreatedOn] = useState('');
  const [description, setDescription] = useState('');
  const [author, setAuthor] = useState('');
  const [authorName, setAuthorName] = useState('');
  const [executorName, setExecutorName] = useState('');
  const [projectName, setProjectName] = useState('');
  const [sprintName, setSprintName] = useState('');
  const [editIndex, setEditIndex] = useState(-1);
  const [editId, setEditId] = useState(null);

  useEffect(() => {
    fetchTasks();
  }, []);

  const fetchTasks = async () => {
    try {
      const response = await api.get('/tasks');
      setTasks(response.data);
    } catch (error) {
      console.error('Error fetching tasks:', error);
    }
  };

  const addTask = async () => {
    try {
      const response = await api.post('/tasks', { name, type, status, createdOn, description, authorName });
      setTasks([...tasks, response.data]);
      clearForm();
      window.location.reload();
    } catch (error) {
      console.error('Error adding task:', error);
    }
  };

  const deleteTask = async (id) => {
    try {
      await api.delete(`/tasks/${id}`);
      setTasks(tasks.filter(task => task.id !== id));
    } catch (error) {
      console.error('Error deleting task:', error);
    }
  };

  const editTask = (index) => {
    const task = tasks[index];
    setName(task.name);
    setType(task.type);
    setStatus(task.status);
    setCreatedOn(task.createdOn);
    setDescription(task.description);
    setAuthorName(task.author.name);
    if(executorName) setExecutorName(task.executor.name);
    if(projectName) setProjectName(task.project.name);
    if(sprintName) setSprintName(task.sprint.name);
    setEditIndex(index);
    setEditId(task.id);
    setShowAddTaskForm(true);
  };

  const updateTask = async () => {
    try {
      const response = await api.put(`/tasks/${editId}`, { name, type, status, createdOn, description, 
                                                            authorName, executorName, projectName, sprintName });
      const updatedTasks = [...tasks];
      updatedTasks[editIndex] = response.data;
      setTasks(updatedTasks);
      clearForm();
      window.location.reload();
    } catch (error) {
      console.error('Error updating task:', error);
    }
  };

  const clearForm = () => {
    setName('');
    setType('');
    setStatus('');
    setCreatedOn('');
    setDescription('');
    setAuthorName('');
    setExecutorName('');
    setProjectName('');
    setSprintName('');
    setEditIndex(-1);
    setEditId(null);
    setShowAddTaskForm(false);
  };

  return (
    <div className="tasks">
      <h2>Tasks</h2>
      <div className="button-group">
        <button onClick={() => setShowAddTaskForm(!showAddTaskForm)}>
          {showAddTaskForm ? 'Cancel' : 'Create Task'}
        </button>
      </div>
      {showAddTaskForm && (
        <div className="add-task-form">
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            placeholder="Enter task name"
          />
          <input
            type="text"
            value={type}
            onChange={(e) => setType(e.target.value)}
            placeholder="Enter task type"
          />
          <input
            type="text"
            value={status}
            onChange={(e) => setStatus(e.target.value)}
            placeholder="Enter task status"
          />
          <input
            type="date"
            value={createdOn}
            onChange={(e) => setCreatedOn(e.target.value)}
            placeholder="Enter creation date"
          />
          <textarea
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            placeholder="Enter task description"
          ></textarea>
          <input
            type="text"
            value={authorName}
            onChange={(e) => setAuthorName(e.target.value)}
            placeholder="Enter author"
          />
          {editIndex !== -1 && (
            <>
              <input
                type="text"
                value={executorName}
                onChange={(e) => setExecutorName(e.target.value)}
                placeholder="Enter executor"
              />
              <input
                type="text"
                value={projectName}
                onChange={(e) => setProjectName(e.target.value)}
                placeholder="Enter project"
              />
              <input
                type="text"
                value={sprintName}
                onChange={(e) => setSprintName(e.target.value)}
                placeholder="Enter sprint"
              />
              <button onClick={updateTask}>Update</button>
            </>
          )}
          {editIndex === -1 && <button onClick={addTask}>Add</button>}
        </div>
      )}
      <ul>
        {tasks.map((task, index) => (
          <li key={task.id}>
            <div className="task-info">
              <div>Name: {task.name}</div>
              <div>Type: {task.type}</div>
              <div>Status: {task.status}</div>
              <div>Created On: {task.createdOn}</div>
              <div>Description: {task.description}</div>
              {task.author && <div>Author: {task.author.name}</div>}
              {task.executor && <div>Executor: {task.executor.name}</div>} 
              {task.project && <div>Project: {task.project.name}</div>} 
              {task.sprint && <div>Sprint: {task.sprint.name}</div>} 
            </div>
            <div className="task-actions">
              <button onClick={() => editTask(index)}>Edit</button>
              <button onClick={() => deleteTask(task.id)}>Delete</button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Tasks;
