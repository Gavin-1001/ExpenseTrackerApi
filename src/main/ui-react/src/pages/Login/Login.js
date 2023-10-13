import './Login.css'

const Login = () => {

    handleSubmit(e) {
        alert('A name was submitted: ' + this.state.value);
        e.preventDefault();
      }


  handleChange(e) {    this.setState({value: e.target.value});  }




    return( 
        <div className={"container"} >
            <div className={"loginContainer"}>
            <form onSubmit={this.handleSubmit}>
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
