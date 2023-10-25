import React, {ReactElement, useEffect} from "react";
import imgDark from "../../../../assets/images/Cegep-Andre-Laurendeau.png";
import img from "../../../../assets/images/logo_AL_COULEURS_FOND_BLANC-scaled-removebg-preview.png";
import toggleOn from "../../../../assets/images/toggle-on-solid.svg";
import toggleOff from "../../../../assets/images/toggle-off-solid.svg";
import { useTranslation } from 'react-i18next';
import Switcher from "../../../../utils/switcher";
import useDarkSide from "../../../../hooks/useDarkSide";
import {useAuth} from "../../../../authentication/AuthContext";
import {authenticateUser} from "../../../../api/AuthenticationAPI";
import {useNavigate} from "react-router-dom";


const ConnectForm = (props:any): ReactElement => {
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0,2),"translation","LoginPage");
    const { loginUser, userRole } = useAuth();
    const navigate = useNavigate();
    const [connectUser, setConnectUser] = React.useState({
        email: "",
        password: ""
    });


    const connect = async (e:any) => {
        e.preventDefault();
        try {
            const response = await authenticateUser(connectUser.email, connectUser.password, loginUser, navigate);
            console.log(userRole)
            if (userRole === "EMPLOYEUR"){
                navigate(`/${userRole}/home/offers`)
            }else
                navigate(`/${userRole}/offers`)
        } catch (error) {
            console.log(error);
        }
    }

    return (
           <>
            <div className="flex min-h-full flex-1 flex-col justify-center px-6 py-12 lg:px-8">
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
                        {fields.Title.text}
                    </h2>
                </div>

                <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
                    <form className="space-y-6" action="#" method="POST">
                        <div>
                            <label htmlFor="email" className="block text-sm font-medium leading-6 text-black dark:text-white">
                                {fields.email.text}
                            </label>
                            <div className="mt-2">
                                <input
                                    id="email"
                                    name="email"
                                    type="email"
                                    autoComplete="email"
                                    placeholder={fields.email.placeholder}
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
                                    {fields.password.text}
                                </label>
                                <div className="text-sm">
                                    <a href="#" className="block text-sm font-medium leading-6 text-blue dark:text-orange">
                                        {fields.ForgotPassword.text}
                                    </a>
                                </div>
                            </div>
                            <div className="mt-2">
                                <input
                                    id="password"
                                    name="password"
                                    type="password"
                                    autoComplete="current-password"
                                    placeholder={fields.password.placeholder}
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
                                className="flex w-full justify-center rounded-md bg-blue dark:bg-orange px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 dark:hover:bg-orange-100 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-blue dark:focus-visible:outline-orange"
                                onClick={connect}
                            >
                                {fields.SignInButton.text}
                            </button>
                        </div>
                    </form>

                    <p className="mt-10 text-center text-sm text-black dark:text-white">
                        {fields.ContactUs.Question.text}{' '}
                        <a href="#" className=
                            {props.darkMode ?
                                "font-semibold leading-6 text-orange hover:text-amber-500"
                                :"font-semibold leading-6 text-blue hover:text-indigo-500 dark:text-orange dark:hover:text-amber-500"}>
                            {fields.ContactUs.text}
                        </a>
                    </p>
                </div>

            </div>

        </>
    );
};

export default ConnectForm;