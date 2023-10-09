import {Navigate} from "react-router-dom";
import { useSelector } from "react-redux"


export const AuthGuard=({children, roles}) => {
    const currentUser = useSelector(state => state.user);

    const authorize = () => {
        if(!currentUser){
            return( <Navigate to={{pathname: '/login'}} /> );
        }

        if(roles?.indexOf(currentUser.role) === -1){
            return (<Navigate to={{pathname: '/401'}} />);
        }
        return children;
    };
    return (authorize());
}
