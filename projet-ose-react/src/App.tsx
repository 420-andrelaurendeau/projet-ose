import './App.css';
import {Link, Outlet} from "react-router-dom";

function App() {
    return (
        <div>
            <div>
                <Link to={"/signIn"}><h1>Sign in</h1></Link>
                <Link to={"/signInTemp"}><h1>Sign in temp</h1></Link>
                <Link to={"/etudiantInscription"}><h1 >Etudiant Inscription</h1></Link>
            </div>
            <div>
                <Outlet />
            </div>
        </div>

    );
}


export default App;