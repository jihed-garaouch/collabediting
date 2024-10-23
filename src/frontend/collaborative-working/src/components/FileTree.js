import * as React from "react";
import { alpha, styled } from "@mui/material/styles";
import { SimpleTreeView } from '@mui/x-tree-view/SimpleTreeView';
import {  TreeItem,  treeItemClasses } from "@mui/x-tree-view/TreeItem";
import Collapse from "@mui/material/Collapse";
// web.cjs is required for IE11 support
import { useSpring, animated } from "react-spring";
import { TransitionProps } from "@mui/material/transitions";

import {
    getIconForFile,
    getIconForFolder,
    getIconForOpenFolder
} from "vscode-icons-js";

function TransitionComponent(props) {
    const style = useSpring({
        from: {
            opacity: 0,
            transform: "translate3d(20px,0,0)"
        },
        to: {
            opacity: props.in ? 1 : 0,
            transform: `translate3d(${props.in ? 0 : 20}px,0,0)`
        }
    });

    return (
        <animated.div style={style}>
            <Collapse {...props} />
        </animated.div>
    );
}

const StyledTreeItem = styled((props) => {
    let iconProps = {
        icon: <img alt="index.js" src={`/icons/${getIconForFile(props.label)}`} />
    };
    if (props.children) {
        iconProps = {
            expandicon: (
                <img alt="index.js" src={`/icons/${getIconForFolder(props.label)}`} />
            ),
            collapseicon: (
                <img
                    alt="index.js"
                    src={`/icons/${getIconForOpenFolder(props.label)}`}
                />
            )
        };
    }
    return (
        <TreeItem itemId={props.nodeId}
            {...{ ...props, ...iconProps }}
            TransitionComponent={TransitionComponent}
        />
    );
})(({ theme }) => ({
    [`& .${treeItemClasses.iconContainer}`]: {
        "& .close": {
            opacity: 0.3
        }
    },
    [`& .${treeItemClasses.group}`]: {
        marginLeft: 15,
        paddingLeft: 18,
        borderLeft: `1px dashed ${alpha(theme.palette.text.primary, 0.4)}`
    }
}));

export default function FileTreeView() {
    return (
        <SimpleTreeView
            aria-label="customized"
            defaultExpanded={["3"]}
            sx={{ height: 400, flexGrow: 1, maxWidth: 400, overflowY: "auto" }}
        >
            <StyledTreeItem nodeId="1" label="app">
                <StyledTreeItem
                    nodeId="2"
                    label="Hello.md"

                />
                <StyledTreeItem nodeId="3" label="components">
                    <StyledTreeItem nodeId="6" label="Hello.log" />
                    <StyledTreeItem nodeId="7" label="source">
                        <StyledTreeItem nodeId="9" label="index.ts" />
                        <StyledTreeItem nodeId="10" label="Demo.spec.tsx" />
                        <StyledTreeItem nodeId="11" label="Demo.tsx" />
                    </StyledTreeItem>
                    <StyledTreeItem nodeId="8" label="Hello.md" />
                </StyledTreeItem>
                <StyledTreeItem nodeId="4" label="README.md" />
                <StyledTreeItem nodeId="5" label="build.sh" />
            </StyledTreeItem>
        </SimpleTreeView>
    );
}
