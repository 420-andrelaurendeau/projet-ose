import React, {useEffect} from "react";
import imgDark from "../../assets/images/Cegep-Andre-Laurendeau.png";
import img from "../../assets/images/logo_AL_COULEURS_FOND_BLANC-scaled-removebg-preview.png";
import toggleOn from "../../assets/images/toggle-on-solid.svg";
import toggleOff from "../../assets/images/toggle-off-solid.svg";
import { useTranslation } from 'react-i18next';
import Switcher from "../../utils/switcher";
import useDarkSide from "../../hooks/useDarkSide";


const ConnectForm = (props:any) => {
    const {i18n} = useTranslation();
    const [colorTheme,setColorTheme] = React.useState();

    const fields = i18n.getResource(i18n.language.slice(0,2),"translation","formField.ConnectForm");
    const [connectUser, setConnectUser] = React.useState({
        email: "",
        password: ""
    });


    useEffect(() => {

        console.log(localStorage.getItem('theme'))
    }, [localStorage.getItem('theme')]);

    const connect = async (e:any) => {
        e.preventDefault();
        console.log(connectUser.email)
        console.log(connectUser.password)
        const response = await fetch("http://localhost:8080/api/auth/authenticate", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(connectUser)
        });
        const data = await response.json();
        console.log(data);
    }

    return (
           <>
            <div className="flex min-h-full flex-1 flex-col justify-center px-6 py-12 lg:px-8">
                <div className=" flex space-y-3 justify-center items-center">
                    <Switcher/>
                </div>

                <div className="sm:mx-auto sm:w-full sm:max-w-sm">
                    <img
                        className="mx-auto h-16 w-auto visible dark:hidden"
                        src={img}
                        alt="Your Company"
                    />
                    <img
                        className="mx-auto h-16 w-auto hidden dark:flex"
                        src={imgDark}
                        alt="Your Company"
                    />
                    <h2 className="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-black dark:text-white">
                        Sign in to your account
                    </h2>
                </div>

                <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
                    <form className="space-y-6"  onSubmit={connect}>
                        <div>
                            <label htmlFor="email" className="block text-sm font-medium leading-6 text-black dark:text-white">
                                {fields["email"].text}
                            </label>
                            <div className="mt-2">
                                <input
                                    id="email"
                                    name="email"
                                    type="email"
                                    autoComplete="email"
                                    placeholder={fields["email"].placeholder}
                                    required
                                    className="block w-full bg-white dark:bg-softdark rounded-md py-2 text-blue dark:text-orange shadow-sm sm:text-sm sm:leading-6 pl-2"
                                    defaultValue={connectUser.email}
                                    onChange={(e) => setConnectUser({...connectUser, email: e.target.value})}
                                />
                            </div>
                        </div>

                        <div>
                            <div className="flex items-center justify-between">
                                <label htmlFor="password" className="block text-sm font-medium leading-6 text-black dark:text-white">
                                    {fields["password"].text}
                                </label>
                                <div className="text-sm">
                                    <a href="#" className="block text-sm font-medium leading-6 text-blue dark:text-orange">
                                        Forgot password?
                                    </a>
                                </div>
                            </div>
                            <div className="mt-2">
                                <input
                                    id="password"
                                    name="password"
                                    type="password"
                                    autoComplete="current-password"
                                    placeholder={fields["password"].placeholder}
                                    required
                                    className="block w-full bg-white dark:bg-softdark rounded-md py-2 text-blue dark:text-orange shadow-sm sm:text-sm sm:leading-6 pl-2"
                                    defaultValue={connectUser.password}
                                    onChange={(e) => setConnectUser({...connectUser, password: e.target.value})}
                                />
                            </div>
                        </div>

                        <div>
                            <button
                                type="submit"
                                className="flex w-full justify-center rounded-md bg-blue dark:bg-orange px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 dark:hover:bg-orange-100 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-blue dark:focus-visible:outline-orange">
                                Sign in
                            </button>
                        </div>
                    </form>

                    <p className="mt-10 text-center text-sm text-black dark:text-white">
                        Not register already ?{' '}
                        <a href="#" className=
                            {props.darkMode ?
                                "font-semibold leading-6 text-orange hover:text-amber-500"
                                :"font-semibold leading-6 text-blue hover:text-indigo-500 dark:text-orange dark:hover:text-amber-500"}>
                            Contact us
                        </a>
                    </p>
                </div>

            </div>

        </>
    );
};

export default ConnectForm;