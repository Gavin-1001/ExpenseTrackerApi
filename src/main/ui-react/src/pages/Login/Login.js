import './Login.css'
import {useEffect, useState} from "react";
import User from "../../common/models/User";
import {useDispatch, useSelector} from "react-redux";
import {useNavigate} from "react-router-dom";
import {setCurrentUser} from "../../redux/store/actions/users";
import AuthService from "../../services/AuthService.service/AuthService";

const Login = () => {

    const [user, setUser] = useState(new User( "", "", ""));
    const [loading, setLoading] = useState(false);
    const [submitted, setSubmitted] = useState(false);
    const [errorMessage, setErrorMessage] = useState(false);

    const currentUser = useSelector((state) => state.user);

    const navigate = useNavigate();
    const dispatch = useDispatch();


    useEffect(() => {
        if (currentUser?.id) {
            navigate("/profile");
        }
    }, []);


    const handleLogin = (e) => {
        e.preventDefault();
        //stops the login creds being displayed in url

        setSubmitted(true);
        if (!user.username || !user.password) {
            return; //checks if username and password fields are not empty
        }
        setLoading(true);

        //Authenitcation next
        AuthService.login(user)
            .then((response) => {
                //set user in session
                dispatch(setCurrentUser(response.data));
                navigate("/dashboard");
            })
            .catch((error) => {
                console.log(error);
                setErrorMessage("USERNAME OR PASSWORD IS NOT VALID!!!");
                setLoading(false);
            });
    };


    const handleChange = (e) => {
        const { name, value } = e.target;
        setUser((previousState) => {
            return {
                ...previousState,
                [name]: value,
            };
        });
    };



    return( 
        <div className={"container"} >
            <div className={"loginContainer"}>
            <form onSubmit={(e) => handleLogin(e)}>
                <div className="form-group">
                    <label htmlFor="exampleInputEmail1">Email address</label>
                    <input type="email" className="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" />
                </div>
                <div className="form-group">
                    <label htmlFor="exampleInputPassword1">Password</label>
                    <input type="password" className="form-control" id="exampleInputPassword1" placeholder="Password" />
                </div>
                <button type="submit" className="btn btn-primary">Submit</button>
                <button type="reset" className="btn btn-danger">Reset</button>
                
        </form>
        </div>


        </div>
     )


}

export default Login;
