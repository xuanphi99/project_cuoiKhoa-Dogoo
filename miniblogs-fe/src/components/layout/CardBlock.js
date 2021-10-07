import React, { Component } from 'react'
import '../../assets/css/CardBlock.css'
export default class CardBlock extends Component {
    render() {
        return (
            <div className="card about__info"  >
            <div className="card-body" style={{marginRight : '1.5rem'}}>
              <h4 className="card-title">Special title treatment</h4>
              <p className="card-text">With supporting Lorem ipsum dolor sit amet consectetur adipisicing elit. Atque qui illo quas, soluta consectetur ut quae aperiam excepturi fugiat ab voluptate tempore est architecto? Blanbnbnbeligendi natus numquam nisi officiis ab minima nostrum voluptas quae similique?  text below as a natural lead-in to additional content.</p>
            </div>
          </div>
          
        )
    }
}
