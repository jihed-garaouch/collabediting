import { Route, Redirect } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
const ProtectedRoutes = ({ component: Component, isAuthenticated, ...rest }) => {
    const navigate = useNavigate();
    return (
        <Route
            {...rest}
            render={(props) =>
                isAuthenticated ? <Component {...props} /> : navigate('/login')
            }
        />
    );
};

export default ProtectedRoutes;