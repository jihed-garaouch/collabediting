import { useState } from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import SignIn from './components/SignIn';
import CodeEditor from './components/CodeEditor';

import {createTheme, ThemeProvider} from "@mui/material/styles";
import Home from "./pages/Home";

const App = () => {
    const [isAuthenticated, setIsAuthenticated] = useState(true);

    const darkTheme = createTheme({
        palette: {
            mode: 'dark',

        },
    });

    return (
        <ThemeProvider theme={darkTheme}>
        <Router>
            <Routes>
                <Route path="/login" element={<SignIn />} />
                <Route path="/" element={isAuthenticated ? <Home/> : <Navigate to="/login" replace />} />
            </Routes>
        </Router>
        </ThemeProvider>
    );
};

export default App;
