import './About.css';


const About = () => {

    let versionNumber = "1.0";

    return (
        <div className="aboutContainer">

            <h5>About page</h5>
            <p>{versionNumber}</p>

            <div className="container">
                <div className="stacked-div">Div 1</div>
                <div className="stacked-div">Div 2</div>
                <div className="stacked-div">Div 3</div>
                <div class="stacked-div">Div 4</div>
            </div>


        </div>
            )
}

export default About;
