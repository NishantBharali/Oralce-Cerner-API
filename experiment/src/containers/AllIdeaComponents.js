import React from "react";
import { useSelector } from "react-redux";
import { Link } from "react-router-dom";

const AllIdeaComponents = () => {

    const ideas = useSelector((state) => state.allIdeas.ideas);
    const renderList1 = ideas.map((idea) => {
    const {id, ideaTitle, ideaDescription, ideaStorypoints} = idea;

        return(        
        <div className="four wide column" style={{marginBottom: '50px', marginTop: '60px'}} key={id}>
        
            <Link to={{pathname:`/repository/${id}`, state: {id: id, ideaTitle: ideaTitle, ideaDescription: ideaDescription, ideaStorypoints: ideaStorypoints}}}>
                <div className="ui link cards" style={{height: "100%", maxHeight:"150px"}}>
                    <div className="card" style={{height: "100%", overflowY: "auto", backgroundColor: 'transparent', border: '2px solid white', borderRadius: '10px', borderColor: 'lightyellow'}}>
                        <div className="content">
                            <div className="description" style={{textAlign: "left", font: "small-caption", fontSize: '17px', fontVariant: 'all-petite-caps', color: 'lightgrey'}}><strong>Title: </strong> {ideaTitle}</div>
                            <div className="description" style={{textAlign: "left", font: "small-caption", fontSize: '17px', fontVariant: 'all-petite-caps', color: 'lightgrey'}}><strong>Description: </strong> {ideaDescription}</div>
                            <div className="meta" style={{textAlign: "left", font: "small-caption", fontSize: '17px', fontVariant: 'all-petite-caps', color: 'lightgrey'}}>Story points: {ideaStorypoints}</div>        
                        </div>
                    </div>
                </div>
            </Link>
            
          </div>
          );
    });

    return <>{renderList1}</>;
};

export default AllIdeaComponents;