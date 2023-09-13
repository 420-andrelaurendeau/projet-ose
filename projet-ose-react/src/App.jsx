import React from "react";
import './i18n';

import AppBar from "./components/common/AppBar";
import ConnectForm from "./components/common/ConnectForm";
function App() {
  return (
      <div className="h-screen items-center">
        <AppBar />
        <ConnectForm />
      </div>
  );
}


export default App;