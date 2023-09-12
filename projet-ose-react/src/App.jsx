import React from 'react';
import { useTranslation } from 'react-i18next';

import './i18n';

const lngs = {
  en: { nativeName: 'English' },
  fr: { nativeName: 'Français' }
};

function App() {
  const { t, i18n } = useTranslation();
  
  return (
    <>

      {/**
       * 
       * Permet de tester le changement de langue
       * 
       * 
       <header>
          <div>
            {Object.keys(lngs).map((lng) => (
              <button key={lng} style={{ fontWeight: i18n.resolvedLanguage === lng ? 'bold' : 'normal' }} type="submit" onClick={() => i18n.changeLanguage(lng)}>
                {lngs[lng].nativeName}
              </button>
            ))}
          </div>
        </header>
      */}

      <div className="">
        
        <h1 className="text-orange">Bienvenue à React avec TailwindCSS!</h1>
        <h1 className="text-blue">Bienvenue à React avec TailwindCSS!</h1>

      </div>
    </>
  );
}


export default App;