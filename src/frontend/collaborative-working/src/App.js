import { useState } from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import SignIn from './components/SignIn';
import CodeEditor from './components/CodeEditor';
import SignUp from "./components/SignUp";

const App = () => {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const handleLogin = (token) => {

        setIsAuthenticated(true);
        localStorage.setItem('token', token);
    };

    return (
        <Router>
            <Routes>
                <Route path="/login" element={<SignIn onLogin={handleLogin} />} />
                <Route path="/signup" element={<SignUp onLogin={handleLogin} />} />
                <Route path="/" element={isAuthenticated ? <CodeEditor /> : <Navigate to="/login" replace />} />
            </Routes>
        </Router>
    );
};

export default App;
