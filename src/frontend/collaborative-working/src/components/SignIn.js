import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import Link from '@mui/material/Link';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import GitHubIcon from '@mui/icons-material/GitHub';
import Typography from '@mui/material/Typography';
import {login} from "../services/authService";
function Copyright(props) {
    return (
        <Typography variant="body2" color="text.secondary" align="center" {...props}>
            {'Copyright © '}
            <Link color="inherit">
                CollabEditor
            </Link>{' '}
            {new Date().getFullYear()}
            {'.'}
        </Typography>
    );
}

export default function SignIn({ onLogin }) {
    return (
        <Grid container component="main" sx={{ height: '100vh' }}>
            <CssBaseline />
            <Grid
                item
                xs={false}
                sm={4}
                md={7}
                sx={{
                    backgroundImage: 'url("https://images.unsplash.com/photo-1522098726370-3e356d42b9c8?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxNzg2NzB8MHwxfGFsbHwxfHx8fHx8fHwxNjIxNzAxNDA4&ixlib=rb-1.2.1&q=80&w=400")',
                    backgroundColor: (t) =>
                        t.palette.mode === 'light' ? t.palette.grey[50] : t.palette.grey[900],
                    backgroundSize: 'cover',
                    backgroundPosition: 'center',
                }}
            />
            <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square>
                <Box
                    sx={{
                        my: 22,
                        mx: 4,
                        display: 'flex',
                        flexDirection: 'column',
                        justifyContent: 'center',
                        alignItems: 'center',
                    }}
                >
                    <Avatar sx={{ m: 1 }}>
                        <GitHubIcon />
                    </Avatar>
                    <Typography component="h1" variant="h5">
                        Sign in With Your Github
                    </Typography>
                    <Box sx={{ mt: 1 }}>
                        <Button
                            fullWidth
                            onClick={ ()=>login()}
                            variant="contained"
                            sx={{ mt: 3, mb: 2, bgcolor: 'primary' }}
                        >
                            Sign In
                        </Button>
                        <Copyright sx={{ mt: 5 }} />
                    </Box>
                </Box>
            </Grid>
        </Grid>
    );
}
