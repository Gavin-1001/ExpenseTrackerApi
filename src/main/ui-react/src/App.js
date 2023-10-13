import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Home from "./components/Home";
import {AuthGuard} from "./AuthGuard/AuthGuard";
import Login from "./pages/Login/Login";
import Dashboard from "./pages/Dashboard/Dashboard";
import {Role} from "./common/modals/Role";
import Sidebar from "./components/Navigation/Sidebar/Sidebar";
import Settings from "./pages/Settings/Settings";

function App() {
    return (
        <BrowserRouter>
            <div className="container">
                <Routes>

                    <Route path="/" element={<Home/>}/>

                    <Route path="/login" element={<Login/>}/>


                    <Route
                        path="/dashboard"
                        element={
                            // <AuthGuard roles={[Role.USER]}>
                            <>
                                <Sidebar/>
                                <Dashboard/>
                            </>
                            // </AuthGuard>
                        }
                    />

                    <Route
                        path="/settings"
                        element={
                             // <AuthGuard roles={[Role.USER]}>
                                <Settings/>
                             // </AuthGuard>
                        }
                    />


                </Routes>
            </div>
        </BrowserRouter>
    );
}

export default App;
