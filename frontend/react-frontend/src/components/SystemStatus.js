import React, { useState, useEffect } from 'react';
import axios from '../api';
import api from "../api";

const SystemStatus = () => {
    const [status, setStatus] = useState('Loading...');

    useEffect(() => {
        const fetchStatus = async () => {
            try {
                const response = await api.get('http://localhost:8080/api/ticketing/status');
                setStatus(response.data);
            } catch (error) {
                console.error(error);
                setStatus('Failed to fetch status');
            }
        };
        fetchStatus();
    }, []);

    return <div>Status: {status}</div>;
};

export default SystemStatus;