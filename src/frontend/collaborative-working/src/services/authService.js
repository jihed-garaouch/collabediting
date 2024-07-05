export const login = () => {
    const URL = `${process.env.REACT_APP_GITHUB_BASE_URL}?client_id=${process.env.REACT_APP_API_OAUTH2_CLIENT_ID}&redirect_uri=${process.env.REACT_APP_API_OAUTH2_REDIRECT_URI}&scope=${process.env.REACT_APP_API_OAUTH2_SCOPES}&state=48585`;
    window.location.href = URL;
};
