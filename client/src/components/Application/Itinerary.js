import React, {Component} from 'react'
import { Card, CardHeader, CardBody } from 'reactstrap'
import { ButtonGroup, Button } from 'reactstrap'
import {request} from '../../api/api.js'

/* Options allows the user to change the parameters for planning
 * and rendering the trip map and itinerary.
 * The options reside in the parent object so they may be shared with the Trip object.
 * Allows the user to set the options used by the application via a set of buttons.
 */
class Itinerary extends Component{
    constructor(props) {
        super(props);
        this.plan = this.plan.bind(this);
    }


    plan(){
        let response = request(this.props.trip, 'plan').then(
            res => {this.props.updateTrip(res);}
        )
    }


    render() {
        return(
            <Card>
                <CardBody>
                    <p>Itinerary</p>
                    <Button onClick={this.plan} type="button">Plan</Button>
                </CardBody>
            </Card>
        )
    }
}

export default Itinerary;