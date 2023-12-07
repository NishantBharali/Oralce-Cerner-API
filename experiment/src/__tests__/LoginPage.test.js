import React from "react";
import ShallowRenderer from 'react-test-renderer/shallow';
import { configure ,shallow } from 'enzyme';
import Adapter from '@wojtekmaj/enzyme-adapter-react-17';
import "core-js/stable";
import "regenerator-runtime/runtime";
import { LoginPage } from "../containers/components/LoginPage";

configure({ adapter: new Adapter() });




it("check for test cases",()=> {
    const renderer = new ShallowRenderer();
    renderer.render(<LoginPage />);
    expect(renderer).toMatchSnapshot();

})


const simulateOnChangeInput = (wrapper, inputSelector, newValue) => {
    const input = wrapper.find(inputSelector);
    input.simulate("change", {
      target: { value: newValue },
    });
  
    return wrapper.find(inputSelector);
  };
  test('useState mock ', () => {
    const wrapper = shallow(<LoginPage />);
    const updatedNameInput = simulateOnChangeInput(
        wrapper,
        "#input-password",
        "Test@1728"
      );
    
    wrapper.find('#btn-primary').simulate('click');
    expect(wrapper).toMatchSnapshot();
    // initial state is set and you can now test your component 
    })
    
    test('useState mock for username ', () => {
        const wrapper = shallow(<LoginPage />);
        const updatedName1Input = simulateOnChangeInput(
            wrapper,
            "#input-username",
            "arthur@gmail.com"
          );
        
        wrapper.find('#btn-primary').simulate('click');
        expect(wrapper).toMatchSnapshot();
        // initial state is set and you can now test your component 
        })