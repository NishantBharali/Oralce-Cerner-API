import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { addIdeas } from "../redux/actions/ideaActions";
import FinalFooter from "./Headers and Footers/Footer";
import ComponentHeader from "./Headers and Footers/ComponentHeader";
import { Popup } from "semantic-ui-react";

export const AddForm = () => {

    const dispatch = useDispatch();
    const ideas = useSelector((state) => state.allideas);

    const [ideaTitle, setIdeaTitle] = useState("");
    const [ideaDescription, setIdeaDescription] = useState("");
    const [ideaStorypoints, setIdeaStorypoints] = useState("");


    const history = useHistory();

    let obj = {
                "ideaTitle": ideaTitle,
                "ideaDescription": ideaDescription,
                "ideaStorypoints": ideaStorypoints
            }

            //dispatching the action addIdeas upon click on submit button
            const submit = (event) => {
                event.preventDefault();
                dispatch(addIdeas(obj));
                history.push('/idealisting');
            }
  
    return (
        <>
        <ComponentHeader />
            <form onSubmit={submit} style={{border: '3px solid lightblue', padding: '10px', marginBottom: '50px', marginTop: '50px', backgroundColor: 'transparent', borderRadius: '5px'}}>
                <div className="row justify-content-center">
                    <div className="col-3 center-content">
                        
                        <div className="form-floating mb-3">
                        <h2 style={{textAlign: 'center', color: 'lightyellow', fontSize: '30px'}}>Add a new Idea</h2>
                        <label className="form-label" style={{height: "24px", width: "231px", color: "white", fontStyle: 'Open Sans', fontSize: "17px", lineHeight: "1px", fontVariant: 'all-petite-caps'}}>Title</label>
                        <Popup
                        trigger={<input id = "input-ideaTitle" style={{boxSizing: 'border-box', height: '39px', width: '426px', border: '1.5px solid white', borderRadius: '10px', backgroundColor: 'transparent', color: 'white'}} type="text" className="form-control" name="ideaTitle" placeholder=""
                        value={ideaTitle}
                        onChange={(e) => setIdeaTitle(e.target.value)}/>}
                        content='Title cannot be empty'
                        position='right center'
                        inverted={true}
                        size="tiny"
                      />
                            
                           
                        </div>
                        
                        <div className="form-floating mb-3">
                        <label className="form-label" style={{height: "24px", width: "231px", color: "white", fontStyle: 'Open Sans', fontSize: "16px", lineHeight: "24px", fontVariant: 'all-petite-caps'}}>Description</label>
                        <br />
                        <Popup
                        trigger={<textarea id = "input-ideaDesc" style={{resize: 'none', boxSizing: 'border-box', height: '100px', width: '426px', border: '1.5px solid white', borderRadius: '2px', backgroundColor: 'transparent', color: 'white'}} type="text" className="form-control" name="ideaDescription" placeholder=""
                        value={ideaDescription}
                        onChange={(e) => setIdeaDescription(e.target.value)}
                 />}
                        content='Description cannot be empty'
                        position='right center'
                        inverted={true}
                        size="tiny"
                        
                      />
                            
                            
                        </div>
                        <div className="form-floating mb-3">
                        <label className="form-label" style={{height: "24px", width: "231px", color: "white", fontStyle: 'Open Sans', fontSize: "16px", lineHeight: "1px", fontVariant: 'all-petite-caps'}}>Story points</label>
                        <Popup
                        trigger={<input id = "input-ideaSP" style={{boxSizing: 'border-box', height: '39px', width: '426px', border: '1.5px solid white', borderRadius: '10px', backgroundColor: 'transparent', color: 'white'}} type="number" min="1" max="25" className="form-control" name="ideaStorypoints" placeholder=""
                        value={ideaStorypoints}
                        onChange={(e) => setIdeaStorypoints(e.target.value)}
                 />}
                        content='Storypoints cannot be empty'
                        position='right center'
                        inverted={true}
                        size="tiny"
                        
                      />
                          
                            
                        </div>
                        <hr/>
                        <button 
                        id="btn-submit"
                        disabled={!ideaTitle || !ideaDescription || !ideaStorypoints}
                        className="ui button" style={{backgroundColor: 'transparent', border: '3px solid white', color: 'floralwhite', borderRadius: '70px'}} tableindex="0" type='submit'>
                        <div className="visible content">Submit</div>
                       </button>

                            <button 
                        className="ui animated button" type="button" style={{backgroundColor: 'transparent', border: '3px solid lightgrey', color: 'white', marginTop: '10px', borderRadius: '70px'}} tableindex="0" onClick={(e) => history.push('/idealisting')}>
                        <div className="visible content">Back</div>
                        <div className="hidden content">
                            <i className="chevron circle left icon"></i></div></button>

                    </div>
                </div>
            
            </form>
            <FinalFooter />
        </>
    );
};