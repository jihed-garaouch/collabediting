import React, { useState, useEffect } from 'react';
import MonacoEditor from 'react-monaco-editor';

const options = {
    autoIndent: 'full',
    contextmenu: true,
    fontFamily: 'monospace',
    fontSize: 13,
    lineHeight: 24,
    hideCursorInOverviewRuler: true,
    matchBrackets: 'always',
    minimap: {
        enabled: true,
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

const CodeEditor = ({ code, language }) => {
    return (
        <>

        <MonacoEditor
            height="800"
            width="1389ds"
            theme= "vs-dark"
            value={code}
           language="javascript"
           options={options}
        />
        </>
    );
};

export default CodeEditor;