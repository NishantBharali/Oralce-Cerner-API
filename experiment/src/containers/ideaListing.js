import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import IdeaComponents from "./ideaComponents";
import { getIdeas } from "../redux/actions/ideaActions";
import FinalHeader from "./Headers and Footers/MainHeader";
import FinalFooter from "./Headers and Footers/Footer";


const IdeaListing = () => {
  

    const dispatch = useDispatch();

    //dispatching getIdeas action
    useEffect(() => {
        dispatch(getIdeas());
               
            }, [dispatch]);

    //extracting state from the global state
    const ideas = useSelector((state) => state);

    

    return(
    <div className="ui grid container">
        <FinalHeader />
    
            {ideas.allIdeas.ideas.length?
        <IdeaComponents />
    : <h2 style={{textAlign: 'center', color: 'lightblue', fontVariant:'all-petite-caps'}}>No data found. Please add a new idea using the Add Idea button using the drop-down!</h2>}

        <FinalFooter />
        
 
    </div>
        
    );
};

export default IdeaListing;