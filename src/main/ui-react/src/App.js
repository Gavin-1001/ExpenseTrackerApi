import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Home from "./pages/Home";
import {AuthGuard} from "./AuthGuard/AuthGuard";
import Login from "./pages/Login/Login";
import Dashboard from "./pages/Dashboard/Dashboard";
import {Role} from "./common/modals/Role";
import Sidebar from "./components/Navigation/Sidebar/Sidebar";
import Settings from "./pages/Settings/Settings";
import ExpenseForm from './pages/ExpenseForm/ExpenseForm';
import Register from "./pages/Register/Register";
import Logout from "./pages/Logout/Logout";

function App() {
    return (
        <BrowserRouter>
            <div className="container">
                <Routes>

                    <Route path="/" element={<Home/>}/>
                    <Route path="/register" element={<Register/>}/>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/logout" element={<Logout />} />


                    <Route
                        path="/dashboard"
                        element={
                            <AuthGuard roles={[Role.USER]}>
                            <>
                                {/*<Sidebar/>*/}
                                <Dashboard/>
                            </>
                            </AuthGuard>
                        }
                    />

                    <Route
                        path="/settings"
                        element={
                             <AuthGuard roles={[Role.USER]}>
                                <Settings/>
                             </AuthGuard>
                        }
                    />

                    <Route path="/expenseForm"
                            element={
                                <AuthGuard>
                                    <ExpenseForm />
                                </AuthGuard>
                            }
                            />


                </Routes>
            </div>
        </BrowserRouter>
    );
}

export default App;
