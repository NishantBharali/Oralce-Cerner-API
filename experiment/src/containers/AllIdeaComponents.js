import React, { useState } from "react";
import ReactPaginate from "react-paginate";
import { useSelector } from "react-redux";
import { Link } from "react-router-dom";

const AllIdeaComponents = () => {

    const [pageNumber, setPageNumber] = useState(0);

    const ideasPerPage = 8
    const pageVisited = pageNumber * ideasPerPage

    const ideas = useSelector((state) => state.allIdeas.ideas);
    const renderList1 = ideas.reverse().slice(pageVisited, pageVisited + ideasPerPage).reverse().map((idea) => {
    const {id, ideaTitle, ideaDescription, ideaStorypoints} = idea;

        return(        
        <div className="four wide column" style={{marginBottom: '50px', marginTop: '5px'}} key={id}>
        
            <Link to={{pathname:`/repository/${id}`, state: {id: id, ideaTitle: ideaTitle, ideaDescription: ideaDescription, ideaStorypoints: ideaStorypoints}}}>
                <div className="ui link cards" style={{height: "100%", maxHeight:"150px"}}>
                    <div className="card" style={{height: "150%", overflowY: "auto", backgroundColor: 'transparent', border: '2px solid white', borderRadius: '10px', borderColor: 'lightblue'}}>
                        <div className="content" style={{backgroundColor: 'rgba(0, 0, 0, 0.1)'}}>
                            <div className="header" style={{textAlign: "left", font: "small-caption", fontSize: '17px', fontVariant: 'all-petite-caps', color: 'lightgrey'}}><strong style={{color: 'lightblue'}}>Title: </strong> {ideaTitle}</div>
                            </div>
                            <div className="content">
                            <div className="description" style={{textAlign: "left", font: "small-caption", fontSize: '17px', fontVariant: 'all-petite-caps', color: 'lightgrey'}}><strong style={{color: 'lightblue'}}>Description: </strong> {ideaDescription}</div>
                            <div className="meta" style={{textAlign: "left", font: "small-caption", fontSize: '17px', fontVariant: 'all-petite-caps', color: 'lightgrey'}}>Story points: {ideaStorypoints}</div>        
                        </div>
                       
                    </div>
                </div>
            </Link>
            
          </div>
          );
    });

     //pagination here  
     const pageCount = Math.ceil(ideas.length / ideasPerPage);
     const changePage = ({selected}) => {
       setPageNumber(selected)
     };
 
    return <>{renderList1}
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
    previousClassName={"prevClass"}
    /></>;
};

export default AllIdeaComponents;