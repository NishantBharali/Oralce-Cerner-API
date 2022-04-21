import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { LoginPage } from './containers/components/LoginPage';
import { RegistrationPage } from './containers/components/RegistrationPage';
import IdeaListing from './containers/ideaListing';
import { UpdateIdea } from './containers/UpdateForm';
import AllIdeaListing from './containers/AllIdeaListing';
import AllIdeaDetails from './containers/AllIdeaDetails';
import { AddForm } from './containers/AddForm';

export const Routes = () => {
    return (
        <Router>
            <Switch>
                <Route path="/" exact>
                    <LoginPage />
                </Route>
                <Route path="/registration" exact>
                    <RegistrationPage />
                </Route>
                <Route path="/idealisting" exact>
                <IdeaListing />
            </Route>
        <Route path="/repository/:id" exact>
            <AllIdeaDetails />
        </Route>
        <Route path="/add" exact>
        <AddForm />
    </Route>
    <Route path="/idea/update/:ideaId1" exact>
    <UpdateIdea />
    </Route>
<Route path="/repository" exact>
<AllIdeaListing />
</Route>
            </Switch>
        </Router>
    );
}