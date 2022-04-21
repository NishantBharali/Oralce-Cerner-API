import React from "react";
import ShallowRenderer from 'react-test-renderer/shallow';
import { configure ,shallow } from 'enzyme';
import Adapter from '@wojtekmaj/enzyme-adapter-react-17';
import "core-js/stable";
import "regenerator-runtime/runtime";
import FinalFooter from "../containers/Headers and Footers/Footer";

configure({ adapter: new Adapter() });

it("check for test cases",()=> {
    const renderer = new ShallowRenderer();
    renderer.render(<FinalFooter />);
    expect(renderer).toMatchSnapshot();

})
