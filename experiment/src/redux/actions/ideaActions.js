import { ActionTypes } from "../constants/action-types";

export const getIdeas = (ideas) => {
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


export const addIdeas = (newIdea) => {
    return {
        type: ActionTypes.ADD_IDEAS,
        payload: newIdea,
    };
};

export const updateIdea = (updatedIdea) => {
    return {
        type: ActionTypes.UPDATE_IDEAS,
        payload: updatedIdea,
    };
};

export const deleteIdea = (id) => {
    return {
        type: ActionTypes.DELETE_IDEAS,
        payload: id
    };
};



