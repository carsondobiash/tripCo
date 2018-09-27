import React, {Component} from 'react'
import { Card, CardHeader, CardBody } from 'reactstrap'
import LoadTrip from "./LoadTrip";

/* Options allows the user to change the parameters for planning
 * and rendering the trip map and itinerary.
 * The options reside in the parent object so they may be shared with the Trip object.
 * Allows the user to set the options used by the application via a set of buttons.
 */
class Trip extends Component{
    constructor(props) {
        super(props);
    }

    render() {
        return(
            <Card>
                <CardBody>
                    <p>Trip</p>
                    <LoadTrip/>
                </CardBody>
            </Card>
        )
    }
}

export default Trip;