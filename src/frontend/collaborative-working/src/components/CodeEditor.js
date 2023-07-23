import React, { useState, useEffect } from 'react';
import MonacoEditor from 'react-monaco-editor';
const options = {
    autoIndent: 'full',
    contextmenu: true,
    fontFamily: 'monospace',
    fontSize: 10,
    lineHeight: 15,
    hideCursorInOverviewRuler: true,
    matchBrackets: 'always',

    minimap: {
        enabled: false,
    },

    scrollbar: {
        horizontalSliderSize: 4,
        verticalSliderSize: 18,
    },
    selectOnLineNumbers: true,
    roundedSelection: false,
    readOnly: false,
    cursorStyle: 'line',
    automaticLayout: true,
};
const CodeEditor = () => {
    const [code, setCode] = useState('');
    const [file, setFile] = useState();
    const [language, setLanguage] = useState('javascript');

    const handleFileChange = (event) => {
        if (event.target.files) {
            setFile(event.target.files[0]);
        }
    };

    useEffect(() => {
        if (file) {
            var reader = new FileReader();
            reader.onload = async (e) => {
                setCode(e.target.result);
            };
            reader.readAsText(file);
            let newLanguage = 'javascript';
            const extension = file.name.split('.').pop();
            if (['css', 'html', 'python', 'dart'].includes(extension)) {
                newLanguage = extension;
            }
            setLanguage(newLanguage);
        }
    }, [file]);


    function handleClear() {
        setCode();


    }

    return (
        <div>
            <div>
                <input type="file" onChange={handleFileChange} />
                <input type="button" onClick={handleClear} value="Clear All "  />
                <input type="button"  value="submit"  />
            </div>
            <hr />
            <MonacoEditor
                height="800"
                width="700"
                language={language}
                value={code}
                options={options}

            />

        </div>
    );
}; export default CodeEditor ;
