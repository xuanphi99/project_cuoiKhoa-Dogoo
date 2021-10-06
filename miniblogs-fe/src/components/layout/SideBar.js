import React, { Component } from 'react'
import '../../assets/css/Sidebar.css'
export default class SideBar extends Component {

    

    render() {

        const linecap =  <hr style={{width:"50%", fontWeight : "bold" , marginLeft : "0rem"}} />
            
        return (
            <div className="card author " style={{width: '18rem'}}>
            <img className="card-img-top author--img" alt="avatar author" src="https://mui.com/static/images/cards/contemplative-reptile.jpg"  />
            <div className="card-body author--info">
              <h5 className="card-title">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Numquam asperiores unde obcaecati nam dolorem rerum a, aperiam, architecto et distinctio id laborum ipsa assumenda similique sunt quo iusto sequi minus!</h5>
              {linecap}
              <h5 className="card-title">Lorem minus!</h5>

              <p className="card-text">
                Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                {linecap}
               <div className="icon">
               <span > <i className="fab fa-facebook"></i></span>
               <span > <i className="fas fa-camera-retro"></i></span>
               <span > <i className="fab fa-twitter"></i> </span>
               <span > <i className="fab fa-linkedin-in"></i> </span>
                </div> 
                <p style={{textTransform:"uppercase" , marginTop:"1rem"}}  > built with web flow</p>

            </div>
         
          </div>
          
        )
    }
}
