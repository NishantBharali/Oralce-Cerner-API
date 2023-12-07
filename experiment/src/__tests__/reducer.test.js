import { ideaReducer, initialState, selectedIdeaReducer } from '../redux/reducers/ideaReducer';
import expect from 'expect';
import { ActionTypes } from "../redux/constants/action-types";
import React from "react";
import ShallowRenderer from 'react-test-renderer/shallow';
import { configure ,render,shallow } from 'enzyme';
import Adapter from '@wojtekmaj/enzyme-adapter-react-17';
import { connect } from 'react-redux';
import "core-js/stable";
import { createTestStore } from "./testStore.test";
import { deleteIdea, setIdeas } from '../redux/actions/ideaActions';
import { initial } from 'lodash';


describe('idea reducer', () => {
    it('should return the initial state', () => {
      expect(ideaReducer(undefined, [])).toEqual({
          ideas: [],
          idea:{}
      });
    });

describe('GET_IDEAS', () => {
    it('should let GET_IDEAS succeed', () => {
        const ideas = [{ ideaTitle: 'test 1' }, { ideaDesription: 'test 1 desc' }, { ideaStorypoints: 3 }]
        const newState = ideaReducer(undefined, {
            type: ActionTypes.GET_IDEAS_SUCCESS,
            payload: ideas
        });
        expect(newState.ideas).toEqual(ideas);
    })
})


describe('GET_ALL_IDEAS', () => {
    it('should let GET_ALL_IDEAS succeed', () => {
        const ideas = [{ ideaTitle: 'test 1' }, { ideaDesription: 'test 1 desc' }, { ideaStorypoints: 3 }]
        const newState = ideaReducer(undefined, {
            type: ActionTypes.GET_ALL_IDEAS_SUCCESS,
            payload: ideas
        });
        expect(newState.ideas).toEqual(ideas);
    })
})

describe('GET_SINGLE_IDEAS_SUCCESS', () => {
    it('should let SELECTED_IDEAS_SUCCESS succeed', () => {
        const ideas = [{ ideaTitle: 'test 1' }, { ideaDesription: 'test 1 desc' }, { ideaStorypoints: 3 }]
        const newState = selectedIdeaReducer(undefined, {
            type: ActionTypes.SELECTED_IDEAS_SUCCESS,
            payload: ideas
        });
        expect(newState.idea).toEqual(ideas);
    })
})

describe('SELECTED_IDEAS', () => {
    it('should let SELECTED_IDEAS succeed', () => {
        const ideas = [{ ideaTitle: 'test 1' }, { ideaDesription: 'test 1 desc' }, { ideaStorypoints: 3 }]
        const newState = selectedIdeaReducer(undefined, {
            type: ActionTypes.SELECTED_IDEAS,
            payload: ideas
        });
        expect(newState.idea).toEqual(ideas);
    })
})

describe('ADD_IDEAS_SUCCESS', () => {
    it('should let ADD_IDEAS succeed', () => {
        const ideas = [{ ideaTitle: 'test 1' }, { ideaDesription: 'test 1 desc' }, { ideaStorypoints: 3 }]
        const newState = ideaReducer(undefined, {
            type: ActionTypes.ADD_IDEAS_SUCCESS,
            payload: ideas
        });
        expect(newState.ideas).toEqual(ideas);
    })
})

describe('DELETE_IDEAS_SUCCESS', () => {
    it('should let DELETE_IDEAS succeed', () => {
        const ideas = [{ ideaTitle: 'test 1' }, { ideaDesription: 'test 1 desc' }, { ideaStorypoints: 3 }]
        const newState = ideaReducer(undefined, {
            type: ActionTypes.DELETE_IDEAS_SUCCESS,
            payload: ideas
        });
        expect(newState.ideas).toEqual([]);
    })
})

describe('UPDATE_IDEAS_SUCCESS', () => {
    it('should let UPDATE_IDEAS succeed', () => {
        const ideas = [{ ideaTitle: 'test 1' }, { ideaDesription: 'test 1 desc' }, { ideaStorypoints: 3 }]
        const newState = ideaReducer(undefined, {
            type: ActionTypes.UPDATE_IDEAS_SUCCESS,
            payload: ideas
        });
        expect(newState.ideas).toEqual([]);
    })
})

    
  });