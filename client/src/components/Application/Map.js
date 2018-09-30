import React, {Component} from 'react'
import { Card, CardHeader, CardBody, CardImg } from 'reactstrap'
import { ButtonGroup, Button } from 'reactstrap'

/* Options allows the user to change the parameters for planning
 * and rendering the trip map and itinerary.
 * The options reside in the parent object so they may be shared with the Trip object.
 * Allows the user to set the options used by the application via a set of buttons.
 */
class Map extends Component{
    constructor(props) {
        super(props);
    }

    render() {
        console.log(this.props.trip.map);
        return(
                <CardBody>
                <CardImg src={"data:image/svg+xml;utf8," + this.props.trip.map}/>
                </CardBody>
        )
    }
}

export default Map;