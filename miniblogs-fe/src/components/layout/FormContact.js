import React, { Component } from 'react'
import Swal from 'sweetalert2';
import '../../assets/css/FormContact.css'
import { MessageResponse, SweetAlertInfo } from '../../constant/ResponseMessage';
import PostService from '../../service/PostService';
import Message from '../Alert/Message';

export default class FormContact extends Component {

  constructor(props) {
    super(props);
    this.myRef = React.createRef();
    this.loader = React.createRef();
    this.state = ({
      contact: {
        userName: " ",
        email: " ",
        message: " ",
      },
      isShowModal: false,
      contentMsgAlert: {},
      iconLoader : false


    })
  }
  //  Receivers from message
  callbackFunction = (childData) => {
   
  
    this.setState({
      isShowModal: childData,
      
    });
  }



  handlerChange = (e) => {
    e.preventDefault()
    var target = e.target;
    var name = target.name;
    var value = target.value;
    this.setState({
      contact: {
        ...this.state.contact,
        [name]: value,
      },

    });
  }

  submitForm  = () => {
        this.setState({
      iconLoader : true
    });
  }

  onHandlerSubmitContact = (e) => {
    e.preventDefault();

    var self = this;

    console.log(JSON.stringify(this.state.contact));
    PostService.createContact(JSON.stringify(this.state.contact))

      .then((res) => {
        console.log(res);
        self.setState({
          isShowModal: !self.state.isShowModal,
          iconLoader: !self.state.iconLoader,
          contentMsgAlert: {
            title: "Server Response",
            text: MessageResponse.SUCCESS_ADD_CONTACT,
            icon: SweetAlertInfo.ICON[0],
            confirmButtonText: 'Close'

          }
        })
      })
      .catch(function (err) {

        if (err.response) {
          self.setState({
            isShowModal: !self.state.isShowModal,
            iconLoader: !self.state.iconLoader,
            contentMsgAlert: {
              title: err.response.data.reasonCode,
              text: err.response.data.description,
              icon: 'error',
              confirmButtonText: 'Close'

            }
          })
        }
        else if (err.request) {
          Swal.fire(MessageResponse.ERROR_CONNECT_CALL_API, '', 'error')
          self.setState({
            iconLoader: !self.state.iconLoader,
     
          })

        }
        else {
          Swal.fire(MessageResponse.ERROR_CONNECT_CALL_API, '', 'error')
          self.setState({
            iconLoader: !self.state.iconLoader,
     
          })
        }



      })

  }

  render() {
    return (

      <form onSubmit={(e) => { this.onHandlerSubmitContact(e) }} style={{ marginRight: '2.5rem', marginLeft: '1.25rem', paddingBottom: '2rem' }}>
        <div className="form-group">
          <label htmlFor="name">Name</label>
          <input type="text" name="userName" onChange={(e) => { this.handlerChange(e) }} className="form-control" id="name" placeholder="Enter your name" />
        </div>

        <div className="form-group">
          <label htmlFor="exampleInputEmail1">Email address</label>
          <input type="text" name="email" onChange={(e) => { this.handlerChange(e) }} className="form-control" id="exampleInputEmail1" placeholder="Enter email" />
        </div>

        <div className="form-group">
          <label htmlFor="exampleFormControlTextarea1">Message</label>
          <textarea name="message" onChange={(e) => { this.handlerChange(e) }} className="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
        </div>

        <button type="submit" ref={this.myRef} onClick={() => {this.submitForm()}} id="btnFetch" className="buttonbox spinner-button btn btn-secondary ">Submit
              {/* <i class="fa fa-circle-o-notch fa-spin"></i> */}
           
           {
             this.state.iconLoader === true ? (
          
              <span  className="loader" ref={this.loader}>
              <i className="fas fa-circle-notch fa-spin"></i>
            </span>  ) : ""
            }
        </button>
        {
          this.state.isShowModal === true ? (
            <Message parentCallback={(childData) => { this.callbackFunction(childData) }}
              title={this.state.contentMsgAlert.title}
              text={this.state.contentMsgAlert.text}
              icon={this.state.contentMsgAlert.icon} />
          ) : ""
        }

      </form>

    )
  }
}
