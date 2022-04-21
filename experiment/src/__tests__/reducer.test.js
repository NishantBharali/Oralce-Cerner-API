import { ideaReducer } from '../redux/reducers/ideaReducer';
import expect from 'expect';
import { ActionTypes } from "../redux/constants/action-types";
import React from "react";
import ShallowRenderer from 'react-test-renderer/shallow';
import { configure ,render,shallow } from 'enzyme';
import Adapter from '@wojtekmaj/enzyme-adapter-react-17';
import { connect } from 'react-redux';
import "core-js/stable";
import { createTestStore } from "./testStore.test";
import { initialState as newState } from '../redux/reducers/ideaReducer';
import { setIdeas } from '../redux/actions/ideaActions';
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
        const initialState = undefined;
        const action = { type: ActionTypes.GET_IDEAS };
        const result = setIdeas(initialState, action.payload);
        expect(result).toEqual({ ideas: {
            ideaTitle: 'test',
            ideaDescription: 'test2',
            ideaStorypoints: 0
            
        } })
    })
})

  
    
  });