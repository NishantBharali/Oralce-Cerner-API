import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link } from "react-router-dom";
import { deleteIdea } from "../redux/actions/ideaActions";

const IdeaComponents = () => {

    const dispatch = useDispatch();

    //extracting the state from the global state
    const ideas = useSelector((state) => state.allIdeas.ideas);
    const renderList = ideas.map((idea) => {
        const {id, ideaTitle, ideaDescription, ideaStorypoints} = idea;

        //dispatching the action deleteIdea
        const handleDelete = () => {
          if(window.confirm("are you sure you want to delete?")){
            dispatch(deleteIdea(id))        
          }
        }

        return(  
          //pagination here      
        <div className="four wide column" style={{marginBottom: '50px', marginTop: '60px'}} key={id}>
        
        <Link to={{pathname:`/idea/update/${id}`, state: {id: id, ideaTitle: ideaTitle, ideaDescription: ideaDescription, ideaStorypoints: ideaStorypoints}}}>
        <div className="ui link cards" style={{height: "100%", maxHeight:"150px"}}>
          <div className="card" style={{height: "90%", overflowY: "auto", backgroundColor: 'transparent', border: '2px solid white', borderRadius: '10px', borderColor: 'lightblue'}}>
              <div className="content">
                <div className="description" style={{textAlign: "left", font: 'small-caption', fontVariant: 'all-small-caps', fontSize: '17px', color: 'lightgrey'}}><strong>Title: </strong> {ideaTitle}</div>
                <div className="description" style={{textAlign: "left", font: 'small-caption', fontVariant: 'all-small-caps', fontSize: '17px', color: 'lightgrey'}}><strong>Description: </strong> {ideaDescription}</div>
                <div className="meta" style={{textAlign: "left", font: 'small-caption', fontVariant: 'all-small-caps', fontSize: '17px', color: 'lightgrey'}}>Story points: {ideaStorypoints}</div>        
                </div>

              </div>
              </div>
              </Link>
              <br />
              
              <button id="delete-btn" className="ui button" style={{color:"white", backgroundColor: 'transparent'}} tableindex="0" onClick={() => handleDelete()}>
              <div className="hidden content">
                  <i className="trash alternate outline icon"></i></div>
              </button>
              
            
          
          </div>
        
          );
    });

    return <>{renderList}</>;
};

export default IdeaComponents;