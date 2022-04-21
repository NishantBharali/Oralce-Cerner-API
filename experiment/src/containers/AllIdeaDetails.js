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
      <div className="ui grid container" style={{marginTop: '15px', marginBottom: '5px'}}>
        <div className="ui centered card">
            <div className="content">
              <p style={{textAlign: "left", fontStyle: "-moz-initial", fontVariant: "all-petite-caps"}}><strong>Title: </strong> {ideaTitle}</p>
              <p style={{textAlign: "left", fontStyle: "-moz-initial", fontVariant: "all-petite-caps"}}><strong>Description: </strong> {ideaDescription}</p>
              <p className="meta" style={{textAlign: "left", fontStyle: "-moz-initial", fontVariant: "all-petite-caps"}}><strong>Story points: </strong> {ideaStorypoints}</p>
            </div>
                <button className="ui animated button" tableindex="0" onClick={() => history.push('/repository')}>
                <div className="visible content">Back</div>
                <div className="hidden content">
                <i className="left arrow icon"></i>
                </div>
                </button>
          </div>
        </div>
  
    )}
  </div>
);
};

export default AllIdeaDetails;