import Sidebar from "../components/Navigation/Sidebar/Sidebar";

const Home = () => {


    return (
        <div className={"container"}>
            <Sidebar/>
            <div className="windowsContainer" style={{marginTop: "7rem"}}>
                <div className="row row-cols-2 row-cols-lg-3">
                    <p>Hello World</p>
                </div>
            </div>
        </div>
    )

}

export default Home;
