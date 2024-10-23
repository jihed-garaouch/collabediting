import FileTreeView from "../components/FileTree";
import MonacoEditor from "react-monaco-editor";
import React from "react";
import Grid from "@mui/material/Grid";
import Header from "../components/Header";
import {Divider} from "@mui/material";
import CodeEditor from "../components/CodeEditor";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import AppBar from "@mui/material/AppBar";

const HomePage = ({ code, language }) => {
    return (
        <>
<Header/>
            <Box sx={{ height: 'calc(100vh - 64px)', display: 'flex' }}>
                <Grid container sx={{ height: 400 }}>
                    <Grid item xs={2} sx={{ display: 'flex', flexDirection: 'column' }}>
                        <AppBar position="static" sx={{ zIndex: 0, height: '10' }}>
                            <Toolbar>
                                <Typography variant="h6" noWrap>
                                    Project
                                </Typography>
                            </Toolbar>
                        </AppBar>
                        <Box sx={{ flexGrow: 1, overflow: 'auto' }}>
                            <FileTreeView />
                        </Box>

                        <AppBar position="static" sx={{ zIndex: 0, height: 'auto' }}>
                            <Toolbar>
                                <Typography variant="h6" noWrap>
                                    Allowed User
                                </Typography>
                            </Toolbar>
                        </AppBar>
                        <Box sx={{ flexGrow: 1, overflow: 'auto' }}>
                            {/* Allowed user content can go here */}
                        </Box>
                    </Grid>

                    <Grid item   xs={10} sx={{ height: '100%' }}>
                        <CodeEditor
                            code={code}
                            language={language}
                            height="100%" // Ensuring the editor takes full height
                        />
                    </Grid>
                </Grid>
            </Box>
        </>
    );
};

export default HomePage;