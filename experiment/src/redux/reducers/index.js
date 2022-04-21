import { combineReducers } from "redux";
import { ideaReducer, selectedIdeaReducer } from "./ideaReducer";

const reducers = combineReducers({
    allIdeas: ideaReducer,
    idea: selectedIdeaReducer
});

export default reducers;