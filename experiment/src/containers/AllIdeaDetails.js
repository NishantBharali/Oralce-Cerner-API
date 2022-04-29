import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { selectedIdeas } from "../redux/actions/ideaActions";
import { useHistory } from "react-router-dom";

const AllIdeaDetails = () => {

  const history = useHistory();
  const dispatch = useDispatch();
  const location = useLocation();

  //extracting the state idea from the global state
  const ideas = useSelector((state) => state.idea);

  const [id, setId] = useState(()=>{return location.state.id});
  const [ideaTitle, setIdeaTitle] = useState(()=>{return location.state.ideaTitle});
  const [ideaDescription, setIdeaDescription] = useState(()=>{return location.state.ideaDescription});
  const [ideaStorypoints, setIdeaStorypoints] = useState(()=>{return location.state.ideaStorypoints});

    //dispatching the action selectedIdeas
    useEffect(() => {
        dispatch(selectedIdeas(id))
    }, [dispatch, id]);

    return(<div className="ui grid container">
    {Object.keys(ideas).length === 0 ? (
      <div>...Loading</div>
    ) : (
      <div>
      <div className="ui grid container" style={{marginTop: '15px', marginBottom: '5px'}}>
        <div className="card" style={{backgroundColor: 'rgba(0, 0, 0, 0.1)', height: '100%', width: '50%', padding: '20px', border: '5px solid', borderImage: 'linear-gradient(#ff512f, #dd2476, #1c64ff) 1', animation: 'rotate 2.5s ease-in infinite'}}>
            <div className="content">
              <p style={{textAlign: "left", fontStyle: "-moz-initial", fontVariant: "all-petite-caps", color: 'white'}}><strong style={{color: 'lightblue'}}>Title: </strong> {ideaTitle}</p>
              <p style={{textAlign: "left", fontStyle: "-moz-initial", fontVariant: "all-petite-caps", color: 'white'}}><strong style={{color: 'lightblue'}}>Description: </strong> {ideaDescription}</p>
              <p className="meta" style={{textAlign: "left", fontStyle: "-moz-initial", fontVariant: "all-petite-caps", color: 'white'}}><strong style={{color: 'lightblue'}}>Story points: </strong> {ideaStorypoints}</p>
            </div>
               
          </div>
          
        </div>
        <button className="ui animated button" style={{border: '2px solid transparent', backgroundColor: 'transparent', color: 'white', width: '30%', marginLeft: '394px'}} tableindex="0" onClick={() => history.push('/repository')}>
        <div className="visible content">Back</div>
        <div className="hidden content">
        <i className="left arrow icon"></i>
        </div>
        </button>
      </div>
    )}
  </div>
);
};

export default AllIdeaDetails;