import React, {Component} from 'react'
import { Card, CardHeader, CardBody } from 'reactstrap'
import { Form, FormGroup } from 'reactstrap'

/* Options allows the user to change the parameters for planning
 * and rendering the trip map and itinerary.
 * The options reside in the parent object so they may be shared with the Trip object.
 * Allows the user to set the options used by the application via a set of buttons.
 */
class LoadTrip extends Component{
    constructor(props) {
        super(props);

        this.upload = this.upload.bind(this);
    }

    upload(event){
        let f = event.target.files[0];
        if(f){
           let reader = new FileReader();
           reader.onload = function(event){
               console.log(event.target.result);
           };
           reader.readAsText(f);
        }
    }

    render() {
        return(
            <form>
                <input type="file" title="input" onChange={this.upload}/>
                <button type="submit">Load</button>
            </form>
        )
    }
}

export default LoadTrip;