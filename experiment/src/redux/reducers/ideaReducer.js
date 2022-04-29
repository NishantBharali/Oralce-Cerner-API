// import _ from "lodash";
import { ActionTypes } from "../constants/action-types";


 export const initialState = {
    ideas:[],
    idea:{}
};


export const ideaReducer = (state = initialState, action) => {
    switch(action.type) {
        case ActionTypes.GET_IDEAS_SUCCESS:
            return { ...state, ideas: action.payload };
        case ActionTypes.DELETE_IDEAS_SUCCESS:
            return {...state, ideas: state.ideas.filter(idea => idea.id !== action.payload)};
        case ActionTypes.UPDATE_IDEAS_SUCCESS: 
            return {...state, ideas: state.ideas.map(idea => {
                if(idea.id === action.payload.id) {
                    return action.payload
                } else {
                    return idea
                }
            })};
        case ActionTypes.ADD_IDEAS_SUCCESS:
             return { ...state, ideas: action.payload };
        case ActionTypes.GET_ALL_IDEAS_SUCCESS:
            return { ...state, ideas: action.payload };
        default:
            return state;
    }
    
};

export const selectedIdeaReducer = (state=initialState, action) => {
    switch(action.type) {
        case ActionTypes.SELECTED_IDEAS:
            return { ...state, idea: action.payload };
            case ActionTypes.SELECTED_IDEAS_SUCCESS:
                return { ...state, idea: action.payload };
        default:
            return state;
    }
};



