import React from 'react';
import ConfigurationForm from './components/ConfigurationForm';
import SystemStatus from "./components/SystemStatus";
import ControlPanel from './components/ControlPanel';
// import './App.css';

const App = () => (
    <div>
      <h1>Real-Time Event Ticketing</h1>
      <ConfigurationForm />
      <SystemStatus />
      <ControlPanel />
    </div>
);

// function App() {
//   return (
//     <div className="App">
//       <header className="App-header">
//         <img src={logo} className="App-logo" alt="logo" />
//         <p>
//           Edit <code>src/App.js</code> and save to reload.
//         </p>
//         <a
//           className="App-link"
//           href="https://reactjs.org"
//           target="_blank"
//           rel="noopener noreferrer"
//         >
//           Learn React
//         </a>
//       </header>
//     </div>
//   );
// }

export default App;
