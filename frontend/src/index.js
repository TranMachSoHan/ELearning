import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import GlobalWrapper from './layouts/StudentLayout';
import { BrowserRouter } from 'react-router-dom';


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      {/* <GlobalWrapper>
        <App />
      </GlobalWrapper> */}

      <App></App>
    
    </BrowserRouter> 
  </React.StrictMode>
);
