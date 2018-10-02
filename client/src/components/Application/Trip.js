import React, {Component} from 'react'
import {Card, CardBody, Button, ButtonGroup, Input} from 'reactstrap'
import {request} from "../../api/api";

/* Options allows the user to change the parameters for planning
 * and rendering the trip map and itinerary.
 * The options reside in the parent object so they may be shared with the Trip object.
 * Allows the user to set the options used by the application via a set of buttons.
 */
class Trip extends Component{
    constructor(props) {
        super(props);
        this.upload = this.upload.bind(this);
        this.fileOnChange = this.fileOnChange.bind(this);
        this.plan = this.plan.bind(this);
        this.state = {file: null}
    }

    fileOnChange(event){
        let f = event.target.files[0];
        this.setState({'file': f})
        if(f){
            let reader = new FileReader();
            reader.onload =  function(event){
                console.log(event.target.result);
                let parsed = JSON.parse(event.target.result);
                this.props.updateBasedOnResponse(parsed);
            }.bind(this);
            reader.readAsText(f);
        }
    }

    plan(){
        let response = request(this.props.trip, 'plan').then(
            res => {this.props.updateBasedOnResponse(res);}
        );
    }


    render() {
        return(
            <CardBody>
                <p>Trip</p>
                <Input type="file" title="input" onChange={this.fileOnChange}/>
                <ButtonGroup>
                    <Button onClick={this.plan} type="button">Plan</Button>
                </ButtonGroup>
            </CardBody>
        )
    }
}

export default Trip;