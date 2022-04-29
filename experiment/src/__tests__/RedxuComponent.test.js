import React from "react";
import ShallowRenderer from 'react-test-renderer/shallow';
import { configure ,render } from 'enzyme';
import Adapter from '@wojtekmaj/enzyme-adapter-react-17';
import "core-js/stable";
import "regenerator-runtime/runtime";
import { createTestStore } from "./testStore.test";
import { Provider } from "react-redux";
import IdeaListing from '../containers/ideaListing';
import IdeaComponents from "../containers/ideaComponents";
import { AddForm } from "../containers/AddForm";

configure({ adapter: new Adapter() });

let store;
describe("Your test", () => {

    beforeEach(() => {
        store = createTestStore();
    });

test('Components with a full reducer flow', async () => {
    const { findByText } = render(
        <Provider store={store}>
        <IdeaListing />
        </Provider>
    );

});

test('Components with a full reducer flow 1', async () => {
    const { findByText1 } = render(
        <Provider store={store}>
        <IdeaComponents />
        </Provider>
    );

});

test('Components with a full reducer flow 2', async () => {
    const { findByText2 } = render(
        <Provider store={store}>
        <AddForm />
        </Provider>
    );

});






})