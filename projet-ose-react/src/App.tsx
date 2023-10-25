import './App.css';
import {Link, Outlet} from "react-router-dom";
import TestBackEndConnection from "./components/common/testBackEndConnection";

function App() {
    return (
        <div>
            <div>
                {/*<Link to={"/signInTemp"}><h1>Sign in temp</h1></Link>*/}
                <TestBackEndConnection/>
                <Link to={"/signIn"}><h1>Sign in</h1></Link>
                <Link to={"/etudiantInscription"}><h1>Etudiant Inscription</h1></Link>
                <Link to={"/employeurInscription"}><h1>Employeur Inscription</h1></Link>
                <Link to={"GSInternOffer"}><h1>GS intern offer</h1></Link>
            </div>
            <div>
                <Outlet />
            </div>
        </div>

    );
}


export default App;