import React, {Component} from 'react'
import { Card, CardHeader, CardBody } from 'reactstrap'
import { ButtonGroup, Button } from 'reactstrap'

/* Options allows the user to change the parameters for planning
 * and rendering the trip map and itinerary.
 * The options reside in the parent object so they may be shared with the Trip object.
 * Allows the user to set the options used by the application via a set of buttons.
 */
class Itinerary extends Component{
    constructor(props) {
        super(props);
    }

    render() {
        return(
            <Card>
                <CardBody>
                    <p>Itinerary</p>
                </CardBody>
            </Card>
        )
    }
}

export default Itinerary;