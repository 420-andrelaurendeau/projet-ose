import './App.css';
import Field from "./components/common/field";
import EmailValidator from "./components/common/validators/EmailValidator";

function App() {
  return (
    <div className="">
        {Field("Email", "email", "email", new EmailValidator())}
    </div>
  );
}


export default App;