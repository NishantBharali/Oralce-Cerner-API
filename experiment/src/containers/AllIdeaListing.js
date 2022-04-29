import React, {useEffect} from "react";
import { useDispatch, useSelector } from "react-redux";
import { getAllIdeas } from "../redux/actions/ideaActions";
import FinalFooter from "./Headers and Footers/Footer";
import AllIdeaComponents from "./AllIdeaComponents";
import AllIdeaHeader from "./Headers and Footers/AllIdeasHeader";


const AllIdeaListing = () => {
  
    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(getAllIdeas());
        
            }, []);

    //extracting the state from the global state
    const ideas = useSelector((state) => state);

    return(<div className="page-container">
        <div className="ui grid container">
    <AllIdeaHeader />
    
            {ideas.allIdeas.ideas.length?
     <AllIdeaComponents />
    : <h2 style={{textAlign: 'center'}}>"No idea found in the repository. Please add your own ideas to view them by going back to your home page."</h2>}
    
 <FinalFooter />
        </div>
           </div>
        
    );
};

export default AllIdeaListing;