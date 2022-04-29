import React, { useState } from "react";
import ReactPaginate from "react-paginate";
import { useDispatch, useSelector } from "react-redux";
import { Link } from "react-router-dom";
import { deleteIdea } from "../redux/actions/ideaActions";

const IdeaComponents = () => {

    const dispatch = useDispatch();
    const [pageNumber, setPageNumber] = useState(0)

    const ideasPerPage = 8
    const pageVisited = pageNumber * ideasPerPage

    //extracting the state from the global state
    const ideas = useSelector((state) => state.allIdeas.ideas);
    const renderList = ideas.reverse().slice(pageVisited, pageVisited + ideasPerPage).map((idea) => {
        const {id, ideaTitle, ideaDescription, ideaStorypoints} = idea;
        

        //dispatching the action deleteIdea
        const handleDelete = () => {
          if(window.confirm("are you sure you want to delete?")){
            dispatch(deleteIdea(id))   
          }
        }

        return(    
        <div className="four wide column" style={{marginBottom: '50px', marginTop: '60px'}} key={id}>
        
        <Link to={{pathname:`/idea/update/${id}`, state: {id: id, ideaTitle: ideaTitle, ideaDescription: ideaDescription, ideaStorypoints: ideaStorypoints}}}>
          <div className="ui link cards" style={{height: "100%", maxHeight:"150px"}}>
            <div className="ui fluid card" style={{height: "90%", overflowY: "auto", backgroundColor: 'transparent', border: '2px solid white', borderRadius: '10px', borderColor: 'lightblue'}}>
              <div className="content" style={{backgroundColor: 'rgba(0, 0, 0, 0.1)'}}>
                <div className="header" style={{opacity: 1, textAlign: "left", font: 'small-caption', fontVariant: 'all-small-caps', fontSize: '17px', color: 'lightgrey'}}><strong style={{color: 'lightblue'}}>Title: </strong> {ideaTitle}</div>
              </div>
              <div className="content">
                <div className="description" style={{textAlign: "left", font: 'small-caption', fontVariant: 'all-small-caps', fontSize: '17px', color: 'lightgrey'}}><strong style={{color: 'lightblue'}}>Description: </strong> {ideaDescription}</div>
                  <div className="meta" style={{textAlign: "left", font: 'small-caption', fontVariant: 'all-small-caps', fontSize: '17px', color: 'lightgrey'}}>Story points: {ideaStorypoints}</div>        
              </div>

            </div>
          </div>
        </Link>
        <br />
              
              <button id="delete-btn" className="ui button" style={{color:"white", backgroundColor: 'transparent'}} tableindex="0" onClick={() => handleDelete()}>
                <div className="hidden content">
                  <i className="trash alternate outline icon"></i>
                </div>
              </button>
      
          </div>
          
        
          );
    });
    
    //pagination here  
    const pageCount = Math.ceil(ideas.length / ideasPerPage);
    const changePage = ({selected}) => {
      setPageNumber(selected)
    };

    return <>{renderList}
    <ReactPaginate 
    previousLabel={<>&laquo;</>}
    nextLabel={<>&raquo;</>}
    pageRangeDisplayed={1}
    marginPagesDisplayed={1}
    pageCount={pageCount}
    onPageChange={changePage}
    containerClassName={"paginationButton"}
    previousLinkClassName={"previousLink"}
    breakLabel={"..."}
    breakClassName={"break-me"}
    nextLinkClassName={"nextLink"}
    disabledClassName={"paginationDisabled"}
    activeClassName={"paginationActive"}
    nextClassName={"nextButtonClass"}
    /></>;
};

export default IdeaComponents;

