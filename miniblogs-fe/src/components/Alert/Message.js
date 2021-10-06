import React, { Component } from 'react'
import Swal from 'sweetalert2'

export default class Message extends Component {


  
    
    sendData = () => {
        this.props.parentCallback(false);
      }

     getAlert(title , text , icon  ) {
        Swal.fire({
            title: title,
            text: text,
            icon: icon,
            confirmButtonText: 'Close',
             
        })
        .then((result) => {
            console.log(result);
           
                 this.sendData() 
            
       //     console.log(Swal.DismissReason);
        })

    }
    render() {


        return (
            <React.Fragment>
                <div >
                 {this.getAlert(this.props.title ,
                     this.props.text,
                     this.props.icon)
                 }
                </div>
            </React.Fragment>
        )
    }
}
