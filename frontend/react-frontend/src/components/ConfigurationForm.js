import React, { useState } from 'react';
import axios from '../api';

const ConfigurationForm = () => {
    const [formData, setFormData] = useState({
        totalTickets: '30000',
        maxCapacity: '100',
        numVendors: '20',
        vendorRate: '30',
        numCustomers: '20',
        customerRate: '5',
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/api/ticketing/start', formData);
            alert(response.data);
        } catch (error) {
            console.error(error);
            alert('Failed to start the system.');
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            {Object.keys(formData).map((key) => (
                <div key={key}>
                    <label>{key}</label>
                    <input
                        type="number"
                        name={key}
                        value={formData[key]}
                        onChange={handleChange}
                        required
                    />
                </div>
            ))}
            <button type="submit">Start System</button>
        </form>
    );
};

export default ConfigurationForm;