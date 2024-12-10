import React from 'react';
import axios from '../api';

const ControlPanel = () => {
    const handleStop = async () => {
        try {
            const response = await axios.post('http://localhost:8080/api/ticketing/stop');
            alert(response.data);
        } catch (error) {
            console.error(error);
            alert('Failed to stop the system.');
        }
    };

    const handleReset = async () => {
        try {
            const response = await axios.post('http://localhost:8080/api/ticketing/reset');
            alert(response.data);
        } catch (error) {
            console.error(error);
            alert('Failed to reset the system.');
        }
    };

    const handleStatus = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/ticketing/status');
            alert(`System Status: ${response.data}`);
        } catch (error) {
            console.error(error);
            alert('Failed to fetch the system status.');
        }
    };

    return (
        <div>
            <button onClick={handleStop}>Stop System</button>
            <button onClick={handleReset}>Reset System</button>
            <button onClick={handleStatus}>Check Status</button>
        </div>
    );
};

export default ControlPanel;
