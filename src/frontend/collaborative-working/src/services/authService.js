export const login = () => {
    const oauth2LoginUrl = 'http://localhost:4444/oauth2/authorization/github';

    window.location.href = oauth2LoginUrl;
};
