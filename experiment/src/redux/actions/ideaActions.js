import { ActionTypes } from "../constants/action-types";

export const setIdeas = (ideas) => {
    return {
        type: ActionTypes.GET_IDEAS,
        payload: ideas,
    };
};

export const getAllIdeas = (ideas) => {
    return {
        type: ActionTypes.GET_ALL_IDEAS,
        payload: ideas,
    }
}

export const selectedIdeas = (idea) => {
    return {
        type: ActionTypes.SELECTED_IDEAS,
        payload: idea,
    };
};


export const addIdeas = (ideas) => {
    return {
        type: ActionTypes.ADD_IDEAS,
        payload: ideas,
    };
};

export const updateIdea = (ideas) => {
    return {
        type: ActionTypes.UPDATE_IDEAS,
        payload: ideas,
    };
};

export const deleteIdea = (ideas) => {
    return {
        type: ActionTypes.DELETE_IDEAS,
        payload: ideas
    };
};



