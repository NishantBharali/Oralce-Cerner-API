import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import { useLocation } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { updateIdea } from "../redux/actions/ideaActions";
import FinalFooter from "./Headers and Footers/Footer";
import ComponentHeader from "./Headers and Footers/ComponentHeader";

export const UpdateIdea = () => {

    const location = useLocation();
    const dispatch = useDispatch();

    //extracting the value from the global state
    const ideas = useSelector((state) => state.allIdeas);
    const [id, setId] = useState(()=>{return location.state.id});
    const [ideaTitle, setIdeaTitle] = useState(()=>{return location.state.ideaTitle});
    const [ideaDescription, setIdeaDescription] = useState(()=>{return location.state.ideaDescription});
    const [ideaStorypoints, setIdeaStorypoints] = useState(()=>{return location.state.ideaStorypoints});

    const history = useHistory();


let obj = {
            "id": id,
            "ideaTitle": ideaTitle,
            "ideaDescription": ideaDescription,
            "ideaStorypoints": ideaStorypoints
        }

    //dispatching the action updateIdea upon button click
    const onButtonHandler = () => {
            dispatch(updateIdea(obj))
            history.push('/idealisting')
    }

    return(
   
        <>
        <ComponentHeader />
            <form style={{border: '3px solid lightblue', padding: '20px', marginBottom: '50px', marginTop: '50px', backgroundColor: 'transparent'}}>
                <div className="row justify-content-center">
                    <div className="col-3 center-content">

                        <div className="form-floating mb-3">
                        <h2 style={{textAlign: 'center', color: 'lightyellow', fontSize: '30px'}}>Update Your Idea</h2>
                        <label className="form-label" style={{color: 'white', fontSize: '15px', fontVariant: 'all-petite-caps'}}>Title</label>
                            <input type="text" style={{boxSizing: 'border-box', height: '39px', width: '426px', border: '1.5px solid white', borderRadius: '10px', backgroundColor: 'transparent', color: 'white'}} className="form-control" name="ideaTitle" placeholder="enter your title"
                                   value={ideaTitle}
                                   onChange={(e) => setIdeaTitle(e.target.value)}/>
                           
                        </div>

                        <div className="form-floating mb-3">
                        <label className="form-label" style={{color: 'white', fontSize: '15px', fontVariant: 'all-petite-caps'}}>Description</label>
                            <textarea type="text" style={{resize: 'none', boxSizing: 'border-box', height: '80px', width: '100%', border: '1.5px solid lightgrey', borderRadius: '10px', backgroundColor: 'transparent', color: 'white'}} className="form-control" name="ideaDescription" placeholder="Description"
                                   value={ideaDescription}
                                   onChange={(e) => setIdeaDescription(e.target.value)}
                            />
                            
                        </div>
                        <div className="form-floating mb-3">
                        <label className="form-label" style={{color: 'white', fontSize: '15px', fontVariant: 'all-petite-caps'}}>Story points</label>
                            <input type="number" className="form-control" style={{boxSizing: 'border-box', height: '39px', width: '426px', border: '1.5px solid white', borderRadius: '10px', backgroundColor: 'transparent', color: 'white'}} name="ideaStorypoints" placeholder="story points"
                                   value={ideaStorypoints}
                                   onChange={(e) => setIdeaStorypoints(e.target.value)}
                            />
                        </div>
                        <hr/>
                        <button 
                        disabled={!ideaTitle || !ideaDescription || !ideaStorypoints}
                        className="ui button" style={{backgroundColor: 'transparent', border: '3px solid lightgrey', color: 'lightgrey', borderRadius: '70px'}} tableindex="0" onClick={() => onButtonHandler()}>
                        <div className="visible content">Submit</div>
                        </button>

                            <button 
                        className="ui animated button" style={{backgroundColor: 'transparent', border: '3px solid white', color: 'lightgrey', marginTop: '10px', borderRadius: '70px'}} tableindex="0" onClick={(e) => history.push('/idealisting')}>
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