import './App.css';
import Field from "./components/common/field";
import EmailValidator from "./components/common/validators/EmailValidator";

function App() {
  return (
    <div className="m-5">
        {Field("Email", "email", "email", new EmailValidator())}
    </div>
  );
}


export default App;